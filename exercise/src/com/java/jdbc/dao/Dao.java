package com.java.jdbc.dao;

import java.sql.*;

/**
 * @classname JDBC
 * @Auther d3Lap1ace
 * @Time 5/2/2024 10:20 PM Thu
 * @description
 * @Version 1.0
 * From the Laplace Demon
 */


public class Dao {
    public void view(Connection connection,String sql,Object... args)throws SQLException{
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        preparedStatement = connection.prepareStatement(sql);
        for (int i = 0; i < args.length; i++) {
            preparedStatement.setObject(i+1,args[i]);
        }
        System.out.println("----------------------------------------------");

        //执行查询
        resultSet = preparedStatement.executeQuery();

        ResultSetMetaData metaData = resultSet.getMetaData();
        int columnCount = metaData.getColumnCount();
        for (int i = 0; i < columnCount; i++) {
            String columnLabel = metaData.getColumnLabel(i + 1);
            System.out.println(columnLabel+"\t");
        }

    }
}
