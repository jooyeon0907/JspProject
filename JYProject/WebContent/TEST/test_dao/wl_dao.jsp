<%@page import="com.jooyeon.dto.PW_Member_DTO"%>
<%@page import="com.jooyeon.dao.JYP_PW_Member_DAO"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.jooyeon.dao.JYP_P_Member_DAO"%>
<%@page import="com.jooyeon.dto.JYP_Member_DTO"%>
<%@page import="com.jooyeon.dao.JYP_Member_DAO"%>
<%@page import="com.jooyeon.dto.P_Work_DTO"%>
<%@page import="com.jooyeon.dao.JYP_Work_DAO"%>
<%@page import="com.jooyeon.dto.P_WL_DTO"%>
<%@page import="com.jooyeon.dao.JYP_WL_DAO"%>
<%@page import="com.jooyeon.dto.P_Member_DTO"%>
<%@page import="com.jooyeon.dto.JYP_Project_DTO"%>
<%@page import="com.jooyeon.dao.JYP_Project_DAO"%>
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
	
	<h3 class="panel-heading">업무리스트 생성하기</h3>
	<%
	P_WL_DTO dto = new P_WL_DTO();
	JYP_WL_DAO dao = new JYP_WL_DAO();
	
	int jyp_no = 4;//(int)session.getAttribute("jyp_no");
	int ws_no = 1;//(int)session.getAttribute("ws_no");
	int p_no = 5;//(int)session.getAttribute("p_no");
	String wl_name = "tsetteㅜ";//request.getParameter("wl_name");
	String wl_ip = "11.111.111.";//(String)session.getAttribute("jyp_ip");
	//워크스페이스 번호
	dto.setWs_no(ws_no);
	//프로젝트 번호
	dto.setP_no(p_no);
	//회원 번호
	dto.setJyp_no(jyp_no);
	//업무리스트 이름 
	dto.setWl_name(wl_name);
	//작성한 회원의 아이피 
	dto.setWl_ip(wl_ip);
	
	//업무리스트 생성 
	int result = dao.wl_add(dto);
	//방금 만든 업무리스트 번호 뽑기
	int wl_no = dao.wl_no(dto);  out.println("wl_no : "+wl_no);
///////@@샘플데이터 추가  -p_work,pw_member @@////////////////
	int sample =0;  jyp_no =0;
		SimpleDateFormat date = new SimpleDateFormat("yyy-MM-dd");
		Calendar now = Calendar.getInstance();
		String nowTime = date.format(now.getTime()); // System.out.println("WS_Add에서 nowTime: " + nowTime);
	
	
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
		dto_work.setWork_ip(wl_ip);
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
		
		dto_mem2.setJyp_no(jyp_no); //0
		dto_mem2.setJyp_ip(wl_ip);
		
		//업무멤버생성//
		sample+= dao_pwmem.pw_member(dto_mem1, dto_mem2);
		
		
		out.print("sample : " + sample);  //2여야함
		
		
		
///////////////////////////////////////////////////////////		
	
	
	if(result>0 && sample==2) {
		out.println("업무리스트 생성완료");
	}else { 
		out.println("업무리스트 생성 ERROR");
	} 
	
	%>
	
