 <%@page import="com.google.gson.JsonObject"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix ="c" uri="http://java.sun.com/jsp/jstl/core" %> <!-- 태그 라이브러리 -->
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> <!-- jstl 줄바꿈 -->
<%@ include file = "/inc/work_header.jsp" %>
<!--            end header            -->
<!--            end header            -->
<!--            end header            -->

<%
pageContext.setAttribute("br", "<br/>");
pageContext.setAttribute("cn", "\n");
%>
<script>
$(document).ready(function(){
	$("#work_form").submit(function(){
		if($("#work_content").val()=="" ) { 
			alert('업무 내용을 입력해주세요'); 
			$("#work_content").focus();
			return false;}
	});

	$("#wl_form").submit(function(){
		if($("#wl_name").val()=="" ) { 
			alert('업무리스트 이름을 입력해주세요'); 
			$("#wl_name").focus();
			return false;}
	});	 
	
	
	$("#wl_delBtn").click(function(){
		var result = confirm('업무리스트를 삭제하시겠습니까? \n 업무리스트를 내 데이터는 영구삭제됩니다.');
		
		if(result){
			$(location).attr('href','${pageContext.request.contextPath}/worklist_delete.work?wl_no=${dto.wl_no}');
		}else{return false;}	
	}); //end click
	


});// end ready
</script>	

