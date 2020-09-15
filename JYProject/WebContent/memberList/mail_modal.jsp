<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix ="c" uri="http://java.sun.com/jsp/jstl/core" %> <!-- 태그 라이브러리 -->


<!-- 
<span class="glyphicon glyphicon-envelope" title="메일보내기"  data-toggle="modal" data-target="#sendEmail"></span>
-->

	  <!-- Modal -->
	  <div class="modal fade" id="sendEmail" role="dialog">
	    <div class="modal-dialog">
	    
	      <!-- Modal content-->
	      <div class="modal-content" style="width: 600px;">
	      
			<!-- 메일보내기 폼 -->
			<!-- 메일보내기 폼 -->
		<form action="${pageContext.request.contextPath}/email.mem" method="post">
		<fieldset style="width: 573px; padding: 2px;">
			<legend>메일 보내기</legend>
			
			<div class="row form-group">
				<div class="col-sm-3"> <label for="get_email">받는 : EMAIL</label> </div><!-- end col-sm-3 -->
				<div class="col-sm-9"> <input type="text" id="get_email" name="get_email" class="form-control"/>  </div><!-- end col-sm-9 -->
			</div><!-- end row -->
			
			<div class="row form-group">
				<div class="col-sm-3"> <label for="send_email">보내는 : EMAIL</label> </div><!-- end col-sm-3 -->
				<div class="col-sm-9"> <input type="text" id="send_email" name="send_email" class="form-control"/>  </div><!-- end col-sm-9 -->
			</div><!-- end row -->
			
			<div class="row form-group">
				<div class="col-sm-3"> <label for="send_pass">비밀번호</label> </div><!-- end col-sm-3 -->
				<div class="col-sm-9"> <input type="password" id="send_pass" name="send_pass" class="form-control"/>  </div><!-- end col-sm-9 -->
			</div><!-- end row -->
			
			<div class="row form-group">
				<div class="col-sm-3"> <label for="subject">TITLE</label> </div><!-- end col-sm-3 -->
				<div class="col-sm-9"> <input type="text" id="subject" name="subject" class="form-control"/> </div><!-- end col-sm-9 -->
			</div><!-- end row -->
			
			<div class="row form-group">
				<div class="col-sm-3"> <label for="content">CONTENT</label> </div><!-- end col-sm-3 -->
				<div class="col-sm-9"> <textarea id="content" name="content" rows="10" cols="10" class="form-control"></textarea> </div><!-- end col-sm-9 -->
			</div><!-- end row -->
			
			<div class="row form-group">
				<div class="col-sm-3"> </div><!-- end col-sm-3 -->
				<div class="col-sm-9"> <input type="submit" value="메일발송하기" id="sendMail" class="form-control btn btn-danger"/> </div><!-- end col-sm-9 -->
			</div><!-- end row -->
		</fieldset>
		</form>
	
	 <script>
           CKEDITOR.replace( 'content' );

      </script>
			<!-- 메일보내기 폼 -->
			<!-- 메일보내기 폼 -->
	 	  
	      </div><!--  end  modal-content-->
	      
	    </div>
	  </div>
	
	<script src="https://cdn.ckeditor.com/4.14.1/standard/ckeditor.js"></script>  <!-- #### -->
	 


<!-- 
IMAP 서버명 : imap.naver.comSMTP 서버명 : smtp.naver.comIMAP 
필요SMTP 포트 : 587, 
아이디 : jooyeon0907
비밀번호 : 네이버 로그인 비밀번호
 -->