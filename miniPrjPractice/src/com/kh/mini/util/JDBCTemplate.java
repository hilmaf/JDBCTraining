package com.kh.mini.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
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
	
	// close (conn) 
	public static void close(Connection x) {
		try {
			if(x != null && !x.isClosed()) {
				x.close();
			}
		} catch (SQLException e) {
			
		}
	}
	
	// close (stmt) 
	public static void close(Statement x) {
		try {
			if(x != null && !x.isClosed()) {
				x.close();
			}
		} catch (SQLException e) {
			
		}
	}
	// close (rs)
	public static void close(ResultSet x) {
		try {
			if(x != null && !x.isClosed()) {
				x.close();
			}
		} catch (SQLException e) {
			
		}
	}
	
	// commit
	public static void commit(Connection x) {
		try {
			if(x != null && !x.isClosed()) {
				x.commit();
			}
		} catch (SQLException e) {
			
		}
	}
	// rollback
	public static void rollback(Connection x) {
		try {
			if(x != null && !x.isClosed()) {
				x.rollback();
			}
		} catch (SQLException e) {
			
		}
	}
	

}