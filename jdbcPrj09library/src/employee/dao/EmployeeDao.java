package employee.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import employee.vo.EmployeeVo;

public class EmployeeDao {

	// login
	public EmployeeVo login(Connection conn, EmployeeVo empVo) throws Exception {
		// SQL
		String loginSql = "SELECT * FROM MEMBER WHERE ID=? AND PWD=?";
		PreparedStatement pstmt = conn.prepareStatement(loginSql);
		pstmt.setString(1, empVo.getEmpId());
		pstmt.setString(2, empVo.getEmpPwd());
		ResultSet rs = pstmt.executeQuery();
		
		// rs 처리
		EmployeeVo dbVo = new EmployeeVo();
		if(rs.next()) {
			String dbId = rs.getString("ID");
			String dbPwd = rs.getString("PWD");
			
			dbVo.setEmpId(dbId);
			dbVo.setEmpPwd(dbPwd);
		}

		return dbVo;
	}
}
