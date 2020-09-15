<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 

<!-- 
<c:set var="y"  value="${fn:substring(w.work_start,0,4)}"></c:set>
<c:set var="m"  value="${fn:substring(w.work_start,4,7)}"></c:set>
<c:set var="d"  value="${fn:substring(w.work_start,8,10)}"></c:set>
<c:set var="start"  value="${y}${m}${d}"></c:set>
 -->
				
				<!-- start 업무일정 --> <!-- start 업무일정 --> <!-- start 업무일정 --> <!-- start 업무일정 --> 
				<div style="max-height: 120px; height: auto; overflow-x: hidden; overflow-y: auto;">
				<c:forEach var="w" items="${list}"  varStatus="status">
					<!-- 0000-00-00형식으로 날짜를 맞추어 work_start와 비교하기 위함. -->
					<c:choose>
						<c:when test="${day<10}"><c:set var="workD"  value="${wday}0${day}"></c:set></c:when>  
						<c:otherwise><c:set var="workD"  value="${wday}${day}"></c:set></c:otherwise>
					</c:choose>
					<!-- 업무 시작일  매치되면 출력   -->
					 <!--  start 문자열 잘라서 숫자형태로 조합 -->
					 <!--  start 문자열 잘라서 숫자형태로 조합 -->
					<c:set var="s_y"  value="${fn:substring(w.work_start,0,4)}"></c:set> <c:set var="e_y"  value="${fn:substring(w.work_end,0,4)}"></c:set>
					<c:set var="s_m"  value="${fn:substring(w.work_start,5,7)}"></c:set> <c:set var="e_m"  value="${fn:substring(w.work_end,5,7)}"></c:set>
					<c:set var="s_d"  value="${fn:substring(w.work_start,8,10)}"></c:set><c:set var="e_d"  value="${fn:substring(w.work_end,8,10)}"></c:set>
					<c:set var="start"  value="${s_y}${s_m}${s_d}"></c:set> 			 <c:set var="end"  value="${e_y}${e_m}${e_d}"></c:set>     
					<c:choose>
						<c:when test="${day<10}"><c:set var="w_d"  value="${year}0${month+1}0${day}"></c:set></c:when>  
						<c:otherwise><c:set var="w_d"  value="${year}${month+1}${day}"></c:set>   </c:otherwise>
					</c:choose>
				
					<!-- end 문자열 잘라서 숫자형태로 조합 -->
					<!-- end 문자열 잘라서 숫자형태로 조합 -->
					<c:if test="${start<=w_d && w_d<=end}"> <!-- 업무의 날짜가 해당 날짜와 일치하면 (년-월-일 형식으로 맞췄음)  -->									
				
					 <div style="margin-bottom: 5px; background-color:#D0F5A9; cursor:pointer;"
					 	  onclick="location.href ='${pageContext.request.contextPath}/work_detail.work?p_no=${w.p_no}&&work_no=${w.work_no}';" >
					    <c:choose>
					    <c:when test="${fn:length(w.work_content) gt 10}">${fn:substring(w.work_content,0,20)}...<%--  <%@include file="start.jsp" %> --%> </c:when>
						<c:otherwise>${w.work_content} <%-- <%@include file="start.jsp" %>   --%></c:otherwise>
						</c:choose>
					 </div>
					</c:if> <!-- end 업무내용  --> <!-- end 업무내용  --> <!-- end 업무내용  --> <!-- end 업무내용  -->
					<!-- end 업무 시작일 매치되면 출력  -->	
					
					<%-- <!-- 업무 마감일 매치되면 출력  -->	
					<c:if test="${w.work_end == workD}"> <!-- 업무의 날짜가 해당 날짜와 일치하면 (년-월-일 형식으로 맞췄음)  -->

					 <div style="margin-bottom: 5px; background-color:#F7BE81; cursor:pointer;"
					 	  onclick="location.href ='${pageContext.request.contextPath}/work_detail.work?p_no=${w.p_no}&&work_no=${w.work_no}';" >
					    <c:choose>
							<c:when test="${fn:length(w.work_content) gt 10}">${fn:substring(w.work_content,0,20)}... <%@include file="end.jsp" %></c:when>
							<c:otherwise>${w.work_content} <%@include file="end.jsp" %></c:otherwise>
						</c:choose>
					 </div>
					</c:if> <!-- end 업무내용  --> <!-- end 업무내용  --> <!-- end 업무내용  --> <!-- end 업무내용  -->
					<!-- end 업무 마감일 매치되면 출력  -->	 --%>
				</c:forEach>
				<!-- end 업무일정 --> <!-- end 업무일정 --> <!-- end 업무일정 --> <!-- end 업무일정 -->
				</div><!-- end 일정리스트 감싼 div -->
				