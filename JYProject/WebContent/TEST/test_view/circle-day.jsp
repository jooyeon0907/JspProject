<%@ page language="java" contentType="text/html; charsetUTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix ="c" uri="http://java.sun.com/jsp/jstl/core" %> <!-- 태그 라이브러리 -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
	<title>Home</title>
	<!-- CSS -->
	<link rel ="stylesheet" href = "https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
</head>
<body>

	<div class="container panel panel-info">
	 	<div
	 	style=" width: 60px; height: 60px; font-weight: bold; border-radius: 50%; 
    			background-color: #A9F5F2; color: #FFFFFF; 
    			font-size: 18px; text-align: center; line-height: 62px;"
	 	>D-1</div> <!-- 디데이 다가올때 (start_date>=2 && start_date<0 일 때) -->
	 	<p>디데이 다가올때 (start_date>=2 && start_date<0 일 때)  D-(-start_date)</p>
	 	
	 	
	 	<div
	 	style=" width: 60px; height: 60px; font-weight: bold; border-radius: 50%; 
    			background-color: #0080FF; color: #FFFFFF; 
    			font-size: 18px; text-align: center; line-height: 62px;"
	 	>Dday</div> <!-- 디데이 (start_date=0 일때) -->
	 	<p>디데이 (start_date=0 일때)</p>
	 	
	 	<div 
	 	style=" width: 60px; height: 60px; font-weight: bold; border-radius: 50%; 
    			background-color: #08088A; color: #FFFFFF; 
    			font-size: 18px; text-align: center; line-height: 62px;"
	 	>+1</div> <!-- 마감일 지남  (end_date <0 일 때 )  -->
	 	<p> 마감일 지남  (end_date < 0 일 때 ) D+(-end_date)</p>
	 	
	 	
	 	
	 	<div style="width:15px; height:15px; border-radius:50%; background-color:#ffb024;" ></div>
	 	
	 	
	</div>


	
	
</body>
</html>
