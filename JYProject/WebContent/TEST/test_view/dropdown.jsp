<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix ="c" uri="http://java.sun.com/jsp/jstl/core" %> <!-- 태그 라이브러리 -->
<!DOCTYPE html>
<html>
<head>
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
</head>
<body>
   
<div class="container">
                                       
  <div class="dropdown">
    <button class="btn btn-default dropdown-toggle" type="button" id="ws_list" data-toggle="dropdown">워크스페이스
    <span class="caret"></span></button>
    <ul class="dropdown-menu" role="menu" aria-labelledby="ws_list">
      <li role="presentation"> 현재 워크스페이스 이름 <a role="menuitem" tabindex="-1" href="#">워크스페이스 설정</a></li>
      <li role="presentation" class="divider"></li>
      
      <c:forEach var="list" begin="1" end="3" varStatus="status">
       <li role="presentation"><a role="menuitem" tabindex="-1" href="#">${status.count }</a></li>
      </c:forEach>
     
      <li role="presentation" class="divider"></li>
      <li role="presentation"><a role="menuitem" tabindex="-1" href="#">
      	<span class="glyphicon glyphicon-plus"></span>워크스페이스 추가하기</a></li>
    </ul>
  </div>
  
  
  
</div>

</body>
</html>
