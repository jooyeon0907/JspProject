<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix ="c" uri="http://java.sun.com/jsp/jstl/core" %> <!-- 태그 라이브러리 -->
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> <!-- jstl 줄바꿈 -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<%-- 
  	<%
		UpdateDAO dao_work = new UpdateDAO();
	 	P_Work_DTO dto_work = new P_Work_DTO();
	 	ArrayList<P_Work_DTO> list_work = new ArrayList<P_Work_DTO>();
	 	
	 	int ws_no_work = 1; ///session값 불러오기
	 	int jyp_no_work =4;//session 값 불러오기
	 	dto_work.setWs_no(ws_no_work);  dto_work.setJyp_no(jyp_no_work);
		list_work = dao_work.workDate(dto_work);

 		pageContext.setAttribute("list_work", list_work);
 	%>  --%>
 	<!-- 
 	    border: 0 none; color: #27b6ba; border-bottom: 2px solid;
    
    
    p.work_content {
    border: 0 none;
    color: #27b6ba;
    border-bottom: 2px solid;
    
    
        height: 300px; overflow-x: hidden; overflow-y: auto;
}



 	 -->
 

 		<div class="scroll panel-body" style=" max-height: 300px; height: auto; overflow-x: hidden; overflow-y: auto;">
 
 	<%-- 	<c:forEach  begin="1" end="4" varStatus="">  --%>
 		<c:forEach  var="list" items="${list_work}"  varStatus="status">
		<!-- 업무시작일이 되었다면  --><!-- 업무시작일이 되었다면  --><!-- 업무시작일이 되었다면  -->
		<!-- 업무시작일이 되었다면  --><!-- 업무시작일이 되었다면  --><!-- 업무시작일이 되었다면  -->
		<c:if test="${list.start_dday==0}">
			<div class="row" style="margin:30px; border: 0 none; border-bottom: 1px solid;  cursor:pointer;"
				onclick="location.href ='${pageContext.request.contextPath}/work_detail.work?p_no=${list.p_no}&&work_no=${list.work_no}';" 
				onmouseover="this.style.backgroundColor='#F2F2F2'" onmouseout="this.style.backgroundColor='#FFFFFF'" >
				<div class="col-sm-2">
					<div
				 	style=" width: 60px; height: 60px; font-weight: bold; border-radius: 50%; 
			    			background-color: #A9F5F2; color: #FFFFFF; 
			    			font-size: 18px; text-align: center; line-height: 62px;"
				 	>Dday</div> <!-- 디데이 다가올때 (start_date>=2 && start_date<0 일 때) -->	
				</div><!-- end col-sm-3 -->
				<div class="col-sm-2">
					<p style="font-weight:bold; padding-top: 16px;">시작일</p>
				</div><!-- end col-sm-1 -->
				<div class="col-sm-6 text-center">
					<div class="row"> 
						<p>
							${list.work_content} 업무의 시작일이 되었습니다.
							</p>
					</div><!-- end row -->
					<div class="row">
						<span class="glyphicon glyphicon-folder-open" style="color: #27b6ba;">
								${list.p_name}>${list.wl_name}</span>
					</div><!-- end row -->
				</div><!-- end col-sm-6 -->
				<div class="col-sm-2 text-center">
					<p style="padding-top: 16px;">${fn:substring(list.work_start,0,16)}</p>
				</div><!-- end col-sm-2 -->
			</div><!-- end row -->
		</c:if> 
		<!-- end 업무시작일이 되었다면  --><!-- end 업무시작일이 되었다면  --><!-- end 업무시작일이 되었다면  -->
		<!-- end 업무시작일이 되었다면  --><!-- end 업무시작일이 되었다면  --><!-- end 업무시작일이 되었다면  -->
			
		<!-- start 마감일이 지났다면 --><!-- start 마감일이 지났다면 -->	<!-- start 마감일이 지났다면 -->
		<!-- start 마감일이 지났다면 --><!-- start 마감일이 지났다면 -->	<!-- start 마감일이 지났다면 -->		
		<c:if test="${list.end_dday<0 && list.end_dday>=-7}">
			<div class="row" style="margin:30px; border: 0 none; border-bottom: 1px solid; cursor:pointer;"
				onclick="location.href ='${pageContext.request.contextPath}/work_detail.work?p_no=${list.p_no}&&work_no=${list.work_no}';" 
				onmouseover="this.style.backgroundColor='#F2F2F2'" onmouseout="this.style.backgroundColor='#FFFFFF'">
				<div class="col-sm-2">
					<div 
				 	style=" width: 60px; height: 60px; font-weight: bold; border-radius: 50%; 
			    			background-color: #08088A; color: #FFFFFF; 
			    			font-size: 18px; text-align: center; line-height: 62px;"
				 	>D+${-(list.end_dday)}</div>
				</div><!-- end col-sm-3 -->
				<div class="col-sm-2">
					<p style="font-weight:bold;color:red; padding-top: 16px;">마감일 ${-(list.end_dday)}일 지남.</p>
				</div><!-- end col-sm-1 -->
				<div class="col-sm-6 text-center">
					<div class="row"> 
						<p> ${list.work_content} </p>
					</div><!-- end row -->
					<div class="row">
						<span class="glyphicon glyphicon-folder-open" style="color: #27b6ba;">
								${list.p_name}>${list.wl_name}</span>
					</div><!-- end row -->
				</div><!-- end col-sm-6 -->
				<div class="col-sm-2 text-center">
					<p style="padding-top: 16px;">${fn:substring(list.work_end,0,16)}</p>
				</div><!-- end col-sm-2 -->
			</div><!-- end row -->
		</c:if> 
		<!-- end 마감일이 지났다면 -->	<!-- end 마감일이 지났다면 -->	<!-- end 마감일이 지났다면 -->		
		<!-- end 마감일이 지났다면 -->	<!-- end 마감일이 지났다면 -->	<!-- end 마감일이 지났다면 -->	
		
			
		</c:forEach>
		</div>


	

