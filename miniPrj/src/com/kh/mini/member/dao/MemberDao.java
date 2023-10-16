package com.kh.mini.member.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.kh.jdbc.JDBCTemplate;
import com.kh.mini.member.vo.MemberVo;

public class MemberDao {
	/*
	 * SQL
	 * rs 처리
	 * close
	 */
	
	
	public int join(Connection conn, MemberVo memberVo) throws Exception {
		// SQL
		String joinSql = "INSERT INTO MEMBER(NO, ID, PWD, NICK) VALUES(SEQ_MEMBER_NO.NEXTVAL, ?, ?, ?)";
		PreparedStatement pstmt = conn.prepareStatement(joinSql);
		pstmt.setString(1, memberVo.getId());
		pstmt.setString(2, memberVo.getPwd());
		pstmt.setString(3, memberVo.getNick());
		int result = pstmt.executeUpdate();
		
		// rs
		
		// close
		JDBCTemplate.close(pstmt);
		
		return result;
	}
	
	public MemberVo login(Connection conn, MemberVo vo) throws Exception {
		//sql
		String loginSql = "SELECT * FROM MEMBER WHERE ID=? AND PWD=? AND DEL_YN='N'";
		PreparedStatement pstmt = conn.prepareStatement(loginSql);
		pstmt.setString(1, vo.getId());
		pstmt.setString(2, vo.getPwd());
		ResultSet rs = pstmt.executeQuery();
		
		MemberVo loginMember=null;
		// rs 처리
		if(rs.next()) {
			String dbNo = rs.getString("NO");
			String dbId = rs.getString("ID");
			String dbPwd = rs.getString("PWD");
			String dbNick = rs.getString("NICK");
			String dbEnrollDate = rs.getString("ENROLL_DATE");
			String dbModifyDate = rs.getString("MODIFY_DATE");
			String dbDelYn = rs.getString("DEL_YN");
			
			loginMember = new MemberVo();
			loginMember.setNo(dbNo);
			loginMember.setId(dbId);
			loginMember.setPwd(dbPwd);
			loginMember.setNick(dbNick);
			loginMember.setEnrollDate(dbEnrollDate);
			loginMember.setModifyDate(dbModifyDate);
			loginMember.setDelYn(dbDelYn);
		}
		
		// close
		JDBCTemplate.close(pstmt);
		JDBCTemplate.close(rs);
		
		return loginMember;
		
	}
}
