<%@page import="com.jooyeon.dto.P_WL_DTO"%>
<%@page import="com.jooyeon.dao.JYP_WL_DAO"%>
<%@page import="com.jooyeon.dao.JYP_Work_DAO"%>
<%@page import="com.jooyeon.dto.P_Work_DTO"%>
<%@page import="com.jooyeon.dto.JYP_Member_DTO"%>
<%@page import="com.jooyeon.dto.PW_Member_DTO"%>
<%@page import="com.jooyeon.dao.JYP_PW_Member_DAO"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.jooyeon.dao.JYP_P_Member_DAO"%>
<%@page import="com.jooyeon.dto.P_Member_DTO"%>
<%@page import="com.jooyeon.dao.JYP_Project_DAO"%>
<%@page import="com.jooyeon.dto.JYP_Project_DTO"%>
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
	
	
	
 <h3 class="panel-heading">프로젝트 추가 </h3>
		<%
	
		JYP_Project_DTO dto = new JYP_Project_DTO();
		JYP_Project_DAO dao = new JYP_Project_DAO();
		
		int ws_no = 1; // session 값 불러오기
		String p_name = "프로젝트TEST222"; //prarmeter 값 불러오기 
		String p_info = "프로젝트 설명 " ; 
		String p_public = "y";
		String p_ip = "111.111.111";  //(String)session.getAttribute("jyp_ip");
		int jyp_no = 4;//(int)session.getAttribute("jyp_no");
		
		//워크스페이스 번호
		dto.setWs_no(ws_no);
		//프로젝트이름
		dto.setP_name(p_name);
		//프로젝트 정보
		dto.setP_info(p_info); 
		//프로젝트 공개여부 y/n
		dto.setP_public(p_public);
		//프로젝트 생성한 회원 ip
		dto.setP_ip(p_ip);
		//프로젝트 생성한 회원 번호
		dto.setJyp_no(jyp_no);
		
		//프로젝트 생성하기 
		int result = dao.add_pro(dto);
		//방금 만든 프로젝트 번호 뽑기 
		int p_no = dao.p_no(dto);
		///////@@샘플데이터 추가  -p_worklist,p_work,pw_member @@////////////////
		int sample =0;  jyp_no =0;
		SimpleDateFormat date = new SimpleDateFormat("yyy-MM-dd");
		Calendar now = Calendar.getInstance();
		String nowTime = date.format(now.getTime()); // System.out.println("WS_Add에서 nowTime: " + nowTime);
		
		JYP_WL_DAO 		  dao_wl = new JYP_WL_DAO();
		P_WL_DTO dto_wl = new P_WL_DTO();
		
		//워크스페이스 번호
		dto_wl.setWs_no(ws_no);
		//프로젝트 번호
		dto_wl.setP_no(p_no);
		//업무리스트 이름 
		dto_wl.setWl_name("test");
		//작성한 회원의 아이피 
		dto_wl.setWl_ip(p_ip);
		//회원 번호
		dto_wl.setJyp_no(jyp_no); //0넣기
		
		//업무리스트 생성//
		sample+= dao_wl.wl_add(dto_wl);
		//방금 만든 업무리스트 번호 뽑기
		int wl_no = dao_wl.wl_no(dto_wl);
		
		
		JYP_Work_DAO	  dao_work = new JYP_Work_DAO();
		P_Work_DTO dto_work = new P_Work_DTO();
		
		//워크페이스번호
		dto_work.setWs_no(ws_no);
		//프로젝트번호
		dto_work.setP_no(p_no);
		//업무리스트 번호
		dto_work.setWl_no(wl_no);
		//업무작성한 회원 번호
		dto_work.setJyp_no(jyp_no); //0
		//업무 내용 ( work_content text not null)
		dto_work.setWork_content("test");
		//업무 시작일 (work_start date)
		dto_work.setWork_start(nowTime);
		//업무 마감일 ( worl_end date)
		dto_work.setWork_end(nowTime);
		//업무 작성한 멤버의 아이피  (work_ip varchar(100) not null)
		dto_work.setWork_ip(p_ip);
		//파일 첨부
		dto_work.setWork_file("no.jpg");
		
		//업무 생성//
		sample+= dao_work.write(dto_work);
		//방금 만든 work_no 뽑기
		int work_no = dao_work.work_no(dto_work);
		
		JYP_PW_Member_DAO dao_pwmem = new JYP_PW_Member_DAO();
		PW_Member_DTO dto_mem1 = new PW_Member_DTO();
		JYP_Member_DTO dto_mem2 = new JYP_Member_DTO();
		
		dto_mem1.setWs_no(ws_no); 
		dto_mem1.setP_no(p_no);
		dto_mem1.setWl_no(wl_no);
		dto_mem1.setWork_no(work_no);

		String jyp_email = "44@44"; //session 값 가져오기 
		dto_mem2.setJyp_email(jyp_email);
		
		dto_mem2.setJyp_no(jyp_no);
		dto_mem2.setJyp_ip(p_ip);
		
		//업무멤버생성//
		sample+= dao_pwmem.pw_member(dto_mem1, dto_mem2);
		
		
		out.print("sample : " + sample);  //3여야함
		
		
	/////////////////////////////////////////////////////
		
		
		if(result>0 && sample==3){ out.println("프로젝트 생성 완료"); }
		else { out.println("프로젝트 생성 ERROR"); }
		
		
		%>  
		
		
		
		
		
	<%-- 	<h3 class="panel-heading">프로젝트 리스트 불러오기</h3>
		<%
		JYP_Project_DAO dao = new JYP_Project_DAO();
		
		
		out.println(dao.p_list(1));
		%> --%>
		
	<%-- 	<h3 class="panel-heading">프로젝트 멤버추가</h3>
		<%
		P_Member_DTO dto = new P_Member_DTO();
		JYP_Project_DAO dao = new JYP_Project_DAO();
		
		//워크스페이스 번호
		dto.setWs_no(1);
		//프로젝트 번호
		dto.setP_no(1);
		//회원번호
		dto.setJyp_no(3);
		//관리자 권한
		dto.setP_access(1);
		//회원 ip
		dto.setP_Mip("111.111.111");
		
		int result = dao.add_memer(dto);
		
		if(result>0){ out.println("프로젝트 멤버추가 완료"); }
		else { out.println("프로젝트 멤버추가 ERROR"); }
		
		%> --%>
		
		
