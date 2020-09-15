 <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %> 
<%@ taglib prefix ="c" uri="http://java.sun.com/jsp/jstl/core" %> <!-- 태그 라이브러리 -->
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> <!-- jstl 줄바꿈 -->

<%--   	<%
 	UpdateDAO dao_alarm = new UpdateDAO();
 	P_Work_DTO dto_alarm = new P_Work_DTO();
 	ArrayList<P_Work_DTO> list_alarm = new ArrayList<P_Work_DTO>();
 	
 	int ws_no_alarm = 1; ///session값 불러오기
 	int jyp_no_alarm =4;//session 값 불러오기
 	dto_alarm.setWs_no(ws_no_alarm); dto_alarm.setJyp_no(jyp_no_alarm);
 	list_alarm = dao_alarm.workAlarm(dto_alarm);
 
 	pageContext.setAttribute("list_alarm", list_alarm);
 	
 //	out.println(list);
 	//미리알림 설정한 날이 디데이라면 (dday = now()) 정보 출력시키기 ( 이건 뷰단에서 )
 	
 	%> 
 --%>

 
 	
 		<div class="scroll" style="max-height: 300px;height: auto; overflow-x: hidden; overflow-y: auto;">
 		
 		<!-- start 알람이 없다면  --> <!-- start 알람이 없다면  --> <!-- start 알람이 없다면  --> <!-- start 알람이 없다면  -->
 		<c:if test="${ empty list_alarm}">
 			<div class="text-center">
 			<p><img src="${pageContext.request.contextPath}/images/null-alarm.png" alt="미리알림없음" width="450"/></p>
 			<p>다가올 알림이 없습니다.</p></div>
 		</c:if>
 		 <!-- end 알람이 없다면  --> <!-- end 알람이 없다면  --> <!-- end 알람이 없다면  --> <!-- end 알람이 없다면  -->
 		<c:forEach  var="list" items="${list_alarm}" varStatus="status">
 			 <c:if test="${list.dday>=0 && list.dday<=2}"> 
			<div class="row work_no${list.work_no}" style="margin:30px; border: 0 none;  border-bottom: 1px solid; cursor:pointer;"
				onclick="location.href ='${pageContext.request.contextPath}/work_detail.work?p_no=${list.p_no}&&work_no=${list.work_no}';" 
				onmouseover="this.style.backgroundColor='#F2F2F2'" onmouseout="this.style.backgroundColor='#FFFFFF'">
				<div class="col-sm-2">
					<p><img src="${pageContext.request.contextPath}/upload/bell.png" alt="미리 알림" ></p>
				</div><!-- end col-sm-2 -->
				
				<div class="col-sm-2">
					<p style="font-weight:bold;">
					<c:choose>
						<c:when test="${list.dday==0}">Dday</c:when>
						<c:when test="${list.dday==1}">D-1</c:when>
						<c:when test="${list.dday==2}">D-2</c:when>
					</c:choose>
					</p>
				</div><!-- end col-sm-2 -->
				
				<div class="col-sm-6 text-center">
					<div class="row">
						<p> ${list.work_content}의 업무 알림 </p>
					</div><!-- end row -->
					<div class="row">
						<span class="glyphicon glyphicon-folder-open" style="color: #27b6ba;">
								${list.p_name}>${list.wl_name}</span>
					</div><!-- end row -->
				</div><!-- end col-sm-6 -->
				
				<div class="col-sm-2 text-center">
					<p>미리알림 날짜</p>
					<p>${fn:substring(list.work_alarm,0,16)}</p>
				</div><!-- end col-sm-2 -->
				
			</div><!-- end row -->
		</c:if> </c:forEach>
		</div>
	
	
	

	
	
	
	
	
	
	

