<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix ="c" uri="http://java.sun.com/jsp/jstl/core" %> <!-- 태그 라이브러리 -->
<%@ include file = "/inc/work_header.jsp" %>    
<!--            end header            -->
<!--            end header            -->

<div class="jyp_tab time">
	<ul class="nav nav-tabs">
	  <li class="active"><a data-toggle="tab" href="#menu1">업무</a></li>
	  <li><a data-toggle="tab" href="#menu2">피드백(준비중)</a></li>
	</ul>
	
	<div class="tab-content">
	  
	  <div id="menu1" class="tab-pane fade in active">
	     <%@ include file="alarmHome.jsp" %> 
	    <p></p>
	  </div>
	  <div id="menu2" class="tab-pane fade">
	    <div class="container" style="width: 80%;">
	    <h3>피드백(준비중)</h3>
	   </div>
	  </div>
	</div>
</div> <!-- tab -->


	
	
	
	
<!--            start footer          -->
<!--            start footer          -->
<!--            start footer          -->
</body>
</html>

