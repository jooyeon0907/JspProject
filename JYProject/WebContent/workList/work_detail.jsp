<%@page import="com.jooyeon.dto.P_Work_DTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix ="c" uri="http://java.sun.com/jsp/jstl/core" %> <!-- 태그 라이브러리 -->   
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> <!-- jstl 줄바꿈 -->


<div class="container myconatiner2 w_detail" style="margin-left: 12%; width: 80%;">
<%

P_Work_DTO dto = (P_Work_DTO)request.getAttribute("dto");

%>
<%
pageContext.setAttribute("br", "<br/>");
pageContext.setAttribute("cn", "\n");
%>
	<h3 class="work_title" style="border-left: 9px solid ${dto.work_label}">
		업무 상세보기 <c:if test="${dto.work_com=='y'}"><span style="color:#FA5858;">(완료된 업무)</span></c:if></h3>
	<%-- <div class="panel" >
		 <div  class="panel-body">
	  		<span class="glyphicon glyphicon-list-alt">업무 제목</span>
	  		<p>${dto.}</p>
		</div>
	</div>	 --%>
	<div class="panel"  >
	  <div  class="panel-body">
	  		<span class="glyphicon glyphicon-list-alt">작성자</span> 
			<p>${writer}</p>
		</div>
	</div>			
		
	<div class="panel"  >
	  <div  class="panel-body">
	  	<span class="glyphicon glyphicon-list-alt">업무 내용</span> 
	  	<p>${fn:replace (dto.work_content,cn,br)}</p>
	     <%-- <p>${dto.work_content }</p> --%>
	  </div>	
	</div>
	
	<div class="panel"  >
	  <div  class="panel-body">
	  	<span class="glyphicon glyphicon-list-alt">업무 시작일</span>
	  	<p>${dto.work_start}</p>
	  </div> 
	</div>	
	
	<div class="panel"  >
	  <div  class="panel-body">
	  	<span class="glyphicon glyphicon-list-alt">업무 마감일</span>
	  	<p>${dto.work_end}</p>
	  </div> 
	</div>	
	
	<div class="panel"  >
	  <div  class="panel-body">
	  	<span class="glyphicon glyphicon-list-alt">미리알림(개인설정)</span>
	  	<p>${fn:substring(dto.work_alarm,0,16)}</p>
	  </div> 
	</div>	
	
	<div class="panel"  >
	  <div  class="panel-body">
	  	<span class="glyphicon glyphicon-list-alt">첨부된 파일</span>
	  	<c:if test="${dto.work_file!='no.jpg'}">
	  		<p data-toggle="modal" data-target="#myFile"><img src="${pageContext.request.contextPath}/upload/${dto.work_file}" alt="${dto.work_file}" style="width:155px;"> </p>
	  		 <!-- Trigger the modal with a button -->
			  <!-- Modal --> <!-- Modal --> <!-- Modal --> <!-- Modal --> <!-- Modal -->
			  <div class="modal fade" id="myFile" role="dialog">
			    <div class="modal-dialog">
			    
			      <!-- Modal content-->
			      <div class="modal-content">
			        <div class="modal-header">
			          <button type="button" class="close" data-dismiss="modal">&times;</button>
			          <h4 class="modal-title">${dto.work_file}</h4>
			        </div>
			        <div class="modal-body" style=" max-height: 600px;height: auto ;max-width: 800px; width: auto; overflow-x: auto; overflow-y: auto;">
			          <p class="text-center"><img src="${pageContext.request.contextPath}/upload/${dto.work_file}" alt="${dto.work_file}"> </p>
			          
			        </div> </div>  </div> </div>
			   <!-- end Modal --> <!-- end Modal --> <!-- end Modal --> <!-- end Modal -->
	  	</c:if>	
	  </div>     
	</div>	
	
	<div class="panel"  >
	  <div  class="panel-body">
	  	<span class="glyphicon glyphicon-user">함께하는 멤버</span>
	  	<%@ include file="work_member.jsp" %> <!-- 업무멤버 추가 페이지 -->
	  	<p> <c:forEach var="pwmem" items="${memList}" varStatus="status">
	  			${pwmem.jyp_name} <c:if test="${!status.last}">,</c:if>
	  		</c:forEach></p>
	  </div> 
	  
	 <div class="panel"  >
	  <div  class="panel-body">
	  	<span class="glyphicon glyphicon-user">업무확인한 멤버</span>
	  	<p> <c:forEach var="pwmem" items="${memList}" varStatus="status">
	  			 <c:if test="${pwmem.work_check=='y'}">
	  				${pwmem.jyp_name} ,
	  			</c:if>
	  		</c:forEach></p>
	  </div> 
	</div>	
	
	<div class="text-right"    >
		 <a href="<%=request.getContextPath()%>/work_edit_view.work?work_no=<%=dto.getWork_no()%>&&p_no=<%=dto.getP_no()%>" 
		 	 class="btn btn-danger" >업무 수정</a> 
		 <a href="<%=request.getContextPath()%>/work_delete.work?work_no=<%=dto.getWork_no()%>&&p_no=<%=dto.getP_no()%>&&page=2"  class="btn btn-danger" >업무 삭제</a>    
		<c:if test="${dto.work_com=='y'}">
			<a href="<%=request.getContextPath()%>/unCheck.work?work_no=<%=dto.getWork_no()%>&&p_no=<%=dto.getP_no()%>&&page=2"  class="btn btn-danger" >업무 미완료</a>    
		</c:if>
		<c:if test="${dto.work_com=='n'}">
		 <a href="<%=request.getContextPath()%>/check.work?work_no=<%=dto.getWork_no()%>&&p_no=<%=dto.getP_no()%>&&page=2"  class="btn btn-danger" >업무 완료</a>    
		</c:if>
		 <a href="<%=request.getContextPath()%>/list.work?p_no=<%=dto.getP_no()%>"  class="btn btn-info" >업무목록보기</a> 
	</div>						 

</div>
</div><!-- end container -->

