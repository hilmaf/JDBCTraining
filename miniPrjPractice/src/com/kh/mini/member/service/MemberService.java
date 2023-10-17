package com.kh.mini.member.service;

import java.sql.Connection;

import com.kh.mini.member.dao.MemberDao;
import com.kh.mini.member.vo.MemberVo;
import com.kh.mini.util.JDBCTemplate;

public class MemberService {

	// field
	MemberDao dao;
	
	// constructor
	public MemberService() {
		dao = new MemberDao();
	}
	
	public int join(MemberVo vo) throws Exception {
		// connection
		Connection conn = JDBCTemplate.getConnection();
		
		// dao 호출
		int result = dao.join(conn, vo);
		
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

	public MemberVo login(MemberVo vo) throws Exception {
		// connection
		Connection conn = JDBCTemplate.getConnection();
		// dao 호출
		MemberVo x = dao.login(conn, vo);
		// tx 처리
		
		// close
		JDBCTemplate.close(conn);
		return x;
	}

}
