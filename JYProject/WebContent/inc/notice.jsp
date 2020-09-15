<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<!DOCTYPE html>
<%@include file= "login_header.jsp"%>

	<div class="notice">
		<h3>포트폴리오공지사항</h3>
		<p>
			본사이트는 상업적목적이 아닌 개인 포트폴리오 용도로 제작되었으며
			홈페이지의 일부내용과 기타 이미지 등은 그 출처가 따로 있음을 밝힙니다.
		</p>
		<p><img src="images/jyp_QR.jpg" alt="포트폴리오바로가기"/></p>
		<p>
			<input type="checkbox" id="subpop" name="subpop" />
			<label for="subpop">오늘 하루동안 이 창 열지 않음.</label>
			<input type="button" value="[close]" class="btn btn-warning" id="colse">
		</p>
	</div><!-- end container -->
	
	
</body>
</html>