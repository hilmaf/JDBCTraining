package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
	
	// commit
	public static void commit(Connection conn) {
		try {
			if(conn!=null) {
				conn.commit();				
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	// rollback
	public static void rollback(Connection conn) {
		try {
			if(conn!=null) {
				conn.rollback();				
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	// conn close
	public static void close(Connection conn) {
		try {
			if(conn!=null && !conn.isClosed()) {
				conn.close();				
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	// stmt close
	public static void close(Statement stmt) {
		try {
			if(stmt!=null && !stmt.isClosed()) {
				stmt.close();				
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	// rs close
	public static void close(ResultSet rs) {
		try {
			if(rs!=null && !rs.isClosed()) {
				rs.close();				
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
