<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.jooyeon.dao.JYP_P_Member_DAO"%>
<%@page import="com.jooyeon.dto.PW_Member_DTO"%>
<%@page import="com.jooyeon.dto.P_Work_DTO"%>
<%@page import="com.jooyeon.dto.P_WL_DTO"%>
<%@page import="com.jooyeon.dto.P_Member_DTO"%>
<%@page import="com.jooyeon.dto.JYP_Project_DTO"%>
<%@page import="com.jooyeon.dao.JYP_Work_DAO"%>
<%@page import="com.jooyeon.dao.JYP_WL_DAO"%>
<%@page import="com.jooyeon.dao.JYP_PW_Member_DAO"%>
<%@page import="com.jooyeon.dao.JYP_Project_DAO"%>
<%@page import="com.jooyeon.dao.JYP_WS_Member_DAO"%>
<%@page import="com.jooyeon.dao.JYP_Member_DAO"%>
<%@page import="com.jooyeon.dto.JYP_Member_DTO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.jooyeon.dto.JYP_WS_DTO"%>
<%@page import="com.jooyeon.dto.WS_Memeber_DTO"%>
<%@page import="com.jooyeon.dao.JYP_WS_DAO"%>
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
	<h3 class="panel-heading">워크스페이스 추가</h3>
 	<% 
	
	JYP_WS_DTO dto = new JYP_WS_DTO();
	JYP_WS_DAO dao = new JYP_WS_DAO();
	
	String ws_name = "wsadsa미가";
	int jyp_no = 4;
	String jyp_ip = "11.111.11";
	
	dto.setWs_name(ws_name);
	dto.setJyp_no(jyp_no);
	dto.setWs_ip(jyp_ip);
//	System.out.println("세션 jyp_no : " + session.getAttribute("jyp_no"));
	
	int result = dao.ws_add(dto); //워크스페이스 생성하기 
	
	
	int ws_no = dao.ws_no(dto); //본인이 방금 만든 워크스페이스 번호 뽑기 ////////// 
	
	System.out.println("WS_Add에서 ws_no: " +ws_no);
	
	
	///////////////@@@@@@@@@@ 샘플 추가 action 따로 만들어 주기 
	// -> jyp_project,p_member,p_worklist,p_work,pw_member 에 샘플데이터 한 개씩 넣어주기 
	int sample =0;
	SimpleDateFormat date = new SimpleDateFormat("yyy-MM-dd");
	Date now = new Date();
	String date1 = date.format(now);
		


	JYP_Project_DAO   dao_pro = new JYP_Project_DAO();
	JYP_Project_DTO dto_pro = new JYP_Project_DTO();
	
	//워크스페이스 번호
	dto_pro.setWs_no(ws_no);
	//프로젝트이름
	dto_pro.setP_name("test");
	//프로젝트 정보
	dto_pro.setP_info("test"); 
	//프로젝트 공개여부 y/n
	dto_pro.setP_public("test");
	//프로젝트 생성한 회원 ip
	dto_pro.setP_ip(jyp_ip);
	//프로젝트 생성한 회원 번호
	dto_pro.setJyp_no(jyp_no);
	
	//프로젝트 생성하기 //
	sample+= dao_pro.add_pro(dto_pro);
	
	JYP_P_Member_DAO dao_pmem = new JYP_P_Member_DAO();
	P_Member_DTO dto_pmem = new P_Member_DTO();
	
 	//워크스페이스 번호
 	dto_pmem.setWs_no(ws_no);
	//프로젝트 번호
	dto_pmem.setP_no(1);
 	//회원번호
 	dto_pmem.setJyp_no(jyp_no);
	//관리자 권한
	dto_pmem.setP_access(1);
 	//회원아이피
 	dto_pmem.setP_Mip(jyp_ip);
	
 	//프로젝트 멤버등록하기 // 
 	sample+= dao_pmem.add_member(dto_pmem);
 	
 	
 	
	JYP_WL_DAO 		  dao_wl = new JYP_WL_DAO();
	P_WL_DTO dto_wl = new P_WL_DTO();
	
	//워크스페이스 번호
	dto_wl.setWs_no(ws_no);
	//프로젝트 번호
	dto_wl.setP_no(1);
	//업무리스트 이름 
	dto_wl.setWl_name("test");
	//작성한 회원의 아이피 
	dto_wl.setWl_ip(jyp_ip);
	
	//업무리스트 생성//
	sample+= dao_wl.wl_add(dto_wl);
	
	JYP_Work_DAO	  dao_work = new JYP_Work_DAO();
	P_Work_DTO dto_work = new P_Work_DTO();
	
	//워크페이스번호
	dto_work.setWs_no(ws_no);
	//프로젝트번호
	dto_work.setP_no(1);
	//업무리스트번호
	dto_work.setWl_no(1);
	//업무작성한 회원 번호
	dto_work.setJyp_no(jyp_no);
	//업무 내용 ( work_content text not null)
	dto_work.setWork_content("test");
	//업무 시작일 (work_start date)
	dto_work.setWork_start(date1);
	//업무 마감일 ( worl_end date)
	dto_work.setWork_end(date1);
	//업무 작성한 멤버의 아이피  (work_ip varchar(100) not null)
	dto_work.setWork_ip(jyp_ip);
	//파일 첨부
	dto_work.setWork_file("no.jpg");
	
	//업무 생성//
	sample+= dao_work.write(dto_work);
	
	JYP_PW_Member_DAO dao_pwmem = new JYP_PW_Member_DAO();
	PW_Member_DTO dto_mem1 = new PW_Member_DTO();
	JYP_Member_DTO dto_mem2 = new JYP_Member_DTO();
	
	dto_mem1.setWs_no(ws_no); 
	dto_mem1.setP_no(1);
	dto_mem1.setWl_no(1);
	dto_mem1.setWork_no(1);
	
	String jyp_email = "44@44"; //session 값 가져오기 
	dto_mem2.setJyp_email(jyp_email);
	dto_mem2.setJyp_ip(jyp_ip);
	
	//업무멤버생성//
	sample+= dao_pwmem.pw_member(dto_mem1, dto_mem2);
	
	
	out.print("sample : " + sample);
	
	/////////////////////////////////////////////////////
	
	
	if(result>0 && ws_no>0) { out.println("워크스페이스 추가 완료"); }
	else {  out.println("워크스페이스 멤버추가 ERROR");}
	
	JYP_WS_Member_DAO mdao = new JYP_WS_Member_DAO();
	WS_Memeber_DTO mdto = new WS_Memeber_DTO();
	//생성후 멤버 추가 
	//워크스페이스 번호
 	dto.setWs_no(ws_no);     
 	//회원번호
 	mdto.setJyp_no(4);
 	//회원아이피
 	mdto.setWs_Mip("111.111.111");
 	 result= mdao.member(dto,mdto);
 	out.println(result);
	%>
	
	
	
	
	 --%>
	
	
	
