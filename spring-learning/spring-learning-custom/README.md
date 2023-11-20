## 手写模拟Spring底层原理









#### 创建容器类：MyApplicationContext

1. 初始化Bean
   1. 获取**@ComponentScan**配置的包路径
   2. 在指定包路径下找到所有使用**@Component**修饰的类
   3. 扫描第二步中的类注解：**@Lazy**、**@Scope** 并解析，保存在**Map<String,BeanDefinition>**对象中缓存
   4. **创建Bean**
   5. 创建Bean的初始化接口：**InitializingBean**，使之在Bean创建完成之后执行重写方法
2. 将Bean对象保存起来

#### 创建Bean的配置类

1.  扫描Bean的目录
2. 使用注解的方式，定义配置扫描目录



#### 创建BeanPostProcessor接口

1. before方法：Bean创建之前执行
2. after方法：Bean创建之后执行

* AOP逻辑就是使用这种方式实现的

* @Value逻辑的赋值也是使用这种方式实现的



#### 创建Bean定义的类：BeanDefinition

有了这个类，我们可以在服务启动时，就将符合Bean标准的类的信息缓存起来

1. Bean的类型是什么？
2. Bean的创建模式是什么？
3. Bean是否是懒加载的？？
4. 等等等等



#### 方法：创建Bean

1. 通过**BeanDefinition**的定义创建Bean
2. 如果存在**@Autowired**则进行依赖注入
3. 暂不考虑循环依赖
4. 暂不考虑懒加载问题



#### 注解定义

1. **@Component**：配置哪些类是Bean。修饰Bean类
2. **@ComponentScan**：配置需要扫描哪些包路径下的类
3. **@Scope**：配置Bean的创建模式：单例、多例。修饰Bean类
4. **@Autowired**：依赖注入



#### 扩展实现

1. 使用CGLIB利用代理实现AOP逻辑
2. 使用**BeanPostProcessor**实现@**Value**赋值
3. 实现**BeanNameAware**在Bean实际对象中获取当前代理对象的Bean的名称





### 功能点

1. 元数据的定义
   1. Bean定义的创建与保存
2. Bean的创建
   1. 通过配置实现包路径的扫描、Bean组件注解
   2. 实现Bean的初始化
   3. 可通过配置实现是单例，还是多例
3. 实现Aware
4. 实现AOP
5. 类初始化完成的执行