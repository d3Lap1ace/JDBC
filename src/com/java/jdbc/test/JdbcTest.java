package com.java.jdbc.test;

import com.java.jdbc.DAO.Dao;
import com.java.jdbc.util.JdbcUtil;
import org.junit.Test;

import javax.swing.plaf.InsetsUIResource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

/**
*    @classname JdbcTest
*    @description 
*    @Auther d3Lap1ace
*    @Time 2024/04/20  09:上午9:03
*    @Version 1.0
*                        From the Laplace Demon
 * +----------+---------------------+------+-----+---------+-------+
 * | Field    | Type                | Null | Key | Default | Extra |
 * +----------+---------------------+------+-----+---------+-------+
 * | id       | int                 | NO   | PRI | NULL    |       |
 * | username | varchar(20)         | YES  |     | NULL    |       |
 * | age      | int                 | YES  |     | NULL    |       |
 * | gender   | enum('man','woman') | YES  |     | man     |       |
 * | mobile   | varchar(20)         | YES  |     | NULL    |       |
 * +----------+---------------------+------+-----+---------+-------+
 * 5 rows in set (0.00 sec)
*/
public class JdbcTest {
    @Test
    public void test07(){
        String sql = "insert into student(name,age,gender,master)values(?,?,?,?)";
        Dao dao = new Dao();
        try {
            int rows = dao.update(sql,"jonhjone",19,"man",1);
            System.out.println("rows = " + rows);
            rows = dao.update(sql,"salekua",20,"woman",1);
            System.out.println("rows = " + rows);
            rows = dao.update(sql,"lukey",23,"man",2);
            System.out.println("rows = " + rows);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test
    public void test06(){
        Dao dao = new Dao();
        String sql = "create table if not exists student("+
                "id int auto_increment,"+
                "name varchar(20) unique,"+
                "age int ,"+
                "gender enum('man','woman') default 'man',"+
                "master int ,"+
                "primary key(id)"+
                ")";
        int rows = 0;
        try {
            rows = dao.update(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("rows = " + rows);
    }
    @Test
    public void test05(){
        Dao dao = new Dao();
        Object[] arr = {"懂大纲", 42,"女","98989",1};
        try {
            int rows = dao.update("update teacher set username = ?, age = ?, gender = ?, mobile = ? where id = ?","小孩",25,"woman","88888",3);
            System.out.println(rows+"rows affected");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    @Test
    public void test04(){

    }
    @Test
    public void test03(){
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = JdbcUtil.getConnection();
            String sql = "insert into Teacher (id,username,age,gender,mobile)values (?,?,?,?,?)";
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1,3);
            preparedStatement.setString(2,"eva");
            preparedStatement.setInt(3,18);
            preparedStatement.setString(4,"woman");
            preparedStatement.setString(5,"19999");

            int rows = preparedStatement.executeUpdate();
            System.out.println(rows + "rows affected");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.close(connection,preparedStatement);
        }
    }
    @Test
    public void test02(){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            Connection connection1 = JdbcUtil.getConnection();
            String sql = "";
            preparedStatement = connection1.prepareStatement(sql); //让服务器提前把给定的sql进行预编译
            int rows = preparedStatement.executeUpdate(); //sql已经在他的内部绑定了
            System.out.println(rows + "rows affected");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.close(connection, preparedStatement);
        }

    }
    @Test
    public void test01(){
        Connection connection = null;
        Statement statement = null;

        try {
            connection = JdbcUtil.getConnection();
            System.out.println(connection);
            statement = connection.createStatement();
            String sql = "create table if not exists Teacher ("+
                    "id int,"+
                    "username varchar(20),"+
                    "age int,"+
                    "gender enum('man','woman') default 'man',"+
                    "mobile varchar(20),"+
                    "primary key (id)"+
                    ")";
            int rows = statement.executeUpdate(sql);
            System.out.println(rows+"rows affected");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.close(connection,statement);
        }
    }
}
