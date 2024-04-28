package com.java.jdbc.DAO;

import com.java.jdbc.util.JdbcUtil;

import java.sql.*;

/**
*    @classname Dao
*    @description 
*    @Auther d3Lap1ace
*    @Time 2024/04/20  10:上午10:31
*    @Version 1.0
*                        From the Laplace Demon 
*/
public class Dao {
    /**
     *
     * @param sql
     * @param args
     */
    public void view(String sql,Object...args)throws SQLException,ClassNotFoundException{
        Connection connection = null;
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
//            System.out.println("columnCount = " + columnCount);
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
    public int update(String sql,Object... args) throws SQLException, ClassNotFoundException {
        Connection connection = null;
        PreparedStatement preparedStatement =null;

        try {
            connection = JdbcUtil.getConnection();
            preparedStatement = connection.prepareStatement(sql);

            for (int i = 0; i < args.length; i++) {
                preparedStatement.setObject(i+1,args[i]);
            }
            int rows = preparedStatement.executeUpdate();
            return rows;
        } finally {
            JdbcUtil.close(connection,preparedStatement);
        }
    }
    public void newView(String sql,Object...args){
        Connection connection = null;
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
            System.out.println(columnCount);
            for (int i = 0; i < columnCount; i++) {
                String columnLabel = metaData.getColumnLabel(i+1);
                System.out.println(columnCount+"\t");
            }
            System.out.println();
            System.out.println("------------------------------");
            while (resultSet.next()){
                for (int i = 0; i < columnCount; i++) {
                    String columnLabel = metaData.getColumnLabel(i+1);
                    Object object = resultSet.getObject(columnLabel);
                    System.out.println(object+"\t");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {

        }

    }
}
