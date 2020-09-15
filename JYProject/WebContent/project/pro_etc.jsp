<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 



		<!-- 프로젝트 list -->
		<!-- 프로젝트 list -->
		<!-- list 불러오기  -->
		<%--test <c:forEach var="p_no" begin="1" end="4" varStatus="status">
			<button type="button" class="btn btn-default p_btn" id="myBtn">프로젝트${status.count}</button>
		</c:forEach> --%>
		<!-- null 아니라면 for문 돌리게 --><!--프로젝트로 이동하는 링크걸기 get방식으로해서 p_no보내기  -->
		<c:forEach var="dto" items="${pro_etc}" varStatus="status">
			<div class="p_div ">
				<div class="row">
					<div class="col-sm-9">
						<c:choose>
							<c:when test="${dto.p_public=='y'}"><span class="glyphicon glyphicon-globe"
								style="padding-right: 5%;" title="공개"></span></c:when>
							<c:when test="${dto.p_public=='n'}"><span class="glyphicon glyphicon-lock" 
								style="padding-right: 5%;" title="비공개"></span></c:when>
						</c:choose> <!-- 공개여부 -->
						
					<%-- <button type="button" class="btn btn-default p_btn">${dto.p_name}</button> --%>
					<!-- 프로젝트로 이동하기 --><!-- 프로젝트로 이동하기 --><!-- 프로젝트로 이동하기 --><!-- 프로젝트로 이동하기 -->
					<div style="width: auto; overflow: hidden; text-overflow: ellipsis;"><a href="<%=request.getContextPath()%>/p_public.pro?p_no=${dto.p_no}&&p_public=${dto.p_public}"
						type="button" class="text-center" style="font-weight: bold;">${dto.p_name}</a></div>
					</div><!-- end col-sm-9 -->
					<div class="col-sm-3">
						<a href="${pageContext.request.contextPath}/p_edit_view.pro?p_no=${dto.p_no}">
							<span class="glyphicon glyphicon-cog" title="프로젝트설정"></span></a>
					</div><!-- end col-sm-3 -->
				</div><!-- end row -->
				
				<div class="row p_status">
					<div class="col-sm-6">
						<p>진행상태 :   <!-- 1:상태없음(기본설정), 2:계획됨, 3: 진행중, 4: 완료됨, 5:보류, 6:취소 - -->
						<c:choose>
							<c:when test="${dto.p_status==1}">상태없음</c:when>
							<c:when test="${dto.p_status==2}"><span style="background-color:#ffb024; color:white; padding: 5px;">계획됨</span></c:when>
							<c:when test="${dto.p_status==3}"><span style="background-color:#62c276; color:white; padding: 5px;">진행중</span></c:when>
							<c:when test="${dto.p_status==4}"><span style="background-color:#27b6ba; color:white; padding: 5px;">완료됨</span></c:when>
							<c:when test="${dto.p_status==5}"><span style="background-color:#D8D8D8; color:white; padding: 5px;">보류</span></c:when>
							<c:when test="${dto.p_status==6}">취소</c:when>
						</c:choose> </p> <!-- 진행상태 -->
					</div><!--end col-sm-6 -->
					<div class="col-sm-6">
					</div><!--end col-sm-6 -->
				</div><!-- end row -->
				
				<div class="row p_work">
					<div class="col-sm-6">
					</div><!--end col-sm-6 -->
					<div class="col-sm-6">
					 	<p style="font-size:8px;">프로젝트업무갯수</p>
					</div><!--end col-sm-6 -->
				</div><!-- end row -->
			</div><!-- end  p_div-->
		</c:forEach>
		<!-- 프로젝트 list -->
		<!-- 프로젝트 list -->
		
		
		