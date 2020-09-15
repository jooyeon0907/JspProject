<%@page import="com.jooyeon.dto.WS_Invitation_DTO"%>
<%@page import="com.jooyeon.dao.JYP_WS_DAO"%>
<%@page import="com.jooyeon.dao.JYP_Member_DAO"%>
<%@page import="com.jooyeon.dao.WS_Invitation_DAO"%>
<%@page import="com.jooyeon.dto.JYP_WS_DTO"%>
<%@page import="com.jooyeon.dto.JYP_Member_DTO"%>
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
<!-- 	<h3 class="panel-heading">ajax search TEST</h3>
		
		<div class="panel-body">
			<input type="text" id="jyp_email" 
				placeholder="초대할 멤버의 이메일을 검색합니다." class="form-control"/>
			<input type="button" id="search" value="검색"  class="btn btn-info" />
			
		</div>
		
		<div class="result r1">
			검색 결과창 나오는 곳 
		</div>
		
		<script>
		$(function(){
			$("#search").click(function(){
				$.ajax({
					url:"${pageContext.request.contextPath}/accept_search.acc",
					type:"get", dataType:"text",
					data :{"jyp_email": $("#jyp_email").val() },
					success:function(data){
						console.log(data);
						 console.log(typeof(data));
						 $(".result.r1").html(data);
					/* 	$(".result.r1").html('검색한 이메일의 회원이 존재하지 않습니다.');
						if($data.equals("")){
							$(".result.r1").html('검색한 이메일의 회원이 존재하지 않습니다.');
						}else{ $(".result.r1").html(data); }
						 */
					},
					error:function( xhr, textStatus, errorThrown){
						$(".result.r1").html(textStatus +"(HTTP - "+ xhr.status + "/" +errorThrown)
					}
				});
			});
		});
		</script>
		 
		 -->
		
<%--  		<h3 class="panel-heading">초대리스트에 등록</h3>
		<%
		JYP_Member_DTO dto_send = new JYP_Member_DTO();
		JYP_Member_DTO dto_get = new JYP_Member_DTO();
		JYP_Member_DAO dao_mem = new JYP_Member_DAO();
		
		JYP_WS_DTO dto_ws = new JYP_WS_DTO();
		JYP_WS_DAO dao_ws = new JYP_WS_DAO();
		WS_Invitation_DAO dao_invi = new WS_Invitation_DAO();
		
		
		//초대보내는 유저 정보(현재 유저)
		String send_email = "44@44"; //session에 저장된 데이터 불러오기
		dto_send.setJyp_email(send_email);
		dto_send = dao_mem.userinfo(dto_send);
		//초대된 유저 정보 (검색한 이메일로 데이터 넣기 )
		String get_email = "333@333";
		dto_get.setJyp_email(get_email);
		dto_get = dao_mem.userinfo(dto_get);
		//워크스페이스 유저
		int ws_no = 2; //session에 저장된 데이터 불러오기
		dto_ws.setWs_no(ws_no);
		dto_ws = dao_ws.ws_info(dto_ws);
		
		//등록되어 있는 유저인지 체크
		WS_Invitation_DTO dto_invi = new WS_Invitation_DTO();
		dto_invi.setWs_no(ws_no);
		dto_invi.setGet_email(get_email);
		int check = dao_invi.check(dto_invi);
		
		if(check>0){
			out.println("이미 초대보낸 회원입니다.");
		}else{
			//초대리스트 등록하기 
			int result = dao_invi.ws_invitation(dto_send, dto_get, dto_ws);  
			if(result>0){out.println("초대리스트등록 완료");}
			else{out.println("초대리스트등록 ERROR");}
		}
		%>
	
		  --%>
		 
<%-- 		
		<h3 class="panel-heading">초대리스트에 등록되어있는지 확인하기</h3>
		<%
		JYP_Member_DTO dto = new JYP_Member_DTO();
		WS_Invitation_DAO dao = new WS_Invitation_DAO();
		
		String jyp_email="333@333";
		dto.setJyp_email(jyp_email);
		out.println(dao.ws_inviList(dto));
		
		
		
		%> --%>
		
		<%-- <p><a href='${pageContext.request.contextPath}/ws_Invitation.ws' class='btn btn-info' >초대하기</a></p> --%>
		
		
<%-- 		
		<h3 class="panel-heading">초대리스트 삭제</h3>
		<%
		WS_Invitation_DTO dto = new WS_Invitation_DTO();
		WS_Invitation_DAO dao = new WS_Invitation_DAO();
		
		int invi_no = 2;
		dto.setInvi_no(invi_no);
		
		int result = dao.ws_inviDelete(dto); //삭제하기 
		if(result>0){out.println("초대리스트 삭제 완료");}
		else{out.println("초대리스트 삭제 ERROR");}
		
		
		%>
		 --%>
		
<%-- 		<h3 class="panel-heading">초대리스트 수락 상태로 바꾸기</h3>
		<%
		WS_Invitation_DTO dto = new WS_Invitation_DTO();
		WS_Invitation_DAO dao = new WS_Invitation_DAO();
		
		int invi_no = 1;
		dto.setInvi_no(invi_no);
		
		
		int result = dao.ws_accept(dto); // 
		if(result>0){out.println("초대리스트 수락 완료");}
		else{out.println("초대리스트 수락 ERROR");}
		
		%>
		 --%>
		
		
		
	</div><!--end container-->
</body>
</html>

