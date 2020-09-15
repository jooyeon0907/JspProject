<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file = "/inc/work_header.jsp" %>
<!--            end header            -->
<!--            end header            -->
<!--            end header            -->
<%@ include file = "/inc/ws_project.jsp" %> <!-- 프로젝트 카데고리 헤더 -->

<div class="container"  style="margin-top:5%; min-height:500px; width:500px">
		<form action="<%=request.getContextPath()%>/work_add.work" method="post"  id="add_Form" 
			enctype="multipart/form-data">
		   <fieldset>
		 <!--   // 	insert into jyp_work (work_title,work_content, work_start, work_end) values (?,?,?,?) -->
		   <legend>업무추가</legend>																	
			<div class="form-group">
			  <label for="work_title"  >제목</label>
			  <input type="text"   name="work_title"   id="work_title"   class="form-control" > 
			</div>	
			<div class="form-group">
			  <label for="work_content"  >업무 내용</label>
			  <textarea name="work_content"  id="work_content"  cols="60"  rows="10"   class="form-control" ></textarea>
			</div>		
			
			<div class="form-group">
				<label for="file">파일첨부</label>
				<input type="file" id ="file" name="file"  class="form-control" />
			</div>
			<div class="form-group">
				<p>멤버추가</p>
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
				<input type="reset"    value="취소"  class="btn btn-default"    >  
				<a href="<%=request.getContextPath()%>/list.work"   class="btn btn-default"   >업무목록보기</a>
			</div>
		 </fieldset>		
		</form> <!-- end form -->	
</div>

<!--            start footer          -->
<!--            start footer          -->
<!--            start footer          -->
<%@ include file = "/inc/footer.jsp" %>