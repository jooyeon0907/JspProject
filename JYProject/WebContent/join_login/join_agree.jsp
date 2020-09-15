<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include  file="/inc/login_header.jsp" %>
<div class="container"   style="margin-top:5%"> 
  <h3>FORM BASIC - JOIN</h3>	  
		<h4  class="col-sm-11">Company 서비스 약관 동의</h4>
		<div class="col-sm-1  text-right">
			<input type="checkbox" name="agree" id="agree1" title="Company 서비스 약관 동의 체크박스입니다." class="form-control">
		</div> 
		<div>	
		  <textarea cols="60" rows="10" id="agreement1" class="form-control" title=" 이용약관동의입니다." 
		  readonly="readonly" disabled="disabled"><%@ include  file="agree1.txt" %></textarea>
		</div><!-- form-group  END -->
 		
 		<hr/>
		<h4   class="col-sm-11"> Company 개인정보 수집 및 이용 동의</h4> 
		<div class="col-sm-1 text-right">
			<input type="checkbox" name="agree" id="agree2" title="Company 개인정보 수집 및 이용 동의 체크박스입니다." class="form-control">
		</div> 
		<div>	
		  <textarea cols="60" rows="10" id="agreement2" class="form-control" title=" 이용약관동의입니다." 
		  readonly="readonly" disabled="disabled"> <%@ include  file="agree2.txt" %></textarea>
		 </div>
		 <hr/>
		<div class="text-center">
			<a href="<%=request.getContextPath()%>/join_view.members"  class="btn  btn-default  btn-lg"   id="join1">동의하기</a>
		</div> 
	<script>
		$(document).ready(function(){
			$("#join1").click(function(){ 
				if( $("#agree1:checked").length == 0 ){ alert("약관에 동의하셔야합니다.");  $("#agree1").focus(); return false; }
				if( $("#agree2:checked").length == 0 ){ alert("약관에 동의하셔야합니다."); $("#agree2").focus(); return false; }
			});
		});
		
	</script>      
</div>
<%@ include  file="/inc/footer.jsp" %>