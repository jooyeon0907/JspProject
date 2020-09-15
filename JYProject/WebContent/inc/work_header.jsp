<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix ="c" uri="http://java.sun.com/jsp/jstl/core" %> <!-- 태그 라이브러리 -->
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
button#ws_list {
    background-color: #1f5c87;
    height: 46px;
    width: 100%;
    border: 0;
    border-left: 2px solid #bbdaef;
}

  .p_div {
    display: inline-block;
    margin-bottom: 0;
    font-weight: 400;
    text-align: center;
    white-space: nowrap;
    vertical-align: middle;
    -ms-touch-action: manipulation;
    touch-action: manipulation;
    cursor: pointer;
    background-image: none;
    border: 1px solid transparent;
    padding: 6px 12px;
    font-size: 14px;
    line-height: 1.42857143;
    border-radius: 4px;
    -webkit-user-select: none;
    -moz-user-select: none;
    -ms-user-select: none;
    user-select: none;
    
    padding: 9px;
    width: 20%;
    height: 140px;
    margin-top: 10px;
    margin-right: 5px;
    margin-bottom: 10px;
    margin-left: 5px;
    color: #333;
    background-color: #fff;
    border-color: #ccc;

}

/*project*/
.row.p_status {
    padding-top: 11%;
    font-size: 11px;
}
.row.p_work {
    padding-top: 11%;
}



/*chat*/
#visual{ height:600px; background-color:#FAFAFA;}
#chat .col-sm-4 { height: 600px; background-color: #EFFBFB; }
#chat .r1   { height:50px; background-color:#F3E2A9; }
#chat .r2  { height:350px; background-color:#F3F781; }
#chat .r3   { height:200px; background-color:#F8E6E0; }

/*20.8.3 타임 헤더*/
.jyp_tab.time {
    /* margin-left: 54px; */
    /* margin-top: 4px; */
/*     position: fixed; */
    left: 46px;
    width: 100%;
    height: 100%;
}
.jyp_tab ul.nav.nav-tabs {
    border: 0 none;
} 
.jyp_tab .tab-content {
    background-color: #e6e8ec;
    height: 100%;
}
.jyp_tab .nav-tabs>li.active>a {
    color: #27b6ba;
    border: 0 none;
    border-bottom: 2px solid;
}
.jyp_tab .nav-tabs>li {
    display: inline-block;
    margin-bottom: -1px;
    float: none;
}
.jyp_tab ul.nav.nav-tabs {
    text-align: center;
}
.jyp_tab .tab-content h3 {
    margin: 0;
}


/*pro 헤더*/
.jyp_tab.pro select, .jyp_tab.pro input {
    vertical-align: -webkit-baseline-middle;
}   
.jyp_tab.pro {
    padding: 8px;
    padding-left: 6%;
}


nav.jyp_left {
    position: fixed;
    left: 0;
    top: 46px;
    width: 46px;
    background-color: #1f5c87;
    height: 100%;
}



.jyp_top {
    height: 46px;
    width: 100%;
    position: fixed;
    height: 46px;
    color: #bbdaef;
    background-color: #1f5c87;
    line-height: 46px;
}



/*8.8 드롭 메뉴 :*/
button.dropmenu {
    background-color:#154566;;
    border: 0 none;
}

</style>
  	
</head>
<body>
<script>
	$(function(){
		$("#ws_list").click(function(){   //alert('워크스페이스 목록');
			$("#workspaceList").empty();
		console.log(<%=session.getAttribute("jyp_no")%>);
			$.ajax({
				url:"${pageContext.request.contextPath}/ws_listBar.ws", 
				type:"post", dataType:"json",
				data:{ "jyp_no" : <%=session.getAttribute("jyp_no")%> },
				success:function(data ){
					console.log(data);
					var ws_list = data.ws_list;
					console.log(ws_list);
					for(var i=0; i<ws_list.length; i++){
						var ws_no = ws_list[i].ws_no; var ws_name = ws_list[i].ws_name;
						var li = $("<li>");
						var a  = $(" <a href='${pageContext.request.contextPath}/ws_home.ws?ws_no="+ws_no+"&&ws_name="+ws_name+"' >").html(ws_name);
						console.log(ws_name);
						li.append(a);
						$("#workspaceList").append(li);
					}//end for
				
				},
				error:function(xhr, textStatus, errorThrown){
					$("#workspaceList").html(textStatus+"(HTTP-" + xhr.status + "/" + errorThrown);
				}
			}); //end ajax
		}); //end click
		
		
		$("#not").click(function(){
			alert('준비중'); return false;
		});
		
	}); //end ready