<%-- 		<h3 class="panel-heading">프로젝트 멤버정보</h3>
		<%
		P_Member_DTO dto = new P_Member_DTO();
		JYP_P_Member_DAO dao = new JYP_P_Member_DAO();
		
		int p_no = 18;
		int jyp_no=4;
		
		dto.setP_no(p_no);
		dto.setJyp_no(jyp_no);
		
		dto = dao.p_memInfo(dto);
		out.println(dto);
		
		
		%> --%>
		
		
<%-- 		<h3 class="panel-heading">해당 프로젝트 정보</h3>
		<%
		JYP_Project_DTO dto = new JYP_Project_DTO();
		JYP_Project_DAO dao = new JYP_Project_DAO();
		
		int p_no=18;
		dto.setP_no(p_no);
		
		dto = dao.p_info(dto);
		out.println(dto);
		
		%>
		 --%>
<%-- 		 
			<h3 class="panel-heading">프로젝트 멤버리스트</h3>
		<%
		P_Member_DTO dto = new P_Member_DTO();
		JYP_P_Member_DAO dao = new JYP_P_Member_DAO();
		ArrayList<P_Member_DTO> list = new ArrayList<P_Member_DTO>(); 
		
		int p_no = 18;
		int jyp_no=4;
		
		dto.setP_no(p_no);
		dto.setJyp_no(jyp_no);
		
		list = dao.p_memList(dto);
		out.println(list);
		
		%>
		 --%>
		 
<%-- 		 
		<h3 class="panel-heading">프로젝트 멤버리스트</h3>
		<%
		JYP_Project_DTO dto = new JYP_Project_DTO();
		JYP_Project_DAO dao = new JYP_Project_DAO();
		
		String p_name = "프로젝트 이름 수정";
		String p_info = "프로젝트 설명 수정";
		int p_status = 4;
		String p_start = "2020-8-2 14:12";
		String p_end = "2020-10-20 00:00";
		String p_complete = "2020-9-30 12:00";
		String p_public = "y";
		int p_no = 18;
		
		dto.setP_name(p_name);
		dto.setP_info(p_info);
		dto.setP_status(p_status);
		dto.setP_start(p_start);
		dto.setP_end(p_end);
		dto.setP_complete(p_complete);
		dto.setP_public(p_public);
		dto.setP_no(p_no);
		
		int result = dao.p_edit(dto);
		
		
		if(result>0){ out.println("프로젝트 수정 완료"); }
		else { out.println("프로젝트 수정 ERROR"); }
		%>  --%>
		 
		 
<%-- 		 
 		<h3 class="panel-heading">프로젝트 삭제하기</h3>
		 <%
		 
		 //권한체크 
		 JYP_P_Member_DAO dao_pMem = new JYP_P_Member_DAO();
		 P_Member_DTO dto_pMem = new P_Member_DTO();
		 
		 int p_no = 4; // parameter 넘겨받기
		 int jyp_no = 4; // session 값 받기
		 dto_pMem.setP_no(p_no);
		 dto_pMem.setJyp_no(jyp_no);
		 
		 //프로젝트 권한 확인 -return값: 관리자 =1, 일반멤버  =2 
		 int p_access = dao_pMem.p_access(dto_pMem); 
		 
		 //관리자 권한이면 삭제실행
		 JYP_Project_DAO dao_pro = new JYP_Project_DAO();
		 JYP_Project_DTO dto_pro = new JYP_Project_DTO();
		 dto_pro.setP_no(p_no);
		 if(p_access==1){
			out.println("삭제권한있음");
			 int result = dao_pro.pro_delete(dto_pro); //프로젝트 삭제 
			 	if(result>0){ 
			 		out.println("프로젝트 삭제 성공");
			 	}else {out.println("프로젝트 삭제 ERROR");}
			 
			 
		 }//if
		 
		 
		 %>
		 --%>
		 
		 
		
	</div><!--end container-->
</body>
</html>

