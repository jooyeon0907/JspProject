<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix ="c" uri="http://java.sun.com/jsp/jstl/core" %> <!-- 태그 라이브러리 -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>



	<div class="container myconatiner2 w_detail" style="margin-left: 12%; width: 70%;">
		<h3 class="work_title">코멘트 달기 </h3>
			<div>
			<c:forEach var="i" begin="0" end="2" varStatus="status">
				<div class="row" style="padding: 13px;border-bottom: 1px solid darkgrey;"> <!-- 대댓글이면 밑에 선 안쳐줌  -->
				<div class="col-sm-2">
					<!--프로필 이미지 -->
					<p><img src="${pageContext.request.contextPath}/upload/no.jpg" alt="프로필사진" width="100px;"/></p>
				</div><!-- end col-sm-2 -->
				<div class="col-sm-7">
					<div class="row">
						<p>이름 <span style="color:#BDBDBD;">작성날짜</span></p>
					</div><!-- end row 이름,날짜 -->
					<div class="row">
						<p>코멘트 내용 </p>
					</div><!-- end row 내용-->
				</div><!-- end col-sm-7 -->
				<div class="col-sm-3">
					<div class="row" style="margin-top: 32px;">
						<div class="col-sm-4">
							<input type="button" id="re_btn" value="댓글" class="btn-info"/> 
						</div> <!-- end col-sm-4 대댓글-->
						<div class="col-sm-4">
							<input type="button" id="edit_btn" value="수정" class="btn-info"/>
						</div> <!-- end col-sm-4 수정-->
						<div class="col-sm-4">
							<input type="button" id="del_btn" value="삭제" class="btn-info"/>
						</div> <!-- end col-sm-4 삭제-->
					</div><!-- end row -->
				</div><!-- end col-sm-3 -->
				</div> <!-- end 댓글보기 row -->
			</c:forEach>
				</div><!-- end 댓글 리스트 컨테이너 -->
			
			
			
			<!-- 
			.row.conmment {
			    padding: 33px; margin-top: 31px;
			}
			 -->
			
			<div class="row" style="padding: 33px; margin-top: 31px;">
				<form action="" method="post">
				<fieldset>
					<div class="form-group">
						<textarea name="comment" id="comment" rows="6" cols="50"  
								  placeholder="코멘트" style="width: -webkit-fill-available;" ></textarea>
					</div>
					
					<div class="form-group  text-right">
						<input type="submit" value="입력" class="btn btn-info" />
					</div>
				
				</fieldset>
				</form>
			</div> <!-- end 댓글달기창 row -->
			
			
		</div><!--end container-->
		



