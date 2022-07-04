package com.mybatis.join.vo;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class EmployeeVO {
	
	private int eno;
	private String ename;
	private String job;
	private int manager;
	private String hiredate;
	private int salary;
	private int commission;
	private int dno;
	
	
}
