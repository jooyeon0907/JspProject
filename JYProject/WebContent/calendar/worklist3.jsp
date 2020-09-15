<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<!-- 초기 버전 -->
				
				<!-- start 업무일정 --> <!-- start 업무일정 --> <!-- start 업무일정 --> <!-- start 업무일정 --> 
				<div style="max-height: 120px; height: auto; overflow-x: hidden; overflow-y: auto;">
				<c:forEach var="w" items="${list}"  varStatus="status">
					<!-- 0000-00-00형식으로 날짜를 맞추어 work_start와 비교하기 위함. -->
					<c:choose>
						<c:when test="${day<10}"><c:set var="workD"  value="${wday}0${day}"></c:set></c:when>  
						<c:otherwise><c:set var="workD"  value="${wday}${day}"></c:set></c:otherwise>
					</c:choose>
					<!-- 업무 시작일  매치되면 출력   -->  <!--  && workD <=w.work_end 하고 싶은데 문자열상태여서 숫자로 바꿔저서 해야됨 .. -->
					<%-- <fmt:formatNumber value="${workD}" type="number" var="workD2" />
					<fmt:formatNumber value="${w.work_end}" type="number" var="w.work_end2" /> --%>
					

					<c:if test="${w.work_start == workD}">  <!-- 업무의 날짜가 해당 날짜와 일치하면 (년-월-일 형식으로 맞췄음)  -->									
					
					 <div style="margin-bottom: 5px; background-color:#D0F5A9; cursor:pointer;"
					 	  onclick="location.href ='${pageContext.request.contextPath}/work_detail.work?p_no=${w.p_no}&&work_no=${w.work_no}';" >
					    <c:choose>
					    <c:when test="${fn:length(w.work_content) gt 10}">${fn:substring(w.work_content,0,20)}... <%@include file="start.jsp" %> </c:when>
						<c:otherwise>${w.work_content} <%@include file="start.jsp" %>  </c:otherwise>
						</c:choose>
					 </div>
					</c:if> <!-- end 업무내용  --> <!-- end 업무내용  --> <!-- end 업무내용  --> <!-- end 업무내용  -->
					<!-- end 업무 시작일 매치되면 출력  -->	
					
					<!-- 업무 마감일 매치되면 출력  -->	
					<c:if test="${w.work_end == workD}"> <!-- 업무의 날짜가 해당 날짜와 일치하면 (년-월-일 형식으로 맞췄음)  -->
					 <div style="margin-bottom: 5px; background-color:#F7BE81; cursor:pointer;"
					 	  onclick="location.href ='${pageContext.request.contextPath}/work_detail.work?p_no=${w.p_no}&&work_no=${w.work_no}';" >
					    <c:choose>
							<c:when test="${fn:length(w.work_content) gt 10}">${fn:substring(w.work_content,0,20)}... <%@include file="end.jsp" %></c:when>
							<c:otherwise>${w.work_content} <%@include file="end.jsp" %></c:otherwise>
						</c:choose>
					 </div>
					</c:if> <!-- end 업무내용  --> <!-- end 업무내용  --> <!-- end 업무내용  --> <!-- end 업무내용  -->
					<!-- end 업무 마감일 매치되면 출력  -->	
				</c:forEach>
				<!-- end 업무일정 --> <!-- end 업무일정 --> <!-- end 업무일정 --> <!-- end 업무일정 -->
				</div><!-- end 일정리스트 감싼 div -->
				