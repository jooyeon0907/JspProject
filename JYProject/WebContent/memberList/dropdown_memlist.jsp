<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix ="c" uri="http://java.sun.com/jsp/jstl/core" %> <!-- 태그 라이브러리 -->
        
        
        
  <!-- start memberList header --> <!-- start memberList header -->
<!-- start memberList header --> <!-- start memberList header -->      
       <script>
		$(function(){
			console.log('dsadas');
			$("#search2").click(function(){
				$(".result_mem").empty();
				console.log('검색하기클릭');
				$.ajax({
					url:"${pageContext.request.contextPath}/jyp_memSearch.mem",
					type:"get", dataType:"json",
					data :{"jyp_email": $("#jyp_email").val() },
					success:function(data){
						console.log(data);
						console.log(typeof(data));    
						
						//1. 데이터 추출
						var email = data.jyp_email; var name = data.jyp_name;
						console.log(email);
					//	if(data==null){ alert('없는 회원 '); }
						//2. 동적 html 만들어서 
						var p1 = $("<p>").html("EMAIL : "+email);
						var p2 = $("<p>").html("NAME : " +name);
						var p3 = $("<p>").html("<a href='${pageContext.request.contextPath}/ws_Invitation.ws?jyp_email="+email+"' class='btn btn-info' >초대하기</a>");
						//3. .result_mem 화면에 보야주기
						 $(".result_mem").append(p1).append(p2).append(p3);
						// $(".result_mem").html(data);
						 
					/* 	 if($email.equals("")){
							$(".result.r1").html('검색한 이메일의 회원이 존재하지 않습니다.');
						}else{ $(".result.r1").html(data); }  */
						
					},
					error:function( xhr, textStatus, errorThrown){
						$(".result_mem").html('회원정보없음');
					//	$(".result_mem").html(textStatus +"(HTTP - "+ xhr.status + "/" +errorThrown)
					}
				});
			});
		});	
		</script>			

		
	<div class="container myconatiner">
		<!-- 주소록 헤더 (제목, 드롭다운, 초대하기버튼) -->
		<div class="row ml">
			<div class="col-sm-7">
				<h3 class="panel-heading">워크스페이스 멤버주소록(멤버/프로필)</h3>
			</div><!-- col-sm-7 -->
			
			<div class="col-sm-2" style="height: 41px; width:146px; margin-top: 24px;">
			<!-- 드롭다운 --><!-- 드롭다운 --><!-- 드롭다운 -->
			<!-- 드롭다운 --><!-- 드롭다운 --><!-- 드롭다운 -->
			  <div class="dropdown">
			    <button class="btn btn-default dropdown-toggle" type="button" id="menu1" data-toggle="dropdown">멤버목록
			    <span class="caret"></span></button>
			    <ul class="dropdown-menu" role="menu" aria-labelledby="menu1">
			      <li role="presentation"><a role="menuitem" tabindex="-1" href="${pageContext.request.contextPath}/member_list.mem">활동 중인 멤버</a></li>
			      <li role="presentation"><a role="menuitem" tabindex="-1" href="${pageContext.request.contextPath}/ws_InviListAll_.ws">초대 중인 멤버</a></li>
			      <li role="presentation" class="divider"></li>
			      <li role="presentation"><a role="menuitem" tabindex="-1" href="${pageContext.request.contextPath}/ws_delList.ws">삭제된 멤버</a></li>
			    </ul>
			   </div><!-- dropdown -->
			<!-- 드롭다운 --><!-- 드롭다운 --><!-- 드롭다운 -->
			<!-- 드롭다운 --><!-- 드롭다운 --><!-- 드롭다운 -->	
			</div><!-- col-sm-3 -->
			
			<div class="col-sm-3">
				<div>
				<!-- 회원검색/초대하기 -->
				<!-- 회원검색/초대하기 -->
					<!-- 초대모달 -->
					<button type="button" class="btn btn-info btn-lg" data-toggle="modal" data-target="#invitataion"
						style="margin-top: 19px;">+멤버초대하기</button>
		
					  <!-- Modal -->
					  <div class="modal fade" id="invitataion" role="dialog">
					    <div class="modal-dialog">
					    
					      <!-- Modal content-->
					      <div class="modal-content">
					        <div class="modal-header">
					          <button type="button" class="close" data-dismiss="modal">&times;</button>
					          <h4 class="modal-title">회원 검색</h4>
					        </div><!-- end  modal-header-->
					        <div class="modal-body">
					         <p><input type="text" id="jyp_email" 
									placeholder="초대할 멤버의 이메일을 검색합니다." class="form-control"/></p>
					          <div class="result_mem"> <!-- 검색 결과창 나오는 곳  --> </div>
					          
					          
					        </div><!-- end modal-body  -->
					        <div class="modal-footer">
					       	  <input type="button" id="search2" value="검색"  class="btn btn-info" />
					          <button type="button" class="btn btn-default" data-dismiss="modal">닫기</button>
					        </div><!-- end  modal-footer-->
					      </div><!-- end modal-content -->
					       <!-- end Modal content -->
					    </div> <!-- end  modal-dialog-->
					  </div><!--  end modal fade -->
		  
					</div> <!-- end ws_accept -->	<!-- end 초대모달 -->
				
			</div><!-- col-sm-3 초대하기 모달  -->
			<!-- 회원검색/초대하기 -->
			<!-- 회원검색/초대하기 -->
			
			
			</div><!-- end row  -->
			
	<!-- end memberlist header --><!-- end memberlist header -->
	<!-- end memberlist header --><!-- end memberlist header -->
  	<!-- </div>end contaioner
 -->
