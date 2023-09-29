package member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Scanner;

import util.JDBCTemplate;

public class MemberController {
	
	// 멤버 변수
	Scanner sc;
	
	// 기본생성자
	public MemberController() {
		sc = new Scanner(System.in);		
	}
	
	public void selectMenu() {
		System.out.println("=== Menu ===");
		System.out.println("1. 회원가입");
		System.out.println("2. 로그인");
		System.out.print("메뉴 선택(숫자를 입력하세요): ");
		String num = sc.nextLine();
		
		switch(num) {
		case "1": join(); break;
		case "2": login(); break;
		}
	}
	
	private void join() {
		System.out.println("--- 회원가입 ---");
		
		// 유저 입력 받기
		System.out.print("아이디: ");
		String userId = sc.nextLine();
		System.out.print("비밀번호: ");
		String userPwd = sc.nextLine();		
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			// 디비 연결
			conn = JDBCTemplate.getConnection();			
			// 쿼리문 실행 후 결과 받기
			String joinSql = "INSERT INTO MEMBER(ID, PWD) VALUES(?, ?)";
			pstmt = conn.prepareStatement(joinSql);
			pstmt.setString(1, userId);
			pstmt.setString(2, userPwd);
			int result = pstmt.executeUpdate();
			// 결과 처리
			if(result==1) {
				System.out.println("회원가입이 완료되었습니다.");
			} else {
				throw new Exception();
			}
		} catch(Exception e) {
			JDBCTemplate.rollback(conn);
			System.out.println("회원가입 도중 문제가 발생했습니다.");
		} finally {
			// 자원 반납
			conn.close();
			pstmt.close();
		}
		
	}
	
	private void login() {
		System.out.println("--- 로그인 ---");
		
		// 유저 입력 받기
		
		// 디비 연결
		
		// 쿼리문 실행 후 결과 받기
		
		// 결과 처리
		
		// 자원 반납
	}
	
}
