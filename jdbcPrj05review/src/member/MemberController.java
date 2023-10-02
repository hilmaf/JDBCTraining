package member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

import util.JDBCTemplate;

public class MemberController {
	
	private Scanner sc;
	
	public MemberController() {
		sc = new Scanner(System.in);
	}
	
	private String selectMenu() {
		System.out.println("--- 메뉴 선택 ---");
		System.out.println("1. 회원가입");
		System.out.println("2. 로그인");
		System.out.println("3. 전체 회원 조회");
		System.out.print("숫자 입력: ");
		String numStr = sc.nextLine();
		
		return numStr;
	}
	
	public void signMember() {		
		try {
			String numStr = selectMenu();
			switch(numStr) {
			case "1": join(); break;
			case "2": login(); break;
			case "3": printMemberList(); break;
			default: throw new Exception();
			}
		} catch(Exception e) {
			System.out.println("잘못 입력했습니다. 다시 입력해주세요.");
		}
		
	}
	
	private void join() {
		System.out.println("--- 회원가입 ---");
		// 유저 입력 받기
		System.out.print("아이디 : ");
		String userId = sc.nextLine();
		System.out.print("비밀번호 : ");
		String userPwd = sc.nextLine();
		
		Connection conn=null;
		PreparedStatement pstmt=null;
		try {
			// 디비 연결(conn 받기)
			conn = JDBCTemplate.getConnection();
			
			// 쿼리문 실행 후 결과 받기
			String joinSql = "INSER INTO MEMBER(ID, PWD) VALUES(?, ?)";
			pstmt=conn.prepareStatement(joinSql);
			pstmt.setString(1, userId);
			pstmt.setString(2, userPwd);
			int result = pstmt.executeUpdate();
			
			// 결과 처리하기
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
		
	}
	
	private void login() {
		System.out.println("--- 로그인 ---");
		// 유저 입력 받기
		System.out.print("아이디 : ");
		String scId = sc.nextLine();
		System.out.print("비밀번호 : ");
		String scPwd = sc.nextLine();
		
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			// conn 받기
			conn = JDBCTemplate.getConnection();
			// 쿼리문 실행 후 결과 받기
			String loginSql = "SELECT * FROM MEMBER WHERE ID=? AND PWD=?";
			pstmt = conn.prepareStatement(loginSql);
			pstmt.setString(1, scId);
			pstmt.setString(2, scPwd);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				System.out.println("로그인 성공");
			} else {
				throw new Exception();
			}
		} catch(Exception e) {
			System.out.println("로그인 실패");
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(pstmt);
			JDBCTemplate.close(conn);			
		}
		
	}
	
	private void printMemberList() {
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			// conn 받아오기
			conn = JDBCTemplate.getConnection();			
			// 쿼리문 실행 후 결과 받기
			String listSql = "SELECT ID, PWD FROM MEMBER";
			pstmt = conn.prepareStatement(listSql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				String dbId = rs.getString("ID");
				String dbPwd = rs.getString("PWD");
				System.out.println(dbId + " / " + dbPwd);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			// 자원 반납
			JDBCTemplate.close(rs);
			JDBCTemplate.close(pstmt);
			JDBCTemplate.close(conn);
		}
		
	}
}
