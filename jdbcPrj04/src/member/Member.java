package member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import util.JDBCTemplate;

public class Member {
	
	private Scanner sc;
	
	private String id;
	private String pwd;
	
	public Member() {
		sc = new Scanner(System.in);
	}
	
	// 유저 입력받기
	public String scanId() {
		try {
			while(true) {
				System.out.print("아이디 : ");
				id = sc.nextLine();
				boolean isChecked = idValidation(JDBCTemplate.getConnection(), id);
				if(isChecked == true) {
					System.out.println("사용 가능한 아이디입니다.");
					break;
				} else {
					System.out.println("중복된 아이디입니다.");
					continue;
				}				
			}
			
			
		} catch(Exception e) {
			System.out.println("아이디 중복확인 도중 문제가 발생했습니다. 잠시 후 다시 시도하세요.");
			e.printStackTrace();
		} finally {
			
		}
		
		return id;
	}
	
	public String scanPwd() {
		try {
			while(true) {
				System.out.print("비밀번호 : ");
				pwd = sc.nextLine();			
				boolean isChecked = pwdValidation(JDBCTemplate.getConnection(), pwd);
				if(isChecked == true) {
					System.out.println("사용 가능한 비밀번호입니다.");
					break;
				} else {
					System.out.println("비밀번호는 반드시 특수문자를 포함해야 합니다.");
					continue;
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			
		}
		
		return pwd;
		
	}
	
	public boolean idValidation(Connection conn, String userId) throws Exception {
		boolean isOk = false;
		String checkSql = "SELECT ID FROM MEMBER WHERE ID = ?";
		PreparedStatement pstmt = conn.prepareStatement(checkSql);
		pstmt.setString(1, userId);
		ResultSet rs = pstmt.executeQuery();
		if(!rs.next()) {
			isOk = true;
		} else {			
			isOk = false;
		}
		
		return isOk;
	}
	
	public boolean pwdValidation(Connection conn, String userPwd) throws Exception{
		boolean isOk = false;
		while(true) {
			// 비밀번호에 특수문자 반드시 포함
			String[] specialChar = {"!", "@", "#", "$", "%", "^", "&", "*", "(", ")", "-", "_", "+", "=", "~"};
			
			for(int i=0; i<specialChar.length; i++) {
				if(userPwd.contains(specialChar[i])) {
					isOk = true;
					break;
				} else {
					isOk = false;
				}			
			}			
		}
		
		return isOk;
	}
}
