package com.jdbc.test.dao;

import com.jdbc.test.util.JdbcUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.util.List;

/**
 * 数据访问对象
 */
public class JdbcDAO<T> {

    private QueryRunner queryRunner = new QueryRunner();
    private Class<T> clazz; // 反射时创建JavaBean对象时用到

    public JdbcDAO(Class<T> clazz) {
        this.clazz = clazz;
    }

    /**
     * 获取表中的所有数据并转换为对象集合
     * @param sql 查询
     * @param args
     * @return 集合
     */
    public List<T> getList(String sql, Object... args) {
        Connection connection = null;
        try {
            connection = JdbcUtil.getConnection();
            // 把结果集中的所有行数据ORM为多个JavaBean对象 并都放入List集合中.
            BeanListHandler<T> beanListHandler = new BeanListHandler<>(clazz); // 使用类模板对象的作用是内部反射
            List<T> list = queryRunner.query(connection, sql, beanListHandler, args);
            return list;
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            JdbcUtil.close(connection);
        }
    }

    /**
     * 从表中获取单个对象
     * @param sql 查询
     * @param args 替换查询中的所有?的可变参
     * @return
     */
    public T getBean(String sql, Object... args) {
        Connection connection = null;
        try {
            connection = JdbcUtil.getConnection();
            // 把结果集中的第1行数据ORM为一个JavaBean对象.
            BeanHandler<T> beanHandler = new BeanHandler<>(clazz);
            T obj = queryRunner.query(connection, sql, beanHandler, args);
            return obj;
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            JdbcUtil.close(connection);
        }
    }

    /**
     * 获取一个简单值
     * @param sql
     * @param args
     * @return
     */
    public Object getValue(String sql, Object... args) {
        Connection connection = null;
        try {
            connection = JdbcUtil.getConnection();
            // 把结果集中的第一行第一列数据取出来, 处理最简单的数据
            ScalarHandler scalarHandler = new ScalarHandler();
            Object query = queryRunner.query(connection, sql, scalarHandler, args);
            return query;
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            JdbcUtil.close(connection);
        }
    }

    /**
     * 执行DML和DDL
     * @param sql
     * @param args
     * @return 影响的行数
     */
    public int update(String sql, Object... args) {
        Connection connection = null;
        try {
            connection = JdbcUtil.getConnection();
            int rows = queryRunner.update(connection, sql, args);
            return rows;
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            JdbcUtil.close(connection);
        }
    }
}
