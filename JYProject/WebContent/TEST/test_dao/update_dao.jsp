<%@page import="com.jooyeon.dto.JYP_Project_DTO"%>
<%@page import="com.jooyeon.dto.P_Work_DTO"%>
<%@page import="com.jooyeon.dao.UpdateDAO"%>
<%@page import="com.jooyeon.dto.JYP_WS_DTO"%>
<%@page import="java.util.ArrayList"%>
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
<%--  
  	<h3 class="panel-heading">미리알림 설정한 날이 디데이라면 정보 출력 </h3>
 	<%
 	UpdateDAO dao = new UpdateDAO();
 	P_Work_DTO dto= new P_Work_DTO();
 	ArrayList<P_Work_DTO> list = new ArrayList<P_Work_DTO>();
 	
 	int ws_no = 1; ///session값 불러오기
 	dto.setWs_no(ws_no);
 	list = dao.workAlarm(dto);
 	
 	
 	out.println(list);
 	//미리알림 설정한 날이 디데이라면 (dday = now()) 정보 출력시키기 ( 이건 뷰단에서 )
 	
 	%> 
 	 --%>
 	
<%--  	<h3 class="panel-heading">업무의 시작일,마감일과  그 정보들(p_work,wl_name,p_name) 출력 </h3>
 	<%
	 	UpdateDAO dao = new UpdateDAO();
	 	P_Work_DTO dto= new P_Work_DTO();
	 	ArrayList<P_Work_DTO> list = new ArrayList<P_Work_DTO>();
	 	
	 	int ws_no = 1; ///session값 불러오기
	 	dto.setWs_no(ws_no); 
 		list = dao.workDate(dto);
 		out.println(list);
 		//
 	
 	
 	
 	%> --%>
 	
 	
<%--  		<h3 class="panel-heading">프로젝트의 시작일,마감일과  그 정보들(jyp_project) 출력 </h3>
 		<%
 		UpdateDAO dao = new UpdateDAO();
 		JYP_Project_DTO dto = new JYP_Project_DTO();
 		ArrayList<JYP_Project_DTO> list = new  ArrayList<JYP_Project_DTO>();
 		
 		int ws_no = 1; ///session값 불러오기
 		dto.setWs_no(ws_no); 
 		list = dao.p_list(dto);
 		out.println(list);
 		%>
 	
 	 --%>
 	
 </div><!--end container-->

	
	
	
</body>
</html>
