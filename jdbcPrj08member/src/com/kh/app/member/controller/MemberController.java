package com.kh.app.member.controller;

import java.util.ArrayList;
import java.util.Scanner;

import com.kh.app.member.service.MemberService;
import com.kh.app.member.vo.MemberVo;

public class MemberController {
	
	// field
	Scanner sc;
	MemberService memberService;
	
	// constructor
	public MemberController() {
		sc = new Scanner(System.in);
		memberService = new MemberService();
	}
	
	public void join() {
		// 데이터 준비
		System.out.println("--- 회원가입 ---");
		System.out.print("아이디 : ");
		String id = sc.nextLine();
		System.out.print("비밀번호 : ");
		String pwd = sc.nextLine();
		System.out.print("닉네임 : ");
		String nick = sc.nextLine();
		
		// 데이터 뭉치기
		MemberVo vo = new MemberVo();
		vo.setId(id);
		vo.setPwd(pwd);
		vo.setNick(nick);
		
		try {
			// 서비스 호출
			int result = memberService.join(vo);
			
			// 결과 처리
			if(result == 1) {
				System.out.println("회원가입이 완료되었습니다.");
			} else {
				throw new Exception();
			}
		} catch(Exception e) {
			System.out.println("작업 도중 문제가 발생했습니다.");
			e.printStackTrace();
		}
	}
	
	public void login() {
		// 데이터 준비
		System.out.println("--- 로그인 ---");
		System.out.print("아이디 : ");
		String id = sc.nextLine();
		System.out.print("비밀번호 : ");
		String pwd = sc.nextLine();
		
		// 데이터 뭉치기
		MemberVo vo = new MemberVo();
		vo.setId(id);
		vo.setPwd(pwd);
		
		try {
			// 서비스 호출
			MemberVo dbVo = memberService.login(vo);
			
			// 결과 처리
			if(dbVo!=null) {
				System.out.println("로그인 성공 !!");
			} else {
				System.out.println("로그인 실패 ..");
			}			
		} catch(Exception e) {
			System.out.println("로그인 도중 문제가 발생했습니다.");
			e.printStackTrace();
		}
	}
	
	public void printMemberList() {
		// 데이터 준비
		
		try {
			// 서비스 호출
			ArrayList<MemberVo> voList = memberService.getMemberList();
			
			// 결과 처리
			for(MemberVo vo : voList) {
				System.out.println(vo);
			}			
		} catch(Exception e) {
			System.out.println("작업 도중 문제가 발생했습니다.");
			e.printStackTrace();
		}
	}
	
	public void quit() {
		// 데이터 준비
		System.out.println("회원 탈퇴를 위해서는 회원정보 확인이 필요합니다.");
		System.out.print("아이디 : ");
		String id = sc.nextLine();
		System.out.print("비밀번호 : ");
		String pwd = sc.nextLine();
		
		// 데이터 뭉치기
		MemberVo vo = new MemberVo();
		vo.setId(id);
		vo.setPwd(pwd);
		
		try {
			// 서비스 호출
			int result = memberService.quit(vo);
			
			// 결과 처리
			if(result==1) {
				System.out.println("회원 탈퇴 완료");
			} else {
				System.out.println("회원 정보를 찾을 수 없습니다. 다시 시도해주세요.");
			}			
		} catch(Exception e) {
			System.out.println("탈퇴 도중 문제가 발생했습니다.");
			e.printStackTrace();
		}
	}
	
	public void 
	
}
