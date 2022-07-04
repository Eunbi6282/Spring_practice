package com.mybatis.join.vo;

import lombok.Data;

@Data
public class DepartmentsVO {
	private int department_id;
	private String department_name; 
	private int manager_id;
	private int location_id;
}   
