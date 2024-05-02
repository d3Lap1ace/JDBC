package com.java.jdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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

    }
}
