package com.jdbc.test.test;

import com.jdbc.test.dao.DAO;
import com.jdbc.test.util.JdbcUtil;
import org.junit.Test;

import java.sql.*;

/**
 * java.sql.DriverManager : 驱动程序管理器
 * java.sql.Driver : 驱动程序
 * java.sql.Connection : 数据库连接, 是一个资源
 * java.sql.Statement : 执行体, 执行任意SQL语句, 是一个资源
 *      java.sql.PreparedStatement : 预编译执行体, 执行效率更高, 更安全, (防止sql注入)
 * java.sql.ResultSet : 结果集虚表, 包含行和列的数据, 使用像一个迭代器, 是一个资源
 *
 * prepare p1 from 'sql';
 * set @var1=val, @var2=...;
 * execute p1 using @var1, @var2
 */

public class JdbcTest2 {

    @Test
    public void test11() {
        DAO dao = new DAO();
        Connection connection = null;
        try {
            connection = JdbcUtil.getConnection();
            String sql = "insert into student(name, age, gender, master) values (?, ?, ?, ?)";
            int rows = dao.update(connection, sql, "小芳", 19, "女", 2);
            System.out.println(rows + " rows");
            rows = dao.update(connection, sql, "小伟", 12, "男", 3);
            System.out.println(rows + " rows");
            rows = dao.update(connection, sql, "小花", 11, "女", 1);
            System.out.println(rows + " rows");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.close(connection);
        }
    }