<%--  	
 		<h3 class="panel-heading">업무리스트 삭제하기 (업무리스트에 해당되는 업무들도 다 삭제)XXX</h3>
		<%
		////삭제 권한 확인하기 
		//프로젝트 관리자인지 확인 
		P_Member_DTO dto_pro = new P_Member_DTO();
		JYP_Project_DAO dao_pro = new JYP_Project_DAO();
		
		int p_no = 15;
		int jyp_no = 4; //session에 저장된데이터 부르기 
		
		dto_pro.setP_no(p_no);
		dto_pro.setJyp_no(jyp_no);
		
		int p_access = dao_pro.p_access(dto_pro);  out.print("p_access: " + p_access +"<br/>");
		
		//업무리스트 작성자인지 확인
		P_WL_DTO dto_wl = new P_WL_DTO();
		JYP_WL_DAO dao_wl = new JYP_WL_DAO();
		
		int wl_no = 18;
		dto_wl.setWl_no(wl_no);
		
		String wl_ip = dao_wl.wl_ip(dto_wl); out.print("dto_wl: " + wl_ip +"<br/>");
		
		//유저가 해당 프로젝트의 관리자이거나 해당 업무리스트의 작성자이라면 삭제하기
		//유저 정보
		JYP_Member_DAO dao_user = new JYP_Member_DAO();
		JYP_Member_DTO dto_user = new JYP_Member_DTO();
		String jyp_email ="44@44";
		dto_user.setJyp_email(jyp_email);
		dto_user = dao_user.userinfo(dto_user);
		String jyp_ip = dto_user.getJyp_ip();
		if(p_access==1 && wl_ip.equals(jyp_ip)){
			//업무리스트 삭제 
			int result1 = dao_wl.wl_delete(dto_wl);   out.println("wl삭제성공여부 : " + result1 +"<br/>");
			
			//업무리스트에 해당하는 업무들 삭제
			P_Work_DTO dto_work = new P_Work_DTO();
			JYP_Work_DAO dao_work = new JYP_Work_DAO();
			
			dto_work.setWl_no(wl_no);
			int result2 = dao_work.wl_delete(dto_work);  out.println("work삭제성공여부 : " + result2 +"<br/>");
			//삭제 성공시
			if(result1>0 && result2>0){ out.println("업무list 삭제 완료"); }
			else {  out.println("업무list 삭제 ERROR");  };
			
			
			
		}
		
		
		%> 
		  --%>
		
		
<%-- 	<h3 class="panel-heading">업무리스트 이름 변경(작성자만 변경가능)</h3>
		<%
		P_WL_DTO dto = new P_WL_DTO();
		JYP_WL_DAO dao = new JYP_WL_DAO();
		
		//업무리스트 작성자인지 확인 
		int wl_no = 18;
		dto.setWl_no(wl_no);
		String wl_ip = dao.wl_ip(dto);  out.println("wl_ip : " + wl_ip +"<br>/");
		
		//유저 정보
		JYP_Member_DAO dao_user = new JYP_Member_DAO();
		JYP_Member_DTO dto_user = new JYP_Member_DTO();
		String jyp_email ="44@44";
		dto_user.setJyp_email(jyp_email); 
		dto_user = dao_user.userinfo(dto_user); 
		String jyp_ip = dto_user.getJyp_ip();    out.println("jyp_ip : " + jyp_ip +"<br>/");
		
		// 유저가 업무리스트 작성자라면 업무리스트 이름변경
		if(wl_ip.equals(jyp_ip)){ 
			String wl_name = "wl_dao TEST";
			dto.setWl_name(wl_name);
			dto.setWl_no(wl_no);
			
			int result = dao.wl_edit_name(dto);
			if(result>0){ out.println("업무list 이름수정 완료"); }
			else {  out.println("업무list 이름수정 ERROR");  }
		}
		%> 
		
		 --%>
		
<%-- 		
		<h3 class="panel-heading">업무리스트 삭제하기</h3>
		<%
		
		 //권한체크  
		 //프로젝트 관리자 권한체크
		 JYP_P_Member_DAO dao_pMem = new JYP_P_Member_DAO();
		 P_Member_DTO dto_pMem = new P_Member_DTO();
		 
		 int p_no = 2; // parameter 넘겨받기
		 int jyp_no = 4; // session 값 받기
		 dto_pMem.setP_no(p_no);
		 dto_pMem.setJyp_no(jyp_no);
		 
		 //프로젝트 권한 확인 -return값: 관리자 =1, 일반멤버  =2 
		 int p_access = dao_pMem.p_access(dto_pMem); 
		
		// 업무리스트 작성자인지 체크
		JYP_WL_DAO dao_wl = new JYP_WL_DAO();
		P_WL_DTO dto_wl = new P_WL_DTO();

		int jypno_Check = dao_wl.wl_jypNo(dto_wl); //작성자 회원의 번호 받기 
		int wl_no = 5; // parameter 값 넘겨 받기 
		dto_wl.setWl_no(wl_no);
		//관리자 권한이거나 업무리스트 작성자이면 삭제실행
		if(p_access==1 || jypno_Check == jyp_no){
			out.println("삭제권한있음 ");
			int result = dao_wl.wl_delete(dto_wl); //업무리스트 삭제
		 	if(result>0){ 
		 		out.println("업무리스트 삭제 성공");
		 	}else {out.println("업무리스트 삭제 ERROR");}
		}//if
		
		
		%> --%>
		
	</div><!--end container-->
</body>
</html>














