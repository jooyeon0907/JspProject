<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 

				
				<!-- start 업무일정 --> <!-- start 업무일정 --> <!-- start 업무일정 --> <!-- start 업무일정 --> 
				<div style="max-height: 120px; height: auto; overflow-x: hidden; overflow-y: auto;">
				<c:forEach var="w" items="${list}"  varStatus="status">
				<!-- 업무라벨색상 - 선택안되어있으면 회색으로 (#F2F2F2) -->
				<c:choose>
					<c:when test="${w.work_label eq '#fdfdfd00'}"><c:set var="color" value="#F2F2F2"/></c:when>
					<c:otherwise><c:set var="color" value="${w.work_label}"/></c:otherwise>
				</c:choose>
				<!-- end 업무라벨 색상 -->
				
					<!--  '-'빼고 숫자로 조합하기 --><!--  '-'빼고 숫자로 조합하기 --><!--  '-'빼고 숫자로 조합하기 --><!--  '-'빼고 숫자로 조합하기 -->
					<c:choose>
						<c:when test="${day<10}"><c:set var="workDN"  value="${wdayN}0${day}"></c:set></c:when>  
						<c:otherwise><c:set var="workDN"  value="${wdayN}${day}"></c:set></c:otherwise>
					</c:choose>
					
					<c:set var="startY" value="${fn:substring(w.work_start,0,4)}"></c:set>  <c:set var="endY" value="${fn:substring(w.work_end,0,4)}"></c:set>
					<c:set var="startM" value="${fn:substring(w.work_start,5,7)}"></c:set>  <c:set var="endM" value="${fn:substring(w.work_end,5,7)}"></c:set>
					<c:set var="startD" value="${fn:substring(w.work_start,8,10)}"></c:set> <c:set var="endD" value="${fn:substring(w.work_end,8,10)}"></c:set>
					<c:set var="startDate" value="${startY}${startM}${startD}"></c:set>		<c:set var="endDate" value="${endY}${endM}${endD}"></c:set>
					<!-- end'-'빼고 숫자로 조합하기 -->	<!-- end'-'빼고 숫자로 조합하기 -->	<!-- end'-'빼고 숫자로 조합하기 -->	<!-- end'-'빼고 숫자로 조합하기 -->							
					
					<c:if test="${startDate<=workDN && workDN<=endDate && not empty startDate && not empty endDate}">
					 <div style="margin-bottom: 5px; background-color:${color}; cursor:pointer;" title="${fn:substring(w.work_content,0,10)}"
					 	  onclick="location.href ='${pageContext.request.contextPath}/work_detail.work?p_no=${w.p_no}&&work_no=${w.work_no}';" >				  
						 <c:choose>
						 	<c:when test="${startDate==workDN}">
							 	<c:choose>
								    <c:when test="${fn:length(w.work_content) gt 10}"><span class="glyphicon glyphicon-list-alt"></span>${fn:substring(w.work_content,0,15)}...</c:when>
									<c:otherwise><span class="glyphicon glyphicon-list-alt"></span>${w.work_content}</c:otherwise>
								</c:choose>	  
						 	</c:when>
						 	
						 	<c:otherwise>&nbsp;</c:otherwise>
						 </c:choose>
						  	
					 </div>
					</c:if><!-- end 업무시작~업무마감  --> 
					<!-- end 업무시작~업무마감  --> <!-- end 업무시작~업무마감  --> <!-- end 업무시작~업무마감  --> <!-- end 업무시작~업무마감  --> 
					   
					
					<!-- 시작일만 선택된 경우  --><!-- 시작일만 선택된 경우  --><!-- 시작일만 선택된 경우  --><!-- 시작일만 선택된 경우  -->   
					<c:if test="${startDate==workDN && not empty startDate && empty endDate}">
					
					 <div style="margin-bottom: 5px; background-color:${color}; cursor:pointer;  margin-right:5px; margin-left:5px;"
					 	  title="${fn:substring(w.work_content,0,10)}"
					 	  onclick="location.href ='${pageContext.request.contextPath}/work_detail.work?p_no=${w.p_no}&&work_no=${w.work_no}';" >
						<c:choose>
						    <c:when test="${fn:length(w.work_content) gt 10}"><span class="glyphicon glyphicon-list-alt"></span>${fn:substring(w.work_content,0,15)}... <span style="color:#58ACFA;">시작일</span></c:when>
							<c:otherwise><span class="glyphicon glyphicon-list-alt"></span>${w.work_content} <span style="color:#58ACFA;">시작일</span></c:otherwise>
						</c:choose>	  
					 </div>
					</c:if>
					
					<!-- 마감일만 선택된 경우 --><!-- 마감일만 선택된 경우 --><!-- 마감일만 선택된 경우 --><!-- 마감일만 선택된 경우 --><!-- 마감일만 선택된 경우 -->
					<c:if test="${endDate==workDN && not empty endDate && empty startDate}">
					 <div style="margin-bottom: 5px; background-color:${color}; cursor:pointer;  margin-right:5px; margin-left:5px;"
					 	  title="${fn:substring(w.work_content,0,10)}"
					 	  onclick="location.href ='${pageContext.request.contextPath}/work_detail.work?p_no=${w.p_no}&&work_no=${w.work_no}';" >
						<c:choose>
						    <c:when test="${fn:length(w.work_content) gt 10}"><span class="glyphicon glyphicon-list-alt"></span>${fn:substring(w.work_content,0,15)}...<span style="color:#FA5858;">마감일</span></c:when>
							<c:otherwise><span class="glyphicon glyphicon-list-alt"></span>${w.work_content} <span style="color:#FA5858;">마감일</span></c:otherwise>
						</c:choose>	  
					</div>
					</c:if>
					
					
					
				</c:forEach>
				<!-- end 업무일정 --> <!-- end 업무일정 --> <!-- end 업무일정 --> <!-- end 업무일정 -->
				</div><!-- end 일정리스트 감싼 div -->
				