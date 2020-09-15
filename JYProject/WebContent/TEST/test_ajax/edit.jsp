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
</head>
<body>
	<div class="container panel panel-info">
		<h3 class="panel-heading">ajax로 게시글 수정하기 </h3>
		
		<div class="title">
			<p><input value="타이틀" id="title"/></p>
		</div> <!-- 클릭시 그자리에서 타이틀만 수정되게 하기  -->
		<div class="content"><p>내용</p></div> <!-- 클릭시 그자리에서 내용만 수정되게 하기  -->
		

		
		<Script>
			$(function(){
				$("#title").click(function(){
					$.ajax({
						url:"${pageContext.request.contextPath}/Coffee",
						type:"get", 
						dataType:"text",
						success:function(data){ 
							$(".result.r1").html(data);   },
						error:function( xhr, textStatus, errorThrown ){
							$(".result.r1").html(textStatus +"(HTTP - "+ xhr.status + "/" +errorThrown);}
					});
				});
			});
		</Script>
		
	</div><!--end container-->
</body>
</html>

