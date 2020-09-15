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
	 	 
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<style>

 .modal-header, h4, .close {
    background-color: #154566;
    color:white !important;
    text-align: center;
    font-size: 30px;
  }
  
  .btn-default.p_btn {
    width: 20%;
    height: 60px;
}
  
 .mybtn{ 
 background-color: #154566;
  color:white !important;}


.tw-project-box__content {
    -webkit-transition-timing-function: ease-in-out;
    transition-timing-function: ease-in-out;
    -webkit-transition-duration: .2s;
    transition-duration: .2s;
    -webkit-transition-property: -webkit-transform,-webkit-box-shadow;
    transition-property: -webkit-transform,-webkit-box-shadow;
    transition-property: transform,box-shadow;
    transition-property: transform,box-shadow,-webkit-transform,-webkit-box-shadow;
    -webkit-transform: translateY(2px);
    transform: translateY(2px);
    -webkit-box-orient: vertical;
    -webkit-box-direction: normal;
    -ms-flex-direction: column;
    flex-direction: column;
    -ms-flex-wrap: nowrap;
    flex-wrap: nowrap;
    -webkit-box-align: center;
    -ms-flex-align: center;
    align-items: center;
    position: relative;
    -webkit-box-pack: justify;
    -ms-flex-pack: justify;
    justify-content: space-between;
    -webkit-box-align: start;
    -ms-flex-align: start;
    align-items: flex-start;
    -webkit-box-sizing: border-box;
    box-sizing: border-box;
    margin: 10px 5px;
    padding: 10px;
    width: 227px;
    height: 170px;
    border-radius: 3px;
    background: #fff;
    color: #142d2f;
    font-weight: 600;
    font-size: 14px;
    cursor: pointer;
}


.tw-project-box-content__projects {
    display: -webkit-box;
    display: -ms-flexbox;
    /* display: flex; */
    /* -webkit-box-orient: horizontal; */
    /* -webkit-box-direction: normal; */
    -ms-flex-direction: row;
    /* flex-direction: row; */
    -ms-flex-wrap: wrap;
    /* flex-wrap: wrap; */
    /* -webkit-box-pack: start; */
    -ms-flex-pack: start;
    /* justify-content: flex-start; */
    /* -webkit-box-align: start; */
    -ms-flex-align: start;
    /* align-items: flex-start; */
    -ms-flex-line-pack: start;
    /* align-content: flex-start; */
}


.new-project-button-box {
    position: relative;
    margin: 10px 5px;
    width: 226px;
    height: 170px;
    border-radius: 2px;
    background-color: #fff;
    color: #27b6ba;
    text-align: center;
    cursor: pointer;
}
</style>
  	
</head>
<body>

	<div id="header">
		<h1 class="myhidden">LOGO</h1><!-- ## -->
		<div class="jyp_top">
			<div class="jyp_header jyp_gnb gnb0 col-sm-1">
			<a href="#">+</a>
			</div>
			<div>
			<div class="jyp_header jyp_gnb gnb1 col-sm-4"> JYPROJECT</div> 
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
			<a><img src="<%=request.getContextPath()%>/inc/images/no.png" alt="마이페이지"/></a>
			</div>
			</div> </div>
			
		<nav class="jyp_left">	
			<h2 class="myhidden">주메뉴</h2>
			    <ul>
			      <li> <a class="glyphicon glyphicon-bell"></a></li> <!-- 알람 -->
			      <li><a class="glyphicon glyphicon-time"></a></li><!-- 전체개요 -->
			      <li><a href="<%=request.getContextPath()%>/project/p_home.jsp" 
			      	class="glyphicon glyphicon-folder-open"></a></li> <!-- 프로젝트 -->
			      <li><a class="glyphicon glyphicon-user"></a></li> <!-- 주소록 -->
			      <li><a class="glyphicon glyphicon-comment"></a></li> <!-- 메신저 -->
			      
			    </ul>
		  
		</nav>
 	</div><!-- end <div id="header"> -->
	<!-- 										 -->
	<!-- 										 -->
	<!-- 										 -->
	
<!--            end header            -->
<!--            end header            -->
<!--            end header            -->


	<div class="container myconatiner">
		<h3 class="panel-heading">프로젝트</h3>
		
				
		<div class="container">
		 <!--  <h2>Modal Login Example</h2> -->
		  <!-- Trigger the modal with a button -->
		  
		  <button type="button" class="btn btn-default p_btn" id="myBtn">새 프로젝트 추가</button>
		  <!-- Modal -->
		  <div class="modal fade" id="myModal" role="dialog">
		    <div class="modal-dialog">
		    
		      <!-- Modal content-->
		      <div class="modal-content">
		        <div class="modal-header" style="padding:35px 50px;">
		          <button type="button" class="close" data-dismiss="modal">&times;</button>
		          <h4>새 프로젝트</h4>
		        </div>
		        <div class="modal-body" style="padding:40px 50px;">
		          <form role="form"> <!--프로젝트 생성 컨트롤러로 보내기 p_add.pro -->
		            <div class="form-group">
		              <label for="usrname">프로젝트 제목</label>
		              <input type="text" class="form-control" id="usrname" placeholder="예)웹사이트 디자인">
		            </div>
		            <div class="form-group">
		              <label for="psw">설명 <span style="color:gray">(선택사항)</span></label>
		              <input type="text" class="form-control" id="psw" >
		            </div>
		            <div class="checkbox">
		              <input type="button" value="멤버추가" >
		            </div>
		              <button type="submit" class="btn mybtn btn-block">프로젝트만들기</button>
		          </form>
		        </div>
		    
		      </div>
		      
		    </div>
		  </div> 
		</div>
		 
		<script>
		$(document).ready(function(){
		  $("#myBtn").click(function(){
		    $("#myModal").modal();
		  });
		});
		</script>
				
			</div><!--end container-->
	
	
<!--            start footer          -->
<!--            start footer          -->
<!--            start footer          -->
</body>
</html>