    @Test
    public void test10() {
        DAO dao = new DAO();
        String sql = "insert into student (name, age, gender, master) values (?, ?, ?, ?)";
        try {
            int rows = dao.update(sql, "小明", 18, "男", 1); // 如果sql中有?, 用实参列表的方式把所有?解决.
            System.out.println(rows + " rows");
            rows = dao.update(sql, "小丽", 20, "女", 1);
            System.out.println(rows + " rows");
            rows = dao.update(sql, "小刚", 17, "男", 2);
            System.out.println(rows + " rows");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test9() {
        DAO dao = new DAO();
        String sql = "create table if not exists student(" +
                            "id int auto_increment, " +
                            "name varchar(20) unique, " +
                            "age int ," +
                            "gender enum('男', '女') default '男'," +
                            "master int," +
                            "primary key(id)" +
                    ")";
        try {
            int rows = dao.update(sql); // 如果sql中没有?, 在调用时不用管后面的东西.
            System.out.println(rows + " rows");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @Test
    public void test8() {
        DAO dao = new DAO();
        //Object[] arr = {"佟大刚", 42, "女", "99999", 1};
        try {
            // 用数组的方式传参更麻烦
            //int rows = dao.update("update teacher set name = ?, age = ?, gender = ?, mobile = ? where id = ?", arr);
            // 3 | 大海   |   35 | 男     | 134533422 |
            int rows = dao.update("update teacher set name = ?, age = ?, gender = ?, mobile = ? where id = ?", "小海", 25, "女", "88888", 3);
            System.out.println(rows + " rows affected");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test7() {
        String sql = "insert into teacher (name, age, gender, mobile) values (?, ?, ?, ?)";
        Object[] arr = {"李老师", 22, "男", "1343459"}; // 实参值的数组必须和sql中的?要顺序一致, 类型一致, 个数一致.

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = JdbcUtil.getConnection();
            // ?只能在sql为值的部分占位.
            preparedStatement = connection.prepareStatement(sql); // 让服务器提前把给定sql进行预编译
            // 解决所有?, 统一使用setObject
            for (int i = 0; i < arr.length; i++) {
                preparedStatement.setObject(i + 1, arr[i]);
            }
            // 如果sql中有?的存在, 在实际执行前必须全部解决掉
            int rows = preparedStatement.executeUpdate();// sql已经在它的内部绑定了
            System.out.println(rows + " rows affected");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.close(connection, preparedStatement);
        }
    }

    @Test
    public void test6() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = JdbcUtil.getConnection();
            // ?只能在sql为值的部分占位.
            String sql = "insert into teacher (name, age, gender, mobile) values (?, ?, ?, ?)";
            preparedStatement = connection.prepareStatement(sql); // 让服务器提前把给定sql进行预编译
            // 解决所有?, 统一使用setObject
            Object[] arr = {"张老师", 28, "女", "1223459"};
            /*
            preparedStatement.setObject(1, "大海");
            preparedStatement.setObject(2, 35);
            preparedStatement.setObject(3, "男");
            preparedStatement.setObject(4, "134533422");
            */
            for (int i = 0; i < arr.length; i++) {
                preparedStatement.setObject(i + 1, arr[i]);
            }
            // 如果sql中有?的存在, 在实际执行前必须全部解决掉
            int rows = preparedStatement.executeUpdate();// sql已经在它的内部绑定了
            System.out.println(rows + " rows affected");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.close(connection, preparedStatement);
        }
    }

    @Test
    public void test5() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = JdbcUtil.getConnection();
            // ?只能在sql为值的部分占位.
            String sql = "insert into teacher (name, age, gender, mobile) values (?, ?, ?, ?)";
            preparedStatement = connection.prepareStatement(sql); // 让服务器提前把给定sql进行预编译
            // 解决所有?
            preparedStatement.setString(1, "程程"); // 为sql中的第1?传入实际的String值"佟刚"
            preparedStatement.setInt(2, 20); // 为sql中的第2个?传入实际int值40
            preparedStatement.setString(3, "女");
            preparedStatement.setString(4, "13233422");

            // 如果sql中有?的存在, 在实际执行前必须全部解决掉
            int rows = preparedStatement.executeUpdate();// sql已经在它的内部绑定了
            System.out.println(rows + " rows affected");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.close(connection, preparedStatement);
        }
    }

    // 创建一个Teacher表, id, name, age, gender, mobile, id自增
    @Test
    public void test4() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = JdbcUtil.getConnection();
            String sql = "create table if not exists teacher (" +
                                "id int auto_increment," +
                                "name varchar(20) not null," +
                                "age int," +
                                "gender enum('男','女') default '男', " +
                                "mobile varchar(15) unique, " +
                                "primary key(id)" +
                        ")";
            preparedStatement = connection.prepareStatement(sql); // 让服务器提前把给定sql进行预编译
            int rows = preparedStatement.executeUpdate();// sql已经在它的内部绑定了
            System.out.println(rows + " rows affected");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.close(connection, preparedStatement);
        }
    }

    @Test
    public void test3() {
        Connection connection = null; // Connection对象仅代表一个数据库连接(会话)
        Statement statement = null;
        try {
            connection = JdbcUtil.getConnection();
            System.out.println(connection);
            statement = connection.createStatement();
            // executeUpdate可以执行任意的DML和DDL
            String sql = "insert into user (id, username, password) values(3, 'root', 'root')";
            int rows = statement.executeUpdate(sql);
            System.out.println(rows + " rows affected");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.close(connection, statement);
        }
    }

    @Test
    public void test2() {
        Connection connection = null; // Connection对象仅代表一个数据库连接(会话)
        Statement statement = null;
        try {
            connection = JdbcUtil.getConnection();
            System.out.println(connection);
            statement = connection.createStatement();
            // executeUpdate可以执行任意的DML和DDL
            String sql = "create table if not exists user (" +
                                "id int, " +
                                "username varchar(20), " +
                                "password varchar(50), " +
                                "primary key(id)" +
                          ")";
            int rows = statement.executeUpdate(sql);
            System.out.println(rows + " rows affected");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.close(connection, statement);
        }
    }

    @Test
    public void test1() throws ClassNotFoundException, SQLException {
        //String url = "主协议:子协议://主机地址:端口号/数据库名?user=用户名&password=密码";
        String driverClassName = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://127.0.0.1:3306/jdbc";
        String user = "root";
        String password = "123456";

        Class.forName(driverClassName); // 加载类模板, 在static块中完成了自我注册, 注册给了DriverManager管理器
        Connection connection = DriverManager.getConnection(url, user, password);
        System.out.println(connection);
        connection.close(); // 释放资源
    }
}
