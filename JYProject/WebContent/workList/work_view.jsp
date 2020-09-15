<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix ="c" uri="http://java.sun.com/jsp/jstl/core" %> <!-- 태그 라이브러리 -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file = "/inc/work_header.jsp" %>    
<!--            end header            -->
<!--            end header            -->
       
<div class="jyp_tab time backDiv" style="position:relative">
	 <p class="backbtn btn  btn-info" style="width: 126px; position: absolute; left: 20px; top: 3px; z-index: 100;"
		 onclick="history.go(-1);">←뒤로가기</p>
	<ul class="nav nav-tabs">
	  <li <c:if test="${editRe==1}">class="active"</c:if>><a data-toggle="tab" href="#menu1">업무보기</a></li>
	  <li <c:if test="${editRe==2}">class="active"</c:if>><a data-toggle="tab" href="#menu2">코멘트</a></li>
	</ul>
	
	<div class="tab-content">
	  <div id="menu1" class="tab-pane fade <c:if test="${editRe==1}">in active</c:if>">
	     <%@ include file="work_detail.jsp" %> 
	  </div>
	  <div id="menu2" class="tab-pane fade <c:if test="${editRe==2}">in active</c:if>">
	   	 <%@ include file="comments.jsp" %> 
	  </div>
	</div>
</div> <!-- tab -->


	
	
	
	
<!--            start footer          -->
<!--            start footer          -->
<!--            start footer          -->
</body>
</html>

