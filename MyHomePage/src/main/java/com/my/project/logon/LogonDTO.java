package com.my.project.logon;

import java.sql.Date;

public class LogonDTO {
	private String id;
	private String email;
	private String name;
	private String pass;
	private String address;
	private String tel;
	private Date regidate;
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public Date getRegidate() {
		return regidate;
	}
	public void setRegidate(Date regidate) {
		this.regidate = regidate;
	}
	
//	@Override
//	public boolean equals(Object obj) {
//		if(obj instanceof LogonDTO) {
//			if (this.pass.equals(((LogonDTO)obj).pass)) {
//				return true;
//			}
//		}
//		return false;
//	}
//	
//	@Override
//	public int hashCode() {
//		return pass.hashCode();
//	}
	
}
