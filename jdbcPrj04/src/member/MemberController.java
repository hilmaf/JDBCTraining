package member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

import util.JDBCTemplate;

public class MemberController {
	
	Scanner sc;
	
	public MemberController() {
		sc = new Scanner(System.in);
	}
	
	private void selectMenu() {
		System.out.println("<< 메뉴 >>");
		System.out.println("1. 회원가입");
		System.out.println("2. 로그인");
		System.out.println("3. 전체 회원 조회");
		System.out.println("4. 회원 탈퇴");
		System.out.print("메뉴 선택(숫자) : ");
	}
	
	public void manageMember() {
		selectMenu();
		String num = sc.nextLine();
		switch(num) {
		case "1": join(); break;
		case "2": login(); break;
		case "3": printMemberList(); break;
		case "4": quit(); break;
		}
	}
	
	private void join() {
		// 유저 입력받기
		System.out.print("아이디 : ");
		String userId = sc.nextLine();
		System.out.print("비밀번호 : ");
		String userPwd = sc.nextLine();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			// 디비 연결
			conn = JDBCTemplate.getConnection();
			// 쿼리문 실행 및 결과 받기
			String joinSql = "INSERT INTO MEMBER(ID, PWD) VALUES(?, ?)";
			pstmt = conn.prepareStatement(joinSql);
			pstmt.setString(1, userId);
			pstmt.setString(2, userPwd);
			int result = pstmt.executeUpdate();
			
			// 결과 처리
			if(result == 1) {
				JDBCTemplate.commit(conn);
				System.out.println("회원가입이 완료되었습니다.");
			} else {
				throw new Exception();
			}
		} catch(Exception e) {
			JDBCTemplate.rollback(conn);
			System.out.println("회원가입 도중 문제가 발생했습니다.");
			e.printStackTrace();
		} finally {
			// 자원 반납
			JDBCTemplate.close(pstmt);
			JDBCTemplate.close(conn);
		}
	}// join
	
	private void login() {
		// 유저 입력 받기
		System.out.print("아이디 : ");
		String scId = sc.nextLine();
		System.out.print("비밀번호 : ");
		String scPwd = sc.nextLine();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			// 디비 연결
			conn = JDBCTemplate.getConnection();
			// 쿼리문 실행 및 결과 받기
			String loginSql = "SELECT * FROM MEMBER WHERE ID = ? AND PWD = ?";
			pstmt = conn.prepareStatement(loginSql);
			pstmt.setString(1, scId);
			pstmt.setString(2, scPwd);
			rs = pstmt.executeQuery();
			// 결과 처리
			if(rs.next()) {
				JDBCTemplate.commit(conn);
				System.out.println("로그인 되었습니다.");
			} else {
				throw new Exception();
			}
		} catch(Exception e) {
			JDBCTemplate.rollback(conn);
			System.out.println("아이디와 비밀번호를 다시 확인하세요.");
			e.printStackTrace();
		} finally {
			// 자원 반납
			JDBCTemplate.close(rs);
			JDBCTemplate.close(pstmt);
			JDBCTemplate.close(conn);
		}
	}
	
	private void printMemberList() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			// 디비 연결
			conn = JDBCTemplate.getConnection();
			// 쿼리문 실행 및 결과 받기
			String listSql = "SELECT * FROM MEMBER";
			pstmt = conn.prepareStatement(listSql);
			rs = pstmt.executeQuery();
			// 결과 처리하기
			while(rs.next()) {
				String dbId = rs.getString("ID");
				String dbPwd = rs.getString("PWD");
				System.out.println(dbId + " / " + dbPwd);
			}
		} catch(Exception e) {
			System.out.println("회원목록 조회 중 문제가 발생했습니다.");
			e.printStackTrace();
		} finally {
			// 자원 반납
			JDBCTemplate.close(rs);
			JDBCTemplate.close(pstmt);
			JDBCTemplate.close(conn);
		}
	}
	
	private void quit() {
		// 유저 입력받기
		System.out.println("회원 탈퇴를 선택하셨습니다.");
		System.out.println("개인정보 확인을 위해 아이디와 비밀번호를 입력해주세요.");
		System.out.print("아이디 : ");
		String userId = sc.nextLine();
		System.out.print("비밀번호 : ");
		String userPwd = sc.nextLine();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			// 디비 연결
			conn = JDBCTemplate.getConnection();			
			// 쿼리문 실행 및 결과 받기
			String quitSql = "DELETE FROM MEMBER WHERE ID = ? AND PWD = ?";
			pstmt = conn.prepareStatement(quitSql);
			pstmt.setString(1, userId);
			pstmt.setString(2, userPwd);
			result = pstmt.executeUpdate();
			if(result == 1) {
				System.out.println("탈퇴 되었습니다.");
			} else {
				throw new Exception();
			}
		} catch(Exception e) {
			System.out.println("문제가 발생했습니다. 나중에 다시 시도해주세요.");
			e.printStackTrace();
		} finally {
			// 자원 반납
			JDBCTemplate.close(pstmt);
			JDBCTemplate.close(conn);
		}
		
	}
	
}
