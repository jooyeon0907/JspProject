<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix ="c" uri="http://java.sun.com/jsp/jstl/core" %> <!-- 태그 라이브러리 -->
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<%@ include file = "/inc/login_header.jsp" %>
<!--            end header            -->
<!--            end header            -->
<!--            end header            -->

<!-- 
페이지 들어올때  ajax로 바로 검사하기 ->소속된 워크스페이스가 있는지 체크해서 값 던져줌 
소속된 워크스페이스가 없다면 $(".workList")에 WS_Add.java 넣어줌   (파일째로 못 넣나 ? include? )
 -->
 <%request.setCharacterEncoding("utf-8");
	pageContext.setAttribute("result", request.getParameter("result"));
 %>
	<div class="container" style="margin-top:5%">
		<h3 class="panel-heading">워크스페이스 리스트 </h3>
			<div class="row workList" style="box-shadow: 0 0 1px black;">
				<div class="col-sm-6">
				
				<c:choose>
					<c:when test="${result==1}"> <!-- 소속되 워크스페이스가 있다면  -->
						<h4>소속된 워크스페이스</h4>
						<table class="table table-striped">
						<caption>${jyp_email}님의 list</caption>
							<thead> 
								<tr> <th scope="col">이름</th> <th scope="col">생성날짜</th> <th></th> </tr>
							</thead>
							
							<tbody>
								<c:forEach var="list" items="${ws_list.list}" varStatus="status">
									<tr>
										<td>${list.ws_name}</td>  <td>${fn:substring(list.ws_date,0,10)} </td> 
										<td><a href="${pageContext.request.contextPath}/ws_home.ws?ws_no=${list.ws_no}&&ws_name=${list.ws_name}" 
												class="btn btn-info">입장</a></td>
									</tr>
								</c:forEach>
							</tbody>
							
							<tfoot>
								<tr><td colspan="3" class="text-center">
									<ul class="pagination">
										<!-- 1.이전페이지 -->
										<c:if test="${ws_list.bottom_start>ws_list.bottomList}">
											<li><a href="${pageContext.request.contextPath}/ws_list.ws?result=1&&pstartNo=${(ws_list.bottom_start-2)*ws_list.onepageLimit}">
												이전</a></li>
										</c:if>
										<!-- 2.현재페이지 -->
										<c:forEach var="btn" begin="${ws_list.bottom_start}" end="${ws_list.bottom_end}">
											<li <c:if test="${btn==ws_list.bottom_current}">class="active"</c:if>>
												<a href="${pageContext.request.contextPath}/ws_list.ws?result=1&&pstartNo=${(btn-1)*ws_list.onepageLimit}">
												${btn}</a></li>
										</c:forEach>
										<!-- 3.다음페이지 -->
										<c:if test="${ws_list.pageAll>ws_list.bottom_end}">
											<li><a href="${pageContext.request.contextPath}/ws_list.ws?result=1&&pstartNo=${ws_list.bottom_end*ws_list.onepageLimit}">
												다음</a></li>
										</c:if>
									</ul>
								</td></tr>
							</tfoot>
						</table>					
					</c:when>
					
					<c:when test="${result==2}"><!-- 소속되 워크스페이스가 없다면  -->
						<%@ include file="ws_add.jsp" %>
					</c:when>
				</c:choose>
					
				</div> <!-- end col -->
				
					<!-- 워크스페이스 초대리스트  -->
					<!-- 워크스페이스 초대리스트  -->
					<%@ include file="ws_inviList.jsp"%>
					<!-- 워크스페이스 초대리스트  -->
					<!-- 워크스페이스 초대리스트  -->
				
			</div> <!-- end row -->

		
	</div><!--end container-->
	
	
<!--            start footer          -->
<!--            start footer          -->
<!--            start footer          -->
</body>
</html>


