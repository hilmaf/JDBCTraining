package com.kh.app.vo;

public class MemberVo {
	
	// 생성자
	public MemberVo() {
		
	}
	
	public MemberVo(String id, String pwd) {
		super();
		this.id = id;
		this.pwd = pwd;
	}

	// 멤버변수
	private String id;
	private String pwd;
	
	// getter setter
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	
	// toString
	@Override
	public String toString() {
		return "MemberVo [id=" + id + ", pwd=" + pwd + "]";
	}
	
	
}
