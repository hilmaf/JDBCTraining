package com.kh.mini.main;

import java.util.Scanner;

import com.kh.mini.board.controller.BoardController;
import com.kh.mini.member.controller.MemberController;

public class Main {

	// field
	public static final Scanner SC = new Scanner(System.in);
	
	public static void main(String[] args) {
		
		System.out.println("=== 미니 프로젝트 ===");
		// 객체준비
		MemberController memberController = new MemberController();
		BoardController boardController = new BoardController();
		
		while(true) {
			// 메뉴판
			System.out.println("1. MEMBER");
			System.out.println("2. BOARD");
			System.out.println("9. QUIT PROGRAM");
			// 메뉴선택
			String num =  Main.SC.nextLine();
			switch(num) {
			case "1": memberController.selectMenu(); break;
//			case "2": boardController.selectMenu(); break;
			case "9": return;
			default: System.out.println("잘못 입력했습니다.");
			}
		}//while
		
		
	}//main

}
