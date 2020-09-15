<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix ="c" uri="http://java.sun.com/jsp/jstl/core" %> <!-- 태그 라이브러리 -->
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file = "/inc/work_header.jsp" %>
<!--            end header            -->
<!--            end header            -->
<!--            end header            -->

	<script>
		$(function(){
			var yy = $("#yy").val(); console.log('yy : ' + yy);
			var mm = $("#mm").val(); console.log('mm : ' + mm);
			$("#prevBtn").click(function(){ //alert('이전달');
				location.href='${pageContext.request.contextPath}/calendar_home.cal?btn=2&&yy='+yy+'&&mm='+mm;
			}); //end click prevBtn
			
			$("#nextBtn").click(function(){ //alert('다음달');
			location.href='${pageContext.request.contextPath}/calendar_home.cal?btn=3&&yy='+yy+'&&mm='+mm;
			}); //end click nextBtn
			
		}); //end ready
	
	</script>

	<div class="container myconatiner cal" style="background-color:#19160f0a;">
		<h3 class="panel-heading">캘린더 일정관리</h3>
	 	<input type="hidden" id="yy" value="${yy}"/>
	 	<input type="hidden" id="mm" value="${mm}"/>
		<c:set var="year"  value="${year}"></c:set>
		<c:set var="month" value="${month}"></c:set> 	
		<c:set var="day"   value="${day}"></c:set>
		<%-- <c:set var="wday"  value="${wday}"></c:set> --%>
		<table class="table table-stripped" style="background-color: #FFFFFF;">
			<thead>
				<!-- start 년,월 --> <!-- start 년,월 --> <!-- start 년,월 --> <!-- start 년,월 -->
				<tr style="height:50px;">
					<th colspan="7" class="text-center">
						<div class="col-sm-3">
							<input type="button" id="prevBtn" value="◀" class="btn btn-info" style="width: 84px;"/>
						</div><!-- end col-sm-3 -->
						<div class="col-sm-6">
							<p style="margin-bottom:0px;"><span id="yy">${year}</span>년  <span id="mm">${month+1}</span>월 </p>
						</div><!-- end col-sm-6 -->
						<div class="col-sm-3">
							<input type="button" id="nextBtn" value="▶" class="btn btn-info" style="width: 84px;"/>
						</div><!-- end col-sm-3 -->
					</th> 
				</tr>
				<!-- end 년,월 --> <!-- end 년,월 --> <!-- end 년,월 --> <!-- end 년,월 -->
				
				
				<!-- start 요일 --> <!-- start 요일 --> <!-- start 요일 --> <!-- start 요일 -->
				<tr style="height:50px;">
				<c:forEach var="weektitle" items="${weektitle}">
					<th>${weektitle}</th>
				</c:forEach>			
				</tr>
				<!-- end 요일 --> <!-- end 요일 --> <!-- end 요일 --> <!-- end 요일 -->	
			</thead>
			
			
			
			<!-- start days --> <!-- start days --> <!-- start days --> <!-- start days -->
			<tbody>	
				<!-- start blank -->
				<tr style="height:120px;"><c:forEach var="blank" begin="1" end="${startyoil-1}">
					<td >*</td>
				    </c:forEach>			
				
				<!-- day -->
				<c:forEach var="day" begin="1" end="${lastday}">
					<c:choose>
						<c:when test="${(month+1)==curMon&&day==currDay}">						
							<td style="width:155px; background-color:#fcf8e3; padding-right: 0px; padding-left: 0px;">							
						</c:when>
						<c:otherwise>
							<td style="width:155px; padding-right: 0px; padding-left: 0px;">
						</c:otherwise> </c:choose>
							<div>
								<c:choose>
									<c:when test="${(startyoil+day-1) % 7 == 0}"><span style="color:blue">${day}</span></c:when>
									<c:when test="${(startyoil+day-1) % 7 == 1}"><span style="color:red">${day}</span></c:when>
									<c:otherwise>${day}</c:otherwise>
								</c:choose>
							</div><!-- end div1 day출력  -->
							
							<%@include file="worklist.jsp" %><!-- div로 감싸져있음  -->
						</td>
						
					<c:if test="${(startyoil+day-1) % 7 == 0}"></tr><tr style="height:120px;"></c:if>
								 <!--  7(토)+1-1  -->
				</c:forEach>	
				<!-- last blank -->
				<c:forEach var="blank" begin="1" end="${(7-lastyoil)}">
					<td>*</td>
				</c:forEach></tr>
				<!-- end days --> <!-- end days --> <!-- end days --> <!-- end days --> 
				</tbody>
				
			</table>


	</div><!--end container-->

	
<!--            start footer          -->
<!--            start footer          -->
<!--            start footer          -->
</body>
</html>


