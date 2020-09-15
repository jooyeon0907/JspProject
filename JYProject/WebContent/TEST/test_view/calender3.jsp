<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Calendar"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <!-- Theme Made By www.w3schools.com - No Copyright -->
  <title>Bootstrap Theme The Band</title>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <link href="https://fonts.googleapis.com/css?family=Lato" rel="stylesheet" type="text/css">
  <link href="https://fonts.googleapis.com/css?family=Montserrat" rel="stylesheet" type="text/css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
 
</head>
<body>
<div class="container panel panel-warning">
	<h3 class="panel-heading"> CALENDAR </h3>
	<%
	  Calendar calendar = Calendar.getInstance();
	  out.print("오늘 : ");
	  calendar.add(calendar.YEAR,0);   //###################### 년도 조절
	  out.print(calendar.get(Calendar.YEAR) + "년 ");
	  calendar.add(calendar.MONTH,0);  //###################### 달 조절
	  out.print(calendar.get(Calendar.MONTH) + 1 + "월 ");
	  out.println(calendar.get(Calendar.DATE) + "일<br/>");
	  out.println("이 달의 마지막 날의 날짜 : " + calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
	 
	  out.println("<br/> 1(일) 2(월) 3(화) 4(수) 5(목) 6(금) 7(토)");
	  calendar.set(Calendar.DATE,   calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
	  out.println("<br/>이 달의 마지막 날의 요일 : " + calendar.get(Calendar.DAY_OF_WEEK));

	  calendar.set(Calendar.DATE,   1);
	  out.println("<br/>이 달의 첫번째 날의 요일 : " + calendar.get(Calendar.DAY_OF_WEEK));

	  
	  out.println("<br/>calendar.get(Calendar.YEAR-1): " + calendar.get(Calendar.YEAR-1));
	  
	  
	  Calendar cal = Calendar.getInstance();
	  
	  cal.add(cal.MONTH,-1);

	  SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMM");

	  String beforeYear = dateFormat.format(cal.getTime()).substring(0,4);

	  String beforeMonth = dateFormat.format(cal.getTime()).substring(4,6);

//	out.println("<br/>beforeMonth : " + beforeMonth);
		
	%>
</div><!-- end container -->

<div class="container">
	<input type="hidden" id="mmprev" value="0"/>
	<input type="hidden" id="mmmnext" value="0"/>

	
	<%
		int yy =-1; int mm =-7;
	
		//////서블릿으로 처리하기 
		/////////////////////////// [1] today
		Calendar today = Calendar.getInstance();
		today.add(today.YEAR,  yy);
		int year  = today.get(Calendar.YEAR);
		today.add(today.MONTH, mm);
		int month = today.get(Calendar.MONTH);
		int day   = today.get(Calendar.DATE);
		/////////////////////////// [2] 현재달의 1일 구하기
		Calendar dday = Calendar.getInstance();
		dday.add(dday.YEAR,  yy);
		dday.add(dday.MONTH,mm);
		dday.set(Calendar.DATE, 1); 				   //1)  현재 달 1일 셋팅 예) 2019-06-01  (토)
		int startyoil = dday.get(Calendar.DAY_OF_WEEK); 
		//2) 2019-06-01 의 요일 구하기 - 앞에 날짜만큼 blank 구하기 

		//3) DAY_OF_WEEK : 특정 날짜가 무슨 요일리턴 Calendar.SUNDAY 1 / Calendar.MONDAY 2 ... 토요일 7
		//		여기에서는 토요일이기때문에 7리턴
		int lastday = today.getActualMaximum(Calendar.DATE); 
		dday.set(Calendar.DATE, lastday); // 오늘날짜, 마지막일
		int lastyoil = dday.get(Calendar.DAY_OF_WEEK); 		  
		//4) java 해당월 마지막 날짜(일) 구하기
		// getActualMaximum : 날짜가 셋팅 된 Calender 가 가질수 있는 값
		///////////////////////////
		String[] weektitle = { "SUN", "MON", "TUE", "WED", "THU", "FRI", "SAT" };
		pageContext.setAttribute("weektitle", weektitle);
		pageContext.setAttribute("year" , year);
		pageContext.setAttribute("month", month);
		pageContext.setAttribute("day"  , day);
		pageContext.setAttribute("startyoil"  , startyoil);
		pageContext.setAttribute("lastday"  , lastday);
		pageContext.setAttribute("lastyoil"  , lastyoil);
		
	%>

	
	<c:set var="year"  value="${year}"></c:set>
	<c:set var="month" value="${month}"></c:set>
	<c:set var="day"   value="${day}"></c:set>
	<table class="table table-stripped">
		<caption>EVENT</caption>
		<thead>
			<tr>
				<th colspan="7" class="text-center">
					<div class="col-sm-3">
						<input type="button" id="prevBtn" value="◀" class="btn btn-info"/>
					</div><!-- end col-sm-3 -->
					<div class="col-sm-6">
						<p><span id="yy">${year}</span>년  <span id="mm">${month+1}</span>월 </p>
					</div><!-- end col-sm-6 -->
					<div class="col-sm-3">
						<input type="button" id="nextBtn" value="▶" class="btn btn-info"/>
					</div><!-- end col-sm-3 -->
				</th>
			</tr>
			<tr>
			<c:forEach var="weektitle" items="${weektitle}">
				<th>${weektitle}</th>
			</c:forEach>			
			</tr>
			
		</thead>
		<tbody>
			<tr><td>
				<div>
					<table>
						<thead><tr>
						<th></th> <!-- day 뽑기 - for문  -->
						</tr></thead>
						
						<tbody>
							<tr>
								<td></td>	
								<td></td>
								<td></td>
							</tr>
						</tbody>
					</table>
				</div>
			
			
			
			
			
			
			
			
			
			
			</td></tr>
			
			
			<!-- start blank -->
			<tr style="height:120px;"><c:forEach var="blank" begin="1" end="${startyoil-1}">
				<td >*</td>
			    </c:forEach>			
			
			<!-- day -->
			<c:forEach var="day" begin="1" end="${lastday}">
				<td>${day}</td>
				<c:if test="${(startyoil+day-1) % 7 == 0}"></tr><tr style="height:120px;"></c:if>
							 <!--  7(토)+1-1  -->
			</c:forEach>	
				
			<!-- last blank -->
			<c:forEach var="blank" begin="1" end="${(7-lastyoil)}">
				<td>*</td>
			</c:forEach></tr>
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
		</tbody><!-- 제일 큰 테이블 -->
	</table>
</div>	
</body>
</html>


