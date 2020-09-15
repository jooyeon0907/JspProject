
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix ="c" uri="http://java.sun.com/jsp/jstl/core" %> <!-- 태그 라이브러리 -->  
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ include file = "/inc/work_header.jsp" %>
<!--            end header            -->
<!--            end header            -->
<!--            end header            -->
<div class="container myconatiner w_detail">

		<form action="${pageContext.request.contextPath}/p_edit.pro?p_no=${dto_pro.p_no}" method="post"  id="editForm" >
		   <fieldset>
		   <legend>프로젝트 설정</legend>
		   
			<div class="form-group">
			  <label for="p_name"  >프로젝트명 : </label>
			  <input type="text"   name="p_name"   id="p_name"   class="form-control"   value="${dto_pro.p_name}" > 
			</div>	
			
			<div class="form-group">
			  <label for="p_info"  >설명</label>
			  <input type="text"   name="p_info"   id="p_info"   class="form-control"   value="${dto_pro.p_info}" > 
			</div>	
			

			<div class="form-group">
			 <label for="p_status"  >프로젝트 상태 (색깔 별로 바뀌게 하기 )</label>
			 <!--  1:상태없음(기본설정), 2:계획됨, 3: 진행중, 4: 완료됨, 5:보류, 6:취소  -->
			   <select id="p_status" name="p_status">
			    <option value="1" <c:if test="${dto_pro.p_status==1}">selected</c:if>>상태없음</option>
			    <option value="2" style="background-color:#ffb024; color:white;" <c:if test="${dto_pro.p_status==2}">selected</c:if>>계획됨</option>
			    <option value="3" style="background-color:#62c276; color:white;" <c:if test="${dto_pro.p_status==3}">selected</c:if>>진행중</option>
			    <option value="4" style="background-color:#27b6ba; color:white;" <c:if test="${dto_pro.p_status==4}">selected</c:if>>완료됨</option>
			    <option value="5" style="background-color:#D8D8D8; color:white;" <c:if test="${dto_pro.p_status==5}">selected</c:if> >보류</option>
			    <option value="6" <c:if test="${dto_pro.p_status==6}">selected</c:if>>취소</option>
			  </select>
			</div>
			
			
					
			<div class="form-group">
			  <label for="p_start"  >시작일 </label>    
			  <input type="datetime-local"   name="p_start"   id="p_start"   class="form-control"   
			  			value="${fn:substring(dto_pro.p_start,0,10)}T${fn:substring(dto_pro.p_start,11,19)}" > 
			</div>		
																		
			<div class="form-group">
			  <label for="p_end" >마감일</label>
			  <input type="datetime-local"   name="p_end"   id="p_end"   class="form-control" 
			  			value="${fn:substring(dto_pro.p_end,0,10)}T${fn:substring(dto_pro.p_end,11,19)}" > 
			</div>	
			
			<div class="form-group">
			  <label for="p_complete"  >실제 완료일</label>
			  <input type="datetime-local"   name="p_complete"   id="p_complete"   class="form-control"
			  			value="${fn:substring(dto_pro.p_complete,0,10)}T${fn:substring(dto_pro.p_complete,11,19)}" > 
			</div>								
			
			<div class="form-group">
			  <strong>프로젝트 관리자
				  <!-- 초대모달 -->
				<span class="glyphicon glyphicon-plus" title="멤버 추가" data-toggle="modal" data-target="#p_member" id="p_search1111"></span>
				<input type="hidden" id="pNo_hidden" value="${dto_pro.p_no}"/> </strong>
			 	 <%@ include file="/project/p_member.jsp" %> <!-- 프로젝트 관리자 추가 페이지 -->
			  <p><c:forEach var ="dto_mem" items="${dto_mem}" varStatus="status">
			  			<c:if test="${dto_mem.p_access==1}">
			  				 ${dto_mem.jyp_name} <c:if test="${!status.last}">,</c:if> </c:if> 
			  	 </c:forEach></p>
			</div>
			
			<div class="form-group">
			  <strong>프로젝트 팀원
				  <!-- 초대모달 -->
				<span class="glyphicon glyphicon-plus" title="멤버 추가" data-toggle="modal" data-target="#p_member2" id="p_search2222"></span>
			</strong>
				  <%@ include file="/project/p_member2.jsp" %><!-- 프로젝트 팀원 추가 페이지 -->
			  
			  <p><c:forEach var ="dto_mem" items="${dto_mem}" varStatus="status">
			  			<c:if test="${dto_mem.p_access==2}">
			  				 ${dto_mem.jyp_name} <c:if test="${!status.last}">,</c:if> </c:if>   
			  	 </c:forEach></p>
			</div>	
			
			
			<div class="form-group">
			  <label for="p_public"  >프로젝트 공개여부</label>
			  <select id="p_public" name="p_public">
			  	<option value="y" <c:if test="${dto_pro.p_public=='y'}">selected</c:if>>공개</option>
			  	<option value="n" <c:if test="${dto_pro.p_public=='n'}">selected</c:if>>비공개</option>
			  </select>
			</div>	
			
			<!-- 프로젝트삭제버튼 -->
			<!-- 프로젝트삭제버튼 -->
			<div class="form-group">
			  <c:if test="${dto_mem2.p_access==1}">
			  	<input type="button" value="프로젝트 삭제"
			  		id="pro_delBtn"  class="btn btn-info form-control"/><!-- 프로젝트 관리자에게만 보임  -->
			  </c:if>
			</div>
			<!-- end 프로젝트삭제버튼 -->
			<!-- end 프로젝트삭제버튼 -->
			
			<div class="form-group">
			   <input type="button" value="프로젝트 나가기"
			          class="btn btn-info form-control"/><!-- 프로젝트 관리자가 1명일때는 못 나감.프로젝트에 최소 1명이상의 프로젝트관리자가 있어야함 -->
			</div>											<!-- 해당 유저 p_member에서 삭제하기   -->
				
			<div class="form-group  text-right">
				<input type="submit"   value="설정하기"   class="btn btn-danger"  >  
				<input type="reset"    value="작성취소"  class="btn btn-default"  >  
				<a href="javascript:history.go(-1)" class="btn btn-default" >뒤로</a>
			</div>
		 </fieldset>		
		</form> <!-- end form -->	
</div>


<script>
	$(function(){

		$("#pro_delBtn").click(function(){
			var result = confirm('프로젝트를 삭제하시겠습니까? \n 프로젝트 내 데이터는 영구삭제됩니다.');
				
			if(result){
				$(location).attr('href','${pageContext.request.contextPath}/p_delete.pro?p_no=${dto_pro.p_no}');
			}else{return false;}
			
			
		}); //end click
	}); //end ready

</script>







<!--            start footer          -->
<!--            start footer          -->
<!--            start footer          -->
<%@ include file = "/inc/footer.jsp" %>