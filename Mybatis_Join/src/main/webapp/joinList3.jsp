<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>조인(Join) 정보 출력</title>
</head>
<body>

<center>
	<h2>조인 정보 출력</h2>
	<hr>
	
	<table border="1" width="700" cellspacing="0" cellpadding="0">
		<tr>
			<td> EMPLOYEE_ID </td>
			<td> FIRST_NAME </td>
			<td> LAST_NAME </td>
			<td> EMAIL </td>
			<td> PHONE_NUMBER </td>
			<td> HIRE_DATE </td>
			<td> JOB_ID </td>
			<td> SALARY </td>
			<td> COMMISSION_PCT </td>
			<td> MANAGER_ID </td>
			<td> DEPARTMENT_ID </td>
			<td> DEPARTMENT_NAME </td>
			<td> LOCATION_ID </td>
			<td> START_DATE </td>
			<td> END_DATE </td>
			<td> STREET_ADDRESS </td>
			<td> POSTAL_CODE </td>
			<td> CITY </td>
			<td> STATE_PROVINCE </td>
			<td> COUNTRY_ID </td>
			
		</tr>
		
		<!-- Loop 시작 -->
		<c:forEach items="${joinList3}" var="join">
		<tr>
			<td> ${join.empsJ.employee_id} </td>
			<td> ${join.empsJ.first_name} </td>
			<td> ${join.empsJ.last_name} </td>
			<td> ${join.empsJ.email} </td>
			<td> ${join.empsJ.phone_number} </td>
			<td> ${join.empsJ.hire_date} </td>
			<td> ${join.empsJ.job_id} </td>
			<td> ${join.empsJ.salary} </td>
			<td> ${join.empsJ.commission_pct} </td>
			<td> ${join.empsJ.manager_id} </td>
			<td> ${join.deptsJ.department_id} </td>
			<td> ${join.deptsJ.department_name} </td>
			<td> ${join.deptsJ.location_id} </td>
			<td> ${join.jhisJ.start_date} </td>
			<td> ${join.jhisJ.end_date} </td>
			<td> ${join.locJ.street_address} </td>
			<td> ${join.locJ.postal_code} </td>
			<td> ${join.locJ.city} </td>
			<td> ${join.locJ.state_province} </td>
			<td> ${join.locJ.country_id} </td>
			
		</tr>
		</c:forEach>
	</table>

</center>
</body>
</html>