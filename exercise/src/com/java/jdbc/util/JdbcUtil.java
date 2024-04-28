package com.java.jdbc.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.util.Date;
import java.util.Properties;

/**
*    @classname JdbcUtil
*    @description 
*    @Auther d3Lap1ace
*    @Time 2024/04/22  20:下午8:56
*    @Version 1.0
*                        From the Laplace Demon 
*/
public class JdbcUtil {
    private static Date dataSource;

    public static Connection getConnection(){
        if(dataSource == null){
            synchronized (""){
                if(dataSource == null){
                    Properties properties = new Properties();

                    try {
                        properties.load(new FileInputStream("druid.properties"));
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
        Connection connection = dataSource.getConnection();
        return connection;
    }
}
