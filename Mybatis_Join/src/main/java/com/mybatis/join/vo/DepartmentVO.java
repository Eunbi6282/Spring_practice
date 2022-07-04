package com.mybatis.join.vo;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class DepartmentVO {
	
	private int dno;
	private String dname;
	private String loc;
	
}
