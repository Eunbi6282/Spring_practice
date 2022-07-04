package com.mybatis.join.vo;

import lombok.Data;

@Data
public class LocationsVO {
	private int location_id;
	private String street_address;
	private String postal_code;
	private String city;
	private String state_province;
	private String country_id;
}
