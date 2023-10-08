package com.kh.app.member.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.kh.app.member.util.JDBCTemplate;
import com.kh.app.member.vo.MemberVo;

public class MemberDao {
	
	public int join(Connection conn, MemberVo vo) throws Exception {
		// SQL
		String joinSql = "INSERT INTO MEMBER(ID, PWD, NICK) VALUES(?, ?, ?)";
		PreparedStatement pstmt = conn.prepareStatement(joinSql);
		pstmt.setString(1, vo.getId());
		pstmt.setString(2, vo.getPwd());
		pstmt.setString(3, vo.getNick());
		int result = pstmt.executeUpdate();
		
		// rs
		
		// close
		JDBCTemplate.close(pstmt);
		
		return result;
	}
	
	public MemberVo login(Connection conn, MemberVo vo) throws Exception {
		// SQL
		String loginSql = "SELECT * FROM MEMBER WHERE ID=? AND PWD=?";
		PreparedStatement pstmt = conn.prepareStatement(loginSql);
		pstmt.setString(1, vo.getId());
		pstmt.setString(2, vo.getPwd());
		ResultSet rs = pstmt.executeQuery();
		
		// rs
		MemberVo dbVo=null;
		if(rs.next()) {
			rs.getString("ID");
			rs.getString("PWD");
			rs.getString("NICK");
			
			dbVo = new MemberVo();
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
		
		ArrayList<MemberVo> voList = new ArrayList<MemberVo>();
		// rs
		while(rs.next()) {
			String dbId = rs.getString("ID");
			String dbPwd = rs.getString("PWD");
			String dbNick = rs.getString("NICK");
			
			MemberVo dbVo = new MemberVo();
			dbVo.setId(dbId);
			dbVo.setPwd(dbPwd);
			dbVo.setNick(dbNick);
			
			voList.add(dbVo);
		}
		
		// close
		JDBCTemplate.close(rs);
		JDBCTemplate.close(pstmt);
		
		return voList;
	}
	
	public int quit(Connection conn, MemberVo vo) throws Exception {
		// SQL
		String quitSql = "DELETE FROM MEMBER WHERE ID=? AND PWD=?";
		PreparedStatement pstmt = conn.prepareStatement(quitSql);
		pstmt.setString(1, vo.getId());
		pstmt.setString(2, vo.getPwd());
		int result = pstmt.executeUpdate();
		
		// result
		
		// close
		JDBCTemplate.close(pstmt);
		
		return result;
	}
	
}
