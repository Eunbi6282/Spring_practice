package com.springbook.biz.user;

public class UserVO {
	private String id;
	private String password;
	private String name;
	private String role;
	
	
	// getter setter생성
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	
	@Override
	public String toString() {  // UserVO의 객체의 RAM에 로드된 값 출력
		return "UserVO [id=" + id + ", password= " + password + ", name= " + name + ", role= " + role + "]";
	}
}
