package com.kh.app.controller;

import java.util.ArrayList;
import java.util.Scanner;

import com.kh.app.service.MemberService;
import com.kh.app.vo.MemberVo;

public class MemberController {
	
	// 멤버변수
	Scanner sc;
	MemberService ms;
	
	public MemberController() {
		sc = new Scanner(System.in);
		ms = new MemberService();
	}
	
	public void selectMenu() {
		System.out.println("1. 회원가입");
		System.out.println("2. 로그인");
		System.out.println("3. 회원 목록 조회");
		
		String num = sc.nextLine();
		
		switch(num) {
		case "1" : join(); break;
		case "2" : login(); break;
		case "3" : printMemberList(); break;
		default : System.out.println("그런 메뉴 없음");
		}
	}
	
	public void join() {
		System.out.println("--- 회원가입 ---");
		// 데이터 준비
		System.out.print("아이디 : ");
		String userId = sc.nextLine();
		System.out.print("비밀번호 : ");
		String userPwd = sc.nextLine();
		
		MemberVo vo = new MemberVo();
		vo.setId(userId);
		vo.setPwd(userPwd);
		
		try {
			// MemberService 호출
			int result = ms.join(vo);
			
			// 결과 처리
			if(result == 1) {
				System.out.println("회원가입 성공!!!");
			} else {
				throw new Exception();
			}			
		} catch(Exception e) {
			System.out.println("회원가입 실패...");
			e.printStackTrace();
		}
	}
	
	public void login() {
		System.out.println("--- 로그인 ---");
		
		// 데이터 준비
		System.out.print("아이디 : ");
		String scId = sc.nextLine();
		System.out.print("비밀번호 : ");
		String scPwd = sc.nextLine();
		
		MemberVo scVo = new MemberVo();
		scVo.setId(scId);
		scVo.setPwd(scPwd);
		
		try {
			// 서비스 계층 호출
			MemberVo dbVo = ms.login(scVo);
			
			// 결과 처리
			if(dbVo != null) {
				System.out.println("로그인 성공!!!");
			} else {
				throw new Exception();
			}			
		} catch(Exception e) {
			System.out.println("로그인 실패...");
			e.printStackTrace();
		}
	}
	
	public void printMemberList() {
		System.out.println("--- 회원 목록 조회 ---");
		
		// 데이터 준비 (X)
		
		try {
			// 서비스 계층 호출
			ArrayList<MemberVo> voList = ms.getMemberList();
			
			// 결과 처리
			for(MemberVo vo : voList) {
				System.out.println(vo);
			}			
		} catch(Exception e) {
			System.out.println("회원 목록 조회 실패...");
			e.printStackTrace();
		}
		
	}
	
}
