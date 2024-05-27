package com.jdbc.test.test;

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
 * 资源重用
 * 由于数据库连接得以重用，避免了频繁创建，释放连接引起的大量性能开销。在减少系统消耗的基础上，另一方面也增加了系统运行环境的平稳性。
 * 更快的系统反应速度
 * 数据库连接池在初始化过程中，往往已经创建了若干数据库连接置于连接池中备用。此时连接的初始化工作均已完成。对于业务请求处理而言，直接利用现有可用连接，避免了数据库连接初始化和释放过程的时间开销，从而减少了系统的响应时间
 * 新的资源分配手段
 * 对于多应用共享同一数据库的系统而言，可在应用层通过数据库连接池的配置，实现某一应用最大可用数据库连接数的限制，避免某一应用独占所有的数据库资源
 * 统一的连接管理，避免数据库连接泄露
 * 在较为完善的数据库连接池实现中，可根据预先的占用超时设定，强制回收被占用连接，从而避免了常规数据库连接操作中可能出现的资源泄露
 */
public class ConnectionPoolTest {

    @Test
    public void test2() throws Exception {
        Properties properties = new Properties();// Map集合, 里面保存所有的配置信息
        properties.load(new FileInputStream("./druid.properties")); // 配置文件放在项目目录下
        DataSource dataSource = DruidDataSourceFactory.createDataSource(properties);
        Connection connection = dataSource.getConnection();
        System.out.println(connection);
    }

    @Test
    public void test1() throws SQLException {
        DruidDataSource druidDataSource = new DruidDataSource();

        druidDataSource.setDriverClassName("com.mysql.cj.jdbc.Driver"); // 驱动类
        druidDataSource.setUrl("jdbc:mysql://127.0.0.1:3306/day05");
        druidDataSource.setUsername("root");
        druidDataSource.setPassword("123456");

        // 设置必要的参数才能连接
        DruidPooledConnection connection = druidDataSource.getConnection();
        System.out.println(connection.getClass());

        connection.close(); // 连接的close, 并不是真的close, 而是把连接对象归还给连接池
    }
}
