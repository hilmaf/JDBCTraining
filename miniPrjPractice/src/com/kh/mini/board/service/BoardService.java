package com.kh.mini.board.service;

import java.sql.Connection;

import com.kh.mini.board.dao.BoardDao;
import com.kh.mini.board.vo.BoardVo;
import com.kh.mini.util.JDBCTemplate;

public class BoardService {

	// field
	private final BoardDao dao;
	
	// constructor
	public BoardService() {
		dao = new BoardDao();
	}
	
	public int post(BoardVo vo) {
		// connection
		Connection conn = JDBCTemplate.getConnection();
		// dao 호출
		int result = dao.post(conn, vo);
		// tx 처리
		if(result == 1) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		//close
		JDBCTemplate.close(conn);
		return result;
	}

}
