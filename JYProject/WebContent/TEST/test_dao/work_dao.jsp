<%@page import="com.jooyeon.dao.JYP_WL_DAO"%>
<%@page import="com.jooyeon.dao.JYP_PW_Member_DAO"%>
<%@page import="com.jooyeon.dto.JYP_Member_DTO"%>
<%@page import="com.jooyeon.dto.PW_Member_DTO"%>
<%@page import="com.jooyeon.dto.P_Member_DTO"%>
<%@page import="com.jooyeon.dto.P_Work_DTO"%>
<%@page import="com.jooyeon.dao.JYP_Work_DAO"%>
<%@page import="com.jooyeon.dto.P_WL_DTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix ="c" uri="http://java.sun.com/jsp/jstl/core" %> <!-- 태그 라이브러리 -->
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel ="stylesheet" 
	href = "https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
</head>
<body>
	<div class="container panel panel-info">
<%-- 	
		  <h3 class="panel-heading">업무리스트 추가 </h3>
		<%
		P_WL_DTO dto = new P_WL_DTO();
		JYP_WL_DAO dao = new JYP_WL_DAO();
		
		//워크스페이스 번호
		dto.setWs_no(41);
		//프로젝트 번호
		dto.setP_no(1);
		//업무리스트 이름 
		dto.setWl_name("업무리스트 d");
		//작성한 회원의 아이피 
		dto.setWl_ip("111.111.111");
		
		int result=dao.wl_add(dto);
		if(result>0){ out.println("업무리스트 생성 완료");}
		else { out.println("업무리스트 생성 ERROR"); } 
		%> 
		 --%>
		
<%-- 		<h3 class="panel-heading"> 해당 프로젝트의 업무리스트 뽑아오기 </h3>
		<%
		P_WL_DTO dto = new P_WL_DTO();
		JYP_Work_DAO dao = new JYP_Work_DAO();
		
		//해당 프로젝트 번호
		dto.setP_no(1);
		
		out.println(dao.list_name(dto));
		
		
		%> --%>
		
		
		<!--                    work    -->
		<!--                    work    -->
		<!--                    work    -->
		
<%-- 	<h3 class="panel-heading"> 업무 작성 </h3>
		<%
		P_Work_DTO dto = new P_Work_DTO();
		JYP_Work_DAO dao = new JYP_Work_DAO();
		
		//워크페이스번호
		dto.setWs_no(1);
		//프로젝트번호
		dto.setP_no(1);
		//업무리스트번호
		dto.setWl_no(3);
		//업무작성한 회원 번호
		dto.setJyp_no(3);
		//업무 내용 ( work_content text not null)
		dto.setWork_content("업무내용2222\n333\n232323");
		//업무 시작일 (work_start date)
		dto.setWork_start(null);
		//업무 마감일 ( worl_end date)
		dto.setWork_end(null);
		//업무 작성한 멤버의 아이피  (work_ip varchar(100) not null)
		dto.setWork_ip("111.111.111");
		
		int result = dao.write(dto);
		if(result>0){ out.println("업무 생성 완료");}
		else { out.println("업무 생성 ERROR"); } 
		%>		
		 --%>
	<%-- 	<h3 class="panel-heading"> 업무 작성자 </h3>
		<%
		JYP_Work_DAO dao = new JYP_Work_DAO();
	
		out.println(dao.writer(3));
		
		%> --%>
		
		
<%-- 		<h3 class="panel-heading"> 업무 상세보기 </h3>
		<%
		P_Work_DTO dto = new P_Work_DTO();
		JYP_Work_DAO dao = new JYP_Work_DAO();
		
		dto=dao.work_detail(1);
		
		out.println(dto.getWork_no());
		out.println(dto.getWs_no());
		out.println(dto.getP_no());
		out.println(dto.getWl_no());
		out.println(dto.getJyp_no());
		out.println(dto.getWork_content());
		out.println(dto.getWork_start());
		out.println(dto.getWork_end());
		out.println(dto.getWork_alarm());
		out.println(dto.getWork_finish());
		out.println(dto.getWork_date());
		out.println(dto.getWork_ip());
		%> --%>
		
		
<%-- 		<h3 class="panel-heading"> 업무 수정하기 </h3>
		<%
		
		P_Work_DTO dto = new P_Work_DTO();
		JYP_Work_DAO dao = new JYP_Work_DAO();
		
		//업무내용
		dto.setWork_content("업무 내용 수정 ");
		//업무 시작일
		dto.setWork_start("2020-07-26");
		//업무 마감일
		dto.setWork_end("2020-09-04");
		//업무게시글 번호
		dto.setWork_no(1);
	
		
	
		int rsuelt = dao.update(dto);
		if(rsuelt>0){ out.println("업무 수정 완료");}
		else { out.println("업무 수정 ERROR"); } 
		%> --%>
