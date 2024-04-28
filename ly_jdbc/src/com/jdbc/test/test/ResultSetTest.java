package com.jdbc.test.test;

import com.jdbc.test.dao.DAO;
import com.jdbc.test.javabean.Student;
import com.jdbc.test.util.JdbcUtil;
import org.junit.Test;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * mysql> select id, name, age, gender, master from student where id > 1;
 * +----+------+------+--------+--------+
 * | id | name | age  | gender | master |  <----------------- cursor, next()
 * +----+------+------+--------+--------+
 * |  2 | 小丽 |   20 | 女     |      1 |  <-------------------- next() 1) 判断当前游标后面是否有行, 如果有返回true, 2) 并且真的要下移游标
 * |  3 | 小刚 |   17 | 男     |      2 |  <----------------------
 * |  4 | 小芳 |   19 | 女     |      2 | <-------------
 * |  5 | 小伟 |   12 | 男     |      3 | <-------------------
 * |  6 | 小花 |   11 | 女     |      1 | <-----------------
 * +----+------+------+--------+--------+
 * 5 rows in set (0.00 sec)
 */
public class ResultSetTest {

    @Test
    public void test6() {
        DAO dao = new DAO();
        try {
            dao.view("select * from world.city where id > ?", 3000);
            dao.view("select count(*), max(population) from world.country");
            dao.view("show tables");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test5() {
        String sql = "select id, name teacherName, age tage, gender, mobile from teacher where id > ?";
        Object[] args = {0}; // id > 0
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = JdbcUtil.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            // 执行sql前先解决所有?
            for (int i = 0; i < args.length; i++) {
                preparedStatement.setObject(i + 1, args[i]);
            }
            // 真的执行查询
            resultSet = preparedStatement.executeQuery();
            // 通过结果集获取它的虚表的表结构对象
            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount(); // 获取列数
            System.out.println("metaData.getColumnCount() = " + columnCount);
            /*
            String columnName1 = metaData.getColumnName(1);// 获取第1列的列名(基表的真实列名)
            String columnName2 = metaData.getColumnName(2);// 获取第2列的列名
            String columnName3 = metaData.getColumnName(3);// 获取第3列的列名
            String columnName4 = metaData.getColumnName(4);// 获取第4列的列名
            String columnName5 = metaData.getColumnName(5);// 获取第5列的列名

            System.out.println(columnName1 + "\t" + columnName2 + "\t" + columnName3 + "\t" + columnName4 + "\t" + columnName5);
            */
            /*
            String columnLabel1 = metaData.getColumnLabel(1); // 虚表的第1列的列名(别名)
            String columnLabel2 = metaData.getColumnLabel(2); // 虚表的第2列的列名(别名)
            String columnLabel3 = metaData.getColumnLabel(3); // 虚表的第3列的列名(别名)
            String columnLabel4 = metaData.getColumnLabel(4); // 虚表的第4列的列名(别名)
            String columnLabel5 = metaData.getColumnLabel(5); // 虚表的第5列的列名(别名)
            System.out.println(columnLabel1 + "\t" + columnLabel2 + "\t" + columnLabel3 + "\t" + columnLabel4 + "\t" + columnLabel5);
            */
            for (int i = 0; i < columnCount; i++) {
                String columnLabel = metaData.getColumnLabel(i + 1); // 根据列索引获取到相应的列标签
                System.out.print(columnLabel + "\t");
            }
            System.out.println();
            System.out.println("------------------------------------------------------------------------------------");
            while (resultSet.next()) {
                // 一行有多个列, columnCount个列,
                for (int i = 0; i < columnCount; i++) {
                    String columnLabel = metaData.getColumnLabel(i + 1); // 获取到列标签
                    Object value = resultSet.getObject(columnLabel); // 获取当前行的指定列标签的值.可以使用统一获取数据的方法 getObject
                    System.out.print(value + "\t");
                }
                System.out.println();
                /*
                Object id = resultSet.getObject("id");
                Object name = resultSet.getObject("teacherName");
                Object age = resultSet.getObject("tage");
                Object gender = resultSet.getObject("gender");
                Object mobile = resultSet.getObject("mobile");
                System.out.println(id + "\t" + name + "\t" + age + "\t" + gender + "\t" + mobile);
                */
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.close(connection, preparedStatement, resultSet);
        }
    }

    /**
     +----+--------+------+--------+----------+
     | id | name   | age  | gender | mobile   |
     +----+--------+------+--------+----------+
     |  1 | 佟大刚 |   42 | 女     | 99999    |
     |  2 | 程程   |   20 | 女     | 13233422 |
     |  3 | 小海   |   25 | 女     | 88888    |
     |  4 | 张老师 |   28 | 女     | 1223459  |
     |  5 | 李老师 |   22 | 男     | 1343459  |
     +----+--------+------+--------+----------+
     */
    @Test
    public void test4() {
        String sql = "select id, name, age, gender, mobile from teacher where id > ?";
        Object[] args = {0}; // id > 0
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = JdbcUtil.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            // 执行sql前先解决所有?
            for (int i = 0; i < args.length; i++) {
                preparedStatement.setObject(i + 1, args[i]);
            }
            // 真的执行查询
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                // 可以使用统一获取数据的方法 getObject
                Object id = resultSet.getObject("id");
                Object name = resultSet.getObject("name");
                Object age = resultSet.getObject("age");
                Object gender = resultSet.getObject("gender");
                Object mobile = resultSet.getObject("mobile");
                System.out.println(id + "\t" + name + "\t" + age + "\t" + gender + "\t" + mobile);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.close(connection, preparedStatement, resultSet);
        }
    }

    @Test
    public void test3() {
        List<Student> list = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = JdbcUtil.getConnection();
            String sql = "select gender, master, id, name, age from student where id > ?";
            preparedStatement = connection.prepareStatement(sql); // 预编译sql
            Object[] args = {1};
            for (int i = 0; i < args.length; i++) {
                preparedStatement.setObject(i + 1, args[i]); // where id > 1
            }
            // 真正执行前必须解决所有?
            resultSet = preparedStatement.executeQuery();
            System.out.println(resultSet);
            while (resultSet.next()) {
                // 列标签 : 虚表的列名
                int id = resultSet.getInt("id");  // 根据列标签获取相应列的值, 使用列标签比列索引更安全, 更好用
                String name = resultSet.getString("name");
                int age = resultSet.getInt("age");
                String gender = resultSet.getString("gender");
                int master = resultSet.getInt("master");
                Student student = new Student(id, name, age, gender, master); // ORM Object Relation Mapping
                //System.out.println(student);
                //System.out.println(id + "\t" + name + "\t" + age + "\t" + gender + "\t" + master);
                list.add(student);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.close(connection, preparedStatement, resultSet);
        }

        for (Student student : list) {
            System.out.println(student);
        }
    }

    @Test
    public void test2() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = JdbcUtil.getConnection();
            String sql = "select gender stuGender, master teacher_id, id, name, age from student where id > ?";
            preparedStatement = connection.prepareStatement(sql); // 预编译sql
            Object[] args = {1};
            for (int i = 0; i < args.length; i++) {
                preparedStatement.setObject(i + 1, args[i]); // where id > 1
            }
            // 真正执行前必须解决所有?
            resultSet = preparedStatement.executeQuery();
            System.out.println(resultSet);
            while (resultSet.next()) {
                // 列标签 : 虚表的列名
                int id = resultSet.getInt("id");  // 根据列标签获取相应列的值, 使用列标签比列索引更安全, 更好用
                String name = resultSet.getString("name");
                int age = resultSet.getInt("age");
                String gender = resultSet.getString("stuGender");
                int master = resultSet.getInt("teacher_id");
                System.out.println(id + "\t" + name + "\t" + age + "\t" + gender + "\t" + master);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.close(connection, preparedStatement, resultSet);
        }
    }

