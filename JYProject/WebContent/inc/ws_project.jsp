<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix ="c" uri="http://java.sun.com/jsp/jstl/core" %> <!-- 태그 라이브러리 -->

<div class="jyp_tab pro">

	<div class="row">
		<div class="col-sm-3">
			<select class="form-control">
					<option value="default">전체프로젝트</option>
					<option value="default" style="background-color:#ffb024; color:white;">계획(계획중인 프로젝트 갯수)</option>
					<option value="default" style="background-color:#62c276; color:white;">진행중</option>
					<option value="default" style="background-color:#27b6ba; color:white;">완료됨</option>
					<option value="default" style="background-color:#D8D8D8; color:white;">보류</option>
					<option value="default">취소</option>
					<option value="default">상태없음</option>
				</select> 
		</div><!-- end col-sm-2 -->
		
		<div class="col-sm-3">
			<input type="search" placeholder="프로젝트 검색하기" class="form-control"/>
		</div><!-- end col-sm-2 -->

		<div class="col-sm-3">
		</div><!-- end col-sm-4-->
		
	<!-- 	<div class="col-sm-2">
			<select class="form-control">
					<option value="default">내가 속한 프로젝트(관리자)</option>
					<option value="default">내가 속한 프로젝트(일반멤버)</option>
					<option value="default">다른 프로젝트</option>
				</select> 
		</div>end col-sm-2 -->
		
		<div class="col-sm-3">
			<input type="button" value="+새 프로젝트" class="btn btn-info" id="myBtn"  style="width: 244px; height: 42px; font-size: 20px;"/>    
		</div><!-- end col-sm-2 -->
	</div><!-- end row -->
			

</div><!-- end class="jyp_tab pro" -->



	
	
	
	
	
</body>
</html>

