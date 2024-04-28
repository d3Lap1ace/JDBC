package com.jdbc.test.test;

import org.junit.Test;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 *
 * bin\mysql --host=127.0.0.1 --port=3306 --user=root --password=123456 world
 *
 *
 * 接口 : java.sql.Driver :
 *      Connection connect(String url, java.util.Properties info) throws SQLException;
 *
 * mysql实现子类 : com.mysql.cj.jdbc.Driver
 *
 * url : uniform resource locator 统一资源定位器
 * https://passport.jd.com:80/new/login.aspx?ReturnUrl=http
 *
 * mysql服务器的url
 * 主协议:子协议://服务器主机地址:端口号/数据库名
 * jdbc:mysql://127.0.0.1:3306/jdbc
 *
 * DriverManager来管理所有的Jdbc驱动程序.
 *      用DriverManager注册驱动程序对象
 */
public class JdbcTest {

    @Test
    public void test4() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver"); // 只要加载驱动类, 就会自动地自我注册给DriverManager
        String url = "jdbc:mysql://127.0.0.1:3306/jdbc";
        Connection connection = DriverManager.getConnection(url, "root", "123456");
        System.out.println(connection);
        connection.close(); // 它是资源, 应该close释放.!!!
    }

    @Test
    public void test3() throws Exception {
        // 反射子类对象的创建, 优点 : 不需要在编译时强烈依赖类及jar包.
        Class<?> clazz = Class.forName("com.mysql.cj.jdbc.Driver"); // 只要加载驱动类, 就会自动地自我注册给DriverManager
        Driver driver = (Driver)clazz.newInstance();
        DriverManager.registerDriver(driver); // 注册驱动程序!!
        String url = "jdbc:mysql://127.0.0.1:3306/jdbc";
        Connection connection = DriverManager.getConnection(url, "root", "123456");
        System.out.println(connection);
        connection.close(); // 它是资源, 应该close释放.!!!
    }

    @Test
    public void test2() throws SQLException {
        java.sql.Driver driver = new com.mysql.cj.jdbc.Driver();
        // 注册给驱动程序管理器
        DriverManager.registerDriver(driver);
        // 通过驱动程序管理器来获取连接
        //String url = "jdbc:mysql://127.0.0.1:3306/jdbc?user=root&password=123456";
        //Connection connection = DriverManager.getConnection(url);
        String url = "jdbc:mysql://127.0.0.1:3306/jdbc";
        Connection connection = DriverManager.getConnection(url, "root", "123456");
        System.out.println(connection);
        connection.close(); // 它是资源, 应该close释放.!!!
    }

    @Test
    public void test1() throws SQLException {
        // 多态 : 左面是接口类型, 右面是实际的子类
        java.sql.Driver driver = new com.mysql.cj.jdbc.Driver();
        //bin\mysql --host=127.0.0.1 --port=3306 --user=root --password=123456 world
        String url = "jdbc:mysql://127.0.0.1:3306/jdbc";
        // 用一个Map集合, 保存好用户名和密码的配置
        Properties properties = new Properties();
        properties.setProperty("user", "root");
        properties.setProperty("password", "123456");
        Connection connection = driver.connect(url, properties);
        System.out.println(connection);
    }
}
