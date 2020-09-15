<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix ="c" uri="http://java.sun.com/jsp/jstl/core" %> <!-- 태그 라이브러리 -->
<!DOCTYPE html>
<html>
<head>
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
  <style>
  .modal-header, h4, .close {
    background-color: #154566;
    color:white !important;
    text-align: center;
    font-size: 30px;
  }
  
  .btn-default.p_btn {
    width: 20%;
    height: 60px;
}
  
 .mybtn{ 
 background-color: #154566;
  color:white !important;}
  
 
  </style>
</head>
<body>

<div class="container">
 <!--  <h2>Modal Login Example</h2> -->
  <!-- Trigger the modal with a button -->
  <button type="button" class="btn btn-default p_btn" id="myBtn">새 프로젝트 추가</button>

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
          <form role="form"> <!--프로젝트 생성 컨트롤러로 보내기 p_add.pro -->
            <div class="form-group">
              <label for="usrname">프로젝트 제목</label>
              <input type="text" class="form-control" id="usrname" placeholder="예)웹사이트 디자인">
            </div>
            <div class="form-group">
              <label for="psw">설명 <span style="color:gray">(선택사항)</span></label>
              <input type="text" class="form-control" id="psw" >
            </div>
            <div class="checkbox">
              <input type="button" value="멤버추가" >
            </div>
              <button type="submit" class="btn mybtn btn-block">프로젝트만들기</button>
          </form>
        </div>
    
      </div>
      
    </div>
  </div> 
</div><!-- end container -->
 
<script>
$(document).ready(function(){
  $("#myBtn").click(function(){
    $("#myModal").modal();
  });
});
</script>

</body>
</html>


