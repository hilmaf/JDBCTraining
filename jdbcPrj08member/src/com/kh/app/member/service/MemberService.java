package com.kh.app.member.service;

import java.sql.Connection;
import java.util.ArrayList;

import com.kh.app.member.dao.MemberDao;
import com.kh.app.member.util.JDBCTemplate;
import com.kh.app.member.vo.MemberVo;

public class MemberService {
	
	// field
	MemberDao dao;
	
	// constructor
	public MemberService() {
		dao= new MemberDao();
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
		MemberVo dbVo = dao.login(conn, vo);
		
		// tx 처리
		
		// close
		JDBCTemplate.close(conn);
		
		return dbVo;
	}
	
	public ArrayList<MemberVo> getMemberList() throws Exception {
		// connection
		Connection conn = JDBCTemplate.getConnection();
		// dao 호출
		ArrayList<MemberVo> voList = dao.getMemberList(conn);
		// tx 처리
				
		// close
		JDBCTemplate.close(conn);
		
		return voList;
	}
	
	public int quit(MemberVo vo) throws Exception {
		// connection 
		Connection conn = JDBCTemplate.getConnection();
		// dao 호출
		int result = dao.quit(conn, vo);
		// tx 처리
		if(result==1) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		// close
		JDBCTemplate.close(conn);
		
		return result;
	}
}
