<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

	<!-- 프로젝트 멤버검색 -->	
			<!-- 초대모달 -->
			<span class="glyphicon glyphicon-plus" title="업무멤버 추가" data-toggle="modal" data-target="#pw_member" id="pw_search"></span>
			<input type="hidden" id="work_hidden" value="${dto.work_no}"/> 
			  <!-- Modal -->
			  <div class="modal fade" id="pw_member" role="dialog">
			    <div class="modal-dialog modal-fullsize">
			    
			      <!-- Modal content-->
			      <div class="modal-content modal-fullsize">
			        <div class="modal-header">
			          <button type="button" class="close" data-dismiss="modal">&times;</button>
			          <h4 class="modal-title">프로젝트 멤버</h4>
			        </div><!-- end  modal-header-->
			        <div class="modal-body">
			  
			  <!-- 멤버 테이블 -->
			  	<div class="row">
			          <div class="col-sm-6">  <!-- 프로젝트에 해당된 멤버 -->
						<table class="table table-striped db1">
							<caption>업무에 배정된 멤버<span class="glyphicon glyphicon-user"></span> </caption>
							<thead>
								<tr>
									 <th scope="col">이름</th> <th scope="col">부서</th> <th scope="col">직함</th> 
									 <th scope="col"></th>
								</tr>
							</thead>
							
							<tbody><!-- 검색 결과창 나오는 곳  --> </tbody>  
						</table>
					  </div><!-- end col-sm-6 -->
					  
			          <div class="col-sm-6">  <!-- 프로젝트에 해당되지 않는 멤버 -->
						<table class="table table-striped db2">
						<caption>업무에 배정되지않은 멤버<span class="glyphicon glyphicon-remove"></span> </caption>
							<thead>
								<tr>
									 <th scope="col">이름</th> <th scope="col">부서</th> <th scope="col">직함</th> <th scope="col"></th>
								</tr>
							</thead>
							
							<tbody><!-- 검색 결과창 나오는 곳  --> </tbody>  
						</table>
					  </div><!-- end col-sm-6 -->	  
				</div><!--end row  -->	  
			  <!-- 멤버 테이블 -->
			  <!-- 멤버 테이블 -->
			  
			       
			        </div><!-- end modal-body  -->
			        <div class="modal-footer">
			          <button type="button" class="btn btn-default" data-dismiss="modal">닫기</button>
			        </div><!-- end  modal-footer-->
			      </div><!-- end modal-content -->
			       <!-- end Modal content -->
			    </div> <!-- end  modal-dialog-->
			  </div><!--  end modal fade -->
  <!--	var td4 = $("<td>").html("<a href='${pageContext.request.contextPath}/p_member.pro?p_no=${dto_pro.p_no}&p_access=1&&jyp_email="+jyp_email+"' class='btn btn-info' >멤버추가</a>");  -->
			<!-- end 초대모달 -->
