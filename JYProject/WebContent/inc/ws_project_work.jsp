<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix ="c" uri="http://java.sun.com/jsp/jstl/core" %> <!-- 태그 라이브러리 -->

<div class="jyp_tab pro">

	<div class="row">
		<div class="col-sm-3">
			<select class="form-control">
					<option value="default">전체프로젝트</option>
					<option value="default">계획(계획중인 프로젝트 갯수)</option>
					<option value="default">진행중</option>
					<option value="default">완료됨</option>
					<option value="default">보류</option>
					<option value="default">취소</option>
					<option value="default">상태없음</option>
				</select> 
		</div><!-- end col-sm-2 -->
		
		<div class="col-sm-2">
			<input type="search" placeholder="프로젝트 검색하기" class="form-control"/>
		</div><!-- end col-sm-2 -->

		<div class="col-sm-3">
		</div><!-- end col-sm-4-->
		
		
		<div class="col-sm-2">
		    <button class="dropmenu glyphicon glyphicon-option-vertical" style="background-color: #ffffff;" id="menu1" data-toggle="dropdown"> </button>
		    <ul class="dropdown-menu" role="menu" aria-labelledby="menu1">
		      <li role="presentation"><a role="menuitem" tabindex="-1" href="#">프로젝트</a></li>
		      <li role="presentation" class="divider">내가 속한 프로젝트(일반멤버)</li>
		      <li role="presentation"><a role="menuitem" tabindex="-1" href="#">다른 프로젝트</a></li>
		    </ul>
		    
	<!-- 		<select class="dropmenu glyphicon glyphicon-option-vertical">
					<option value="default">프로젝트 멤버</option>
					<option value="default">내가 속한 프로젝트(일반멤버)</option>
					<option value="default">다른 프로젝트</option>
				</select>  -->
		</div><!-- end col-sm-2 -->
		
		<div class="col-sm-2">
			<input type="button" value="+새 프로젝트" class="btn btn-info" id="myBtn" />
		</div><!-- end col-sm-2 -->
	</div><!-- end row -->
			

</div><!-- end class="jyp_tab pro" -->



	
	
	
	
	
</body>
</html>

