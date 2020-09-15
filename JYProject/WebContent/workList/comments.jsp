<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix ="c" uri="http://java.sun.com/jsp/jstl/core" %> <!-- 태그 라이브러리 -->
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<%
pageContext.setAttribute("br", "<br/>");
pageContext.setAttribute("cn", "\n");
%>
	<div class="container myconatiner2 w_detail" style="margin-left: 12%; width:70%;">
			<div class="co_container"style="max-height: 500px;height: auto; overflow-x: hidden; overflow-y: auto; " >
				<!-- 코멘트가 없을 경우 -->
			
				<c:if test="${empty co_list}">
					<p class="text-center"><img src="${pageContext.request.contextPath}/images/null.png" alt ="코멘트 없음" width="450px"/></p>
					<p class="text-center" style="font-size:30px;">코멘트를  달아주세요!</p>
				</c:if>
				<c:forEach var="list" items="${co_list}" varStatus="status"> 
					<div class="row" style="padding: 13px;border-top: 1px solid darkgrey; margin-bottom: 2%;
						<c:choose>
							<c:when test="${list.co_indent==0}"> 
										background-color: aliceblue; border-bottom: 4px solid #123456; border-color: skyblue</c:when>
							<c:otherwise>
										background-color: white; border-bottom: 4px solid #2a0cd2;
							</c:otherwise>
						</c:choose>
						"> <!-- 대댓글이면 밑에 선 안쳐줌  -->
					<div class="col-sm-3">
						<!--프로필 이미지 -->
						<p><span>
							<c:forEach begin="1" end="${list.co_indent}">ㄴ </c:forEach>
						</span><img src="${pageContext.request.contextPath}/upload/${list.jyp_profile}" alt="프로필사진" style="width:50px;"/></p>
					</div><!-- end col-sm-3 -->
					<div class="col-sm-6">
						<div class="row">
							<p>${list.jyp_name}<span style="color:#BDBDBD;">${fn:substring(list.co_date,0,16)}</span></p>
						</div><!-- end row 이름,날짜 -->
						<div class="row co ${status.index}">
							<input type="hidden" class="co_name ${status.index}" value="${list.jyp_name}"/>
							<input type="hidden" class="comment ${status.index}"  value="${list.comment}"/>
							<input type="hidden" class="co_no ${status.index}" value="${list.co_no}"/>
							<p >${fn:replace (list.comment,cn,br)}</p>
						</div><!-- end row 내용-->
					</div><!-- end col-sm-6 -->
					<div class="col-sm-3">
						<div class="row" style="margin-top: 32px;">
							<div class="col-sm-4">
								<input type="button"  value="댓글" class="co_re btn-info"/> 
							</div> <!-- end col-sm-4 대댓글-->
							<!-- 로그인된 유저가 코멘트 작성자라면 수정,삭제 버튼 보이게 하기  -->
							<c:if test="${list.jyp_no==jyp_no}">
							<div class="col-sm-4">
								<input type="button"  value="수정" class="co_edit btn-info"/>
							</div> <!-- end col-sm-4 수정-->
							<div class="eeeee col-sm-4"
								onclick="location.href='${pageContext.request.contextPath}/commentdel.work?co_no=${list.co_no}&&work_no=${list.work_no}';">
								<input type="button"  value="삭제" class="co_del btn-info"/>
							</div> <!-- end col-sm-4 삭제-->
							</c:if>
							<!--end  로그인된 유저가 코멘트 작성자라면 수정,삭제 버튼 보이게 하기  -->
						</div><!-- end row -->
					</div><!-- end col-sm-3 -->
					</div> <!-- end 댓글보기 row -->
				</c:forEach>
			
	
			</div><!-- end co_container -->
			
			
			
			<!-- 
			.row.conmment {
			    padding: 33px; margin-top: 31px;
			}
			 -->
			
			<div class="row" style="padding: 33px; margin-top: 31px;">
				<h3 class="work_title co">코멘트 달기 </h3>
					<div class="form-group">
						<textarea name="comment" id="comment" rows="6" cols="50"  
								  placeholder="코멘트" style="width: -webkit-fill-available;" ></textarea>
					</div>
					
					<div class="form-group  text-right">
						<span id="co_btn"><button id="insert_co" class='btn btn-info'>입력</button></span>
						<a href="${pageContext.request.contextPath}/list.work?p_no=${dto.p_no}"  class="btn btn-info" >업무목록보기</a> 
					</div>

			</div> <!-- end 댓글달기창 row -->
			
			
		</div><!--end container-->
		
	<!-- ws_no, p_no, wl_no, work_no, jyp_no -->
	
	<input type="hidden" id="co_wsNo" value="${dto.ws_no}"/>
	<input type="hidden" id="co_pNo" value="${dto.p_no}"/> 
	<input type="hidden" id="co_wlNo" value="${dto.wl_no}"/>
	<input type="hidden" id="co_workNo" value="${dto.work_no}"/>
	<input type="hidden" id="co_jypNO" value="${jyp_no}"/><!-- session에 저장된 회원번호  -->
	<script>
		$(function(){
			
			
			//댓글 insert 
			$("#insert_co").on('click',function(){ //alert('클릭'); return false;
				//빈칸검사
				if($("#comment").val()==""){
					alert('빈칸입니다.\n확인해주세요'); return false;
				}
				var ws_no = $("#co_wsNo").val();
				var p_no = $("#co_pNo").val();
				var wl_no = $("#co_wlNo").val();
				var work_no = $("#co_workNo").val();
				var jyp_no = $("#co_jypNO").val();
				var comment = $("#comment").val();
				
				console.log(jyp_no); console.log(comment);
				
				$.ajax({
					url:"${pageContext.request.contextPath}/add_comments.work",
					type:"post", dataType:"text",
					data:{"ws_no":ws_no , "p_no":p_no, "wl_no":wl_no ,
						  "work_no":work_no, "jyp_no": jyp_no , "comment": comment},
					success:function(text){
						console.log(text);
						if(text.trim()=="성공"){ 
							alert('댓글 등록.');
						//	$("#comment").empty();
							//댓글 목록 출력하는 함수
							
						//	 listReply();
							location.href='${pageContext.request.contextPath}/work_detail.work?work_no='+work_no+'&&editRe=2';
						}
						else if(text.trim()=="ERROR"){ alert('댓글달기ERROR');}
					},
					error:function(xhr, textStatus, errorThrown){
						alert("댓글달기 ERORR!");
					}
				}); //end ajax
			}); //end click
				
			
			//댓글 수정
			$(".co_edit").on('click',function(){//  alert('댓글수정');
				$(".work_title.co").html('수정하기')
				$("#co_btn").html("<button id='update_co' class='btn btn-info'>수정</button>");
				
				/* console.log($(this).parent());   //.css("color","red")
				console.log( $(this).parent().parent()  );
				console.log( $(this).parent().parent().prevAll(".comment").css("color","red")  );
				console.log($(this).parent().parent().parent().parent() ); // 
				console.log($(this).parent().parent().parent().parent().children('.row.co').css("color","red")  ); */
			//	console.log($(this).parent().parent().parent().parent().find(".comment").css("color","red") ); 
			//	console.log($(this).parent().parent().parent().parent().find(".comment") ); 
				console.log($(this).parent().parent().parent().parent().find(".comment").val() ); 
				var comment = $(this).parent().parent().parent().parent().find(".comment").val();
				var co_no = $(this).parent().parent().parent().parent().find(".co_no").val() ;
				var work_no = $("#co_workNo").val();
				$("#comment").html(comment);
				$("#comment").focus();
				$("#update_co").on('click',function(){ // alert('댓글수정하기');
					$.ajax({
						url:"${pageContext.request.contextPath}/commentEdit.work",
						type:"get", dataType:"text",
						data:{ "comment": $("#comment").val() , "co_no" : co_no, "work_no" : work_no},
						success:function(text){
							if(text.trim()=="성공"){ 
								alert('댓글수정 완료.');
								location.href='${pageContext.request.contextPath}/work_detail.work?work_no='+work_no+'&&editRe=2';
							}
							else if(text.trim()=="ERROR"){ alert('댓글수정 ERROR');}	
						},
						error:function(xhr, textStatus, errorThrown){ alert('댓글수정 ERROR'); }
					}); //end ajax
				});//end click co_edit
				
				
			});//end click co_edit
			
			
			
			//대댓글 달기
			$(".co_re").on('click',function(){ // alert('대댓달기');
				var ws_no = $("#co_wsNo").val();
				var p_no = $("#co_pNo").val();
				var wl_no = $("#co_wlNo").val();
				var work_no = $("#co_workNo").val();
				
				$(".work_title.co").html('대댓글 달기');
				$("#co_btn").html("<button id='co_re' class='btn btn-info'>대댓달기</button>");

				var name = $(this).parent().parent().parent().parent().find(".co_name").val();
				console.log($(this).parent().parent().parent().parent().find(".co_name"));
				var comment = $(this).parent().parent().parent().parent().find(".comment").val();
				var co_no = $(this).parent().parent().parent().parent().find(".co_no").val();
				$("#comment").html(" : "+name /* +": " + comment.substring(0,20)  */+"\n");
				$("#comment").focus();
				
				$("#co_re").on('click',function(){ /// alert('대댓글 달기');
					$.ajax({
						url:"${pageContext.request.contextPath}/commentRe.work",
						type:"get", dataType:"text",
						data:{"ws_no":ws_no , "p_no":p_no, "wl_no":wl_no ,
							  "work_no":work_no,"co_no": co_no, "comment": $("#comment").val()},
						success:function(text){
							if(text.trim()=="성공"){ 
								alert('리플달기 완료.');
								location.href='${pageContext.request.contextPath}/work_detail.work?work_no='+work_no+'&&editRe=2';
							}
							else if(text.trim()=="ERROR"){ alert('리플달기 ERROR');}	
						},
						error:function(xhr, textStatus, errorThrown){ alert('리플달기 ERROR'); }
					}); //end ajax
				});//end click co_edit
				
				
			});//end click co_edit
			
			
			
			
			
			
			
			
		}); //end ready
	
	</script>







