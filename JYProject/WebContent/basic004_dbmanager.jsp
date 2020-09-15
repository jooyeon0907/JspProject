
<%@page import="com.jooyeon.dbmanager.DBManager"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
</head>
<body>
	<div class="container panel panel-info"  style="min-height:800px;">
		<h3  class="panel-heading"> db연동 </h3>
		<%
		Connection conn = null;
		DBManager db = new DBManager();
		conn = db.getConnection();
		if(conn != null){ out.println("DB연동OK");}
		else { out.println("DB연동실패");} 
		%>
	</div><!-- end container -->
</body>
</html>
