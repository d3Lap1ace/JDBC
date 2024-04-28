package com.java.jdbc.test;
import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.java.jdbc.javabean.Student;
import com.java.jdbc.util.JdbcUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.junit.Test;
import javax.sql.DataSource;
import java.io.FileInputStream;
import java.sql.Connection;
import java.util.List;
import java.util.Properties;

/**
*    @classname ConnectionPoolTest
*    @description 
*    @Auther d3Lap1ace
*    @Time 2024/04/20  15:下午3:47
*    @Version 1.0
*                        From the Laplace Demon 
*/
public class ConnectionPoolTest {
    @Test
    public void test3(){

    }
    @Test
    public void test2(){
        QueryRunner queryRuner = new QueryRunner();
        Connection connection = null;
        try {
            connection = JdbcUtil.getConnection();
            String sql = "select *from teacher where id > ?";
            BeanListHandler<Student> beanListHandler = new BeanListHandler<>(Student.class);
            List<Student> list = queryRuner.query(connection, sql, beanListHandler, 1);
            for (Student student : list) {
                System.out.println(student);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.close(connection);
        }
    }

    @Test
    public void test1() throws Exception {
        Properties properties = new Properties();
        properties.load(new FileInputStream("druid.properties"));
        DataSource dataSource = DruidDataSourceFactory.createDataSource(properties);
        Connection connection = dataSource.getConnection();
        System.out.println(connection);

    }
}
