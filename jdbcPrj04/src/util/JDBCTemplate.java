package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class JDBCTemplate {
	
	// getConnection
	public static Connection getConnection() throws Exception {
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String username = "C##KH_JDBC";
		String pwd = "1234";
		Connection conn = DriverManager.getConnection(url, username, pwd);
		conn.setAutoCommit(false);
		return conn;
	}
	
	// commit
	public static void commit(Connection conn) {
		try {
			if(conn!=null) {
				conn.commit();				
			}
		} catch(Exception e) {
			
		}
	}
	
	// rollback
	public static void rollback(Connection conn) {
		try {
			if(conn!=null) {
				conn.rollback();
			}
		} catch(Exception e) {
			
		}
	}
	
	// conn close
	public static void close(Connection conn) {
		try {
			if(conn!=null && !conn.isClosed()) {
				conn.close();
			}
		} catch(Exception e) {
			
		}
	}
	
	// stmt close
	public static void close(Statement stmt) {
		try {
			if(stmt!=null && !stmt.isClosed()) {
				stmt.close();
			}
		} catch(Exception e) {
			
		}
	}
	
	// rs close
	public static void close(ResultSet rs) {
		try {
			if(rs!=null && !rs.isClosed()) {
				rs.close();
			}
		} catch(Exception e) {
			
		}
	}
}
