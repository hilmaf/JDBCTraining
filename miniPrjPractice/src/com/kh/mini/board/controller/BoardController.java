package com.kh.mini.board.controller;

import com.kh.mini.board.service.BoardService;
import com.kh.mini.board.vo.BoardVo;
import com.kh.mini.main.Main;

public class BoardController {
	
	BoardService service;
	
	// constructor
	public BoardController() {
		service = new BoardService();
	}
	
	/**
	 * 게시글 작성
	 * 
	 * INSERT INTO BOARD(NO, TITLE, CONTENT, WRITER_NO) VALUES(SEQ_BOARD.NEXVAL, ?, ?, loginMember)
	 * 
	*/
	public void post() {
		try {
			// 게시글 작성 시 로그인 필수
			if(Main.loginMember==null) {
				throw new Exception("글 작성은 회원만 가능");
			}
			
			// 데이터 준비
			System.out.print("제목 : ");
			String title = Main.SC.nextLine();
			System.out.print("작성자 : ");
			String writer = Main.SC.nextLine();
			System.out.print("내용 : ");
			String content = Main.SC.nextLine();
			
			BoardVo vo = new BoardVo();
			vo.setTitle(title);
			vo.setWriterNick(writer);
			vo.setContent(content);
			
			// 서비스 호출
			int result = service.post(vo);
			
			// 결과 처리
			if(result != 1) {
				throw new Exception();
			}
			
			System.out.println("게시글 작성 완료");
			
		} catch(Exception e) {
			System.out.println("게시글 작성 실패");
			e.printStackTrace();
		}
	}
	// 게시글 목록 조회 (등록순)
	
	// 게시글 수정
	
	// 게시글 삭제
	
	// 게시글 검색
}
