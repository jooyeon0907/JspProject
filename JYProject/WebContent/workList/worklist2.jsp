<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix ="c" uri="http://java.sun.com/jsp/jstl/core" %> <!-- 태그 라이브러리 -->
<%@ include file = "/inc/work_header.jsp" %>
<!--            end header            -->
<!--            end header            -->
<!--            end header            -->

	
	<div class="container myconatiner wl main" >
	<!-- 	<div class="wl main"> -->
		<!--     <div class="main_1 main_common"><p class="content">test1</p></div>
		    <div class="main_2 main_common"><p class="content">test2</p></div>
		    <div class="main_3 main_common"><p class="content">test3</p></div> -->
		   <%--  <c:forEach var="dto" begin="1" end="5" varStatus="status"> --%>
	<div class="row">
		   <c:forEach var="dto" begin="1" end="6" varStatus="status"> 
		    
		    	<div class="main_${status.count} main_common">
		    	
		    	

			 
		    	<!-- #accordion -->
		    				<div class="col-sm-4 well">
		    				<div class="panel panel-default">
		    			
						      <div class="panel-heading" style="background-color: #154566;">
						        <h4 class="panel-title" >
						          <a data-toggle="collapse" data-parent="#accordion" href="#collapse${status.count}">${status.count}</a>
						        </h4>
						       
						      </div>
						      
						      <div id="collapse${status.count}" class="panel-collapse collapse in">
						       <!-- 업무내용 -->
						        	<%-- <div class="panel-body"> ${status.count }</div> --%>
						        	 <c:forEach var="dto2"  begin="1" end="3" varStatus="status">
						        		<p>dsadsadasd</p>
						        	</c:forEach> 
						        <!-- end 업무내용 -->
						        
						       		
						      </div>
						    </div>    </div> <!-- end col --> 
		    	
		    	<!-- end #accordion -->
		    	</div> <!-- .main_common -->
		    </c:forEach> </div><!-- end row -->
		 		<!-- 업무리스트 추가하기 버튼  -->
		 		<div class="main_common ">
		 			<button class="wl_add text-center" data-toggle="modal" data-target="#work_add"><span class="glyphicon glyphicon-plus"></span>새 업무리스트 만들기</button>
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
		 
<!-- 		</div> end wl main
 -->

	</div><!--end container-->
	
	
<!--            start footer          -->
<!--            start footer          -->
<!--            start footer          -->
</body>
</html>

