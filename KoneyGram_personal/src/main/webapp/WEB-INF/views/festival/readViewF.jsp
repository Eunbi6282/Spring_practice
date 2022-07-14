<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
	<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	<meta charset="UTF-8">
	<title>축제 View</title>
	 <script src="https://apis.openapi.sk.com/tmap/jsv2?version=1&appKey=l7xx57a22f8ff2d84a60b68032f171244141"></script>
        <script type="text/javascript">
			function initTmap(){
				var map = new Tmapv2.Map("map_div",  
				{
					center: new Tmapv2.LatLng(37.566481622437934,126.98502302169841), // 지도 초기 좌표
					width: "890px", 
					height: "400px",
					zoom: 15
				});
			} 
		</script>
</head>
<body>
	<div id="map_div">
    </div>     
</body>
</html>