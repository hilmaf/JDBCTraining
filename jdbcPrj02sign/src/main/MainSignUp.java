package main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class MainSignUp {

	public static void main(String[] args) throws Exception {
		
		// 연결 준비
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String id = "C##KH_JDBC";
		String pwd = "1234";
		
		// 디비 연결
		Connection conn = DriverManager.getConnection(url, id, pwd);
		
		// 유저 입력받기
		Scanner sc = new Scanner(System.in);
		System.out.print("아이디 : ");
		String userId = sc.nextLine();
		System.out.print("비밀번호 : ");
		String userPwd = sc.nextLine();
		
		// 쿼리문 실행 및 결과 받기
		String sql = "INSERT INTO MEMBER(ID, PWD) VALUES(?, ?)";
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, userId);
		pstmt.setString(2, userPwd);
		
		int result = pstmt.executeUpdate();
		
		// 완료 안내
		if(result == 1) {
			System.out.println("회원가입이 완료되었습니다.");
		}
	}

}