    @Test
    public void test1() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = JdbcUtil.getConnection();
            String sql = "select id, name, age, gender, master from student where id > ?";
            preparedStatement = connection.prepareStatement(sql); // 预编译sql
            Object[] args = {1};
            for (int i = 0; i < args.length; i++) {
                preparedStatement.setObject(i + 1, args[i]); // where id > 1
            }
            // 真正执行前必须解决所有?
            resultSet = preparedStatement.executeQuery();
            System.out.println(resultSet);
            while (resultSet.next()) { // next不仅用于检测当前游标后面是否有行 返回boolean, 而且会真的移动游标
                // 如果next()为true, 说明当前游标指向的行是一个有效行, 但是一行又有多列, 需要分别取多个列的值
                int id = resultSet.getInt(1); // 获取第1列的数据,是一个int
                String name = resultSet.getString(2); // 获取第2列的数据, 是一个String
                int age = resultSet.getInt(3); // // 获取第3列的数据,是一个int
                String gender = resultSet.getString(4); // 获取第4列的数据, 是一个字符串
                int master = resultSet.getInt(5); // 获取第5列数据, 是一个int
                System.out.println(id + "\t" + name + "\t" + age + "\t" + gender + "\t" + master);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.close(connection, preparedStatement, resultSet);
        }
    }
}
