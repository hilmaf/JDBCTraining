package employee.controller;

import java.util.Scanner;

import employee.service.EmployeeService;
import employee.vo.EmployeeVo;

public class EmployeeController {
	// 멤버변수
	Scanner sc;
	EmployeeService employeeService;
	
	// 생성자
	public EmployeeController() {
		sc = new Scanner(System.in);
		employeeService = new EmployeeService();
	}
	// login
	public void login() {
		// 데이터 준비
		System.out.print("아이디 : ");
		String id = sc.nextLine();
		System.out.println("비밀번호 : ");
		String pwd = sc.nextLine();
		
		// 데이터 뭉치기
		EmployeeVo empVo = new EmployeeVo();
		empVo.setEmpId(id);
		empVo.setEmpPwd(pwd);
		
		try {
			// 서비스 호출
			EmployeeVo dbVo = employeeService.login(empVo);
			
			// 결과 처리
			if(dbVo != null) {
				System.out.println("로그인 되었습니다");
			} else {
				System.out.println("아이디와 비밀번호를 다시 확인하세요");
			}			
		} catch(Exception e) {
			System.out.println("로그인 도중 문제가 발생했습니다");
			e.printStackTrace();
		}
		
	}
}
