package com.mybatis.join.vo;

import java.util.Date;

import lombok.Data;

@Data
public class EmploymentsVO {
	private int employee_id;
	private String first_name;
	private String last_name;
	private String email;
	private String phone_number;
	private Date hire_date;
	private String job_id;
	private int salary;
	private int commission_pct;
	private int manager_id;
	private int department_id;
}
