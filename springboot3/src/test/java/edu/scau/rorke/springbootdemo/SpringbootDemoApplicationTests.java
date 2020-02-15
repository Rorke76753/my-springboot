package edu.scau.rorke.springbootdemo;

import edu.scau.rorke.springbootdemo.dao.UserDao;
import edu.scau.rorke.springbootdemo.utils.PasswordEncodingUtil;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.SQLException;

@SpringBootTest
class SpringbootDemoApplicationTests {
    //记录器
    Logger logger = LoggerFactory.getLogger(getClass());

    @Test
    public void logTest() {
        //日志记录级别
        //调整日志级别，那么日志只会在同等级别或比它高的时候输出
        //默认级别=info
        logger.trace("hello log");//追踪 hello log
        logger.debug("debug log");
        logger.info("infor log");
        logger.warn("warn log");
        logger.error("error log");
    }
    @Autowired
    private PasswordEncodingUtil passwordEncodingUtil;
    @Test
    public void md5Test() throws NoSuchAlgorithmException {
        System.out.println(passwordEncodingUtil.passwordMD5Encoding("123456"));
    }
    @Autowired
    private UserDao dao;
    @Test
    public void daoTest(){
        System.out.println(dao.findByUserName("13533377155").getUserName());
    }

    @Autowired
    DataSource dataSource;

    @Test
    public void dataSourceTest() throws SQLException {
        System.out.println(dataSource.getClass());
        Connection connection = dataSource.getConnection();
        System.out.println(connection);
        connection.close();
    }
}