</script>


	<div id="header">
		<h1 class="myhidden">JYPROJECT</h1><!-- ## -->
		<div class="jyp_top" style="z-index:1000">
		
			<div class="row">
			<div class="jyp_header jyp_gnb gnb1 col-sm-2"><a
				 href="${pageContext.request.contextPath}/home.members" style="padding-left: 43px; font-size: 20px; color: white;">
				  <img src="<%=request.getContextPath()%>/inc/images/jyproject_logo.png" alt="jyproject" style="height: 55px; margin-top: -10px;"/></a></div> <!-- end gnb1 col-sm-3-->
			<div class="jyp_header jyp_gnb gnb2 col-sm-6">
				<form action="#"  method="get">
			    		<fieldset>
			    		<legend  class="myhidden">search</legend> 
			    		<label for="search" > <span class="glyphicon glyphicon-search"></span></label><input type="search" name="search"  id="search"  title="검색어 입력창" placeholder="워크스페이스를 검색해주세요."/> 
			    		</fieldset>
			    </form>
			</div><!-- end gnb2 col-sm-3-->
			<div class="jyp_header jyp_gnb gnb3 col-sm-2">
				<!-- <select >
					<option value="default">워크스페이스1</option>
				</select> -->
				 <div class="dropdown">
				   <!--  <button class="btn btn-default dropdown-toggle" type="button" id="ws_list" data-toggle="dropdown">워크스페이스 -->
				    <button  id="ws_list" data-toggle="dropdown">${ws_name} <span class="caret"></span></button>
				  
				    <ul class="dropdown-menu" role="menu" aria-labelledby="ws_list" style=" max-height: 230px; height: auto; overflow-x: hidden; overflow-y: auto;">
				      <li role="presentation">
				      	<a role="menuitem" tabindex="-1" href="<%=request.getContextPath()%>/ws_edit_view.ws?ws_no=${ws_no}">
				      	<strong style="font-size:16px;">${ws_name}</strong><br/>
				      			<span style="color:gray">워크스페이스 설정</span></a></li>
				      <li role="presentation" class="divider"></li>
				      
				      <!-- WS_HOME 에서 내가속한  워크스페이스 리스트 불러와서 가져오기  -->
				    
				      
				      <li><p style="color: #1f5c87;" id="wrokList">워크스페이스 목록</p><div id="workspaceList"></div></li>
				     
				      <li role="presentation" class="divider"></li>
				      <li role="presentation"><a role="menuitem" tabindex="-1" href="<%=request.getContextPath()%>/ws_add_view2.ws">
				      	<span class="glyphicon glyphicon-plus"></span>워크스페이스 추가하기</a></li>
				    </ul>
				  </div><!-- end dropdown -->
				
			</div> <!-- end gnb3 col-sm-4-->
			
			<div class="jyp_header jyp_gnb gnb4 col-sm-2" style="width: 11.66666666%;">
			<a href="<%=request.getContextPath()%>/mypage_view.members">
				<img src="${pageContext.request.contextPath}/upload/${jyp_profile}" alt="프로필" 
					style="margin-bottom: 5px; width: 36px; height: 46px;" title="${jyp_profile}"></a>    
			</div><!-- end gnb4 col-sm-2-->
			</div><!-- end row -->
			 </div> <!-- end jyp top -->
			
			
			
		<nav class="jyp_left" style="z-index:1000">	
			<h2 class="myhidden">주메뉴</h2>
			    <ul>
			      <li> <a href="<%=request.getContextPath()%>/update_home.up"
			      	 class="glyphicon glyphicon-bell" title="${jyp_profile}"></a></li> <!-- 알람 -->
			      <li><a href="<%=request.getContextPath()%>/calendar_home.cal?btn=1"
			      	class="glyphicon glyphicon-time" title="전체개요"></a></li><!-- 전체개요 -->
			   <%--    <li><a href="<%=request.getContextPath()%>/project/p_home.jsp"  --%>
			   	  <li><a href="<%=request.getContextPath()%>/pro_list.pro"
			      	class="glyphicon glyphicon-folder-open" title="프로젝트"></a></li> <!-- 프로젝트 -->
			      <li><a href="<%=request.getContextPath()%>/member_list.mem"
			      	class="glyphicon glyphicon-user" title="멤버"></a></li> <!-- 주소록 -->
			      <li id ="not"><a href="<%=request.getContextPath()%>/chat_home.chat"
			      	 class="glyphicon glyphicon-comment" title="대화"></a></li> <!-- 메신저 -->
			      
			    </ul>
		  
		</nav>
		
		
		

 	</div><!-- end <div id="header"> -->
	<!-- 										 -->
	<!-- 										 -->
	<!-- 										 -->
	