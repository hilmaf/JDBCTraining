package com.kh.mini.member.service;

import java.sql.Connection;

import com.kh.jdbc.JDBCTemplate;
import com.kh.mini.member.dao.MemberDao;
import com.kh.mini.member.vo.MemberVo;

public class MemberService {
	
	private final MemberDao dao;
	
	public MemberService() {
		dao = new MemberDao();
	}
	
	public int join(MemberVo memberVo) throws Exception {
		// connection
		Connection conn = JDBCTemplate.getConnection();
		// DAO 호출
		int result = dao.join(conn, memberVo);
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
	
	// 로그인
	public MemberVo login(MemberVo vo) throws Exception {
		// connection
		Connection conn = JDBCTemplate.getConnection();
		// dao 호출
		MemberVo dbVo = dao.login(conn, vo);
		// tx 처리
		
		// close
		JDBCTemplate.close(conn);
		
		return dbVo;
	}

	public int quit(String no) throws Exception {
		// conn
		Connection conn = JDBCTemplate.getConnection();
		// dao
		int result = dao.quit(conn, no);
		
		// tx
		if(result == 1) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		
		// close
		JDBCTemplate.close(conn);
		
		return result;
	}
}
