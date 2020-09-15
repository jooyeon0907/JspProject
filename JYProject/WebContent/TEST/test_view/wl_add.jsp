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
</head>
<body>

<div class="container">
  <div class="panel-group" id="accordion">
  
    <div class="panel panel-default">
      <div class="panel-heading">
        <h4 class="panel-title">
          <a data-toggle="collapse" data-parent="#accordion" href="#wl_add2">업무리스트 추가</a>
        </h4>
      </div>
      <div id="wl_add2" class="panel-collapse collapse in">
        <div class="panel-body">
        	<form action="<%=request.getContextPath()%>/worklist_add.work" method="get">
        		<input type="text" name ="wl_name" placeholder="업무리스트 이름"/>
        	</form>
        </div>
      </div>
    </div>
 
  </div> 
</div> <!-- end container --> 
    
    
  <div class="container">
  <h2>Basic Modal Example</h2>
  
  <!-- Trigger the modal with a button -->
  <button class="wl_add text-center" data-toggle="modal" data-target="#wl_add"><span class="glyphicon glyphicon-plus"></span>새 업무리스트 만들기</button>

  <!-- Modal -->
  <div class="modal fade" id="wl_add" role="dialog">
    <div class="modal-dialog">
    
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title">업무리스트 추가</h4>
        </div>
        <div class="modal-body">
          <form>
				<input type="text" name="wl_name"  class="form-control" placeholder ="업무리스트 이름"/>
				<button type="submit" class="btn btn-danger" data-dismiss="modal">추가</button>
				<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
		  </form>
        </div>
    
      </div>
      
    </div>
  </div>
  
  
  
</div>  
    
    
    
    
    
</body>
</html>


