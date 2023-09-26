package main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class MainSignIn {

	public static void main(String[] args) throws Exception{
		System.out.println("--- 로그인 ---");
		
		// 연결 준비
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String id = "C##KH_JDBC";
		String pwd = "1234";
		
		// 디비 연결
		Connection conn = DriverManager.getConnection(url, id, pwd);
		
		// 유저 입력받기
		Scanner sc = new Scanner(System.in);
		System.out.print("아이디 : ");
		String userScId = sc.nextLine();
		System.out.print("비밀번호 : ");
		String userScPwd = sc.nextLine();
		
		// 쿼리문 실행
		String signInSql = "SELECT * FROM MEMBER WHERE ID=? AND PWD=?";
		PreparedStatement pstmt = conn.prepareStatement(signInSql);
		pstmt.setString(1, userScId);
		pstmt.setString(2, userScPwd);
		ResultSet rs = pstmt.executeQuery();
		
		if(rs.next()) {
			String dbId = rs.getString("ID");
			String dbPwd = rs.getString("PWD");
			System.out.println("로그인 성공!");
		} else {
			System.out.println("로그인 실패..");
		}
		
	}

}
