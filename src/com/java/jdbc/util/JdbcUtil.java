package com.java.jdbc.util;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import javax.sql.DataSource;
import java.io.FileInputStream;
import java.sql.*;
import java.util.Properties;

public class JdbcUtil {


    private static DataSource dataSource;

    public static Connection getConnection() throws SQLException {
        if (dataSource == null) {
            synchronized ("") {
                if (dataSource == null) {
                    Properties properties = new Properties();// Map集合, 里面保存所有的配置信息
                    try {
                        properties.load(new FileInputStream("druid.properties")); // 配置文件放在项目目录下
                        dataSource = DruidDataSourceFactory.createDataSource(properties);
                    } catch (Exception e) {
                        throw new SQLException(e);
                    }
                }
            }
        }
        Connection connection = dataSource.getConnection();
        return connection;
    }

    /**
     * 用于获取数据库连接对象
     * @return 连接对象
     * @throws ClassNotFoundException
     * @throws SQLException 连接失败了.
     */
    public static Connection oldgetConnection() throws ClassNotFoundException, SQLException {
        //String url = "主协议:子协议://主机地址:端口号/数据库名?user=用户名&password=密码";
        String driverClassName = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://127.0.0.1:3306/jdbc";
        String user = "root";
        String password = "123456";

        Class.forName(driverClassName); // 加载类模板, 在static块中完成了自我注册, 注册给了DriverManager管理器
        Connection connection = DriverManager.getConnection(url, user, password);
        return connection;
    }

    /**
     * 关闭连接对象, 释放资源
     * @param connection
     */
    public static void close(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 关闭连接对象和执行体对象资源
     * @param connection
     * @param statement
     */
    public static void close(Connection connection, Statement statement) {
        // 顺序
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 关闭所有资源
     * @param connection 连接对象
     * @param statement 执行体对象
     * @param resultSet 结果集对象
     */
    public static void close(Connection connection, Statement statement, ResultSet resultSet) {
        // 顺序
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
