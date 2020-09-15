<%@page import="com.jooyeon.dto.JYP_Member_DTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file = "/inc/login_header.jsp" %>
<!--            end header            -->
<!--            end header            -->
<!--            end header            -->


<script>
	$(function(){
		$("#noImg").click(function(){
			//	console.log($("#jyp_profile").val()); //C:\fakepath\icon3.png
			
			$.ajax({
				 url:"${pageContext.request.contextPath}/mypage_profile.members",   
					type:"post", 
					dataType :"text",   
					success:function(text){
						location.href='${pageContext.request.contextPath}/mypage_view.members';
					},
					error:function(xhr, textStatus, errorThrown){
						alert('ERRROR');
					}
			 }); //end ajax
			 
		}); //end click
	}); //end ready

</script>



<div style="margin:10%;">
<!-- Tab 영역 태그는 ul이고 클래스는 nav와 nav-tabs를 설정한다. -->
<ul class="nav nav-tabs">
<!-- Tab 아이템이다. 태그는 li과 li > a이다. li태그에 active는 현재 선택되어 있는 탭 메뉴이다. -->
	<li class="active"><a href="#profile">Profile</a></li>
	<!-- a 태그의 href는 아래의 tab-content 영역의 id를 설정하고 data-toggle 속성을 tab으로 설정한다. -->
	<li><a href="<%=request.getContextPath()%>/mypage_pass_view.members">비밀번호 변경</a></li>
	<li><a href="<%=request.getContextPath()%>/mypage_delete_view.members" >계정 삭제</a></li>
</ul>

<!-- Tab이 선택되면 내용이 보여지는 영역이다. -->
<!-- 태그는 div이고 class는 tab-content로 설정한다. -->
<div class="tab-content">
	<div class="tab-pane fade in active" id="profile">
	
		<form action="<%=request.getContextPath()%>/mypage_edit.members" method="post"
				enctype="multipart/form-data">	
		<fieldset>
		<table  class="table table-striped">
			<caption>MY Profile</caption>
			<tbody>
				<% JYP_Member_DTO dto = (JYP_Member_DTO)request.getAttribute("dto"); %>
				<tr><th scope="row">프로필 사진</th>
					<td>
						<input type="hidden" name ="oldfile" value="${dto.jyp_profile}"/>
						<p><img src="${pageContext.request.contextPath}/upload/${dto.jyp_profile}" alt="${dto.jyp_profile}" style="width:80px;"></p>
						<p><input type="file" id ="jyp_profile" name="jyp_profile" value="프로필 업로드하기" /></p>
						<%-- <p><a href="${pageContext.request.contextPath}/mypage_profile.members" class="btn btn-info">기본이미지로 설정</a></p> --%> 
						<p><input type="button" class="btn btn-info" value="기본이미지로 설정" id="noImg"></p>
						<!-- noImg.png로 변경 -->
					</td>
				</tr>
				<tr><th scope="row">이메일 계정			</th>
					<td><%=dto.getJyp_email()%></td></tr>		
				
				<tr><th scope="row"><label for="jyp_name">이름</label>    </th>
					<td><input type="text"  id="jyp_name"    name="jyp_name"  value="<%=dto.getJyp_name()%>"  class="form-control"  /></td></tr>		
				
				<tr><th scope="row"><label for="dept">부서</label>    </th>
					<td><input type="text"  id="dept"    name="dept"  value="<%=dto.getDept()%>"  class="form-control"  /></td></tr>		
				
				<tr><th scope="row"><label for="position">직합</label>    </th>
					<td><input type="text"  id="position"    name="position"  value="<%=dto.getPosition()%>"  class="form-control"  /></td></tr>		
				
				<tr><th scope="row"><label for="jyp_tel">TEL</label>    </th>
					<td><input type="number"  id="jyp_tel"    name="jyp_tel"  value="<%=dto.getJyp_tel()%>"  class="form-control"  /></td></tr>		
				
				<tr><th scope="row">성별					</th>
					<td><%=dto.getGender()%></td></tr>		
				
				<%String birth=dto.getBirth(); %>
				<tr><th scope="row"><label for="birth">생일</label>    </th>
					<td><input type="date"  id="birth"    name="birth"  value="<%=birth.substring(0,10)%>"  class="form-control"  /></td></tr>		
				
				<tr><th scope="row">가입날짜					</th>
					<td><%=dto.getJyp_date()%></td></tr>		
 			</tbody>
		</table>
		<p class="text-center"> 
			<%-- <a href="<%=request.getContextPath()%>/mypage.members"  class="btn btn-danger">이전</a> --%> 
			<a href="javascript:history.go(-1)"  class="btn btn-danger">이전</a>
			<input type="submit"   value="회원정보수정" class="btn btn-danger"> 
		</p>
	</fieldset>
	</form>	
	
	</div> <!-- emd profile -->
	
</div><!-- end  tab-content -->
</div>
<!-- 각 탭이 선택되면 보여지는 내용이다. 태그는 div이고 클래스는 tab-pane이다. -->
<!-- active 클래스는 현재 선택되어 있는 탭 영역이다. -->
<!-- id는 고유한 이름으로 설정하고 tab의 href와 연결되어야 한다. -->

<!-- fade 클래스는 선택적인 사항으로 트랜지션(transition)효과가 있다.
<!-- in 클래스는 fade 클래스를 선언하여 트랜지션효과를 사용할 때 in은 active와 선택되어 있는 탭 영역의 설정이다. -->






<!--            start footer          -->
<!--            start footer          -->
<!--            start footer          -->
<%@ include file = "/inc/footer.jsp" %>
