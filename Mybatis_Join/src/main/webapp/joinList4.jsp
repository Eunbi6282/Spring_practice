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
		<c:forEach items="${joinList4}" var="join">
		<tr>
			<td> ${join.employee_id} </td>
			<td> ${join.first_name} </td>
			<td> ${join.last_name} </td>
			<td> ${join.email} </td>
			<td> ${join.phone_number} </td>
			<td> ${join.hire_date} </td>
			<td> ${join.job_id} </td>
			<td> ${join.salary} </td>
			<td> ${join.commission_pct} </td>
			<td> ${join.manager_id} </td>
			<td> ${join.department_id} </td>
			<td> ${join.department_name} </td>
			<td> ${join.location_id} </td>
			<td> ${join.start_date} </td>
			<td> ${join.end_date} </td>
			<td> ${join.street_address} </td>
			<td> ${join.postal_code} </td>
			<td> ${join.city} </td>
			<td> ${join.state_province} </td>
			<td> ${join.country_id} </td>
			
		</tr>
		</c:forEach>
	</table>

</center>
</body>
</html>