package com.java.jdbc.test;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.alibaba.druid.pool.DruidPooledConnection;
import org.junit.Test;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @classname JDBC
 * @Auther d3Lap1ace
 * @Time 5/12/2024 10:47 PM Sun
 * @description
 * @Version 1.0
 * From the Laplace Demon
 */


public class ConnectionPoolTest {
    @Test
    public void test2() throws Exception {
        Properties properties = new Properties();// Map集合, 里面保存所有的配置信息
        properties.load(new FileInputStream("druid.properties")); // 配置文件放在项目目录下
        DataSource dataSource = DruidDataSourceFactory.createDataSource(properties);
        Connection connection = dataSource.getConnection();
        System.out.println(connection);
    }

    @Test
    public void test01() throws SQLException {
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setDriverClassName("com.mysql.cj.jdbc.driver");
        druidDataSource.setUrl("jdbc:mysql://127.0.0.1:3306/jdbc");
        druidDataSource.setUsername("root");
        druidDataSource.setPassword("123456");

        DruidPooledConnection connection = druidDataSource.getConnection();
        System.out.println(connection.getClass());
        connection.close();
    }
}
