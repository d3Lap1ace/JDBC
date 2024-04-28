package com.jdbc.test.test;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.Scanner;

import com.jdbc.test.util.JdbcUtil;

import java.sql.Statement;

class User {
	
	private String username;
	private String password;
	
	public User() {}
	
	public User(String username, String password) {
		this.username = username;
		this.password = password;
	}
	
	public String toString() {
		return "username = " + username + ", password = " + password;
	}
}

public class TestStatement {

	// 弊端：需要拼写sql语句，并且存在SQL注入的问题
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.print("用户名：");
		String userName = scan.nextLine();
		System.out.print("密   码：");
		String password = scan.nextLine();

		String sql = "select " +
						"username, " +
						"password " +
					"from " +
						"user " +
					"where " +
							"username = '" + userName + "' " +
						"and " +
							"password = '" + password + "'"; 
		
		System.out.println(sql);
		
		User user = get(sql, User.class);
		if(user != null){
			System.out.println("登陆成功!");
		}else{
			System.out.println("用户名或密码错误！");
		}
	}

	public static <T> T get(String sql, Class<T> clazz) {
		T t = null;

		Connection conn = null;
		Statement stam = null;
		ResultSet rs = null;
		try {
			conn = JdbcUtil.getConnection();

			stam = conn.createStatement();

			rs = stam.executeQuery(sql);

			// 获取结果集的元数据
			ResultSetMetaData rsmd = rs.getMetaData();

			// 获取结果集的列数
			int columnCount = rsmd.getColumnCount();

			if (rs.next()) {

				t = clazz.newInstance();

				for (int i = 0; i < columnCount; i++) {
					// //1. 获取列的名称
					// String columnName = rsmd.getColumnName(i+1);

					// 1. 获取列的别名
					String columnName = rsmd.getColumnLabel(i + 1); // 注意：
																	// 获取结果集中（相当于数据表）列的名称（别名）

					// 2. 根据列名获取对应数据表中的数据
					Object columnVal = rs.getObject(columnName);

					// 3. 将数据表中得到的数据，封装进对象
					Field field = clazz.getDeclaredField(columnName); // 注意：反射根据Java中类的属性获取
																		// Field对象
					field.setAccessible(true);
					field.set(t, columnVal);
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
//			JDBCUtils.close(rs, stam, conn);
		}

		return t;
	}

}
