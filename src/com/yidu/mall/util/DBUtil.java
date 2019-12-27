package com.yidu.mall.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * �������ݿ⹤����
 * @author С��ħ
 *
 */
public class DBUtil {
	/**
	 * �������Ӷ��󹤾߷���
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static Connection getConnection() throws ClassNotFoundException, SQLException{
		Class.forName("com.mysql.jdbc.Driver");
		return DriverManager.getConnection("jdbc:mysql://localhost:3306/huaweimall?user=root&password=123&allowMultiQueries=true");
	}
	/**
	 * �ر���Դ����
	 * @param connection  ���Ӷ���
	 * @param statement  sqlִ�ж���
	 * @param set  ���������
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
