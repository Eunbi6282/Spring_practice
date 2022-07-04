package com.mybatis.join.vo;

import java.util.Date;

import lombok.Data;

@Data
public class JobHistoryVO {
	private int employee_id;
	private Date start_date;
	private Date end_date;
	private String job_id;
	private int department_id;
}
