import cn.hutool.json.JSONUtil;
import com.chenze.learning.mybatis.mapper.EmployeeMapper;
import com.chenze.learning.mybatis.model.Employee;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.log4j.Log4j;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Log4j
public class Test1 {


    private InputStream in;
    private SqlSession sqlSession;
    private EmployeeMapper mapper;

    /**
     * 初始化
     * @throws IOException
     */
    @Before
    public void init() throws IOException{
        //1.读取配置文件
        in = Resources.getResourceAsStream("mybatis-config.xml");
        //2.创建SqlSessionFactory构建者对象
        SqlSessionFactoryBuilder builder= new SqlSessionFactoryBuilder();
        //3.创建SqlSessionFactory：解析xml配置文件
        SqlSessionFactory factory = builder.build(in);
        //4.使用 SqlSessionFactory 生产 SqlSession 对象：DefaultSqlSession
        sqlSession = factory.openSession();
        //5.使用 SqlSession 创建 dao 接口的代理对象
        mapper = sqlSession.getMapper(EmployeeMapper.class);
    }

    /**
     * 释放资源
     */
    @After
    public void destroy() throws Exception{
        sqlSession.close();
        in.close();
    }



    /**
     * 一级缓存：判断两次查询对象是否是一个
     * employee1List = employee2List ==> true：表示同一内存地址
     */
    @Test
    public void test_cache_first() throws IOException {
        List<Employee> employee1List = mapper.findAll();
        for (Employee employee : employee1List) {
            System.out.println(employee);
        }

        System.out.println("---------------");

        List<Employee> employee2List = mapper.findAll();
        for (Employee employee : employee2List) {
            System.out.println(employee);
        }
        System.out.println(employee1List == employee2List);
    }

    /**
     * 分页 - limit的使用
     */
    @Test
    public void test_page_limit(){
        long count = mapper.countAllForLimit();
        List<Employee> employeeList = mapper.selectAllForLimit(0, 4);
        log.info(String.format("count=%s", count));
        log.info(String.format("list=\n%s", JSONUtil.toJsonPrettyStr(employeeList)));
    }

    @Test
    public void test_page_rowbounds(){
        RowBounds rowBounds = new RowBounds(3, 4);
        List<Employee> employeeList = mapper.selectAllForRowBounds(rowBounds);
        log.info(String.format("list=\n%s", JSONUtil.toJsonPrettyStr(employeeList)));
    }


    /**
     * 分页 - PageHelper的使用
     */
    @Test
    public void test_page_pagehelper() {
        PageHelper.startPage(1, 4);
        List<Employee> employeeList = mapper.findAll();
        PageInfo<Employee> employeePageInfo = new PageInfo<>(employeeList);
        log.info("\n" + JSONUtil.toJsonPrettyStr(employeePageInfo));
    }

}
