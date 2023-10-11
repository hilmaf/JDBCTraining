package library.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import library.vo.BookVo;
import util.JDBCTemplate;

public class LibraryDao {
		// 도서 검색
		public void searchBook() {
			
		}
		
		// 도서 목록 조회
		ArrayList<BookVo> voList = new ArrayList<BookVo>();
		public ArrayList<BookVo> getBookList(Connection conn) throws Exception {
			// SQL
			String voListSql = "SELECT * FROM BOOK";
			PreparedStatement pstmt = conn.prepareStatement(voListSql);
			ResultSet rs = pstmt.executeQuery();
			
			// rs 처리
			while(rs.next()) {
				String dbTitle = rs.getString("TITLE");
				String dbAuthor = rs.getString("AUTHOR");
				String dbPublisher = rs.getString("PUBLISHER");
				String dbGenre = rs.getString("GENRE");
				String dbLocation = rs.getString("LOCATION");
				
				BookVo dbVo = new BookVo(dbTitle, dbAuthor, dbPublisher, dbGenre, dbLocation);
				
				voList.add(dbVo);
			}
			
			return voList;
		}
		
		// 신규 도서 입력
		public int addNewBook(Connection conn, BookVo vo) throws Exception {
			// SQL
			String addSql = "INSERT INTO BOOK(BK_TITLE, BK_AUTHOR, BK_PUBLISHER, BK_GENRE, BK_LOCATION)"
							+ "VALUES(?, ?, ?, ?, ?)";
			PreparedStatement pstmt = conn.prepareStatement(addSql);
			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getAuthor());
			pstmt.setString(3, vo.getPublisher());
			pstmt.setString(4, vo.getGenre());
			pstmt.setString(5, vo.getLocation());
		
			int result = pstmt.executeUpdate();
			
			// rs 처리
			
			// close
			JDBCTemplate.close(pstmt);
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
