<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix ="c" uri="http://java.sun.com/jsp/jstl/core" %> <!-- 태그 라이브러리 -->
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>



		<div class="col-sm-6">
			<h4>초대받은 리스트</h4>
				<table class="table table-striped">
				<caption>워크스페이스 초대장(초대날짜로부터 7일 뒤 자동 거절됩니다.)</caption>
					<thead> 
						<tr> <th scope="col">워크스페이스 이름</th>
							 <th scope="col">초대한 id(email)</th>
							 <th scope="col">초대 날짜</th> 
						</tr>
					</thead>
					
					<tbody>
						<c:forEach var="invi" items="${list_invi.list}" varStatus="status">
							<tr>
								<td>${invi.ws_name}</td>
								<td>${invi.send_email}</td>
								<td>${fn:substring(invi.acc_date,0,19)}</td>
								<td><a href="${pageContext.request.contextPath}/ws_Accept.ws?invi_no=${invi.invi_no}&&ws_no=${invi.ws_no}">수락</a></td> <!-- 수락/거절시 해당리스트 invi_no넘기기  -->
								<td><a href="${pageContext.request.contextPath}/ws_InviRefuse.ws?invi_no=${invi.invi_no}&&ws_no=${invi.ws_no}">거절</a></td>
							</tr>
						</c:forEach>
					</tbody>
			
					<tfoot>
						<tr><td colspan="4" class="text-center">
							<ul class="pagination">
								<!-- 1.이전페이지 -->
								<c:if test="${list_invi.bottom_start>list_invi.bottomList}">
									<li><a href="${pageContext.request.contextPath}/ws_list.ws?result=1&&pstartNo2=${(list_invi.bottom_start-2)*list_invi.onepageLimit}">
										이전</a></li>
								</c:if>
								<!-- 2.현재페이지 -->
								<c:forEach var="btn" begin="${list_invi.bottom_start}" end="${list_invi.bottom_end}">
									<li <c:if test="${btn==list_invi.bottom_current}">class="active"</c:if>>
										<a href="${pageContext.request.contextPath}/ws_list.ws?result=1&&pstartNo2=${(btn-1)*list_invi.onepageLimit}">
										${btn}</a></li>
								</c:forEach>
								<!-- 3.다음페이지 -->
								<c:if test="${list_invi.pageAll>list_invi.bottom_end}">
									<li><a href="${pageContext.request.contextPath}/ws_list.ws?result=1&&pstartNo2=${list_invi.bottom_end*list_invi.onepageLimit}">
										다음</a></li>
								</c:if>
							</ul>
						</td></tr>
					</tfoot>
							
				</table>
		</div> <!-- end col -->


		


