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
	<li><a href="<%=request.getContextPath()%>/mypage_pass_view.members" >비밀번호 변경</a></li>
	<li class="active"><a href="#delete" >계정 삭제</a></li>
</ul>

<div class="tab-content">
	<div class="tab-pane fade in active"  id="#delete">
			<form action="<%=request.getContextPath()%>/mypage_delete.members"  method="post"  id="delete">
			<fieldset>
				<pre>
				한 번 삭제된 계정은 다시 복구할 수 없습니다. 계정이 삭제되면, 현재 계정에서 생성된 모든 데이터에 더이상 엑세스할 수 없습니다.
				 삭제 후, JYProject를 다시 이용하고자 한다면, 새로 가입해주셔야합니다.
				</pre>
				
			<div style="text-align:center">
				<label for="jyp_pass" style="margin:5px">비밀번호 입력</label>
				<input type="password" name="jyp_pass" id="jyp_pass"/>
				<input type="submit"   value="계정 삭제하기" class="btn btn-danger" style="margin:8px">
			</div>	
			</fieldset>
		</form>	
	
	
	</div><!-- end delete -->
	
</div><!-- end  tab-content -->
</div>


<script>
	$(document).ready(function(){
		$("#delete").submit(function(){
			if( $("#jyp_pass").val() ==""   ){  alert("빈칸입니다\n확인해주세요");   $("#old").focus();  return false; }
		});
	});
</script>	

<!--            start footer          -->
<!--            start footer          -->
<!--            start footer          -->
<%@ include file = "/inc/footer.jsp" %>