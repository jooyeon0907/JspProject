<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
	 <link rel="stylesheet" href="<%=request.getContextPath()%>/css/mainCss.css">
</head>
<body>

	<div id="header">
		<h1 class="myhidden">LOGO</h1><!-- ## -->
		<div class="jyp_top">
			<div class="jyp_header jyp_gnb gnb0 col-sm-1">
			<a href="#">+</a>
			</div>
			<div>
			<div class="jyp_header jyp_gnb gnb1 col-sm-4"> 워크스페이스 샘플워크스페이스는 7월 28일 이후에 삭제됩니다.</div> 
			<div class="jyp_header jyp_gnb gnb2 col-sm-4">
				<form action="#"  method="get">
			    		<fieldset>
			    		<legend  class="myhidden">search</legend> 
			    		<label for="search" > <span class="glyphicon glyphicon-search"></span></label><input type="search" name="search"  id="search"  title="검색어 입력창" placeholder="워크스페이스를 검색해주세요."/> 
			    		</fieldset>
			    </form>
			    <a class="glyphicon glyphicon-envelope"></a>
			</div>
			<div class="jyp_header jyp_gnb gnb3 col-sm-2">
				<select >
					<option value="default">워크스페이스1</option>
				</select>
				
			</div>
			
			<div class="jyp_header jyp_gnb gnb4 col-sm-1">
			<a><img src="images/no.png" alt="마이페이지"/></a>
			</div>
			</div> </div>
			
		<nav class="jyp_left">	
			<h2 class="myhidden">주메뉴</h2>
			    <ul>
			      <li> <a class="glyphicon glyphicon-eye-open"></a></li>
			      <li><a class="glyphicon glyphicon-eye-open"></a></li>
			      <li><a class="glyphicon glyphicon-eye-open"></a></li>
			      <li><a class="glyphicon glyphicon-eye-open"></a></li>
			      
			    </ul>
		  
		</nav>
 	</div><!-- end <div id="header"> -->
	<!-- 										 -->
	<!-- 										 -->
	<!-- 										 -->
	


	<div class="container panel panel-info  mycontainer">
	</div><!--  end mycontainer -->
	<!-- 										 -->
	<!-- 										 -->
	<!-- 										 -->
	<div id="footer"></div><!-- end  <div id="footer">-->
	<!-- 										 -->
	<!-- 										 -->
	<!-- 										 -->
	
	
</body>
</html>