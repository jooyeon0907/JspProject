<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix ="c" uri="http://java.sun.com/jsp/jstl/core" %> <!-- 태그 라이브러리 -->
<!DOCTYPE html>
<html lang="en">
<head>
  <!-- Theme Made By www.w3schools.com -->
  <title>JYPROJECT</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">

  
   <link href="<%=request.getContextPath()%>/inc/images/jyp_icon.png" rel="shortcut icon" type="images/x-icon"  >
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <link href="https://fonts.googleapis.com/css?family=Montserrat" rel="stylesheet" type="text/css">
  <link href="https://fonts.googleapis.com/css?family=Lato" rel="stylesheet" type="text/css">
  <link rel="stylesheet" href="<%=request.getContextPath()%>/css/mycss.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<style>
.myhidden{  width:1px; height:1px; overflow:hidden; position:absolute; left:-9999%; font-size:0; line-height:0;}

  .carousel-inner > .item > img,
  .carousel-inner > .item > a > img {
    width: 70%; margin: auto;
  }
  
  
  
  /*notice.jsp*/
  .notice{width:300px; position:fixed; top:20%; left:20%; boxshadow:0 0 5px rgba(0,0,0,0.5);
  		padding:10px; border-radius:20px;     margin-top: -17px;}
  .notice img{ width:200px; }
</style>
</head>
<body id="myPage" data-spy="scroll" data-target=".navbar" data-offset="60">

<nav class="navbar navbar-default navbar-fixed-top">
  <div class="container">
    <div class="navbar-header">
      <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>                        
      </button>
      <a class="navbar-brand" href="<%=request.getContextPath()%>/index.jsp">
      <img src="<%=request.getContextPath()%>/inc/images/jyproject_logo.png" alt="jyproject" style="height: 55px; margin-top: -19px;"/></a>
    </div>    
    <div class="collapse navbar-collapse" id="myNavbar">
      <ul class="nav navbar-nav navbar-right">
      
      <li><a href="<%=request.getContextPath()%>/java_MiniProject.members">자바 MINI PROJECT</a></li> 	
      <li><a href="http://joooo1234.cafe24.com/sboard2">SPRING PROJECT</a></li> 	
      	
      <% if(session.getAttribute("jyp_email") != null){ %>
      		<%--  <li><a href="jdbc_login_mypage.jsp"><%="아이디명"%></a></li> --%>
      	 <li><a href="<%=request.getContextPath()%>/login2.members">BOARD</a></li> 
      	<li><a href="<%=request.getContextPath()%>/mypage_view.members"><%=session.getAttribute("jyp_email")%></a></li> 
      	<li><a href="<%=request.getContextPath()%>/logout.members">LOGOUT</a></li>
      	<% }else{%>
        <li><a href="<%=request.getContextPath()%>/login_view.members">LOGIN</a></li>
         <li><a href="<%=request.getContextPath()%>/join_agree.members">JOIN</a></li>
      	<%} %>	
      </ul>
    </div>
  </div>
</nav>
  <!-- header -->
  <!-- header -->
  <!-- header -->
  <!-- header -->
  
  