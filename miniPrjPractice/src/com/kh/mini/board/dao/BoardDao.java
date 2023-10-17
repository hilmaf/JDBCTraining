package com.kh.mini.board.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.kh.mini.board.vo.BoardVo;
import com.kh.mini.main.Main;
import com.kh.mini.util.JDBCTemplate;

public class BoardDao {

	public int post(Connection conn, BoardVo vo) throws Exception {
		// sql
		String sql = "INSERT INTO BOARD(NO, TITLE, CONTENT, WRITER_NO) VALUES(SEQ_BOARD.NEXTVAL, ?, ?, ?)";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, vo.getTitle());
		pstmt.setString(2, vo.getContent());
		pstmt.setString(3, Main.loginMember.getNo());
		int result = pstmt.executeUpdate();
		// rs 처리
		
		// close
		JDBCTemplate.close(pstmt);
		return result;
	}

}
