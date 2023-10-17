package com.kh.mini.board.service;

import java.sql.Connection;
import java.util.List;

import com.kh.jdbc.JDBCTemplate;
import com.kh.mini.board.dao.BoardDao;
import com.kh.mini.board.vo.BoardVo;

public class BoardService {
	
	//field
	private final BoardDao boardDao;
	
	//constructor
	public BoardService() {
		boardDao = new BoardDao();
	}
	
	public int post(BoardVo vo) throws Exception {
		
		// connection
		Connection conn = JDBCTemplate.getConnection();
		
		// dao 호출
		int result = boardDao.post(conn, vo);
		
		// tx 처리
		if(result == 1) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		
		// close
		JDBCTemplate.close(conn);
		
		return result;
	}

	// 게시글 목록(최신순)
	public List<BoardVo> boardList() throws Exception {
		// connection
		Connection conn = JDBCTemplate.getConnection();
		// dao호출
		List<BoardVo> voList = boardDao.boardList(conn);
		// tx 처리
		
		// close
		JDBCTemplate.close(conn);
		
		return voList;
	}

	// 게시글 상세조회
	public BoardVo boardDetailbyNo(String num) throws Exception {
		// conn
		Connection conn = JDBCTemplate.getConnection();
		
		// dao호출
		BoardVo vo = boardDao.boardDetailbyNo(conn, num);
		
		// tx
		
		// close
		JDBCTemplate.close(conn);
		
		return vo;
	}
}
