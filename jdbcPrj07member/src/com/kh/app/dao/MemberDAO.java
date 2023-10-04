package com.kh.app.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.kh.app.jdbc.JDBCTemplate;
import com.kh.app.vo.MemberVo;

public class MemberDAO {
	
	public int join(Connection conn, MemberVo vo) throws Exception {
		// SQL
		String joinSql = "INSERT INTO MEMBER(ID, PWD) VALUES(?, ?)";
		
		PreparedStatement pstmt = conn.prepareStatement(joinSql);
		pstmt.setString(1, vo.getId());
		pstmt.setString(2, vo.getPwd());
		int result = pstmt.executeUpdate();
		
		JDBCTemplate.close(pstmt);
		
		return result;
	}
	
	public MemberVo login(Connection conn, MemberVo scVo) throws Exception {
		// SQL
		String loginSql = "SELECT * FROM MEMBER WHERE ID=? AND PWD=?";
		PreparedStatement pstmt = conn.prepareStatement(loginSql);
		pstmt.setString(1, scVo.getId());
		pstmt.setString(2, scVo.getPwd());
		ResultSet rs = pstmt.executeQuery();
		
		// rs
		MemberVo dbVo = null;
		if(rs.next()) {
			String dbId = rs.getString("ID");
			String dbPwd = rs.getString("PWD");
			
			dbVo = new MemberVo();
			dbVo.setId(dbId);
			dbVo.setPwd(dbPwd);
		}
		
		// close
		JDBCTemplate.close(rs);
		JDBCTemplate.close(pstmt);
		
		return dbVo;
	}
	
	public ArrayList<MemberVo> getMemberList(Connection conn) throws Exception {
		// SQL
		String listSql = "SELECT * FROM MEMBER";
		PreparedStatement pstmt = conn.prepareStatement(listSql);
		ResultSet rs = pstmt.executeQuery();
		
		// rs
		ArrayList<MemberVo> voList = new ArrayList<MemberVo>();
		while(rs.next()) {
			String dbId = rs.getString("ID");
			String dbPwd = rs.getString("PWD");
			
			MemberVo vo = new MemberVo();
			vo.setId(dbId);
			vo.setPwd(dbPwd);
			
			voList.add(vo);
		}
		
		return voList;
	}
}
