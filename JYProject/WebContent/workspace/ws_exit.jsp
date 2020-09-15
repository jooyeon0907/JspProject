<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix ="c" uri="http://java.sun.com/jsp/jstl/core" %> <!-- 태그 라이브러리 -->
<!DOCTYPE html>

<%-- 
		      <!-- Modal content-->
		      
		      <div class="modal-content">
		        <div class="modal-header" style="padding:5px; background-color:red;">
		          <button type="button" class="close" data-dismiss="modal">&times;</button>
		          <h4  style="background-color:red;">워크스페이스 탈퇴</h4>
		        </div>
		        <div class="modal-body" style="padding:40px 50px;">
		        	<strong style="color:red">'${ws_name}' 워크스페이스를 탈퇴하시겠습니까?</strong>
								
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
			       	
			              <input type="submit" class="btn mybtn btn-danger form-control" value="탈퇴하기" id="btn_del" />
		              </fieldset>
		          </form> 
		          
		          
		        </div>
		    
		      </div> <!--end Modal content-->
		      
		      
		      		      <!-- Modal content-->

 --%>
		      
		      
		      
		      