<script>
$(function(){
	var work_no =  $("#work_hidden").val();
	$("#pw_search").on( "click", function(){
		$.ajax({
			url:"${pageContext.request.contextPath}/pro_memSearch.mem",
			type:"get", dataType:"json",
			data:{"work_no": work_no },
			success:function(data){
				$(".db1 tbody").empty(); 
				console.log("1 : " + data);
				console.log(data.members);
				var members = data.members;
				for(var i=0; i<members.length; i++){
					//1. 데이터 추출
					var jyp_name  = members[i].jyp_name;
					var position  = members[i].position;
					var dept 	  = members[i].dept;
					var jyp_no 	  = members[i].jyp_no;
					var jyp_email = members[i].jyp_email;
						//2. 동적 html
						var tr = $("<tr>");
						var td1 = $("<td>").html(jyp_name);
						var td2 = $("<td>").html(dept);
						var td3 = $("<td>").html(position);	
						var td4 = $("<td>").html("<input type='button' value='삭제' class='delpwMem  a"+i+" btn btn-info'/> <input type='hidden' value='"+jyp_no+"'/>");
						//데이터 삽입하기 
						tr.append(td1).append(td2).append(td3).append(td4);
						$(".db1 tbody").append(tr);			
				}//end for

				
				//////click ////
				$(".delpwMem").on("click", function(){
					console.log('클릭');
				//	console.log(tr);
				//	alert('delMem에서 jyp_no : ' + $(".jyp_no_hidden").val() +"/" + jyp_no);
				//	alert('삭제 클릭시 jyp_no : ' + $(this).next().val());
				//	console.log('delMem에서 p_no : '+p_no );
					 $.ajax({
							url:"${pageContext.request.contextPath}/work_memDelete.work",   
							type:"get", 
							dataType :"text",   
							data:{"work_no": work_no , "jyp_no": $(this).next().val() },
							success:function(text){
								console.log(text);
								var result =text; 
							//	alert(result);
								if(result.trim()=="SUCCESS"){
									alert('멤버삭제완료');
									location.href='${pageContext.request.contextPath}/work_detail.work?work_no='+work_no+'';
								}else if(result.trim()=="ERROR2"){
									alert('최소 1명이상의 업무에 배정된 인원이 존재해야합니다.'); 
									return false;
									}
								else if(result.trim()=="ERROR1"){
									alert('탈퇴 ERROR'); //history.go(-1);
									return false;
								}
								
						},
						error:function(xhr, textStatus, errorThrown){
							$(".db2 tbody").html(textStatus + "(HTTP-"+ xhr.status+"/" + errorThrown);
						}
				 });///end ajax
				}); //end click		
				////end click/////
				
			},
			error:function( xhr, textStatus, errorThrown){
				$(".row").html(textStatus +"(HTTP - "+ xhr.status + "/" +errorThrown)
			}
		}); //end ajax
		//////////////////////////////////////////////////////////////////////////////////////
	
		$.ajax({ 
			url:"${pageContext.request.contextPath}/pro_memSearchNo.mem",
			type:"get", dataType:"json",
			data:{"work_no": work_no },
			success:function(data){
				$(".db2 tbody").empty();
				console.log(data);
				console.log(data.members);
				var members = data.members;
				for(var i=0; i<members.length; i++){
					//1. 데이터 추출
					var jyp_name  = members[i].jyp_name;
					var position  = members[i].position;
					var dept 	  = members[i].dept;
					var jyp_no 	  = members[i].jyp_no;
					var jyp_email = members[i].jyp_email;
						//2. 동적 html
						var tr = $("<tr>");
						var td1 = $("<td>").html(jyp_name);
						var td2 = $("<td>").html(dept);
						var td3 = $("<td>").html(position);		
						var td4 = $("<td>").html("<input type='button' value='추가' class='addpwMem  a"+i+" btn btn-info'/> <input type='hidden' value='"+jyp_email+"' />");
						//데이터 삽입하기 
						tr.append(td1).append(td2).append(td3).append(td4);
						$(".db2 tbody").append(tr);			
				}//end for
				
				
				$(".addpwMem").on("click", function(){
					var work_no =  $("#work_hidden").val();
					console.log('work_no: ' + work_no);
					 $.ajax({
							url:"${pageContext.request.contextPath}/work_member2.work",   
							type:"get", 
							dataType :"text",   
							data:{"work_no": work_no , "jyp_email":$(this).next().val()  },
							success:function(text){
								console.log(text);
								var result =text; 
							//	alert(result);
								if(result.trim()=="성공"){
									alert('멤버추가완료');
									location.href='${pageContext.request.contextPath}/work_detail.work?work_no='+work_no+'';
								}else if(result.trim()=="오류"){
									alert('ERROR');
									return false;
								}

							},
							error:function(xhr, textStatus, errorThrown){
								$(".db2 tbody").html(textStatus + "(HTTP-"+ xhr.status+"/" + errorThrown);
							}
					 });
					
				}); //end click	
				
				
				
			},
			error:function( xhr, textStatus, errorThrown){
				$(".row").html(textStatus +"(HTTP - "+ xhr.status + "/" +errorThrown)
			}
		}); //end ajax
		
		//////////////////////////////////////////////////////////////////////////////////////
	}); //end click 

	
	
	
}); //end ready 
</script>			
		
		<!-- <a href='${pageContext.request.contextPath}/p_member.pro?p_no=${dto_pro.p_no}&p_access=1&&jyp_email="+jyp_email+"' class='btn btn-info' id='addMem'>추가</a>   -->

