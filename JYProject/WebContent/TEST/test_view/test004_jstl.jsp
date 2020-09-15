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
	<div class="container panel panel-info">
		<h3 class="panel-heading">JDBC+DB</h3>
		<table class="table table-striped">
		<caption>item</caption>
			<thead>
				<tr>
					<th scope="col">NO</th> <th scope="col">NAME</th> <th scope="col">AGE</th>
				</tr>
			</thead>
			
			<tbody>
				<c:forEach var="dto" items="${list}" >
					<tr>
						<td>${dto.no}</td>
						<td>${dto.name}</td>
						<td>${dto.age}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div><!--end container-->
	
	<div class="container panel panel-info">
		<h3 class="panel-heading">아코디언 ver</h3>
		<!--  https://www.w3schools.com/bootstrap/bootstrap_ref_js_collapse.asp    -->
	     
	<%--  	 <c:forEach var="dto" items="${list}" varStatus="status">
	 	 <div class="panel-group">
	     
		    <div class="panel panel-default">
		      <div class="panel-heading">
		        <h4 class="panel-title">
		          <a data-toggle="collapse" href="#collapse${dto.no}">${dto.name }</a>
		        </h4>
		      </div>
		      <div id="collapse${dto.no}" class="panel-collapse collapse">
		        <div class="panel-body">
		        	<p>NO : ${dto.no }</p>
		        	<p>NAME : ${dto.name }</p>
		        	<p>AGE : ${dto.age }</p>
		        </div>
		      </div>
		    </div>
		        </div>
	 	 </c:forEach> --%>
	 	 
	 	 
	 	   <div class="panel-group" id="accordion">
		    <c:forEach var="dto" items="${list}" varStatus="status">
	
				    <div class="panel panel-default">
				      <div class="panel-heading">
				        <h4 class="panel-title">
				          <a data-toggle="collapse" data-parent="#accordion" href="#collapse${dto.no}">${dto.name }</a>
				        </h4>
				      </div>
				      <div id="collapse${dto.no }" class="panel-collapse collapse <c:if test="${status.first}">in</c:if>">
				        <div class="panel-body"> ${dto.age }</div>
				      </div>
				    </div>    
				    
			</c:forEach> 
		</div> 

	</div><!-- end container -->
	
	
	

		
		
	<div class="container panel panel-info">
		<h3 class="panel-heading">modal ver</h3>
		<c:forEach var="dto" items="${list}" varStatus="status">
		
			<button type="button" class="btn btn-info btn-lg" data-toggle="modal" data-target="#myModal${dto.no } ">${dto.name }</button>
	
				  <!-- Modal -->
			  <div class="modal fade" id="myModal${dto.no }" role="dialog">
			    <div class="modal-dialog">
			    
			      <!-- Modal content-->
			      <div class="modal-content">
			        <div class="modal-header">
			          <button type="button" class="close" data-dismiss="modal">&times;</button>
			          <h4 class="modal-title">${dto.name }</h4>
			        </div>
			        <div class="modal-body">
			          <p>${dto.age }</p>
			        </div>
			        <div class="modal-footer">
			          <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
			        </div>
			      </div>
			      
			    </div>
	 		 </div>
		
		</c:forEach>
		
	</div>	
	
	
		
</body>
</html>

