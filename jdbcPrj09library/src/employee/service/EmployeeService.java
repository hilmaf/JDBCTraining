package employee.service;

import java.sql.Connection;

import employee.dao.EmployeeDao;
import employee.vo.EmployeeVo;
import util.JDBCTemplate;

public class EmployeeService {
	
	// 멤버변수
	EmployeeDao empDao;
	
	// 생성자
	public EmployeeService() {
		empDao = new EmployeeDao();
	}
	
	// login
	public EmployeeVo login(EmployeeVo empVo) throws Exception {
		// connection
		Connection conn = JDBCTemplate.getConnection();
		
		// dao 호출
		EmployeeVo dbVo = empDao.login(conn, empVo);
		
		// tx 처리
		
		// close
		JDBCTemplate.close(conn);
		
		return dbVo;
	}
	
}
