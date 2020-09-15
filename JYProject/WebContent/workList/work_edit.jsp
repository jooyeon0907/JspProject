<%@page import="com.jooyeon.dto.P_Work_DTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix ="c" uri="http://java.sun.com/jsp/jstl/core" %> <!-- 태그 라이브러리 -->  
<%@ include file = "/inc/work_header.jsp" %>
<!--            end header            -->
<!--            end header            -->
<!--            end header            -->
<%-- <%@ include file = "/inc/ws_project.jsp" %> <!-- 프로젝트 카데고리 헤더 --> --%>
<div class="container myconatiner w_detail">
	<%
	P_Work_DTO dto = (P_Work_DTO)request.getAttribute("dto");
	// 	update p_work set work_title= ?, work_content = ?, work_start=? , work_end= ?  where work_no = ?
	%>
		<form action="<%=request.getContextPath()%>/work_edit.work?work_no=<%=dto.getWork_no()%>" method="post"  id="editForm" 
			enctype="multipart/form-data">
		   <fieldset>
		   <legend>업무 수정</legend>
			
			<div class="form-group">
			  <label for="work_writer"  >작성자</label>
			  <input type="text"   name="work_writer"   id="work_writer"   class="form-control"   value="${writer}"  readonly> 
			</div>	
					
			<div class="form-group">
			  <label for="work_content"  >업무 내용</label>
			  <textarea name="work_content"  id="work_content"  cols="60"  rows="10"   class="form-control" ><%=dto.getWork_content()%></textarea>
			</div>
																				
			<div class="form-group">
			  <label for="work_start"  >업무 시작일</label>
			  <input type="date"   name="work_start"   id="work_start"   class="form-control"  value="<%=dto.getWork_start() %>"> 
			</div>	
			
			<div class="form-group">
			  <label for="work_end"  >업무 마감일</label>
			  <input type="date"   name="work_end"   id="work_end"   class="form-control" value="<%=dto.getWork_end()%>" > 
			</div>	
			
			<div class="form-group">
				<p><span class="glyphicon glyphicon-bell"></span>미리알림</p>
				<label for="work_alarm_date">날짜설정</label>
					<input type="date" id="work_alarm_date" name="work_alarm_date" />
				<label for="work_alarm_time">시간설정</label>
					<input type="time" id="work_alarm_time" name="work_alarm_time" />
			</div>	
		
			<div class="form-group">
				<label for="work_label" class="label_title">색상라벨</label> 
				<p class="btn label_color c1" style="width:15px; height:15px; border-radius:50%; background-color:#ffb024; text-indent:-9999px;" >#ffb024</p>
				<p class="btn label_color c2" style="width:15px; height:15px; border-radius:50%; background-color:#ECCEF5; text-indent:-9999px;" >#ECCEF5</p>
				<p class="btn label_color c3" style="width:15px; height:15px; border-radius:50%; background-color:#CEE3F6; text-indent:-9999px;" >#CEE3F6</p>
				<p class="btn label_color c4" style="width:15px; height:15px; border-radius:50%; background-color:#CEF6F5; text-indent:-9999px;" >#CEF6F5</p>
				<p class="btn label_color c5" style="width:15px; height:15px; border-radius:50%; background-color:#CEF6CE; text-indent:-9999px;" >#CEF6CE</p>
				<p class="btn label_color c6" style="width:15px; height:15px; border-radius:50%; background-color:#F5D0A9; text-indent:-9999px;" >#F5D0A9</p>
				<p class="btn label_color c7" style="width:15px; height:15px; border-radius:50%; background-color:#ebb378; text-indent:-9999px;" >#ebb378</p>
				<p class="btn label_color c8" style="width:15px; height:15px; border-radius:50%; background-color:#F5BCA9; text-indent:-9999px;" >#F5BCA9</p>
				<p class="btn label_color c9" style="width:15px; height:15px; border-radius:50%; background-color:#fdfdfd00; text-indent:-9999px; border: 1px solid #424242;" >#fdfdfd00</p>
				<!-- 색상선택하면 input태그 value값에 반영, 색상라벨 배경색에 반영-->
				<input type="hidden" id="work_label" name="work_label" value="${dto.work_label}">
				
			</div>
		
			<div class="form-gourp">
				<label for="file">파일 수정</label>
				<c:if test="${dto.work_file!='no.jpg'}">
	  				<p data-toggle="modal" data-target="#myFile2"><img src="${pageContext.request.contextPath}/upload/${dto.work_file}" alt="${dto.work_file}" style="width:155px;"> </p>
		  			 <!-- Trigger the modal with a button -->
					  <!-- Modal --> <!-- Modal --> <!-- Modal --> <!-- Modal --> <!-- Modal -->
					  <div class="modal fade" id="myFile2" role="dialog">
					    <div class="modal-dialog">
					    
					      <!-- Modal content-->
					      <div class="modal-content">
					        <div class="modal-header">
					          <button type="button" class="close" data-dismiss="modal">&times;</button>
					          <h4 class="modal-title">${dto.work_file}</h4>
					        </div>
					        <div class="modal-body" style=" max-height: 600px;height: auto ;max-width: 800px; width: auto; overflow-x: auto; overflow-y: auto;">
					          <p class="text-center"><img src="${pageContext.request.contextPath}/upload/${dto.work_file}" alt="${dto.work_file}"> </p>
					          
					        </div> </div>  </div> </div>
					   <!-- end Modal --> <!-- end Modal --> <!-- end Modal --> <!-- end Modal -->
	  			</c:if>	
				<input type="hidden" name ="oldfile" value="${dto.work_file}"/>
				<input type="file" id="file" name ="file" class="form-control"/>
			</div>
						
			<div class="form-group  text-right">
				<input type="submit"   value="수정하기"   class="btn btn-danger"  >  
				<input type="reset"    value="취소"  class="btn btn-default"    >  
				<a href="<%=request.getContextPath()%>/list.work"   class="btn btn-default"   >업무 목록보기</a>
			</div>
		 </fieldset>		
		</form> <!-- end form -->	
</div>

<script>
$(function(){
	$("#editForm").submit(function(){
	//	console.log($("#work_alarm_date").val()); 
	//	console.log($("#work_alarm_time").val()); 
		if($("#work_alarm_date").val() != "" && $("#work_alarm_time").val() == "" ){
			alert('날짜와 시간을 같이 입력해주세요.1'); $("#work_alarm_time").focus(); return false;
		}else if(  $("#work_alarm_date").val() == "" && $("#work_alarm_time").val() !=""  ){
			alert('날짜와 시간을 같이 입력해주세요.2'); $("#work_alarm_date").focus(); return false;
		}
			
	}); //end submit
}); //end ready



$(function(){
	$(".label_title").css({"backgroundColor":$("#work_label").val()});
    
     $(".label_color").click(function(){
        $("input[name=work_label]").attr('value',$(this).html()); //색상값 데이터 넘겨주기 
        $(".label_title").css({"backgroundColor":$("#work_label").val()});
        return false;
    });  //click
 }); // end ready 
 
 
 
</script>






<!--            start footer          -->
<!--            start footer          -->
<!--            start footer          -->
<%@ include file = "/inc/footer.jsp" %>