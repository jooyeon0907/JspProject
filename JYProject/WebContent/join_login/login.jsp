<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file = "/inc/login_header.jsp" %>
<!--            end header            -->
<!--            end header            -->
<!--            end header            -->
	<%
		    String mid = (String)session.getAttribute("jyp_email");
			String remember = "";
			String id = "";
			//1. 쿠키존재하는지확인
		    String cookie = request.getHeader("Cookie");
			//2. 쿠키값가져오기
			if(  cookie != null){
				Cookie  []cookies = request.getCookies();  
				for(int i=0;  i<cookies.length;  i++){   
					if(cookies[i].getName().equals("remember")){  
						remember = cookies[i].getValue();  
					}
					if(cookies[i].getName().equals("jyp_email")){  
						id = cookies[i].getValue();  
					}				
				}
			}
	%>	
	<div class="container"   style="margin-top:10%;  width:75%;">
		
		<div class="row">
		<!-- 로그인 이미지  --><!-- 로그인 이미지  --><!-- 로그인 이미지  --><!-- 로그인 이미지  -->
		<div class="col-sm-6" style="margin-top:-24px;">
		<p><img src="<%=request.getContextPath()%>/join_login/images/login2.png"  
			alt="로그인화면입니다." style="width:400px;"/></p>
		</div><!-- end col-sm-6 -->
		<!-- 로그인 이미지  --><!-- 로그인 이미지  --><!-- 로그인 이미지  --><!-- 로그인 이미지  -->
		
		
		
		<!-- 로그인하기   --><!-- 로그인하기   --><!-- 로그인하기   --><!-- 로그인하기   --><!-- 로그인하기   --><!-- 로그인하기   -->
		<div class="col-sm-6"> <!-- style="margin-bottom:2px solid #101010;" -->
			<!-- start 로그인 폼--><!-- start 로그인 --><!-- start 로그인 --><!-- start 로그인 --><!-- start 로그인 -->
			<div>
			<form action="<%=request.getContextPath()%>/login.members"  method="post"  id="login">
				<fieldset>
					<div class="form-group">
						<label for="jyp_email">아이디</label>
						<input type="email"  id="jyp_email"      name="jyp_email"  placeholder="아이디를입력하세요"   class="form-control" 
							<% if(remember.equals("remember")){ %> value="<%=id%>"  <% } %>
						 />
					</div> <!--  end group -->
					<div class="form-group">
						<label for="jyp_pass">비밀번호</label>
						<input type="password"  id="jyp_pass"    name="jyp_pass"  placeholder="비밀번호를 입력해주세요"   class="form-control"  />
					</div> <!--  end group -->
					<div class="form-group">
						<input type="checkbox"  id="remember"    name="remember" 
							<% if(remember.equals("remember")){ %> checked <% } %>
						  />
						<label for="remember">아이디 기억하기</label>
					</div> <!--  end group -->
					<div>
					<!-- <a href="https://kauth.kakao.com/oauth/authorize?client_id=a5c4e80d1a39794cebbf754bcb5092d0&redirect_uri=http://localhost:8080/JYProject/kokoLogin&response_type=code"> -->
						<a href="https://kauth.kakao.com/oauth/authorize?client_id=a5c4e80d1a39794cebbf754bcb5092d0&redirect_uri=http://joooo1234.cafe24.com/jyproject/kokoLogin&response_type=code"> 
							<img src="<%=request.getContextPath()%>/join_login/images/kakao_login_large_narrow.png" alt="카카오 로그인하기"
							 style="margin-bottom:10px; max-width:310px; width:100%; height:45px;"> <!-- width="320" height="45"  -->
						</a>
			 		</div> <!-- end kakaoLogin -->
					<div class="form-group  text-right">
						<input type="submit"  class="btn2  btn-default form-control"  value="LOGIN"  />
						<a href="<%=request.getContextPath()%>/join_agree.members"  class="btn2  btn-default form-control" >JOIN</a>  
					</div> <!--  end group -->
				</fieldset>
			</form></div> 
			<!-- end 로그인 --><!-- end 로그인 --><!-- end 로그인 --><!-- end 로그인 --><!-- end 로그인 -->
			
		</div><!-- end col-sm-6 -->
	
		</div><!-- end row -->
		
		
		<!-- 로그인하기   --><!-- 로그인하기   --><!-- 로그인하기   --><!-- 로그인하기   --><!-- 로그인하기   --><!-- 로그인하기   -->
		
		
		
		
		
		
		
	</div><!-- end container -->
	<script>
	$(document).ready(function(){
		$("#login").submit(function(){
			if( $("#jyp_email").val() ==""   ){  alert("빈칸입니다\n확인해주세요");   $("#jyp_email").focus();  return false; }
			if( $("#jyp_pass").val() ==""   ){  alert("빈칸입니다\n확인해주세요");   $("#jyp_pass").focus();  return false; } 
		});
	});
</script>	


<!--            start footer          -->
<!--            start footer          -->
<!--            start footer          -->
<%@ include file = "/inc/footer.jsp" %>