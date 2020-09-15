<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix ="c" uri="http://java.sun.com/jsp/jstl/core" %> <!-- 태그 라이브러리 -->
<!DOCTYPE html>
<html lang="en">
<head>
  <!-- Theme Made By www.w3schools.com - No Copyright -->
  <title>Bootstrap Theme The Band</title>
  <meta charset="utf-8">
  
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
  <style>

 .col-sm-4.well { width: 31.3333333%; margin-left: 1%; margin-right: 1%; }
  
 .col-sm-3.well { width: 23%; margin-left: 1%; margin-right: 1%; }

 .carousel-inner > .item > img,
  .carousel-inner > .item > a > img {
    width: 70%;
    margin: auto;
  }
  
  
.sl_padding {
    padding-right: 5%;
    padding-left: 5%;
}
  
.sl {
    height: 80%;
    width: 70%;
}
  
  </style>
</head>
<body>

		<div class="container panel panel-info">
		  <h3 class="panel-heading">IMG BOARD -VIEW1 (GALLERY) </h3>

		  <div class="row">
		  
			  <c:forEach var="dto" items="${users}" varStatus="status">
			    <div class="col-sm-4 well">
			      <p>NO - <strong>${dto.no }</strong></p>
			      <p>NAME - <strong>${dto.name }</strong></p>
			      <p>AGE - <strong>${dto.age }</strong></p>
			      <p><img src="/upload/${dto.file}" alt="${dto.file}" ></p>
			    </div>
			  </c:forEach>
			  </div>

		</div>
		
		<!-- 																				 -->
		<!-- 																				 -->
		<!-- 																				 -->
		<!-- 																				 -->
		
		<div class="container panel panel-info">
		  <h3 class="panel-heading">IMG BOARD -VIEW2 (GALLERY) 4단 갤러리</h3>

		  <div class="row">

		  <c:forEach var="dto" items="${users}" varStatus="status">		  
		    <div class="col-sm-3 well">
		      <p><span class="glyphicon glyphicon-ok"></span> NO - <strong>${dto.no}</strong></p>
		      <p><span class="glyphicon glyphicon-ok"></span> NAME - <strong>${dto.name}</strong></p>
		      <p><span class="glyphicon glyphicon-ok"></span> AGE - <strong>${dto.age}</strong></p>
		      <img src="/upload/${dto.file}" alt="${dto.file}"   class="img-thumbnail" >
		    </div>
		  </c:forEach>
		  
		  </div> <!-- end row -->
		</div>
		
		
		<!-- 																				 -->
		<!-- 																				 -->
		<!-- 																				 -->
		<!-- 																				 -->
		
		
		<div class="container panel panel-info text-center">
		  <h3 class="panel-heading">IMG BOARD -VIEW2 : SLIDING VINDOW1 - 1단</h3>

			  <div id="myCarousel" class="carousel slide" data-ride="carousel">
			    <!-- Indicators -->
			    <ol class="carousel-indicators">
			    	 <c:forEach var="dto" items="${users}" varStatus="status">		
			    		<li data-target="#myCarousel" data-slide-to=${status.index } <c:if test="${status.first}"> class="active"</c:if>></li>
			    	</c:forEach>			      
			    </ol>
			
			    <!-- Wrapper for slides -->
			    <div class="carousel-inner" role="listbox">
			
				<c:forEach var="dto" items="${users}" varStatus="status">	
			      <div  class="item<c:if test="${status.first}"> active</c:if>">

			        <img src="/upload/${dto.file}" alt="${dto.file}" width="460" height="345" >
			        <div class="carousel-caption">
			          <h3>${dto.name}</h3>
			          <p>userinfo</p>
			        </div>
			      </div>
			</c:forEach>

			    </div>
			
			    <!-- Left and right controls -->
			    <a class="left carousel-control" href="#myCarousel" role="button" data-slide="prev">
			      <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
			      <span class="sr-only">Previous</span>
			    </a>
			    <a class="right carousel-control" href="#myCarousel" role="button" data-slide="next">
			      <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
			      <span class="sr-only">Next</span>
			    </a>
			  </div>
			</div>
		
		
		<!-- 																				 -->
		<!-- 																				 -->
		<!-- 																				 -->
		<!-- 																				 -->
		<!-- 
		 <div class="item active">
           <div class="col-sm-6">
             block1
           </div>
           <div class="col-sm-6">
             block2
           </div>
         </div>
		 -->
		
		<div class="container panel panel-info text-center">
		  <h3 class="panel-heading">IMG BOARD -VIEW2 : SLIDING WINDOW2 - 4단</h3>

			  <div id="myCarousel2" class="carousel slide" data-ride="carousel"   style="margin-bottom: 25px;">
			  
			    <!-- Indicators -->
			    <ol class="carousel-indicators">
			    	 <c:forEach var="dto" items="${users}" varStatus="status">		
			    		<li data-target="#myCarousel2" data-slide-to=${status.index } <c:if test="${status.first}"> class="active"</c:if>></li>
			    	</c:forEach>			      
			    </ol>
			
			    <!-- Wrapper for slides -->
			    <div class="carousel-inner sl_padding" role="listbox">
			<div class="row">
				<c:forEach var="dto" items="${users}" varStatus="status">	
			      <div  class="item<c:if test="${status.first}"> active</c:if>">   
						<div class="col-sm-3">
					        <img src="/upload/${dto.file}" alt="${dto.file}"   class="sl">
					        <div class="carousel-caption">
					          <h3>${dto.name}   / ${status.index }</h3>
					          <p>userinfo</p>
					        </div>						
						</div>
			      </div> 
				</c:forEach>
			</div><!-- end row -->
			    </div>
			
			    <!-- Left and right controls -->
			    <a class="left carousel-control" href="#myCarousel2" role="button" data-slide="prev">
			      <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
			      <span class="sr-only">Previous</span>
			    </a>
			    <a class="right carousel-control" href="#myCarousel2" role="button" data-slide="next">
			      <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
			      <span class="sr-only">Next</span>
			    </a>
			  </div>
			</div>
		
		<!-- 
		
		0 1 2 3
		4(start) 5 6 7(end=>start+3)
		8(start) 9 10 11(end=>start+3)
		 -->
</body>
</html>

