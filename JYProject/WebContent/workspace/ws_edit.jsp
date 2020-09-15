 <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix ="c" uri="http://java.sun.com/jsp/jstl/core" %> <!-- 태그 라이브러리 -->
<%@ include file = "/inc/work_header.jsp" %>
<!--            end header            -->
<!--            end header            -->
<!--            end header            -->

<!-- 워크스페이스 수정  -->
	<div class="container myconatiner">
		<h3>워크스페이스 설정</h3>
			<div>
				<h4>워크스페이스 이름</h4>
 			<form action="${pageContext.request.contextPath}/ws_edit.ws" method="post">
			<fieldset>
				<div class="form-group">
					<label for="ws_name"> </label>	
						<c:choose>
							<c:when test="${ws_jypno==jyp_no}">
								<input type="text" value="${ws_name}" name= "ws_name" id="ws_name" />
							</c:when>
							<c:otherwise>
								<input type="text" value="${ws_name}" name= "ws_name" id="ws_name" readonly/>
							</c:otherwise>
						</c:choose>
							<input type="submit" value="수정하기"/>
				</div>				
			</fieldset>
			</form>	 
			</div> <!-- end 이름 수정  -->
			
			<div>
				<c:if test="${not empty dto.address}">
				<h4>회사 위치</h4>
				<%@include file="map.jsp" %>
				</c:if>
				
				<!-- 관리자만 회사 위치 등록할 수 있음  -->
				<c:if test="${ws_jypno==jyp_no}">
				<strong>회사 위치 등록하기</strong> 
				<button type="button" class="btn btn-info" data-toggle="collapse" data-target="#demo">위치등록</button>
					<div  id="demo" class="collapse"
					style="max-height: 500px; height: auto;  width: 600px; overflow-x: hidden; overflow-y: auto; background-color:#D8D8D8;">
					<%@include file="address.jsp" %></div>
				</c:if>  
				</div><!-- end 회사 위치등록 -->
				
			
			
			
			
	</div><!--end container-->		
	
	
	
	<!--        			 --><!--        			 --><!--        			 -->
	<!--        			 --><!--        			 --><!--        			 -->
	
	<div class="container myconatiner">
		<c:choose>
			<c:when test="${ws_jypno==jyp_no}">
				<h3>워크스페이스 삭제</h3>
				 <!-- Trigger the modal with a button -->
				<pre>
				
					워크스페이스를 삭제하면 모든 팀원이 워크스페이스에 접속할 수 없게됩니다.
					 삭제 버튼을 클릭하면, 만약의 경우에 대비하여 워크스페이스를 복원할 수 있도록 14일의 기간이 주어집니다.
					  <button type="button" class="btn btn-danger" data-toggle="modal" data-target="#wsDel_btn" >워크스페이스 삭제</button>
				</pre>
			  <!-- Modal -->
			   <div class="modal fade" id="wsDel_btn" role="dialog">
			     <div class="modal-dialog">
				<%@ include file ="ws_delete.jsp" %>		    
		   		 </div>
		  	    </div> 
			</c:when>
			
			<c:otherwise>
				<h3>워크스페이스 나가기</h3>
					  <button type="button" class="btn btn-danger"  data-toggle="modal" data-target="#delete_btn">워크스페이스 나가기</button>		
			  <!-- Modal -->
			   <div class="modal fade" id="delete_btn" role="dialog">
			     <div class="modal-dialog">
			     <%@ include file ="ws_memDelete.jsp" %>	
		   		 </div>
		  	    </div> 			
			</c:otherwise>
		</c:choose>
						
							
			
		 
				
		</div><!--end container-->	
		

	
<!-- 	
<script>
$(document).ready(function(){
	
  $("#wsDel_btn").on('click',function(){/// alert('dddd'); $("#new_pro1").modal(); });  ///click
  
  $("#delete_btn").on('click',function(){ $("#wsMem_delete").modal(); });
  
  
  }); // end ready
  
  

</script> -->
	
<!--            start footer          -->
<!--            start footer          -->
<!--            start footer          -->
</body>
</html>



