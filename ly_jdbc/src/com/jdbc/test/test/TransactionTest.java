package com.jdbc.test.test;

import com.jdbc.test.dao.DAO;
import com.jdbc.test.util.JdbcUtil;

import java.sql.Connection;
import java.sql.SQLException;

public class TransactionTest {

    /**
     * 事务中的所有DML,DQL的执行必须共用同一个Connection对象.
     * @param args
     */
    public static void main(String[] args) {
        DAO dao = new DAO();
        Connection connection = null;
        try {
            connection = JdbcUtil.getConnection();
            // 取消自动提交
            connection.setAutoCommit(false);
            // 若干DML, DQL

            dao.view(connection, "select * from world.city");
            int rows = dao.update(connection, "delete from world.city");
            System.out.println(rows + " rows affected");
            System.out.println("after delete");
            dao.view(connection, "select * from world.city");

            //dao.update(connection, "laksdjflkasjdflkjasdf");

            // 如果没有出现问题 提交事务
            connection.commit();
        } catch (Exception e) {
            // 如果出现任意问题, 回滚事务
            try {
                connection.rollback();
            } catch (SQLException ex) {
                e.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            // 还原设置
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            JdbcUtil.close(connection);
        }

    }
}
