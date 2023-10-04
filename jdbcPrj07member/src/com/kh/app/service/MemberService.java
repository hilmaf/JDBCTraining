package com.kh.app.service;

import java.sql.Connection;
import java.util.ArrayList;

import com.kh.app.dao.MemberDAO;
import com.kh.app.jdbc.JDBCTemplate;
import com.kh.app.vo.MemberVo;

public class MemberService {
	
	// 멤버 변수
	MemberDAO dao;
	
	// 기본생성자
	public MemberService() {
		dao = new MemberDAO();
	}
	
	public int join(MemberVo vo) throws Exception {
		// connection  생성
		Connection conn = JDBCTemplate.getConnection();
		
		// DAO 호출
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
	
	public MemberVo login(MemberVo scVo) throws Exception {
		// connection 생성
		Connection conn = JDBCTemplate.getConnection();
		// DAO 호출
		MemberVo dbVo = dao.login(conn, scVo);
		// tx 처리
		
		// close
		JDBCTemplate.close(conn);
		
		return dbVo;
	}
	
	public ArrayList<MemberVo> getMemberList() throws Exception {
		// conn
		Connection conn = JDBCTemplate.getConnection();
		// DAO
		ArrayList<MemberVo> voList = dao.getMemberList(conn);
		
		// tx
		
		// close
		JDBCTemplate.close(conn);
		
		return voList;
	}
}
