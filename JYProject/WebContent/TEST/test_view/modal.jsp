<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix ="c" uri="http://java.sun.com/jsp/jstl/core" %> <!-- 태그 라이브러리 -->
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel ="stylesheet" 
	href = "https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
	
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>


</head>
<body>


<div class="container">
 
  <!-- Trigger the modal with a button -->
 <span class="glyphicon glyphicon-envelope" title="메일보내기"  id="emailBtn"></span>
  <!-- Modal -->
  <div class="modal fade" id="sendEmail" role="dialog">
    <div class="modal-dialog">
    
      <!-- Modal content-->
      <div class="modal-content" style="width: 275px;">
      <form>
      <fieldset>     
         <div class="modal-body">
	          <button type="button" class="close" data-dismiss="modal">&times;</button>
	          <h4><label for="wl_name"> 업무리스트 이름 수정 </label></h4>
	          <p><input type="text" id="wl_name" name="wl_name"  /></p>
       	 </div><!-- end  modal-body-->
     	 <div class="modal-footer">
	     	  <input type="submit" class="btn btn-info" value="수정"/>
	          <button type="button" class="btn btn-default" data-dismiss="modal">닫기</button>
         </div> <!--  end  modal-footer-->
       </fieldset> 
 	  </form>
      </div><!--  end  modal-content-->
      
    </div>
  </div>
</div>
 
<script>
$(document).ready(function(){
  $("#emailBtn").click(function(){
    $("#sendEmail").modal("toggle");
  });
});
</script>



</body>
</html>

