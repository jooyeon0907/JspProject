<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix ="c" uri="http://java.sun.com/jsp/jstl/core" %> <!-- 태그 라이브러리 -->
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel ="stylesheet" 
	href = "https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
 <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

<style>
.wl.main {
    /* background-color: yellow;
    border: 1px solid red; */
    /* width: auto; */
    /* height: 102px; */
    position: absolute;
    top: 50px;
    right: 0;
    bottom: 0;
    left: 0;
    
      display: flex;
     /*  flex-wrap: wrap; */
  align-content: stretch;
}
   
.main_common{
    display:inline-table;
    float: left;
    width: 300px;
    height: 100px;
   /*  border: 1px solid blue; */
margin: 0 13px 10px;
}
 
.content {
    display: table-cell;
    vertical-align: middle;
    text-align: center;
}

button.wl_add{
    height: 38%;
    width: 90%;
}



</style>

</head>
<body>

 
<div class="wl main">
<!--     <div class="main_1 main_common"><p class="content">test1</p></div>
    <div class="main_2 main_common"><p class="content">test2</p></div>
    <div class="main_3 main_common"><p class="content">test3</p></div> -->
    <c:forEach var="dto" begin="1" end="5" varStatus="status">
    	<div class="main_${status.count} main_common">
    	
    	<!-- #accordion -->
    				<div class="panel panel-default">
				      <div class="panel-heading">
				        <h4 class="panel-title">
				          <a data-toggle="collapse" data-parent="#accordion" href="#collapse${status.count}">업무리스트 제목${status.count}</a>
				        </h4>
				      </div>
				      <div id="collapse${status.count}" class="panel-collapse collapse in">
				        <div class="panel-body"> ${status.count }</div>
				      </div>
				    </div>    
    	
    	<!-- end #accordion -->
    	</div> <!-- .main_common -->
    </c:forEach>
 		<!-- 업무리스트 추가하기 버튼  -->
 		<div class="main_common text-center">
 			<button class="wl_add"><span class="glyphicon glyphicon-plus"></span>새 업무리스트 만들기</button>
 		</div>
 
</div> <!--end wl main -->

</body>
</html>