<%-- 		
		
	  <h3 class="panel-heading"> 업무 삭제하기 (관리자권한이거나 해당 업무 작성자인지 판단) </h3>
		<%
		P_Member_DTO dto2 =new P_Member_DTO();
		P_Work_DTO dto = new P_Work_DTO();
		JYP_Work_DAO dao = new JYP_Work_DAO();
		
		//관리자 권한인 경우
		dto2.setP_no(1); 
		dto2.setJyp_no(3); 
		dto.setWork_no(3);
		
		int result1 = dao.delete1(dto, dto2);
		
		//해당업무 작성자가 아님 
		int result2= dao.delete2(dto, 3);
		
		
		if(result1>0 || result2>0){ out.println("업무 삭제 완료"); }
		else {  out.println("업무 삭제 ERROR");  }

		
		%>  --%>
		 
		
<%-- 		<h3 class="panel-heading"> 업무추가시 work멤버추가 </h3>
		<%
		
		//업무추가
		P_Work_DTO dto = new P_Work_DTO();
		JYP_Work_DAO dao = new JYP_Work_DAO();
		
		//워크페이스번호
		dto.setWs_no(7);
		//프로젝트번호
		dto.setP_no(13);
		//업무리스트번호
		dto.setWl_no(13);
		//업무작성한 회원 번호
		dto.setJyp_no(4);
		//업무 내용 ( work_content text not null)
		dto.setWork_content("dao_test2222\n333\n232323");
		//업무 시작일 (work_start date)
		dto.setWork_start(null);
		//업무 마감일 ( worl_end date)
		dto.setWork_end(null);
		//업무 작성한 멤버의 아이피  (work_ip varchar(100) not null)
		dto.setWork_ip("221.221.111");
		
		
		//방금 만든 업무의 work_no번호 뽑기 
		int work_no = dao.work_no(dto);
		out.println("work_no : " + work_no);
		

		
		int result = dao.write(dto);
		if(result>0 && work_no>0){ out.println("업무 생성 완료");}
		else { out.println("업무 생성 ERROR"); } 

		
		//멤버추가 
		
		PW_Member_DTO dto_mem = new PW_Member_DTO();
		JYP_Member_DTO dto2 = new JYP_Member_DTO();
		dto_mem.setWs_no(dto.getWs_no());
		dto_mem.setP_no(dto.getP_no());
		dto_mem.setWl_no(dto.getWl_no());
		//방금 만든 work_no 뽑아와야됨 
		dto_mem.setWork_no(work_no);
		dto_mem.setJyp_no(dto.getJyp_no());
		dto2.setJyp_ip("11.11.11");
		
		result = dao.pw_member(dto_mem, dto2);
		if(result>0){ out.println("멤버추가 완료");}
		else { out.println("멤버추가ERROR"); } 
		
		%>
		 --%>
		 
		
		
 		<h3 class="panel-heading"> 업무 미리 알림 설정하ㄱㅣ</h3>
		<%
		JYP_PW_Member_DAO dao = new JYP_PW_Member_DAO();
		PW_Member_DTO dto = new PW_Member_DTO();
		
		 //설정할 날짜,시간
		String work_alarm = "2020-08-19 16:57";
		//미리알림할 업무번호
		int work_no = 134; 
		//유저 번호 (현재 유저)
		int jyp_no = 6;
		
		dto.setWork_alarm(work_alarm);
		dto.setWork_no(work_no);
		dto.setJyp_no(jyp_no);
		
		int result = dao.work_alarm(dto);
		if(result>0){out.println("미리알림설정 완료" +result);}
		else{ out.println("미리알림설정 ERROR");}
		%> 
		 
<%-- 		 
		<h3 class="panel-heading"> 업무 멤버삭제</h3> 
		 <%
		 JYP_PW_Member_DAO dao = new JYP_PW_Member_DAO();
		 PW_Member_DTO dto = new PW_Member_DTO();
			
		int work_no = 47; // request값 가져오기
		int jyp_no = 8;  // 삭제하려는 멤버 번호 > request값 가져오기
			
		dto.setWork_no(work_no); dto.setJyp_no(jyp_no);
		int result = dao.work_memDel(dto); //멤보삭제
		if(result>0){out.println("멤버삭제 완료");}
		else{ out.println("멤버삭제 ERROR");}
		 %>
		 
		 
		 
		  --%>
		 
		
	</div><!--end container-->
</body>
</html>

