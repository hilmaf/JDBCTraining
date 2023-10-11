package library.controller;

import java.util.ArrayList;
import java.util.Scanner;

import library.service.LibraryService;
import library.vo.BookVo;

public class LibraryController {
	// 멤버변수
	private final Scanner sc;
	private final LibraryService libraryService;
	
	// 생성자
	public LibraryController() {
		sc = new Scanner(System.in);
		libraryService = new LibraryService();
	}
	
	// 도서 검색
	public void searchBook() {
		
	}
	
	// 도서 목록 조회
	public void printBookList() {
		System.out.println("=== 도서 목록 조회 ===");
		// 데이터 준비
		
		
		try {
			// 서비스 호출
			ArrayList<BookVo> voList = new ArrayList<BookVo>();
			voList = libraryService.getBookList();
			
			// 결과 처리
			System.out.println(voList); // 할일: BK_CODE DB에서 받아와서 같이 출력하기 + 대출상태 표시 같이 해주기
		} catch(Exception e) {
			System.out.println("도서 목록을 갖고 오지 못했습니다.");
			e.printStackTrace();
		}
		
	}
	
	// 신규 도서 등록
	public void addNewBook() {
		System.out.println("=== 신규 도서 등록 ===");
		// 데이터 준비
		System.out.print("---- 도서 제목 : ");
		String title = sc.nextLine();
		System.out.print("---- 저자 : ");
		String author = sc.nextLine();
		System.out.print("---- 출판사 : ");
		String publisher = sc.nextLine();
		System.out.print("---- 분류 : ");
		String genre = sc.nextLine();
		System.out.print("---- 도서 위치 : ");
		String location = sc.nextLine();
		// 할일: BK_CODE 자동생성
		BookVo vo = new BookVo();
		vo.setTitle(title);
		vo.setAuthor(author);
		vo.setPublisher(publisher);
		vo.setGenre(genre);
		vo.setLocation(location);
		
		try {
			// 서비스 호출
			int result = libraryService.addNewBook(vo);
			
			// 결과 처리
			if(result == 1) {
				System.out.println("신규 도서가 등록되었습니다");
			} else {
				System.out.println("등록 불가");
			}			
		} catch(Exception e) {
			System.out.println("도서 등록 중 문제가 발생했습니다");
			e.printStackTrace();
		}
		
	}
	
	// 도서 대출
	public void borrowBook() {
		
	}
	
	// 도서 반납
	public void returnBook() {
		
	}
}
