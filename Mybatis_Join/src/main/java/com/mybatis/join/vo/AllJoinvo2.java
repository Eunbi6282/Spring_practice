package com.mybatis.join.vo;

import java.util.Date;

import lombok.Data;

@Data
public class AllJoinvo2 {
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
	private String department_name; 
	private Date start_date;
	private Date end_date;
	private int location_id;
	private String street_address;
	private String postal_code;
	private String city;
	private String state_province;
	private String country_id;
	
	
}