<div class="jyp_tab backDiv" style="position:relative"  > <!--  -->
	<p class="backbtn btn  btn-info" style="width: 126px; position: absolute; left: 77px; top: 3px; z-index: 100;"
		 onclick="history.go(-1);">←뒤로가기</p>
	<ul class="nav nav-tabs">
	  <li class="active"><a  href="#work_menu1">보드</a></li>
	  <li><a  href="${pageContext.request.contextPath}/fileList.work">파일</a></li>
	</ul>

	<div class="tab-content">
	  
	  <div id="work_menu1" class="tab-pane fade in active">
	  
	  
	<div class="container myconatiner wl main" style="margin-top: 8px;" >
	<!-- 	<div class="wl main"> -->
		<!--     <div class="main_1 main_common"><p class="content">test1</p></div>
		    <div class="main_2 main_common"><p class="content">test2</p></div>
		    <div class="main_3 main_common"><p class="content">test3</p></div> -->
		   <%--  <c:forEach var="dto" begin="1" end="5" varStatus="status"> --%>

		   <c:forEach var="dto" items="${worklist}" varStatus="status"> 
		    	<div class="main_${status.count} main_common">
		    	
		    	<!-- #accordion -->
		    			<!-- WL header(아코디언) -->	<!-- WL header(아코디언) -->	<!-- WL header(아코디언) -->
		    			<!-- WL header(아코디언) -->	<!-- WL header(아코디언) -->	<!-- WL header(아코디언) -->
		    				<div class="panel panel-default">
						      <div class="panel-heading" style="background-color: #154566;">
						      <div class="panel-title" style="width:220px; color:white;" > 
						      <!-- 업무리스트이름 -->
						        <div style="/* width: auto;  */overflow: hidden; text-overflow: ellipsis;">
						        	 <a data-toggle="collapse" data-parent="#accordion" href="#collapse${status.count}" class="wl_name"
						        	 style="color:white;"> ${dto.wl_name}</a> 
						         </div>
						         <!-- 업무리스트 이름수정 -->
						          <span id="WlName" class="wlName glyphicon glyphicon-pencil" data-toggle="modal" data-target="#wlName${dto.wl_no}"  
						            style="position: relative; left: 85px;"></span> 
						         <!-- 업무추가  -->
						         <span class="glyphicon glyphicon-plus" data-toggle="modal" data-target="#work_add${dto.wl_no}"  
						          		 style="position: relative; left: 90px;" > </span> 
						          <!-- 업무삭제 -->
						         <a href="${pageContext.request.contextPath}/worklist_delete.work?p_no=${dto.p_no}&&wl_no=${dto.wl_no}">
						          		<span id="wl_delBtn" class="glyphicon glyphicon-trash"  style="position: relative; left: 100px;"></span> </a>
						          		</div>
						  <!-- END WL header(아코디언) -->  <!-- END WL header(아코디언) -->  <!-- END WL header(아코디언) -->       
						  <!-- END WL header(아코디언) -->  <!-- END WL header(아코디언) -->  <!-- END WL header(아코디언) -->        
						  
						  
						          <!-- span + 눌렀을때  모달창 뜸       업무추가            -->
						           <!--       업무추가            -->  <!--       업무추가            -->
						           <!--       업무추가            -->  <!--       업무추가            -->
						           		<!-- Modal -->
									  	<div class="modal fade" id="work_add${dto.wl_no}" role="dialog">
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
											<form action="<%=request.getContextPath()%>/work_add.work?wl_no=${dto.wl_no}" method="post"  id="work_form" 
												enctype="multipart/form-data">
											   <fieldset>
											 <!--   // 	insert into jyp_work (work_title,work_content, work_start, work_end) values (?,?,?,?) -->
											 <!--   <legend>업무추가</legend> -->																	
												<div class="form-group">
												  <label for="work_content"  >업무 내용 </label>
												  <textarea name="work_content"  id="work_content"  cols="60"  rows="10"   class="form-control" ></textarea>
												</div>		
											
												<div class="form-group">
													<label for="file">파일첨부</label>
													<input type="file" id ="file" name="file"  class="form-control" />
												</div>
												
												<div class="form-group">
													<label for="work_start">업무 시작일</label>
													<input type="date" id="work_start" name="work_start" />
												</div>
												
												<div class="form-group">
													<label for="work_end">업무 마감일</label>
													<input type="date" id="work_end" name="work_end" />
												</div>		
												
												<!-- <div class="form-group">
													<p><span class="glyphicon glyphicon-bell"></span>미리알림</p>
													<label for="work_alarm_date">날짜설정</label>
														<input type="date" id="work_alarm_date" name="work_alarm_date" />
													<label for="work_alarm_time">시간설정</label>
														<input type="time" id="work_alarm_time" name="work_alarm_time" />
												</div>	 -->
													
												<div class="form-group  text-right">
													<input type="submit"   value="입력"  class="btn btn-default"  style="color:white; background-color:#f4511e"   >  
													 <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
												</div>
											 </fieldset>		
											</form> <!-- end form -->	
												</div><!-- end container -->
									         <!-- end 업무추가 --> <!-- end 업무추가 --> <!-- end 업무추가 -->
									          <!-- end 업무추가 --> <!-- end 업무추가 --> <!-- end 업무추가 -->
									          
									        </div><!-- <div class="modal-body"> -->
									        
									        <!-- <div class="modal-footer">
									          <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
									        </div>end footer -->
									      </div>
									      
									    </div>
									  </div> <!-- end modal -->
						           <!--     end 업무추가            --><!--     end 업무추가            --><!--     end 업무추가            -->         
						            <!--     end 업무추가            --><!--     end 업무추가            --><!--     end 업무추가            -->   
						  
						           
						 			
							<!--이름수정  -->
							<!--이름수정  -->
						  <!-- Modal -->
						  <div class="modal fade" id="wlName${dto.wl_no}" role="dialog">
						    <div class="modal-dialog">
						    
						      <!-- Modal content-->
						      <div class="modal-content" style="width: 275px;">
						      <form action="${pageContext.request.contextPath}/worklist_name.work?wl_no=${dto.wl_no}" method="post">
						      <fieldset>     
						         <div class="modal-body">
							          <button type="button" class="close" data-dismiss="modal">&times;</button>
							          <h4 style="background-color: #FFFFFD;"><label for="wl_name_edit" style="color: black;"> 업무리스트 이름 수정 </label></h4>
							          <p><input type="text" id="wl_name_edit" name="wl_name_edit" value="${dto.wl_name}" /></p>
						       	 </div><!-- end  modal-body-->
						     	 <div class="modal-footer">
							     	  <input type="submit" class="btn btn-info" value="수정"/>
							          <button type="button" class="btn btn-default" data-dismiss="modal">닫기</button>
						         </div> <!--  end  modal-footer-->
						       </fieldset> 
						 	  </form>
						      </div><!--  end  modal-content-->
						      
						    </div>
						</div> <!-- end 이름수정  --> <!--end  이름수정  --> <!--end  이름수정  -->
								<!-- end 이름수정  --> <!--end  이름수정  --> <!--end  이름수정  -->
						           
						           
						  
						      </div>
						      
						      
						       <!-- 업무내용 --> <!-- 업무내용 --> <!-- 업무내용 --> <!-- 업무내용 -->
						       <!-- 업무내용 --> <!-- 업무내용 --> <!-- 업무내용 --> <!-- 업무내용 -->
						       <div style="max-height: 750px; height:auto; overflow-x:hidden; overflow-y:auto;">
						      <div id="collapse${status.count}" class="panel-collapse collapse in">
						      
						        	<%-- <div class="panel-body"> ${status.count }</div> --%>
						        	<c:forEach var="dto2" items="${work}" varStatus="status">
						        		
						        	
						        		<c:if test="${dto.wl_no == dto2.wl_no}">
						        			<!--start panel-body wl ->클릭시 업무 상세페이지로  -->
						        			<!-- <input type="checkbox" checked /> -->
							      			<div  class="panel-body wl" style="padding-bottom: 0px; border-left: 9px solid ${dto2.work_label}">
							      			<c:if test="${dto2.work_com=='y'}">
												<a href="<%=request.getContextPath()%>/unCheck.work?work_no=${dto2.work_no}&&p_no=${dto2.p_no}&&page=1"  >
													<img src="${pageContext.request.contextPath}/images/check.png" alt="업무미완료하기" width="15px"/></a>    
												</c:if>
											<c:if test="${dto2.work_com=='n'}">
												 <a href="<%=request.getContextPath()%>/check.work?work_no=${dto2.work_no}&&p_no=${dto2.p_no}&&page=1"  >
												 	<img src="${pageContext.request.contextPath}/images/uncheck.png" alt="업무완료하기" width="15px"/>
												 </a>    
												</c:if>
							      			<%--   <input type="checkbox" name="work_com" id="work_com" value="${work_no}"/>
							      			  <input type="hidden" class="workCheck ${work_no}" value="${dto2.work_com}"/> --%>
							      			   
							        		<div onclick="location.href='<%=request.getContextPath()%>/work_detail.work?work_no=${dto2.work_no}';"
							        			style="cursor:pointer;" onmouseover="this.style.backgroundColor='#F2F2F2'" onmouseout="this.style.backgroundColor='#FFFFFF'">
							        			<!-- 업무상세내용 -->
							        			<div><p>${fn:replace (dto2.work_content,cn,br)}</p></div><!-- end 업무내용 -->		 
							        			<div> <p style="font-size:11px;">${dto2.work_start}~${dto2.work_end}</p> </div> <!--end 업무 시작일/마감일 -->
							        			<!-- 첨부 파일이 있다면 보이게 -->
							        			<div> <p><c:if test="${dto2.work_file!='no.jpg'}">
							        			<img src="${pageContext.request.contextPath}/upload/${dto2.work_file}" alt="${dto2.work_file}" style="width:60%;">
							        				</c:if></p></div><!-- end 첨부파일 -->
							        			<!-- 업무상세내용 -->
							        			</div><!-- end onclick -->
								    		</div><!-- end panel-body wl ->클릭시 업무 상세페이지로 -->
							    	 	</c:if>

						        	</c:forEach>
						       
						      </div>
						    </div>     <!-- end 업무내용 --> <!-- end 업무내용 --> <!-- end 업무내용 -->
						       		 <!-- end 업무내용 --> <!-- end 업무내용 --> <!-- end 업무내용 -->
						    </div><!-- 업무내용 틀 -  -->
		    			
		    			
		  
		    			
		    			
		    	<!-- end #accordion -->
		    	</div> <!-- .main_common -->
		    </c:forEach>
		 		 <!-- Trigger the modal with a button -->
		 	<div class="main_common">
			  <button class="wl_add text-center" data-toggle="modal" data-target="#wl_add" style=" height: 48px; margin-top: 13px;" >
			  	<span class="glyphicon glyphicon-plus"></span>새 업무리스트 만들기</button>
			
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
			          <form action="<%=request.getContextPath()%>/worklist_add.work" method="get" id="wl_form">
			          		<label for="wl_name"></label>
							<input type="text" name="wl_name" id="wl_name" class="form-control" placeholder ="업무리스트 이름"/>
							
							<button type="submit" class="btn btn-danger" >추가</button>
							<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
					  </form>
			        </div>
			        <input type="hidden" value="${worklist.size()}" id="wlSize"/>
			        
			        <input type="hidden" id="pNo" value="${p_no}"/> 
			    	<script>
			    	$(function(){
			    		//업무리스트 가로배열 
			    		$(".wl.main").css({"width": 50+ 330*$("#wlSize").val()  + 330/* , "z-index":"1" */});
			    		
			    		//업무완료 체크박스
			    		$("input:checkbox").on('click', function() {
			    			
			    			if ( $(this).prop('checked') ) {// alert('체크됨');
			    				$.ajax({
					 				url:"${pageContext.request.contextPath}/Check.work",   
									type:"get", 
									dataType :"json",   
									data:{"work_no": pNo },
									success:function(data){
										console.log('체크유무 ㅣ:' +data);
										
									},
									error:function(xhr, textStatus, errorThrown){
										alert('ERROR');
									}
					 			}); //end ajax
			   
			    			} 
			    			else {// alert('체크안됨'); 
			    			}
			    		});//end click checkbox
			    			
			    		
			    		
			    		
			    	}); //end ready	
			    	
		/* 	    	$(function(){
			    		//업무완류에 따른 체크박스 체크/미체크
			 			//ajax로 값 불러와서 판단하기? 
			 			$.ajax({
			 				url:"${pageContext.request.contextPath}/workCheck.work",   
							type:"get", 
							dataType :"json",   
							data:{"p_no": pNo },
							success:function(data){
								console.log('체크유무 ㅣ:' +data);
								
							},
							error:function(xhr, textStatus, errorThrown){
								alert('ERROR');
							}
			 			}); //end ajax
			    	}); //end ready	
			    	 */
			    	</script>
			      </div>
			      
			    </div>
			  </div>
	
	
				
			
	
			</div><!-- end <div class="main_common ">  -->
	</div><!--end container-->
	
<!-- 탭 --><!-- 탭 -->
	</div> <!-- emd profile -->
</div><!-- end  tab-content -->
</div>	
<!-- 탭 --><!-- 탭 -->
<!--            start footer          -->
<!--            start footer          -->
<!--            start footer          -->
</body>
</html>