<%-- 	 	<h3 class="panel-heading">워크스페이스 멤버추가</h3>
	 	<%
	 	WS_Memeber_DTO dto = new WS_Memeber_DTO();
	 	JYP_WS_DAO dao = new JYP_WS_DAO();
	 	
	 	//워크스페이스 번호
	 	dto.setWs_no(1);
	 	//회원번호
	 	dto.setJyp_no(3);
	 	//회원아이피
	 	dto.setWs_Mip("111.111.111");
	 	
	 	int result= dao.member(dto);
	 	if(result>0){ out.println("워크스페이스 멤버추가 완료"); }
	 	else {  out.println("워크스페이스 멤버추가 ERROR"); }
	 	
	 	%>
	 	
		
 <%--  <h3 class="panel-heading">워크스페이스 번호</h3>
		<%
		JYP_WS_DAO dao = new JYP_WS_DAO();
		out.println(dao.ws_no(3));
		
		%>  --%>
<%-- 		
		<h3 class="panel-heading">워크스페이스 정보 리스트 </h3>
		<%
		JYP_WS_DAO dao = new JYP_WS_DAO();
		ArrayList<JYP_WS_DTO> list = new ArrayList<JYP_WS_DTO>();
		int jyp_no=4;
		//session에 저장된 회원번호
		list =dao.ws_info(jyp_no);
		
		out.print(list +"\n");
		
		
		
		%> --%>
<%-- 		
		<h3 class="panel-heading">워크스페이스 이름 변경  </h3>
		<%
		JYP_WS_DAO dao = new JYP_WS_DAO();
		JYP_WS_DTO dto1 = new JYP_WS_DTO();
		JYP_Member_DTO dto2 = new JYP_Member_DTO();
		
		dto1.setWs_name("WS 이름 변경");
		dto1.setWs_no(10);
		dto2.setJyp_no(4);
		
		int result = dao.ws_editName(dto1, dto2);
		
		if(result>0){ out.println("워크스페이스 이름 변경 완료"); }
	 	else {  out.println("워크스페이스 이름 변경 ERROR"); }
		
		%>
		 --%>
		
