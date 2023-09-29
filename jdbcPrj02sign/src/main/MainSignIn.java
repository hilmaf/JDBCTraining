package main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class MainSignIn {

	public static void main(String[] args) throws Exception {
		
		System.out.println("--- 로그인 ---");
		
		// 연결 준비
		String url = "jdbc:oracle:thin:@127.0.0.1:1521:xe";
		String id = "C##KH_JDBC";
		String pwd = "1234";
		// 디비 연결
		Connection conn = DriverManager.getConnection(url, id, pwd);
		// 유저 입력받기
		Scanner sc = new Scanner(System.in);
		System.out.print("아이디 : ");
		String scId = sc.nextLine();
		System.out.print("비밀번호 : ");
		String scPwd = sc.nextLine();
		// 쿼리문 실행 및 결과 받기
		String sql = "SELECT * FROM MEMBER WHERE ID = ? AND PWD = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, scId);
		pstmt.setString(2, scPwd);
		ResultSet rs = pstmt.executeQuery();
		// 로그인 판별
		if(rs.next()) {
			String dbId = rs.getString("ID");
			String dbPwd = rs.getString("PWD");
			System.out.println(dbId + " " + dbPwd);
			System.out.println("로그인 성공!");
		} else {
			System.out.println("로그인 실패..");
		}
	}

}
