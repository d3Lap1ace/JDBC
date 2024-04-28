package com.jdbc.test.test;

import com.jdbc.test.javabean.Teacher;
import com.jdbc.test.util.JdbcUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.*;
import org.junit.Test;

import java.sql.Connection;
import java.util.List;

/**
 * public <T> T query(Connection conn,
 *                    String sql,
 *                    ResultSetHandler<T> rsh,
 *                    Object... params)
 *         throws SQLException
 */
public class DBUtilsTest {

    @Test
    public void test6() {
        QueryRunner queryRunner = new QueryRunner();
        Connection connection = null;
        try {
            connection = JdbcUtil.getConnection();
            String sql = "select count(*) from teacher where id > ?";
            // 把结果集中的第一行第一列数据取出来, 处理最简单的数据
            ScalarHandler scalarHandler = new ScalarHandler();
            Object query = queryRunner.query(connection, sql, scalarHandler, 1);
            System.out.println(query);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            JdbcUtil.close(connection);
        }
    }

    @Test
    public void test5() {
        QueryRunner queryRunner = new QueryRunner();
        Connection connection = null;
        try {
            connection = JdbcUtil.getConnection();
            String sql = "select * from teacher where id > ?";
            // 把结果集中的所有行数据ORM为多个JavaBean对象 并都放入List集合中.
            BeanListHandler<Teacher> beanListHandler = new BeanListHandler<>(Teacher.class); // 使用类模板对象的作用是内部反射
            List<Teacher> list = queryRunner.query(connection, sql, beanListHandler, 1);
            for (Teacher teacher : list) {
                System.out.println(teacher);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            JdbcUtil.close(connection);
        }
    }

    @Test
    public void test4() {
        QueryRunner queryRunner = new QueryRunner();
        Connection connection = null;
        try {
            connection = JdbcUtil.getConnection();
            String sql = "select * from teacher where id > ?";
            // 把结果集中的第1行数据ORM为一个JavaBean对象.
            BeanHandler<Teacher> beanHandler = new BeanHandler<>(Teacher.class);
            Teacher teacher = queryRunner.query(connection, sql, beanHandler, 1);
            System.out.println(teacher);

        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            JdbcUtil.close(connection);
        }
    }

    @Test
    public void test3() {
        QueryRunner queryRunner = new QueryRunner();
        Connection connection = null;
        try {
            connection = JdbcUtil.getConnection();
            String sql = "select * from teacher where id > ?";
            ArrayListHandler arrayListHandler = new ArrayListHandler(); // 把结果集中的第一行数据全部放入一个数组. 所有行对应多个数组, 所有数组再放入一个List集合
            List<Object[]> list = queryRunner.query(connection, sql, arrayListHandler, 1);
            for (Object[] objects : list) {
                for (Object object : objects) {
                    System.out.print(object + "\t");
                }
                System.out.println();
            }
            System.out.println();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            JdbcUtil.close(connection);
        }
    }

    @Test
    public void test2() {
        QueryRunner queryRunner = new QueryRunner();
        Connection connection = null;
        try {
            connection = JdbcUtil.getConnection();
            String sql = "select * from teacher where id > ?";
            ArrayHandler arrayHandler = new ArrayHandler(); // 把结果集中的第一行数据全部放入一个数组. 数组中保存是第一行的所有列的数据
            Object[] arr = queryRunner.query(connection, sql, arrayHandler, 1);
            for (Object o : arr) {
                System.out.print(o + "\t");
            }
            System.out.println();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            JdbcUtil.close(connection);
        }
    }

    @Test
    public void test1() {
        //update(Connection conn, String sql, Object... params)
        //Execute an SQL INSERT, UPDATE, or DELETE query.
        QueryRunner queryRunner = new QueryRunner();
        Connection connection = null;
        try {
            connection = JdbcUtil.getConnection();
            String sql = "delete from teacher where id = ?";
            int rows = queryRunner.update(connection, sql, 1);
            System.out.println(rows + " rows");
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            JdbcUtil.close(connection);
        }
    }
}