<%-- 		<h3 class="panel-heading">워크스페이스 정보불러오기</h3>
		<%
		JYP_WS_DTO dto = new JYP_WS_DTO();
		JYP_WS_DAO dao = new JYP_WS_DAO();
		
		int ws_no = 11;
		dto.setWs_no(ws_no);
		dto = dao.ws_info(dto);
		out.println(dto);
		%>
		 --%>
		
		
		
		
<%-- 		
	<h3 class="panel-heading">워크스페이스 이름 변경  </h3>
		<%
		JYP_WS_DAO dao = new JYP_WS_DAO();
		JYP_WS_DTO dto1 = new JYP_WS_DTO();
		JYP_Member_DTO dto2 = new JYP_Member_DTO();
		
		String ws_name = "test_dao수정";
		int ws_no = 11;
		int jyp_no2=4;
		
		dto1.setWs_name(ws_name);
		dto1.setWs_no(ws_no);
		int jyp_no1=dao.ws_info(dto1).getJyp_no(); //ws 정보불러오기 (워크스페이스의 관리자 번호를 빼오기 위해)
		out.println("jyp_no1 : "+jyp_no1+"<br/>" );
		dto1.setJyp_no(jyp_no1);
		dto2.setJyp_no(jyp_no2);
		
		int result = dao.ws_editName(dto1, dto2);
		
		if(result>0){ out.println("워크스페이스 이름수정 완료"); }
	 	else {  out.println("워크스페이스 이름수정 ERROR"); }
		
		
		%>  --%>
		
		
		
		
 	<h3 class="panel-heading">워크스페이스 삭제  </h3>
	<%
	JYP_WS_DAO dao_ws = new JYP_WS_DAO();
	JYP_WS_DTO dto_ws = new JYP_WS_DTO();
	JYP_Member_DTO dto_mem = new JYP_Member_DTO();
	JYP_Member_DAO dao_mem = new JYP_Member_DAO();
	
	//워크스페이스 관리자번호와 유저번호가 일치하는지, 그 번호가의 아이디비번이 입력한 아이디비번이랑 일치하는지 
	
	//유저 정보 빼오기
	String jyp_email = "44@44";
	
	dto_mem.setJyp_email(jyp_email);
	dto_mem = dao_mem.userinfo(dto_mem);
	
	int jyp_no = dto_mem.getJyp_no();
	String jyp_pass =dto_mem.getJyp_pass(); out.println("jyp_pass: " + jyp_pass);
	
	//워크스페이스 정보빼오기 
	int ws_no = 54;
	dto_ws.setWs_no(ws_no);
	dto_ws = dao_ws.ws_info(dto_ws);
	
	int jyp_no_ws = dto_ws.getJyp_no(); out.println("jyp_no_ws: " + jyp_no_ws); 
	String emaidlCheck = "44@44"; //request 받은 값
    String passCheck = "44"; //resquest 받은값 
    
	//워크스페이스 관리자번호와 유저번호가 일치하는지, 그 번호가의 아이디비번이 입력한 아이디비번이랑 일치하는지 
    if(jyp_no==jyp_no_ws && jyp_email.equals(emaidlCheck) && jyp_pass.equals(passCheck) ){
  		int result = dao_ws.ws_delete(dto_ws);
    	if(result>0){ out.println("워크스페이스 삭제 완료"); }
     	else {  out.println("워크스페이스 삭제 ERROR"); }

    }

	
	%>	  
		
<%-- 		
		<h3 class="panel-heading">워크스페이스 멤버 정보리스트  </h3>
		<%

		JYP_WS_Member_DAO dao = new JYP_WS_Member_DAO();
		WS_Memeber_DTO dto = new WS_Memeber_DTO();
		
		int ws_no = 29;
		dto.setWs_no(ws_no);
	
		out.println(dao.ws_memInfo(dto));
		
		
		
		%>
		 --%>
	
<%-- 	
	<h3 class="panel-heading">워크스페이스 멤버 삭제  </h3>		
	<%
	JYP_WS_Member_DAO dao = new JYP_WS_Member_DAO();
	WS_Memeber_DTO dto = new WS_Memeber_DTO();
	
	int ws_no = 2; // session 값 받아오기
	int jyp_no =6;  //삭제하려는 멤버번호-> requets 값 받아오기 
	
	dto.setWs_no(ws_no); dto.setJyp_no(jyp_no);
	
	//멤버삭제하기
	int result = dao.ws_memDelete(dto);
	if(result>0){ out.println("멤버삭제 성공 ");}
	else{ out.println("멤버삭제 ERROR" );}
	
	
	%>
		 --%>
		
		
		
		
		
		
		
		
		
		
		
		
		
	</div><!--end container-->
</body>
</html>

