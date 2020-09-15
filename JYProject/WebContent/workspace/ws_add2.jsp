<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
<!--워크스페이스 생성하기 페이지 -->


<div style="margin-top:5%">
	<div>
	<h3 class="text-center">워크스페이스 만들기</h3> 
	</div>
	<div class="container panel panel-info text-center" style="max-width: 450px; width: 100%; padding-top: 15px; ">
		<form action="<%=request.getContextPath()%>/ws_add.ws" method="get">
		<fieldset>
		<legend>WORKSPCE 생성하기</legend>
		<p>워크스페이스는 회사, 부서, 팀 혹은 개인이 될 수 있습니다.</p>
			<div>
				<label for="ws_name">워크스페이스 이름</label>
			</div>	
			<div>
				<input type="text" name="ws_name" id="ws_name"  style="margin:5px; width:60%;  " />
			</div>		
			<div>
				<input type="submit" value="만들기" class="btn btn-primary"  style="margin:2px; width:50%"/>
			</div>
			<div>
				<a href="javascript:history.go(-1)" style="margin:2px; width:50%" class="btn btn-default"> 뒤로</a>
			</div>
			
		</fieldset>
		</form>
	</div><!--end container-->
</div>	
</body>
</html>

