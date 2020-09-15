<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file = "/inc/login_header.jsp" %>
<!--            end header            -->
<!--            end header            -->
<!--            end header            -->

<div style="margin:10%;">
<!-- Tab 영역 태그는 ul이고 클래스는 nav와 nav-tabs를 설정한다. -->
<ul class="nav nav-tabs">
	<!-- Tab 아이템이다. 태그는 li과 li > a이다. li태그에 active는 현재 선택되어 있는 탭 메뉴이다. -->
	<li><a href="<%=request.getContextPath()%>/mypage_view.members">Profile</a></li>
	<!-- a 태그의 href는 아래의 tab-content 영역의 id를 설정하고 data-toggle 속성을 tab으로 설정한다. -->
	<li class="active"><a href="#pass">비밀번호 변경</a></li>
	<li><a href="<%=request.getContextPath()%>/mypage_delete_view.members" >계정 삭제</a></li>
</ul>

<div class="tab-content">
	<div class="tab-pane fade in active"  id="pass">
		<form action="<%=request.getContextPath()%>/mypage_pass.members"  method="post"  id="passUpdate">
			<fieldset>
			<table  class="table table-striped">
				<caption>비밀번호 수정</caption>
				<tbody> 
					<tr><th scope="row"><label for="old">이전 비밀번호</label>    </th>
					<td><input type="password"   id="old"    name="old" class="form-control"/></td></tr>
					<tr><th scope="row"><label for="new">새 비밀번호</label>    </th>
					<td><input type="password"   id="new"   name="new"  class="form-control"/></td></tr>
					<tr><th scope="row"><label for="new2">새 비밀번호확인 </label></th>
					<td><input type="password"  id="new2"   name="new2"   class="form-control"/></td></tr>
				</tbody>
			</table>
			<p class="text-center"> 
				<a href="javascript:history.go(-1)"  class="btn btn-danger">이전</a> 
				<input type="submit"   value="비밀번호 수정하러가기" class="btn btn-danger"> 
			</p>
			</fieldset>
		</form>	
	
	</div><!-- end pass -->
		
</div><!-- end  tab-content -->
</div>

<script>
	$(document).ready(function(){
		$("#passUpdate").submit(function(){
			if( $("#old").val() ==""   ){  alert("빈칸입니다\n확인해주세요");   $("#old").focus();  return false; }
			if( $("#new").val() ==""   ){  alert("빈칸입니다\n확인해주세요");   $("#new").focus();  return false; } 
			if( $("#new2").val() ==""   ){  alert("빈칸입니다\n확인해주세요");   $("#new2").focus();  return false; } 
			if( $("#new").val() !=$("#new2").val()  ){  alert("비밀번호와 비밀번호 확인이 다릅니다.\n확인해주세요");  
									$("#new").focus();  return false; } 
		});
	});
</script>		


<!--            start footer          -->
<!--            start footer          -->
<!--            start footer          -->
<%@ include file = "/inc/footer.jsp" %>