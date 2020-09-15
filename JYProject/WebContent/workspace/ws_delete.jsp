<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix ="c" uri="http://java.sun.com/jsp/jstl/core" %> <!-- 태그 라이브러리 -->
<!DOCTYPE html>


		      <!-- Modal content-->
		      
		      <div class="modal-content">
		        <div class="modal-header" style="padding:5px; background-color:red;">
		          <button type="button" class="close" data-dismiss="modal">&times;</button>
		          <h4  style="background-color:red;">워크스페이스 삭제</h4>
		        </div>
		        <div class="modal-body" style="padding:40px 50px;">
		        	<strong style="color:red">'${ws_name}' 워크스페이스를 삭제하겠습니까?</strong>
								
 		          <form action="${pageContext.request.contextPath}/ws_delete.ws" method="post"> 
 		          	<fieldset>
			            <div class="form-group">
			              <label for="jyp_email">이메일</label>
			              <input type="text" class="form-control" id="jyp_email" name="jyp_email" />
			            </div>
			            <div class="form-group">
			              <label for="jyp_pass">비밀번호 </label>
			              <input type="password" class="form-control" id="jyp_pass" name="jyp_pass" >
			            </div>
			       	
			              <input type="submit" class="btn mybtn btn-danger form-control" value="삭제하기" id="btn_del" />
		              </fieldset>
		          </form> 
		          
		          		          

		          
		          
		        </div>
		    
		      </div> <!--end Modal content-->
		      
		      
		      		      <!-- Modal content-->

		      
		      
		      
<!-- 		      
		      <script>
		      $(function(){
		    	  $("#btn_del").click(function(){
		    		 $.ajax({
		    			 url:"${pageContext.request.contextPath}/ws_delete.ws",
		    			 type:"post", dataType:"text",
		    			 data:{"jyp_email":$("#jyp_email").val() , "jyp_pass": $("#jyp_pass").val()},
		    			 success:function( data ){
		    				 console.log(data);
		    				 var result = data;
		    				 console.log(result);
		    				// alert(data);
		    				if(result=="워크스페이스 삭제 완료"){
		    					alert('워크스페이스 삭제 완료');
		    					location.href='${pageContext.request.contextPath}/ws_list.ws';
		    				}else if(result=="워크스페이스 삭제 ERROR"){
		    					alert('워크스페이스 삭제 ERROR');
		    					return false;
		    				}else if(result=="일치되는 정보가 없습니다."){
		    					alert('일치되는 정보가 없습니다.');
		    					return false;
		    				}
		    			 },
		    			 error:function( xhr, textStatus, errorThrown){
		    				 alert(textStatus + "(HTTP-" + xhr.status +"/" +errorThrown);
		    			 }
		    		 });  //end ajax
		    	  }); //end click
		      }); //end ready
		      
		      </script>
		      -->
		      
		      
		      
		      
		      
		      