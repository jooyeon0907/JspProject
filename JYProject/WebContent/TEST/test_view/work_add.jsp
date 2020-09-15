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
  <h2>Basic Modal Example</h2>
  
  
  
  <!-- Trigger the modal with a button -->
  <button  data-toggle="modal" data-target="#work_add">Open Modal</button>
  <!-- Modal -->
  <div class="modal fade" id="work_add" role="dialog">
    <div class="modal-dialog">
    
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title">업무추가</h4>
        </div>
        <div class="modal-body">
        
         <!-- 업무추가 -->
         	<div class="container"  style="margin-top:5%; min-height:500px; width:500px">
		<form action="<%=request.getContextPath()%>/work_add.work" method="post"  id="add_Form" >
		   <fieldset>
		 <!--   // 	insert into jyp_work (work_title,work_content, work_start, work_end) values (?,?,?,?) -->
		 <!--   <legend>업무추가</legend> -->																	
			<div class="form-group">
			  <label for="work_title"  >제목</label>
			  <input type="text"   name="work_title"   id="work_title"   class="form-control" > 
			</div>	
			<div class="form-group">
			  <label for="work_content"  >업무 내용</label>
			  <textarea name="work_content"  id="work_content"  cols="60"  rows="10"   class="form-control" ></textarea>
			</div>		
			
			<div class="form-group">
				<p>파일첨부(X)</p>
			</div>
			<div class="form-group">
				<p>멤버초대(X)</p>
			</div>
			
			<div class="form-group">
				<label for="work_start">업무 시작일</label>
				<input type="date" id="work_start" name="work_start" />
			</div>
			
			<div class="form-group">
				<label for="work_end">업무 마감일</label>
				<input type="date" id="work_end" name="work_end" />
			</div>		
			
			<div class="form-group  text-right">
				<input type="submit"   value="입력"  class="btn btn-default"  style="color:white; background-color:#f4511e"   >  
				 <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
			</div>
		 </fieldset>		
		</form> <!-- end form -->	
			</div><!-- end container -->
         
          <!-- end 업무추가 -->
        </div><!-- <div class="modal-body"> -->
        
        <!-- <div class="modal-footer">
          <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        </div>end footer -->
      </div>
      
    </div>
  </div> <!-- end modal -->
  
  
  
  
  
  
</div>

</body>
</html>

