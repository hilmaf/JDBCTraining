package com.kh.app.member.main;

import com.kh.app.member.controller.MemberController;

public class Main {

	public static void main(String[] args) {
		
		System.out.println("<< 회원 관리 프로그램 >>");
		
		MemberController memberController = new MemberController();
		memberController.join();
		memberController.login();
		memberController.printMemberList();
		memberController.quit();
		memberController.editPwd();
		
	}

}
