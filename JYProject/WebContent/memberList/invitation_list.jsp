<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix ="c" uri="http://java.sun.com/jsp/jstl/core" %> <!-- 태그 라이브러리 -->
<%@ include file = "/inc/work_header.jsp" %>
<!--            end header            -->
<!--            end header            -->
<!--            end header            -->


<!-- start memberList header --> <!-- start memberList header -->
<!-- start memberList header --> <!-- start memberList header -->
<%@ include file ="dropdown_memlist.jsp" %>
<!-- end memberlist header --><!-- end memberlist header -->
<!-- end memberlist header --><!-- end memberlist header -->
		
		<!-- start 초대리스트 -->  <!-- start 초대리스트 -->  <!-- start 초대리스트 -->
	<div> <!-- start 초대리스트 -->  <!-- start 초대리스트 -->  <!-- start 초대리스트 -->
		<!-- <h3 class="panel-heading">워크스페이스 초대목록(페이징달기)</h3> -->
		<div>	
			<table class="table table-striped db1">
			<caption>초대날짜로부터 7일 뒤 자동 삭제 됩니다.</caption>
				<colgroup>
					<col style="width:5%">
					<col style="width:20%">
					<col style="width:20%">
					<col style="width:20%">
					<col style="width:20%">
				</colgroup>
				
				<thead>
					<tr>
						<th scope="col">NO</th> <th scope="col">초대받는  이메일</th>  <th scope="col">초대보낸 이메일</th> 
						<th scope="col">초대날짜</th> <th scope="col">초대상태</th>
					</tr>
				</thead>
				
			
				<tbody>  </tbody>

					<tfoot>
						<tr><td colspan="5" class="text-center">
							<ul class="pagination">
								<!-- 1.이전페이지 -->
								<c:if test="${list_invi.bottom_start>list_invi.bottomList}">
									<li><a href="${pageContext.request.contextPath}/ws_list.ws?result=1&&pstartNo2=${(list_invi.bottom_start-2)*list_invi.onepageLimit}">
										이전</a></li>
								</c:if>
								<!-- 2.현재페이지 -->
								<c:forEach var="btn" begin="${list_invi.bottom_start}" end="${list_invi.bottom_end}">
									<li <c:if test="${btn==list_invi.bottom_current}">class="active"</c:if>>
										<a href="${pageContext.request.contextPath}/ws_list.ws?result=1&&pstartNo2=${(btn-1)*list_invi.onepageLimit}">
										${btn}</a></li>
								</c:forEach>
								<!-- 3.다음페이지 -->
								<c:if test="${list_invi.pageAll>list_invi.bottom_end}">
									<li><a href="${pageContext.request.contextPath}/ws_list.ws?result=1&&pstartNo2=${list_invi.bottom_end*list_invi.onepageLimit}">
										다음</a></li>
								</c:if>
							</ul>
						</td></tr>
					</tfoot>
									
			</table>
		</div><!-- end table -->
	</div> <!--end 초대리스트-->  <!--end 초대리스트-->  <!--end 초대리스트-->
		 <!--end 초대리스트-->  <!--end 초대리스트-->  <!--end 초대리스트-->
	
	
	<script>
		$(function(){
			$(".db1 tbody").empty();
			$.ajax({
				url:"${pageContext.request.contextPath}/ws_InviListAll.ws",
				type:"get", dataType:"json",
				success:function( data ){
					console.log(data);
					var list = data.list;
					console.log(list);
					var total = list.length;  console.log('전체 리스트 개수 : '+total);
					for(var i=0; i<list.length; i++){
						//1. 데이터 추출
						var send_email = list[i].send_email;
						var get_email = list[i].get_email;
						var acc_date = list[i].acc_date;
						var ass_status = list[i].ass_status;
						//2. 동적 html
						var tr = $("<tr>");
						var td1 = $("<td>").html(i+1);
						var td2 = $("<td>").html(get_email);
						var td3 = $("<td>").html(send_email);
						var td4 = $("<td>").html(acc_date);
							 if(ass_status.trim()=='y'){ var td5 = $("<td>").html('수락'); }
						else if(ass_status.trim()=='n'){ var td5 = $("<td>").html('보류'); }
						else if(ass_status.trim()=='x'){ var td5 = $("<td>").html('거부'); }
						
							 
						tr.append(td1).append(td2).append(td3).append(td4).append(td5);
						$(".db1 tbody").append(tr);
						
						/*
						//	<ul class="pagination">
								<!-- 1.이전페이지 -->
								<c:if test="${list_invi.bottom_start>list_invi.bottomList}">
									<li><a href="${pageContext.request.contextPath}/ws_list.ws?result=1&&pstartNo2=${(list_invi.bottom_start-2)*list_invi.onepageLimit}">
										이전</a></li>
								</c:if>
								<!-- 2.현재페이지 -->
								<c:forEach var="btn" begin="${list_invi.bottom_start}" end="${list_invi.bottom_end}">
									<li <c:if test="${btn==list_invi.bottom_current}">class="active"</c:if>>
										<a href="${pageContext.request.contextPath}/ws_list.ws?result=1&&pstartNo2=${(btn-1)*list_invi.onepageLimit}">
										${btn}</a></li>
								</c:forEach>
								<!-- 3.다음페이지 -->
								<c:if test="${list_invi.pageAll>list_invi.bottom_end}">
									<li><a href="${pageContext.request.contextPath}/ws_list.ws?result=1&&pstartNo2=${list_invi.bottom_end*list_invi.onepageLimit}">
										다음</a></li>
								</c:if>
						//	</ul>
						*/
						
						
						
						
						
						
						
					}//end for
					
				},
				error:function( xhr, textStatus, errorThrown){
					$(".db1 tbody").html(textStatus + "(HTTP-"+xhr.status +"/"+errorThrown);
				}
			}); //end ajax
		});//end ready
	
	
	</script>
	
<!--            start footer          -->
<!--            start footer          -->
<!--            start footer          -->
</body>
</html>



