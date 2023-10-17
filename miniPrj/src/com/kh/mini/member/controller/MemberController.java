package com.kh.mini.member.controller;

import java.util.Scanner;

import com.kh.mini.main.Main;
import com.kh.mini.member.service.MemberService;
import com.kh.mini.member.vo.MemberVo;

public class MemberController {
	
	// field
	private final MemberService memberService;
	
	// constructor
	public MemberController() {
		memberService = new MemberService();
	}
	
	// 메뉴 선택
	public void selectMenu() {
		System.out.println("=== MEMBER ===");
		System.out.println("1. 회원가입");
		System.out.println("2. 로그인");
		System.out.println("3. 로그아웃");
		System.out.println("4. 회원탈퇴");
		
		String str = Main.SC.nextLine();
		
		switch(str) {
		case "1": join(); break;
		case "2": login(); break;
		case "3": logout(); break;
		case "4": quit(); break;
		default: System.out.println("잘못 입력하셨습니다.");
		}
	}
	
	
	/* <컨트롤러>
	 * 데이터 준비
	 * 서비스 호출
	 * 결과처리
	 */

	
	/**
	 *  회원가입
	 *  클라이언트로부터 아이디, 패스워드, 닉네임을 입력받아서 DB에 저장
	 *  
	 *  INSERT INTO MEMBER(NO, ID, PWD, NICK)
	 *  VALUES(SEQ_MEMBER_NO.NEXTVAL, 아이디, 비번, 닉네임);
	 */
	public void join() {
		try {
			System.out.println("회원가입");
			
			// 데이터 준비
			System.out.print("아이디 : ");
			String id = Main.SC.nextLine();
			System.out.print("비밀번호 : ");
			String pwd = Main.SC.nextLine();
			System.out.print("닉네임 : ");
			String nick = Main.SC.nextLine();
			
			MemberVo memberVo = new MemberVo();
			memberVo.setId(id);
			memberVo.setPwd(pwd);
			memberVo.setNick(nick);
			
			// 서비스 호출
			int result = memberService.join(memberVo);
			
			// 결과처리
			if(result != 1) {
				throw new Exception();
			}
			
			System.out.println("회원가입 성공");
			
		} catch(Exception e) {
			System.out.println("회원가입 실패...");
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 로그인
	 * 
	 * 아이디, 비번 입력받기
	 * 
	 * SELECT * FROM MEMBER WHERE ID = 아이디 AND PWD = 패스워드
	 */
	public void login() {
		System.out.println("--- 로그인 ---");
		try {
			// 데이터 준비
			System.out.print("아이디 : ");
			String id = Main.SC.nextLine();
			System.out.print("비밀번호 : ");
			String pwd = Main.SC.nextLine();
			
			MemberVo vo = new MemberVo();
			vo.setId(id);
			vo.setPwd(pwd);
			
			// 서비스 호출
			MemberVo dbVo = memberService.login(vo);
			
			// 결과 처리
			if(dbVo==null) {
				throw new Exception();
			}
			
			Main.loginMember = dbVo;
			System.out.println("로그인 성공");
		} catch(Exception e) {
			System.out.println("로그인 실패");
			e.printStackTrace();
		}
	}
	
	// 로그아웃
	public void logout() {
		System.out.println("--- 로그아웃 ---");
		Main.loginMember = null;
		System.out.println("로그아웃 되었습니다.");
	}
	
	/**
	 * 회원 탈퇴
	 * UPDATE MEMBER
	 * SET
	 * 	DEL_YN = 'Y'
	 * 	, MODIFY_DATE = SYSDATE
	 * WHERE NO = ?
	 * 
	 * 
	 */
	public void quit() {
		try {
			System.out.println("--- 회원 탈퇴 ---");
			// 검사
			if(Main.loginMember == null) {
				throw new Exception("로그인 후에 회원 탈퇴가 가능합니다.");
			}
			// 데이터 준비
			String no = Main.loginMember.getNo();
			
			// 서비스 호출
			int result = memberService.quit(no);
			
			// 결과 처리
			if(result != 1) {
				throw new Exception();
			}
			System.out.println("탈퇴 성공");
		} catch(Exception e) {
			System.out.println("회원 탈퇴 실패");
			e.printStackTrace();
		}
	}
	
	// 마이페이지 (현재 로그인한 본인 정보 조회)
	
	// 비밀번호 수정
	
	// 닉네임 수정
	
	// 전체 회원 조회 (관리자만 가능)
	
	// 회원 조회 - 번호 (관리자만 가능)
	
	// 회원 조회 - 아이디 (관리자만 가능)
	
	// 회원 조회 - 닉네임 (관리자만 가능)
	
	// 회원 강제탈퇴 (관리자만 가능)
	
	
}
