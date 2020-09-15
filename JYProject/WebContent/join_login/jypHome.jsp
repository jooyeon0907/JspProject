<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix ="c" uri="http://java.sun.com/jsp/jstl/core" %> <!-- 태그 라이브러리 -->
<%@ include file = "/inc/login_header.jsp" %>
<!--            end header            -->
<!--            end header            -->
<!--            end header            -->


<script>
$(document).ready(function(){ $("#myModal").modal(); });


$(function(){
	$("#popup_colse").on("click",function(){
		$.ajax({
			url:"<%=request.getContextPath()%>/",
			type:"get",dataType:"data",
			success:function( data ){
				
			},
			error:function( xhr, textStatus, errorThrown){
				$("#myModal").html(textStatus + "(HTTP - )"+xhr.status +"/" + errorThrown);
			}
		}); //end ajax
	}); //end click
}); //end ready
</script>
<!-- 공지 팝업창 --><!-- 공지 팝업창 --><!-- 공지 팝업창 --><!-- 공지 팝업창 --><!-- 공지 팝업창 --><!-- 공지 팝업창 -->
 
  <!-- Modal -->
  <div class="modal fade" id="myModal" role="dialog">
    <div class="modal-dialog">
    
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header" style="background-color: #1a92c5;">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title">포트폴리오공지사항</h4>
        </div>
        <div class="modal-body">
         	<p>
			본사이트는 상업적목적이 아닌 개인 포트폴리오 용도로 제작되었으며<br>
			홈페이지의 일부내용과 기타 이미지 등은 그 출처가 따로 있음을 밝힙니다.
			</p>
			<p><img src="${pageContext.request.contextPath}/join_login/images/jyp_QR.jpg" alt="포트폴리오바로가기"/></p>
        </div>
        <div class="modal-footer">
       		<input type="checkbox" id="subpop" name="subpop" />
	        <label for="subpop">오늘 하루동안 이 창 열지 않음.</label>
			<input type="button" value="[close]" class="btn btn-info" id="popup_colse" data-dismiss="modal">
   		<!--<button type="button" class="btn btn-default" data-dismiss="modal">Close</button> -->
        </div>
      </div>
      
    </div>
  </div>
  
 <!-- end 공지 팝업창  --><!-- end 공지 팝업창  --><!-- end 공지 팝업창  --><!-- end 공지 팝업창  --><!-- end 공지 팝업창  -->
 
 
 
 
	<div class="container"  style="margin-top:5%; width: 100%;">
	
		<!-- start sliding --><!-- start sliding --><!-- start sliding --><!-- start sliding -->
		<div class="sliding" style="height:350px;">
			<div id="myCarousel" class="carousel slide" data-ride="carousel">
		    <!-- Indicators -->
		    <ol class="carousel-indicators">
		      <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
		      <li data-target="#myCarousel" data-slide-to="1"></li>
		      <li data-target="#myCarousel" data-slide-to="2"></li>
		     <!--  <li data-target="#myCarousel" data-slide-to="3"></li> -->
		    </ol>
		
		    <!-- Wrapper for slides -->
		    <div class="carousel-inner" role="listbox">
		
		      <div class="item active" style=" border-bottom: 1px solid;">
		        <img src="${pageContext.request.contextPath}/join_login/images/main2.png" alt="main2.png" 
				       	 	style="width:85%; -webkit-filter: none; height:400px;" >
				
		      </div>
		
		      <div class="item" style=" border-bottom: 1px solid;">
		        <a href="<%=request.getContextPath()%>/java_MiniProject.members"><img src="${pageContext.request.contextPath}/join_login/images/JYPOS.png" alt="JYPOS.png" 
				       	 	style="width:85%; -webkit-filter: none; height:400px;" ></a>
		      </div>
		    
		      <div class="item" style=" border-bottom: 1px solid;">
		       <a href="http://joooo1234.cafe24.com/sboard2"><img src="${pageContext.request.contextPath}/join_login/images/springBoard.png" alt="springBoard.png" 
				       	 	style="width:85%; -webkit-filter: none; height:400px;" ></a>
		      </div>
		
<!-- 		      <div class="item" style=" border-bottom: 1px solid;">
		        <img src="img_flower2.jpg" alt="Flower" width="460" height="345">
		        <div class="carousel-caption">
		          <h3>Flowers</h3>
		          <p>Beautiful flowers in Kolymbari, Crete.</p>
		        </div>
		      </div> -->
		  
		    </div>
		
		    <!-- Left and right controls -->
		    <a class="left carousel-control" href="#myCarousel" role="button" data-slide="prev" style="opacity:0.2;">
		      <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
		      <span class="sr-only">Previous</span>
		    </a>
		    <a class="right carousel-control" href="#myCarousel" role="button" data-slide="next" style="opacity:0.2;">
		      <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
		      <span class="sr-only">Next</span>
		    </a>
		  </div>
		
	
				
		</div><!-- end sliding -->
		<!-- end sliding --><!-- end sliding --><!-- end sliding --><!-- end sliding --><!-- end sliding -->
		
		
		<!-- 바로가기 링크 -->
		<div>


		<!-- 하단 이미지 (로그인페이지로 이동) --><!-- 하단 이미지 (로그인페이지로 이동) --><!-- 하단 이미지 (로그인페이지로 이동) -->
		<div class="container text-center" style="margin-top:10%;">
			<p><strong style="color: #123456; font-size:20px;">※회원가입 없이 빠르게 포트폴리오를 확인하실 분들은 아래 링크를 클릭하시면 됩니다.</strong></p>
			<p><a href="<%=request.getContextPath()%>/login.members?jyp_email=jooyeon@naver.com&&jyp_pass=wndus1234">
				<img src="${pageContext.request.contextPath}/join_login/images/bottom.png" alt="/bottom.png" width="800"/>
				</a></p>
		</div>
	 	<!-- end 하단 이미지 (로그인페이지로 이동) --><!-- end 하단 이미지 (로그인페이지로 이동) --><!-- end 하단 이미지 (로그인페이지로 이동) -->
		<!-- end 바로가기 링크 -->	
		</div>

	
		<div style="background-color: aliceblue; padding-top: 1px; padding-bottom: 100px;">
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
		
		
		</div>
		
		<!-- start youtube --><!-- start youtube --><!-- start youtube --><!-- start youtube -->	
		<div class="container text-center" style="margin-top:5%; width: 100%;">
		
			<iframe width="1000" height="500" src="https://www.youtube.com/embed/vE9eHyrTWyg" frameborder="0"
					 allow="accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture" allowfullscreen></iframe>
		
		</div>
		<!-- end youtube --><!-- end youtube --><!-- end youtube --><!-- end youtube --><!-- end youtube -->	
	
		
		
		
		<!-- 3단갤러리 --><!-- 3단갤러리 --><!-- 3단갤러리 -->
		<div class="container" style="margin-top:10%; width: 80%;">
			<h3 class="text-center"><strong>JYPTOJECT 소개</strong></h3>
			<div class="row">
			  <c:forEach var="i" begin="1" end="6" varStatus="status">
			    <div class="col-sm-4">
			      <p>
			      <img src="${pageContext.request.contextPath}/join_login/images/3단${i}.png" alt="/3단${i}.png"  width="300" height="260">
			      </p>
			    </div>
			  </c:forEach>
			</div><!-- end row -->
		</div>
		<!-- end 3단 --><!-- end 3단 --><!-- end 3단 --><!-- end 3단 -->
	
	
		
	</div><!-- end  container -->
	



<!--            start footer          -->
<!--            start footer          -->
<!--            start footer          -->
<%@ include file = "/inc/footer.jsp" %>