<%@page import="java.util.Calendar"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix ="c" uri="http://java.sun.com/jsp/jstl/core" %> <!-- 태그 라이브러리 -->
<%@ include file = "/inc/login_header.jsp" %>
<!--            end header            -->
<!--            end header            -->
<!--            end header            -->

<div class="container"    style="margin-top:5%; max-width: 500px; width: 100%;">
<c:set var="jyp_email" value="${jyp_email}" ></c:set>
<c:set var="jyp_name" value="${jyp_name}" ></c:set>
<c:set var="loginType" value="${loginType}" ></c:set>
		<h3>회원가입</h3> 
		<p>(*)는 필수입력사항입니다.  </p> 
		<form  action="<%=request.getContextPath()%>/join.members" method="post"  id="join" name="joinForm">
			<fieldset>
			<legend>필수정보입력</legend>
			<!-- 	/*insert into jyp_member (jyp_email,jyp_name, jyp_pass, jyp_tel, gender, jyp_ip)  -->
				<div class="form-group">
					<label for="jyp_email">(*) 이메일 </label>
				<%-- 	<input type="text"  id="jyp_email"  name="jyp_email"  value="${jyp_email}" placeholder="이메일을 입력해주세요."  class="form-control"/> --%>
					
					
						<c:choose>
							<c:when test="${loginType==1}">
								<div>
								<input type="text"  id="jyp_email"  name="jyp_email"  value="${jyp_email}" class="form-control"/>
		               			</div>
								<div class="idCheck_r" style="color:blue; padding-left: 19px;">사용 가능한 이메일</div>		
							</c:when>
							<c:otherwise>
								<span><input type="button" value="중복확인" id="idDouble" 
								class="btn btn-primary" style="    margin-left: 14px;"/></span>
								<div>
								<input type="text" id="jyp_email" name="jyp_email" > @
								<select name="jyp_email2" id ="jyp_email2" style="width: 157px; height: 30px;">
			                     <option value="naver.com">naver.com</option>
			                     <option value="google.com">google.com</option>
			                     <option value=daum.net>daum.net</option>
			               		</select>
			               		</div>
			               		<div class="idCheck_r" style="color:blue; padding-left: 19px;"></div>	
							</c:otherwise>
						</c:choose>
					
					
				</div> <!-- end group -->
				<div class="form-group">
					<label for="jyp_name">(*) 이름</label> 
					<input type="text"  id="jyp_name"  name="jyp_name" value="${jyp_name}" placeholder="이름을 입력해주세요"  class="form-control"/>
				</div> <!-- end group -->
				<div class="form-group">
					<label for="jyp_pass">(*) 비밀번호</label>
					<input type="password"  id="jyp_pass"  name="jyp_pass"  placeholder="비밀번호를 입력해주세요"  class="form-control"/>
				</div> <!-- end group -->
				<div class="form-group">
					<label for="jyp_pass2">(*) 비밀번호 확인</label>
					<input type="password"  id="jyp_pass2"  name="jyp_pass2"  placeholder="비밀번호를 입력해주세요"  class="form-control"/>
				</div> <!-- end group -->
				<div class="form-group">
					<label for="jyp_tel">(*) 전화번호</label> 
					 <input type="hidden"  id="jyp_tel"  name="jyp_tel"/> 
					<p><input type="text" name="tel1" id="tel1" size="3" maxlength="3" />&nbsp-&nbsp 
					<input type="text" name="tel2" id="tel2" size="4" maxlength="4"/>&nbsp-&nbsp 
					<input type="text" name="tel3" id="tel3" size="4" maxlength="4"/></p>
				</div> <!-- end group --> 
				<div class="form-group">
					<strong>(*) 성별</strong>
					<div>
						<label for="female">여자</label> 
						<input type="radio"  id="female" value="여자" name="gender"/>
						
						<label for="male">남자</label> 
						<input type="radio"  id="male" value="남자" name="gender"/>
					</div>
				</div> <!-- end group --> 
				
				<div class="form-group">
					<label for="birth">(*) 생년월일</label>
					<div> 
					<%String today =new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());%>
						<input type="date" id="birth" name="birth" max="<%=today%>" class="form-control" />
					</div>
				</div>
				
				
				<div class="form-group"> 
					<input type="submit" value="회원가입하러가기" class="btn2 btn-default  form-control"/>
				</div> <!-- end group -->
				<div class="form-group"> 					
					<input type="reset"   value="이전" class="btn2 btn-default form-control"/>
				</div> <!-- end group -->				
			</fieldset>
		</form>
	</div>
	
	
	<script>
 	$(document).ready(function(){
 		
 	
		$("#join").submit(function(){
		//	console.log('결과 text: ' + $(".idCheck_r").text());
			if( $("#jyp_email").val() ==""   ){  alert("빈칸입니다\n확인해주세요");   $("#jyp_email").focus();  return false; }
			if( $("#jyp_name").val() ==""   ){  alert("빈칸입니다\n확인해주세요");   $("#jyp_name").focus();  return false; }
			if( $("#jyp_pass").val() ==""   ){  alert("빈칸입니다\n확인해주세요");   $("#jyp_pass").focus();  return false; }
			if( $("#jyp_pass2").val() ==""   ){  alert("빈칸입니다\n확인해주세요");   $("#jyp_pass2").focus();  return false; }
			if( $("#jyp_pass").val() !=   $("#jyp_pass2").val() ){  alert("비밀번호가 다릅니다.\n확인해주세요");   $("#jyp_pass").focus();  return false; }
		//	if( $("#jyp_tel").val() ==""   ){  alert("빈칸입니다\n확인해주세요");   $("#jyp_tel").focus();  return false; }
			if( $(":radio[name='gender']:checked").length ==0   ){  alert("성별을 체크해주세요.");   $(":radio[id='female']").focus();  return false; }
			if( $("#birth").val() ==""   ){  alert("생년월일을 입력해주세요.");   $("#birth").focus();  return false; }
			if( $(".idCheck_r").text() !="사용 가능한 이메일"){ alert('아이디 중복확인을 해주세요.'); return false;}
			
			// 전화번호 
			$("#jyp_tel").val($("#tel1").val() + $("#tel2").val() + $("#tel3").val());  //조합시키기 
			console.log('jyp_no : ' + $("#jyp_no").val());
			if ($("#jyp_tel").val() == "" || $("#jyp_tel").val().length != 11 || isNaN($("#jyp_tel").val())) {
 					alert("휴대폰번호를 정확히 입력해 주세요");  return false;  }

			if (isNaN($("#jyp_tel").val())) {  alert("휴대폰번호를 숫자로만 입력해 주세요");  return false;  }

			for (var i=0; i<$("#jyp_tel").val().length; i++)  {

			     var chk = $("#jyp_tel").val().substring(i,i+1);

			     if(chk == " "){  alert("휴대폰번호를 정확히 입력해주세요");  return false;
			     }
			}

			

		}); //end submit
 	}); //end ready
		
 
 	
 		//아이디 중복체크 
		$(function(){
			$("#idDouble").click(function(){
				//중복체크 전 이메일이 맞는 형식인지 체크하기 (영문,숫자만 가능 )
				//이메일
			for (var i = 0; i < document.joinForm.jyp_email.value.length; i++) {
            chm = $("#jyp_email").val().charAt(i)
            if (!(chm >= '0' && chm <= '9') && !(chm >= 'a' && chm <= 'z')&&!(chm >= 'A' && chm <= 'Z')) {
                $(".idCheck_r").html('<span style="color:red">이메일은 영문 대소문자, 숫자만 입력가능합니다.</span>');
                $("#jyp_email").focus();
                return false;
	            }//end  inf
	        }//end for
					
				
				var jyp_email = document.joinForm.jyp_email.value +'@'+  $("select[name='jyp_email2'] option:selected").val()
				console.log('아이디 체크 : ' + jyp_email);
				if($("#jyp_email").val() =="" ){ $("#jyp_email").focus(); return false;}
				$.ajax({
					url:"${pageContext.request.contextPath}/idDouble.members",
					type:"get", dataType:"json",
					data:{"jyp_email": jyp_email },
					success:function(db){
						console.log(db);
						console.log($("#jyp_email").val());
						var data = "<span style='color:blue'>" + db.data+"</span>";
						if(db.data =="이미 가입된 이메일입니다."){ data = "<span style='color:red'>" + db.data+"</span>"; }
						$(".idCheck_r").html(data);
					},
					error:function(xhr, textStatus, errorThrown){
						$(".idCheck_r").html(textStatus+"(HTTP-" + xhr.status + "/" + errorThrown); }
				}); //end ajax
			}); //end click
			
			
		}); //end ready
		
		</script>



<!--            start footer          -->
<!--            start footer          -->
<!--            start footer          -->
<%@ include file = "/inc/footer.jsp" %>