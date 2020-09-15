<%@page import="com.jooyeon.dto.JYP_Member_DTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file = "/inc/login_header.jsp" %>
<!--            end header            -->
<!--            end header            -->
<!--            end header            -->
<%JYP_Member_DTO dto = (JYP_Member_DTO)request.getAttribute("dto");  %>
	<div class="container" style="margin-top:5%; max-wirdth:400px; wirdth:100%; text-align:center;">
	<h1 style="color:black">회원가입이 완료되었습니다.</h1>
	<p>반갑습니다. <%=dto.getJyp_email()%>님 !</p>
	
	<a href="<%=request.getContextPath()%>/login_view.members" class="btn btn-default">로그인하러 가기</a>
	<a class="btn btn-default">홈으로 </a>
	</div>


<!--            start footer          -->
<!--            start footer          -->
<!--            start footer          -->
<%@ include file = "/inc/footer.jsp" %>