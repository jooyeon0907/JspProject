<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix ="c" uri="http://java.sun.com/jsp/jstl/core" %> <!-- 태그 라이브러리 -->
<%@ include file = "/inc/work_header.jsp" %>
<!--            end header            -->
<!--            end header            -->
<!--            end header            -->
<%@ include file = "/inc/ws_project.jsp" %> <!-- 프로젝트 카데고리 헤더 -->
	
<script>
$(document).ready(function(){
  $("#myBtn").click(function(){
    $("#myModal").modal();
  });
  

 // console.log( $(":radio[name='p_public']") ); 
 // console.log( $(":radio[name='p_public']:checked").length ); 
  $("#pro_form").submit(function(){
	  if( $(":radio[name='p_public']:checked").length==0 ){
		  alert('공개여부를 선택해주세요.');
		  return false;
	  }
  });
  
  
});

</script>

	<div class="container myconatiner2">
		<h3>프로젝트</h3>
	
		<div class="container">
		
		
		
			<!-- start  내가 속한 프로젝트(관리자)--><!-- start  내가 속한 프로젝트(관리자)--><!-- start  내가 속한 프로젝트(관리자)-->
			<div class="panel-group" id="accordion1">
					 <div class="panel panel-default">
				 
				      <div class="panel-heading">
				          <a data-toggle="collapse" data-parent="#accordion1" href="#mypro_admin"
				          	style="color: #171717; font-size: 16px; font-weight: bold;">내가 속한 프로젝트(관리자)</a>
				      </div> <!-- end heading-->
				      <div id="mypro_admin" class="panel-collapse collapse in">
				        <div class="panel-body">
				        	 <%@include file="pro_admin.jsp" %>
				        </div><!-- end  panel-body-->
				      </div><!-- end  panel-collapse-->
				    </div>     <!-- end  panel-default-->    
			</div> <!-- end  accordion-->
			<!-- end 내가 속한 프로젝트(관리자)--><!-- end 내가 속한 프로젝트(관리자)--><!-- end 내가 속한 프로젝트(관리자)-->
			
			
			
			<!-- start 내가 속한 프로젝트(멤버)--><!-- start 내가 속한 프로젝트(멤버)--><!-- start 내가 속한 프로젝트(멤버)-->
			<div class="panel-group" id="accordion2">
					 <div class="panel panel-default">
				 
				      <div class="panel-heading">
				          <a data-toggle="collapse" data-parent="#accordion2" href="#mypro_member"
				          	style="color: #171717; font-size: 16px; font-weight: bold;">내가 속한 프로젝트(멤버)</a>
				      </div> <!-- end heading-->
				      <div id="mypro_member" class="panel-collapse collapse in">
				        <div class="panel-body">
				        	 <%@include file="pro_member.jsp" %>
				        </div><!-- end  panel-body-->
				      </div><!-- end  panel-collapse-->
				    </div>     <!-- end  panel-default-->    
			</div> <!-- end  accordion-->
			<!-- end 내가 속한 프로젝트(멤버)--><!-- end 내가 속한 프로젝트(멤버)--><!-- end 내가 속한 프로젝트(멤버)-->
			
			
			
			
			
			
			<!-- start 그 외 프로젝트 -->	<!-- start 그 외 프로젝트 -->	<!-- start 그 외 프로젝트 -->	<!-- start 그 외 프로젝트 -->
			<div class="panel-group" id="accordion3">
						 <div class="panel panel-default">
					 
					      <div class="panel-heading">
					          <a data-toggle="collapse" data-parent="#accordion3" href="#pro_etc"
					          	style="color: #171717; font-size: 16px; font-weight: bold;">그 외 프로젝트</a>
					      </div> <!-- end heading-->
					      <div id="pro_etc" class="panel-collapse collapse in">
					        <div class="panel-body">
					        	 <%@include file="pro_etc.jsp" %>
					        </div><!-- end  panel-body-->
					      </div><!-- end  panel-collapse-->
					    </div>     <!-- end  panel-default-->    
				</div> <!-- end  accordion-->
			<!-- end 그 외 프로젝트 -->	<!-- end 그 외 프로젝트 -->	<!-- end 그 외 프로젝트 -->	<!-- end 그 외 프로젝트 -->
			
			
	
		<!-- 프로젝트 add -->
		<!-- 프로젝트 add -->
		 <!--  <button type="button" class="btn btn-default p_btn" id="myBtn">
		  	<span class="glyphicon glyphicon-plus"></span>새 프로젝트 추가</button> -->
		<!-- 버튼은 프로젝트 헤더에 있음 (/inc/ws_project.jsp)-->
		
		  <!-- Modal -->
		  <div class="modal fade" id="myModal" role="dialog">
		    <div class="modal-dialog">
		    
		      <!-- Modal content-->
		      <div class="modal-content">
		        <div class="modal-header" style="padding:35px 50px;">
		          <button type="button" class="close" data-dismiss="modal">&times;</button>
		          <h4>새 프로젝트</h4>
		        </div>
		        <div class="modal-body" style="padding:40px 50px;">
		          <form action="<%=request.getContextPath()%>/p_add.pro" method="post" id="pro_form"> <!--프로젝트 생성 컨트롤러로 보내기 p_add.pro -->
		            
		            <div class="form-group">
		              <label for="p_name">프로젝트 제목</label>
		              <input type="text" class="form-control" id="p_name" name ="p_name" placeholder="예)웹사이트 디자인">
		            </div>
		            
		            <div class="form-group">
		              <label for="p_info">설명 <span style="color:gray">(선택사항)</span></label>
		              <input type="text" class="form-control" id="p_info" name="p_info">
		            </div>
		            
		            <div class="form-group">
		            	<p>공개 범위 설정</p>
		            	<div>
		            		<input type="radio" name="p_public" value ="n" id="n"/>
		            		<span class="glyphicon glyphicon-eye-close"></span>
		            			<label for="n">비공개<span style="color:gray; font-weight:lighter;">(추가된 멤버만 엑세스 가능)</span></label>
		            	</div>
		            	<div>	
		            		<input type="radio" name="p_public" value ="y" id="y"/>
		            		<span class="glyphicon glyphicon-eye-open"></span>
		            			<label for="y">공개<span style="color:gray; font-weight:lighter;">(모든 워크스페이스 멤버 엑세스 가능)</span></label>
		            	</div>
		            </div>
		       
		              <button type="submit" class="btn mybtn btn-block">프로젝트만들기</button>
		          </form>
		        </div>
		    
		      </div>
		      
		    </div>
		  </div> 
		</div><!-- end container -->
		<!-- end 프로젝트 add -->
		<!-- end 프로젝트 add -->


	</div><!--end container-->

<!--            start footer          -->
<!--            start footer          -->
<!--            start footer          -->
</body>
</html>
