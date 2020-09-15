<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix ="c" uri="http://java.sun.com/jsp/jstl/core" %> <!-- 태그 라이브러리 -->
<%@ include file = "/inc/work_header.jsp" %>
<!--            end header            -->
<!--            end header            -->
<!--            end header            -->


<!-- start memberList header --> <!-- start memberList header -->
<!-- start memberList header --> <!-- start memberList header -->
<%@ include file ="dropdown_memlist.jsp" %>
<!-- end memberlist header --><!-- end memberlist header -->
<!-- end memberlist header --><!-- end memberlist header -->
		
		
		<div> <!-- 주소록테이블 --><!-- 주소록테이블 --><!-- 주소록테이블 --><!-- 주소록테이블 -->
			<table class="table table-striped">
			<c:set var ="ws_admin" value="${ws_admin}"></c:set>
			<c:set var ="ws_email" value="${ws_email}"></c:set>
			<c:set var ="jyp_no" value="${jyp_no}"></c:set>
			<caption>워크스페이스 주소록(클릭하면 해당 유저의 프로필로 이동)</caption>
				<colgroup>
					<col style="width:10%">
					<col style="width:15%">
					<col style="width:15%">
					<col style="width:15%">
					<col style="width:15%">
					<col style="width:10%">
				</colgroup>
				
				<thead>
					<tr>
						<th scope="col">이름</th>  <th scope="col">워크스페이스 가입날짜</th>  <th scope="col">워크스페이스 탈퇴날짜</th>
					</tr>
				</thead>
				
				<tbody>
				
					
				</tbody>
				
		<%-- 		<tfoot>
					<tr><td colspan="6" class="text-center">
						<ul class="pagination">
							<!-- 1.이전페이지 -->
							<c:if test="${memberList.bottom_start>memberList.bottomList}">
								<li><a href="${pageContext.request.contextPath}/member_list.mem?pstartNo=${(memberList.bottom_start-2)*memberList.onepageLimit}">
									이전</a></li>
							</c:if>
							<c:forEach var="btn" begin="${memberList.bottom_start}" end="${memberList.bottom_end}">
							<!-- 2. 현재페이지 -->
								<li <c:if test="${btn==memberList.bottom_current}">class="active"</c:if>>
									<a href="${pageContext.request.contextPath}/member_list.mem?pstartNo=${(btn-1)*memberList.onepageLimit}">
										${btn}</a></li>
							</c:forEach>
							<!-- 3. 다음페이지 -->
							<c:if test="${memberList.pageAll>memberList.bottom_end}">
								<li><a href="${pageContext.request.contextPath}/member_list.mem?pstartNo=${memberList.bottom_end *memberList.onepageLimit}">
									다음</a></li>
							</c:if>	
						</ul>
					</td></tr>	
				</tfoot> --%>
			</table>
		</div> <!-- end 주소록 테이블  --><!-- end 주소록 테이블  --><!-- end 주소록 테이블  --><!-- end 주소록 테이블  -->
		
		
		
	</div><!-- end container -->
	
	
<!--            start footer          -->
<!--            start footer          -->
<!--            start footer          -->
</body>
</html>
