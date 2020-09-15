<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix ="c" uri="http://java.sun.com/jsp/jstl/core" %> <!-- 태그 라이브러리 -->
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> <!-- jstl 줄바꿈 -->
<%@ include file = "/inc/work_header.jsp" %>
<!--            end header            -->
<!--            end header            -->
<!--            end header            -->



<div class="jyp_tab backDiv" style="position:relative">
	<p class="backbtn btn  btn-info" style="width: 126px; position: absolute; left: 77px; top: 3px; z-index: 100;"
		 onclick="history.go(-1);">←뒤로가기</p>
	<ul class="nav nav-tabs">
	  <li ><a  href="${pageContext.request.contextPath}/list.work">보드</a></li>
	  <li class="active"><a  href="#work_menu2">파일</a></li>
	</ul>

	<div class="tab-content">
	  
	  <div id="work_menu2" class="tab-pane fade in active">
	  	<c:choose>
	  		<c:when test="${empty fileList.list}">
		  		<p class="text-center"><img src="${pageContext.request.contextPath}/images/null_file.png"alt="null_file.png" width="500"/></p>
				<p class="text-center"><strong>프로젝트에 첨부된 파일이 없습니다.</strong></p>
	  		</c:when>
	  		
	  		<c:otherwise>
	  		
	  		<div class="container myconatiner wl" style="margin-top:9px;">	
		 	<table class="table table-striped" style="margin-top:35px;">
		 		<colgroup>
		 			<col width="10%"> <col width="50%"> <col width="20%"> <col width="20%">
		 		</colgroup>
		 		<thead>
		 			<tr>
		 				<th scope="col">NO</th> <th scope="col">이름</th> <th scope="col">업로드 날짜</th> <th scope="col">작성자</th>
		 			</tr>
		 		</thead>
		 		
		 		<tbody>
		 			<c:forEach var="list" items="${fileList.list}" varStatus="status">
			 			<tr onclick="location.href ='${pageContext.request.contextPath}/work_detail.work?work_no=${list.work_no}';" 
							onmouseover="this.style.backgroundColor='#F2F2F2'" onmouseout="this.style.backgroundColor='#FFFFFF'"
							style="cursor:pointer;">
			 				<td>${fileList.pstartNo + status.count}</td>
			 				<td>
			 					<div class="row">
			 						<div class="col-sm-3">
			 						<img src="${pageContext.request.contextPath}/upload/${list.work_file}" alt="${list.work_file}" width="80"/>
			 						</div><!-- end  col-sm-3 파일-->
			 						<div class="col-sm-8">
			 							<div>${list.work_file}</div> <!-- end 파일 이름  -->
			 							<div style="color: #27b6ba;">${list.wl_name}>${list.work_content}</div><!-- end 파일 위치  -->
			 						</div><!-- end  col-sm-8-->
			 					</div><!-- end row  -->
			 				</td>
			 				<td>${fn:substring(list.work_date,0,19)}</td>
			 				<td>${list.jyp_name}</td>
			 			</tr>
		 			</c:forEach>
		 		</tbody>
		 		
				<tfoot>
					<tr><td colspan="4" class="text-center">
						<ul class="pagination">
							<!-- 1.이전페이지 -->
							<c:if test="${fileList.bottom_start>fileList.bottomList}">
								<li><a href="${pageContext.request.contextPath}/fileList.work?pstartNo=${(fileList.bottom_start-2)*fileList.onepageLimit}">
									이전</a></li>
							</c:if>
							<c:forEach var="btn" begin="${fileList.bottom_start}" end="${fileList.bottom_end}">
							<!-- 2. 현재페이지 -->
								<li <c:if test="${btn==fileList.bottom_current}">class="active"</c:if>>
									<a href="${pageContext.request.contextPath}/fileList.work?pstartNo=${(btn-1)*fileList.onepageLimit}">
										${btn}</a></li>
							</c:forEach>
							<!-- 3. 다음페이지 -->
							<c:if test="${fileList.pageAll>fileList.bottom_end}">
								<li><a href="${pageContext.request.contextPath}/fileList.work?pstartNo=${fileList.bottom_end *fileList.onepageLimit}">
									다음</a></li>
							</c:if>	
						</ul>
					</td></tr>	
				</tfoot>
		 		 		
		 	</table>
		 	
		 	</div><!--  end  class="container myconatiner wl main"-->
		 	
	  		</c:otherwise>
	  	</c:choose> 
		
	
	 	
	 
	 	
	 

 
 <!-- 탭 --><!-- 탭 -->
	</div> <!-- emd profile -->
</div><!-- end  tab-content -->
</div>	
<!-- 탭 --><!-- 탭 -->

<!--            start footer          -->
<!--            start footer          -->
<!--            start footer          -->
</body>
</html>