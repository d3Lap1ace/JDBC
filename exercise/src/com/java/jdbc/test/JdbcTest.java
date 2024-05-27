package com.java.jdbc.test;

import com.mysql.cj.jdbc.Driver;
import org.junit.Test;

import java.sql.*;
import java.util.Properties;

/**
 * @classname JDBC
 * @Auther d3Lap1ace
 * @Time 5/12/2024 10:22 PM Sun
 * @description
 * @Version 1.0
 * From the Laplace Demon
 */


public class JdbcTest {

    @Test
    public void test04() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/day05", "root", "123456");
        PreparedStatement statement = connection.prepareStatement("select count(*) as count from t_emp");
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()){
            int count = resultSet.getInt("count");
            System.out.println("count = " + count);
        }
        resultSet.close();
        statement.close();
        connection.close();
    }

    @Test
    public void test03() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");  //只要加载驱动类 就自动给driverManager
        String url = "jdbc:mysql://127.0.0.1:3306/day05";
        Connection connection = DriverManager.getConnection(url, "root", "123456");
        System.out.println(connection);
        connection.close();

    }
    @Test
    public void test02() throws SQLException {
        Driver driver = new Driver();
        DriverManager.registerDriver(driver); //通过驱动程序管理器来连接
        String url = "jdbc:mysql://127.0.0.1:3306/day05";
        Connection connection = DriverManager.getConnection(url, "root", "123456");
        System.out.println(connection);
        connection.close();
    }
    @Test
    public void test01() throws SQLException {
        Driver driver = new Driver();
        String url = "jdbc:mysql://127.0.0.1:3306/day05";

        Properties properties = new Properties();
        properties.setProperty("user","root");
        properties.setProperty("password","123456");
        Connection connect = driver.connect(url, properties);
        System.out.println(connect);
    }
}
