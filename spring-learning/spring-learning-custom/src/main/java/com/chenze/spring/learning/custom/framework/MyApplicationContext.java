package com.chenze.spring.learning.custom.framework;

import java.beans.Introspector;
import java.io.File;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.chenze.spring.learning.custom.framework.Constants.SCOPE_PROTOTYPE;
import static com.chenze.spring.learning.custom.framework.Constants.SCOPE_SINGLETON;

/**
 * @author chenze
 * @date 2023/11/17 0:09
 */
public class MyApplicationContext {

    private Map<String, BeanDefinition> beanDefinitionMap = new HashMap<>();

    // 单例池
    private Map<String, Object> singletonObjects = new HashMap<>();
    private List<BeanPostProcessor> beanPostProcessorList = new ArrayList<>();




    public MyApplicationContext(Class<?> clazz) throws Exception {
        ComponentScan componentScan = clazz.getAnnotation(ComponentScan.class);
        if (componentScan == null) {
            throw new RuntimeException();
        }

        String[] componentScanValue = componentScan.value();

        this.scan(componentScanValue);

        this.initBean();

    }

    private void scan(String[] packageValues) throws Exception{
        for (String packageValue : packageValues) {
            scan(packageValue);
        }
    }


    /**
     * 扫描Bean文件
     */
    private void scan(String packageValue) throws Exception{
        String path = packageValue.replaceAll("\\.", "/");

        URL resource = this.getClass().getClassLoader().getResource(path);
        if (resource == null) {
            throw new RuntimeException();
        }


        File dirFile = new File(resource.getFile());

        if (!dirFile.isDirectory()) {
            throw new RuntimeException();
        }

        File[] files = dirFile.listFiles();
        if (files == null) {
            throw new RuntimeException();
        }

        for (File file : files) {
            String clazzName = packageValue + "." + file.getName().replaceAll(".class", "");

            Class<?> clazz = this.getClass().getClassLoader().loadClass(clazzName);

            Component component = clazz.getAnnotation(Component.class);
            if (component == null) {
                continue;
            }


            /**
             * 检查一个类是否可以被安全地转换为另一个类。
             * 具体来说，它用于判断一个类是否是另一个类的子类或接口的实现，或者是否可以被转换为另一个类
             */
            if (BeanPostProcessor.class.isAssignableFrom(clazz)) {
                BeanPostProcessor bpp = (BeanPostProcessor)clazz.getConstructor().newInstance();
                beanPostProcessorList.add(bpp);
            }

            Scope scope = clazz.getAnnotation(Scope.class);

            String scopeValue;
            if (scope == null) {
                scopeValue = SCOPE_SINGLETON;
            } else {
                scopeValue = scope.value();
            }

            String beanName = component.value();

            if (beanName.isEmpty()) {
                beanName = Introspector.decapitalize(clazz.getSimpleName());
            }

            BeanDefinition beanDefinition = new BeanDefinition();
            beanDefinition.setClazz(clazz);
            beanDefinition.setScope(scopeValue);
            beanDefinition.setBeanName(beanName);


            beanDefinitionMap.put(beanName, beanDefinition);
        }

    }

    private void initBean() throws Exception {
        for (BeanDefinition definition : beanDefinitionMap.values()) {
            String scope = definition.getScope();
            if (!SCOPE_SINGLETON.equals(scope)) {
                continue;
            }

            Object bean = this.createBean(definition);
            String beanName = definition.getBeanName();
            singletonObjects.put(beanName, bean);
        }
    }

    private Object createBean(BeanDefinition definition) throws Exception{
        Class<?> clazz1 = definition.getClazz();
        Object instance = clazz1.getConstructor().newInstance();

        for (BeanPostProcessor beanPostProcessor : beanPostProcessorList) {
            instance = beanPostProcessor.postProcessBeforeInitialization(instance, definition.getBeanName());
        }

        Field[] fields = instance.getClass().getDeclaredFields();
        for (Field field : fields) {
            /**
             * 判断字段是否存在某个注解、或者某个注解的子类注解
             */
            if (!field.isAnnotationPresent(Autowired.class)) {
                continue;
            }
            String name = field.getName();
            field.setAccessible(true);
            field.set(instance, getBean(name));
        }

        if (instance instanceof InitializingBean) {
            ((InitializingBean)instance).afterPropertiesSet();
        }

        for (BeanPostProcessor beanPostProcessor : beanPostProcessorList) {
            instance = beanPostProcessor.postProcessAfterInitialization(instance, definition.getBeanName());
        }



        return instance;
    }


    public Object getBean(String beanName) throws Exception{
        BeanDefinition beanDefinition = beanDefinitionMap.get(beanName);
        if (beanDefinition == null) {
            throw new RuntimeException();
        }

        /**
         * 获取单例Bean
         * 单例Bean在容器启动的时候就已经创建在缓存Map中了
         * （这里不考虑懒加载）
         */
        Object bean = singletonObjects.get(beanName);
        if (bean != null) {
            return bean;
        }

        /**
         * 单例Bean
         */
        String scope = beanDefinition.getScope();
        if (!SCOPE_PROTOTYPE.equals(scope)) {
            throw new RuntimeException();
        }

        return createBean(beanDefinition);
    }




}
