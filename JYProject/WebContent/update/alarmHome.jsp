<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %> 
<%@ taglib prefix ="c" uri="http://java.sun.com/jsp/jstl/core" %> <!-- 태그 라이브러리 -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
   

 <div class="container" style="width: 80%;">
 
 
 	 <div class="panel" id="accordion">
		   
		    <div class="panel panel-default"><!-- class="panel panel-default" -->
		      <div  class="panel-heading"> <!-- class="panel-heading" -->
		        <h3 class="panel-title"><!-- class="panel-title" -->
		          <a data-toggle="collapse" data-parent="#accordion1" href="#workAlarm">미리알림</a>
		        </h3>
		      </div>
		      <div id="workAlarm" class="panel-collapse collapse in">
		       <!--  <div class="panel-body"> 각 멤버가 설정해논 업무 알림 </div> -->
		       	 <%@ include file="workAlarm.jsp"%>  
		      </div>
		    </div>    
		    
		    <div class="panel panel-default">
		      <div class="panel-heading">
		        <h3 class="panel-title">
		          <a data-toggle="collapse" data-parent="#accordion2" href="#workDate">업무날짜</a>
		        </h3>
		      </div>
		      <div id="workDate" class="panel-collapse collapse in">
		        <div class="row"> 
		        	<h3 style="text-align: center;margin-top: 19px;">프로젝트 시작일/마감일 알림</h3>	
		       		 <%@ include file="date_project.jsp" %>   
		        </div><!-- end row  -->
		        <div class="row">
		       		 <h3 style="text-align: center;">업무 시작일/마감일 알림</h3>	
	        		  <%@ include file="date_work.jsp" %> 
		        </div><!-- end row --> <!-- accordion_alarm.jsp -->
		      </div>
		    </div>    
			
		</div> 
		
		
 </div><!--end container-->



