<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

	<!-- 프로젝트 멤버검색 -->	
			<!-- 초대모달 -->
			<span class="glyphicon glyphicon-plus" title="업무멤버 추가" data-toggle="modal" data-target="pw_member" id="pw_search"></span>
			<input type="hidden" id="work_hidden" value="${dto_pro.p_no}"/> 
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
									 <th scope="col">이름</th> <th scope="col">부서</th> <th scope="col">직함</th> <th scope="col">프로젝트권한</th> 
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
	$("#pw_search").on( "click", function(){
		console.log('멤버검색하기');
		var p_no =  $("#work_hidden").val();
		
		$.ajax({
			url:"${pageContext.request.contextPath}/pro_memSearch.mem",
			type:"get", dataType:"json",
			data:{"p_no": p_no },
			success:function(json){
				$(".db1 tbody").empty(); 
				console.log("1 : " + json);
				console.log(json.members);
				var members = json.members;
				for(var i=0; i<members.length; i++){
					//1. 데이터 추출
					var jyp_name  = members[i].jyp_name;
					var position  = members[i].position;
					var dept 	  = members[i].dept;
					var jyp_no 	  = members[i].jyp_no;
					var jyp_email = members[i].jyp_email;
					var work_no = members[i].work_no;
						//2. 동적 html
						var tr = $("<tr>");
						var td1 = $("<td>").html(jyp_name);
						var td2 = $("<td>").html(dept);
						var td3 = $("<td>").html(position);	
						var td4 = $("<td>").html("<a href='${pageContext.request.contextPath}/' class='btn btn-info' >삭제</a>");
						//데이터 삽입하기 
						tr.append(td1).append(td2).append(td3).append(td4);
						$(".db1 tbody").append(tr);			
				}//end for
			},
			error:function( xhr, textStatus, errorThrown){
				$(".row").html(textStatus +"(HTTP - "+ xhr.status + "/" +errorThrown)
			}
		}); //end ajax
		//////////////////////////////////////////////////////////////////////////////////////
	
		$.ajax({ 
			url:"${pageContext.request.contextPath}/pro_memSearchNo.mem",
			type:"get", dataType:"json",
			data:{"p_no": p_no },
			success:function(json){
				$(".db2 tbody").empty();
				console.log(json);
				console.log(json.members);
				var members = json.members;
				for(var i=0; i<members.length; i++){
					//1. 데이터 추출
					var jyp_name  = members[i].jyp_name;
					var position  = members[i].position;
					var dept 	  = members[i].dept;
					var jyp_no 	  = members[i].jyp_no;
					var jyp_email = members[i].jyp_email;
					var work_no = members[i].work_no;
					console.log(p_access);
						//2. 동적 html
						var tr = $("<tr>");
						var td1 = $("<td>").html(jyp_name);
						var td2 = $("<td>").html(dept);
						var td3 = $("<td>").html(position);		
						var td4 = $("<td>").html("<input type='button' value='추가' class='addMem  a"+i+"'/>");
						var td5 = $("<td>").html("<input type='hidden' value='"+jyp_email+"' id='jyp_email'/>");
						//데이터 삽입하기 
						tr.append(td1).append(td2).append(td3).append(td4).append(td5);
						$(".db2 tbody").append(tr);			
				}//end for
				
				
				$(".addMem").on("click", function(){
					var btn = $(this);
					var tr = $(this).parent().parent(); 
					console.log(tr);
					 $.ajax({
						 url:"${pageContext.request.contextPath}/p_member.pro",   
							type:"get", 
							dataType :"text",   
							data:{"p_no": p_no , "p_access":"1", "jyp_email":$("#jyp_email").val()},
							success:function(text){
								console.log(text);
								var result =text; 
							//	alert(result);
								if(result.trim()=="성공"){
									console.log('예');
									alert('프로젝트멤버추가완료');//
									//  1. 다시 리스트 불러오는 클릭 호출 
									// 2. 
									btn.attr("value","삭제");
									btn.attr("class","btn btn-info");
									$(".db1 tbody").append(tr);	
							//		location.href='${pageContext.request.contextPath}/pro_list.pro';
								}else if(result=="오류"){alert('ERROR'); history.go(-1); }

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

