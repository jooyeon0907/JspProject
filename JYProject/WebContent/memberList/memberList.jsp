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
		
		<script>
		$(function(){
			$(".wsMemDelete").on('click',function(){ //alert('zㅋ를릭')
			  var result = confirm('멤버를 워크스페이스에서 삭제하시겠습니까?');
			 var jyp_email = $(this).parent().parent().find(".jyp_email").val();  
			 console.log(jyp_email);
			
				if(result){
					$.ajax({
						url:"${pageContext.request.contextPath}/ws_Memdelete2.ws",
						type:"post", dataType:"text",
						data:{"jyp_email":jyp_email},
						success:function(text){
							console.log(text);
							if(text.trim()=="성공"){ 
								alert('삭제완료');
								location.href='<%=request.getContextPath()%>/member_list.mem';
							}
							else if(text.trim()=="ERROR1"){ alert('댓글달기ERROR1');}
						},
						error:function(xhr, textStatus, errorThrown){
							alert("댓글달기 ERORR!");
						}
					}); //end ajax
					
				}else{ return false; }
			}); //end click
		}); //end ready
		
		</script>
		
		
		<div> <!-- 주소록테이블 --><!-- 주소록테이블 --><!-- 주소록테이블 --><!-- 주소록테이블 -->
			<table class="table table-striped">
			<c:set var ="ws_admin" value="${ws_admin}"></c:set>
			<c:set var ="ws_email" value="${ws_email}"></c:set>
			<c:set var ="jyp_no" value="${jyp_no}"></c:set>
			<caption>워크스페이스 주소록(클릭하면 해당 유저의 프로필로 이동)</caption>
				<colgroup>
					<col style="width:10%">
					<col style="width:15%">
					<col style="width:15%">
					<col style="width:15%">
					<col style="width:15%">
					<col style="width:10%">
				</colgroup>
				
				<thead>
					<tr>
						<th scope="col">이름</th>  <th scope="col">직함</th>             <th scope="col">부서</th>
						<th scope="col">이메일</th> <th scope="col">워크스페이스 가입날짜</th> <th scope="col"></th>
					</tr>
				</thead>
				
				<tbody>
					<c:forEach var="dto" items="${memberList.list}" varStatus="status">
						<tr>
							<td>${dto.jyp_name}  
								<c:if test="${dto.jyp_email == ws_email}"><button>관리자</button></c:if>
							</td>  
							<td>${dto.position}</td> <td>${dto.dept}</td> 
							<td>${dto.jyp_email}
							<input type="hidden" class="jyp_email ${dto.jyp_no}" value="${dto.jyp_email}"/>
							</td> <td>${dto.ws_Mdate}</td> 
							
							<td> 
								<span class="glyphicon glyphicon-comment" title="대화"></span>
								<span class="jyp_Email glyphicon glyphicon-envelope" title="메일보내기"  data-toggle="modal" data-target="#sendEmail"></span>
								<input type="hidden" class="jypEmail ${dto.jyp_no}" value="${dto.jyp_email}" />
								<%@ include file="mail.jsp" %>
								<c:if test="${ws_admin==jyp_no}">
								<span class="wsMemDelete glyphicon glyphicon-trash" title="멤버삭제" style="cursor:pointer;"></span></c:if>
							</td>
							<!-- <td><span class=" glyphicon glyphicon-option-vertical"></span></td> -->
						</tr>
					</c:forEach>	
				</tbody>
				
				<tfoot>
					<tr><td colspan="6" class="text-center">
						<ul class="pagination">
							<!-- 1.이전페이지 -->
							<c:if test="${memberList.bottom_start>memberList.bottomList}">
								<li><a href="${pageContext.request.contextPath}/member_list.mem?pstartNo=${(memberList.bottom_start-2)*memberList.onepageLimit}">
									이전</a></li>
							</c:if>
							<c:forEach var="btn" begin="${memberList.bottom_start}" end="${memberList.bottom_end}">
							<!-- 2. 현재페이지 -->
								<li <c:if test="${btn==memberList.bottom_current}">class="active"</c:if>>
									<a href="${pageContext.request.contextPath}/member_list.mem?pstartNo=${(btn-1)*memberList.onepageLimit}">
										${btn}</a></li>
							</c:forEach>
							<!-- 3. 다음페이지 -->
							<c:if test="${memberList.pageAll>memberList.bottom_end}">
								<li><a href="${pageContext.request.contextPath}/member_list.mem?pstartNo=${memberList.bottom_end *memberList.onepageLimit}">
									다음</a></li>
							</c:if>	
						</ul>
					</td></tr>	
				</tfoot>
			</table>
		</div> <!-- end 주소록 테이블  --><!-- end 주소록 테이블  --><!-- end 주소록 테이블  --><!-- end 주소록 테이블  -->
		
		
		
	</div><!-- end container -->
	
	
	
<!--            start footer          -->
<!--            start footer          -->
<!--            start footer          -->
</body>
</html>
