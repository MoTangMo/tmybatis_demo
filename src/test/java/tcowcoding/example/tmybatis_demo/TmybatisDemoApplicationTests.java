package tcowcoding.example.tmybatis_demo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import tcowcoding.example.tmybatis_demo.binding.MapperProxyFactory;
import tcowcoding.example.tmybatis_demo.binding.MapperRegistry;
import tcowcoding.example.tmybatis_demo.dao.schoolDao;
import tcowcoding.example.tmybatis_demo.dao.userDao;
import tcowcoding.example.tmybatis_demo.session.DefaultSqlSessionFactory;
import tcowcoding.example.tmybatis_demo.session.SqlSession;
import tcowcoding.example.tmybatis_demo.session.SqlSessionFactory;

import java.util.HashMap;

@SpringBootTest
class TmybatisDemoApplicationTests {


    @Test
    void contextLoads() {

        MapperRegistry registry = new MapperRegistry();
        registry.addMappers("tcowcoding.example.tmybatis_demo.dao");

        SqlSessionFactory sqlSessionFactory = new DefaultSqlSessionFactory(registry);
        SqlSession sqlSession = sqlSessionFactory.openSession();

        userDao userDao = sqlSession.getMapper(userDao.class);
        schoolDao schoolDao = sqlSession.getMapper(schoolDao.class);
//        Integer integer = userDao.queryUserAge("1001");
        String username = userDao.queryUserName("10001");
        String schoolName = schoolDao.querySchoolName("10001");
        System.out.println("测试结果为：" + username);
        System.out.println("测试结果2为：" + schoolName);


//        MapperProxyFactory<userDao> daofactory = new MapperProxyFactory<userDao>(userDao.class);
//
//        HashMap<String,String> sqlsession = new HashMap<>();
//        sqlsession.put("tcowcoding.example.tmybatis_demo.dao.userDao.queryUserName","模拟执行Mapper.xml queryUserName");
//        sqlsession.put("tcowcoding.example.tmybatis_demo.dao.userDao.queryUserAge","模拟执行Mapper.xml queryUserAge");
//
//        userDao userDao = daofactory.newInstance(sqlsession);
//        String name = userDao.queryUserName("Chen");
//        System.out.println("测试结果"+name);


    }

}
