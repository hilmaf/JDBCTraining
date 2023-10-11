package employee.vo;

public class EmployeeVo {
	
	// 멤버변수
	private String empId;
	private String empPwd;
	private String empName;
	
	// 생성자
	public EmployeeVo() {
		super();
	}
	
	public EmployeeVo(String empId, String empPwd, String empName) {
		super();
		this.empId = empId;
		this.empPwd = empPwd;
		this.empName = empName;
	}

	// getter setter
	public String getEmpId() {
		return empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
	}

	public String getEmpPwd() {
		return empPwd;
	}

	public void setEmpPwd(String empPwd) {
		this.empPwd = empPwd;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	
	// toString
	@Override
	public String toString() {
		return "EmployeeVo [empId=" + empId + ", empPwd=" + empPwd + ", empName=" + empName + "]";
	}
	
	
}
