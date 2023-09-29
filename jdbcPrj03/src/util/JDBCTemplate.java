package util;

import java.sql.Connection;
import java.sql.DriverManager;

public class JDBCTemplate {
	
	public static Connection getConnection() throws Exception {
		// 연결 준비
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String id = "C##KH_JDBC";
		String pwd = "1234";
		
		// 디비 연결
		Connection conn = DriverManager.getConnection(url, id, pwd);
		conn.setAutoCommit(false);
		return conn;
	}
	
	// rollback
	public static void rollback(Connection conn) {
		try {
			conn.rollback();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
}
