package com.jdbc.test.dao;

import com.jdbc.test.util.JdbcUtil;

import java.sql.*;

/**
 * DAO Data Access Object
 */
public class DAO {

    /**
     * 执行任意DQL的方法
     * @param connection 连接对象
     * @param sql 查询
     * @param args 替换?的实参列表
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public void view(Connection connection, String sql, Object... args) throws SQLException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            // 执行sql前先解决所有?
            for (int i = 0; i < args.length; i++) {
                preparedStatement.setObject(i + 1, args[i]);
            }
            System.out.println("------------------------------------------------------------------------------------");
            // 真的执行查询
            resultSet = preparedStatement.executeQuery();
            // 通过结果集获取它的虚表的表结构对象
            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount(); // 获取列数
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
            }
            System.out.println("------------------------------------------------------------------------------------");
        } finally {
            JdbcUtil.close(null, preparedStatement, resultSet);
        }
    }

    /**
     * 执行任意的DQL, 显示结果
     * @param sql 查询
     * @param args 替换sql中的所有?的实参列表
     */
    public void view(String sql, Object... args) throws SQLException, ClassNotFoundException {
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
            System.out.println("------------------------------------------------------------------------------------");
            // 真的执行查询
            resultSet = preparedStatement.executeQuery();
            // 通过结果集获取它的虚表的表结构对象
            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount(); // 获取列数
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
            }
            System.out.println("------------------------------------------------------------------------------------");
        } finally {
            JdbcUtil.close(connection, preparedStatement, resultSet);
        }
    }

    /**
     * 传入一个连接对象, 执行任意DML和DDL
     * @param connection 外部传入的Connection对象
     * @param sql 要执行的
     * @param args 用于批量替换sql中的所有?的实参值列表
     * @return 影响的行数
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public int update(Connection connection, String sql, Object... args) throws SQLException {
        PreparedStatement preparedStatement = null;
        try {
            // ?只能在sql为值的部分占位.
            preparedStatement = connection.prepareStatement(sql); // 让服务器提前把给定sql进行预编译
            // 解决所有?, 统一使用setObject方法替换
            for (int i = 0; i < args.length; i++) {
                preparedStatement.setObject(i + 1, args[i]);
            }
            // 如果sql中有?的存在, 在实际执行前必须全部解决掉
            int rows = preparedStatement.executeUpdate();// sql已经在它的内部绑定了
            return rows;
        } finally {
            JdbcUtil.close(null, preparedStatement);
        }
    }

    /**
     * 通用的执行DML或DDL的方法
     * @param sql 要执行sql
     * @param args 用于批量替换sql中的所有?的实参列表
     * @return 影响的行数
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public int update(String sql, Object... args) throws SQLException, ClassNotFoundException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = JdbcUtil.getConnection();
            // ?只能在sql为值的部分占位.
            preparedStatement = connection.prepareStatement(sql); // 让服务器提前把给定sql进行预编译
            // 解决所有?, 统一使用setObject方法替换
            for (int i = 0; i < args.length; i++) {
                preparedStatement.setObject(i + 1, args[i]);
            }
            // 如果sql中有?的存在, 在实际执行前必须全部解决掉
            int rows = preparedStatement.executeUpdate();// sql已经在它的内部绑定了
            return rows;
        } finally {
            JdbcUtil.close(connection, preparedStatement);
        }
    }
}
