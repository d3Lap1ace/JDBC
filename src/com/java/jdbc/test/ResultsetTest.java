package com.java.jdbc.test;

import com.java.jdbc.DAO.Dao;
import com.java.jdbc.javabean.Student;
import com.java.jdbc.util.JdbcUtil;
import org.junit.Test;

import java.sql.*;
import java.util.ArrayList;
import java.util.Iterator;


/**
*    @classname ResultsetTest
*    @description 
*    @Auther d3Lap1ace
*    @Time 2024/04/20  11:上午11:26
*    @Version 1.0
*                        From the Laplace Demon 
*/
public class ResultsetTest {
    @Test
    public void test03() throws SQLException, ClassNotFoundException {
        Dao dao = new Dao();
        dao.view("select *from world.city where id >?",0);
    }
    @Test
    public void test02(){
        String sql = "select id,username,age,gender,mobile from teacher where id > ?";
        Object[] args = {0};
        Connection  connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = JdbcUtil.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                preparedStatement.setObject(i+1,args[i]);
            }
            resultSet = preparedStatement.executeQuery();
            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();
            System.out.println("columnCount = " + columnCount);

            for (int i = 0; i < columnCount; i++) {
                String columnLabel = metaData.getColumnLabel(i + 1);
                System.out.print(columnLabel+"\t");
            }
            System.out.println();
            System.out.println("-------------------------------------------------");
            while (resultSet.next()){
                for (int i = 0; i < columnCount; i++) {
                    String columnLabel = metaData.getColumnLabel(i + 1);
                    Object value = resultSet.getObject(columnLabel);
                    System.out.print(value +"\t");
                }
                System.out.println();
            }


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.close(connection,preparedStatement,resultSet);
        }


    }
    @Test
    public void test01(){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultset = null;
        ArrayList<Student> arrayList = new ArrayList<Student>();
        try {
            connection = JdbcUtil.getConnection();
            String sql = "select id,name,age,gender,master from student where id >?";
            preparedStatement = connection.prepareStatement(sql);
            Object[] args = {0};
            for (int i = 0; i < args.length; i++) {
                preparedStatement.setObject(i+1,args[i]);
            }
            resultset = preparedStatement.executeQuery();
            while (resultset.next()){
                int id = resultset.getInt(1);
                String name = resultset.getString(2);
                int age = resultset.getInt(3);
                String gender = resultset.getString(4);
                int master = resultset.getInt(5);
                Student student = new Student(id, name, age, gender, master);
                arrayList.add(student);
//                System.out.println(id+"\t"+name+"\t"+age+"\t"+gender+"\t\t"+master);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.close(connection,preparedStatement,resultset);
        }
        Iterator<Student> iterator = arrayList.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }
}
