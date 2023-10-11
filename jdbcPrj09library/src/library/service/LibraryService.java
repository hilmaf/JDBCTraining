package library.service;

import java.sql.Connection;
import java.util.ArrayList;

import library.dao.LibraryDao;
import library.vo.BookVo;
import util.JDBCTemplate;

public class LibraryService {
	// 멤버 변수
	LibraryDao libraryDao;
	
	// 생성자
	public LibraryService() {
		libraryDao = new LibraryDao();
	}
	// 도서 검색
	public void searchBook() {
		
	}
	
	// 도서 목록 조회
	public ArrayList<BookVo> getBookList() throws Exception {
		// connection
		Connection conn = JDBCTemplate.getConnection();
		// dao 호출
		ArrayList<BookVo> voList = libraryDao.getBookList(conn);
		// tx 처리
		
		// close
		JDBCTemplate.close(conn);
		// return
		return voList;
	}
	
	// 신규 도서 입력
	public int addNewBook(BookVo vo) throws Exception {
		// connection
		Connection conn = JDBCTemplate.getConnection();
		
		// dao 호출
		int result = libraryDao.addNewBook(conn, vo);
		
		// tx 처리
		if(result == 1) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		
		// close
		JDBCTemplate.close(conn);
		
		// return
		return result;
	}
	
	// 도서 대출
	public void borrowBook() {
		
	}
	
	// 도서 반납
	public void returnBook() {
		
	}
}
