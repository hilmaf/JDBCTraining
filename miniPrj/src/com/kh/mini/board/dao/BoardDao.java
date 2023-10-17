package com.kh.mini.board.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.kh.jdbc.JDBCTemplate;
import com.kh.mini.board.vo.BoardVo;
import com.kh.mini.main.Main;

public class BoardDao {
	
	public int post(Connection conn, BoardVo vo) throws Exception{
		// SQL
		String postSql = "INSERT INTO BOARD(NO, TITLE, CONTENT, WRITER_NO) VALUES(SEQ_BOARD_NO.NEXTVAL, ?, ?, ?)";
		PreparedStatement pstmt = conn.prepareStatement(postSql);
		pstmt.setString(1, vo.getTitle());
		pstmt.setString(2, vo.getContent());
		pstmt.setString(3, Main.loginMember.getNo());
		int result = pstmt.executeUpdate();
		
		// rs
		
		// close
		JDBCTemplate.close(pstmt);
		
		return result;
	}

	public List<BoardVo> boardList(Connection conn) throws Exception {
		// SQL
		String listSql = "SELECT B.NO AS 번호 , B.TITLE 제목 , M.NICK AS 작성자닉네임 , B.HIT 조회수 , TO_CHAR(B.ENROLL_DATE, 'YYYY\"년\"MM\"월\"DD\"일\"') 등록일 FROM BOARD B JOIN MEMBER M ON M.NO = B.WRITER_NO WHERE B.DEL_YN='N' ORDER BY B.NO DESC";
		PreparedStatement pstmt = conn.prepareStatement(listSql);
		ResultSet rs = pstmt.executeQuery();
		
		ArrayList<BoardVo> voList = new ArrayList<BoardVo>();
		// rs
		while(rs.next()) {
			String no = rs.getString("번호");
			String title = rs.getString("제목");
			String writerNick = rs.getString("작성자닉네임");
			String hit = rs.getString("조회수");
			String enrollDate = rs.getString("등록일");
			
			BoardVo vo = new BoardVo();
			vo.setNo(no);
			vo.setTitle(title);
			vo.setWriterNick(writerNick);
			vo.setHit(hit);
			vo.setEnrollDate(enrollDate);
			
			voList.add(vo);
		}
		// close
		JDBCTemplate.close(rs);
		JDBCTemplate.close(pstmt);
		
		return voList;
	}

	public BoardVo boardDetailbyNo(Connection conn, String num) throws Exception {
		// SQL
		String detailSql = "SELECT B.NO, B.TITLE, B.CONTENT, B.WRITER_NO , M.NICK WRITER_NICK , B.HIT , TO_CHAR(B.ENROLL_DATE, 'MM-DD') ENROLL_DATE , B.MODIFY_DATE FROM BOARD B JOIN MEMBER M ON M.NO = B.WRITER_NO WHERE B.NO = ? AND B.DEL_YN = 'N'";
		PreparedStatement pstmt = conn.prepareStatement(detailSql);
		pstmt.setString(1, num);
		ResultSet rs = pstmt.executeQuery();
		// rs
		BoardVo vo = null;
		if(rs.next()) {
			String no = rs.getString("NO");
			String title = rs.getString("TITLE");
			String content = rs.getString("CONTENT");
			String writerNo = rs.getString("WRITER_NO");
			String writerNick = rs.getString("WRITER_NICK");
			String hit = rs.getString("HIT");
			String enrollDate = rs.getString("ENROLL_DATE");
			String modifyDate = rs.getString("MODIFY_DATE");
			
			vo = new BoardVo();
			
			vo.setNo(no);
			vo.setTitle(title);
			vo.setContent(content);
			vo.setWriterNo(writerNo);
			vo.setHit(hit);
			vo.setEnrollDate(enrollDate);
			vo.setModifyDate(modifyDate);
		}
		// close
		JDBCTemplate.close(rs);
		JDBCTemplate.close(pstmt);
		
		return vo;
	}
}
