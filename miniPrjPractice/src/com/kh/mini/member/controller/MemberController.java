package com.kh.mini.member.controller;

import com.kh.mini.main.Main;
import com.kh.mini.member.service.MemberService;
import com.kh.mini.member.vo.MemberVo;

public class MemberController {
	
	// field
	MemberService service;
	
	// constructor
	public MemberController() {
		service = new MemberService();
	}
	
	public void selectMenu() {
		System.out.println("=== MEMBER ===");
		System.out.println("1. 회원가입");
		System.out.println("2. 로그인");
		String num = Main.SC.nextLine();
		
		switch(num) {
		case "1": join(); break;
		case "2": login(); break;
		default: System.out.println("잘못 입력함");
		}
	}
	
	// 회원가입
	public void join() {
		try {
			// 데이터 준비
			System.out.print("아이디 : ");
			String id = Main.SC.nextLine();
			System.out.print("비밀번호 : ");
			String pwd = Main.SC.nextLine();
			System.out.print("닉네임 : ");
			String nick = Main.SC.nextLine();
			
			MemberVo vo = new MemberVo();
			vo.setId(id);
			vo.setPwd(pwd);
			vo.setNick(nick);
			
			// 서비스 호출
			int result = service.join(vo);
			// 결과 처리
			if(result != 1) {
				throw new Exception();
			}
			System.out.println("회원가입 완료");
		} catch(Exception e) {
			System.out.println("회원가입 실패...");
			e.printStackTrace();
		}
	}
	
	// 로그인
	public void login() {
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
			MemberVo x = service.login(vo);
			// 결과 처리
			if(x==null) {
				throw new Exception();
			}
		} catch(Exception e) {
			System.out.println("로그인 실패..");
			e.printStackTrace();
		}
	}
	// 로그아웃
	
	// 전체 회원 조회
	
	// 
	
	
}
