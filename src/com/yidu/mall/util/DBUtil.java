package com.yidu.mall.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 操作数据库工具类
 * @author 小恶魔
 *
 */
public class DBUtil {
	/**
	 * 创建连接对象工具方法
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static Connection getConnection() throws ClassNotFoundException, SQLException{
		Class.forName("com.mysql.jdbc.Driver");
		return DriverManager.getConnection("jdbc:mysql://localhost:3306/huaweimall?user=root&password=123&allowMultiQueries=true");
	}
	/**
	 * 关闭资源对象
	 * @param connection  连接对象
	 * @param statement  sql执行对象
	 * @param set  结果集对象
	 */
	public static void closeSource(Connection connection,Statement statement,ResultSet set){
		try {
			if (connection != null) {
				connection.close();
			}
			if (statement != null) {
				statement.close();
			}
			if (set != null) {
				set.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
