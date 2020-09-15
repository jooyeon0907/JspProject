package com.jooyeon.controller_work;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jooyeon.dao.JYP_PW_Member_DAO;
import com.jooyeon.dao.JYP_Work_DAO;
import com.jooyeon.dto.PW_Member_DTO;
import com.jooyeon.dto.P_Work_DTO;
import com.jooyeon.frontcontroller.MAction;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class WEdit implements MAction {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession(true);
		
		String path ="/upload/";
		path = request.getServletContext().getRealPath(path);
		 
		
		MultipartRequest multi = new MultipartRequest(request, path, 1024*1024*15, "UTF-8",new DefaultFileRenamePolicy());
		String file = multi.getFilesystemName("file");
		String oldfile = multi.getParameter("oldfile");
		if(file==null) {System.out.println("파일 업로드 안됨"); file =oldfile;}
		System.out.println("WEdit에서 file : " + file);
		////////////////////////////////////////////////////////////
		
		P_Work_DTO dto = new P_Work_DTO();
		JYP_Work_DAO dao = new JYP_Work_DAO();	
		int jyp_no = (int)session.getAttribute("jyp_no");
		
		String work_start = multi.getParameter("work_start");
			if(work_start.equals("")) {work_start=null;}
		String work_end = multi.getParameter("work_end"); 
			if(work_end.equals("")) {work_end=null;}
		String work_content = (multi.getParameter("work_content"));
		int work_no = Integer.parseInt(multi.getParameter("work_no"));
		String work_label = multi.getParameter("work_label"); System.out.println("WEdit에서 work_label : " +work_label);
					
		dto.setWork_content(work_content);
		dto.setWork_start(work_start);
		dto.setWork_end(work_end);
		dto.setWork_no(work_no);
		dto.setWork_file(file);
		dto.setWork_label(work_label);
	
		///////////////
		//미리알림 설정하기 
		 //설정할 날짜,시간
		String work_alarm_date = multi.getParameter("work_alarm_date"); System.out.println("WEdit에서 work_alarm_date : " + work_alarm_date);
		String work_alarm_time = multi.getParameter("work_alarm_time"); System.out.println("WEdit에서 work_alarm_time : " + work_alarm_time);
		if(!(work_alarm_date.equals("")) && !(work_alarm_time.equals("")) ) {  //미리알람설정을 했다면 데이터 저장시키기
		//	if(work_alarm_time != null) {work_alarm_time="00:00:00.0";} //날짜만 설정하고 시간은 설정안했을때, 
		//(만약 2020-08-11 08:08:12 였다가 20일로 날짜만 바꾸면 2020-08-20 08:08:12 이렇게 시간은 그대로 남아서 00으로 맞춰줌)
		String work_alarm = work_alarm_date +" " + work_alarm_time;
		//System.out.println("###1. WAdd에서 work_alarm : " + work_alarm);
		
		PW_Member_DTO dto_mem = new PW_Member_DTO();
		JYP_PW_Member_DAO dao_mem  = new JYP_PW_Member_DAO();
		dto_mem.setWork_alarm(work_alarm); System.out.println("###1.WEdit에서 work_alarm : " + dto_mem.getWork_alarm());
		dto_mem.setWork_no(work_no); System.out.println("###2.WEdit에서 work_no : " + dto_mem.getWork_no());
		dto_mem.setJyp_no(jyp_no);	System.out.println("###3.WEdit에서 jyp_no : " + dto_mem.getJyp_no());
		
		int result2 = dao_mem.work_alarm(dto_mem); //알림 설정하기  
		System.out.println("###4.WEdit에서 미리알림추가 성공? : " + result2);
		}//if
		////////////////
	
		if(dao.update(dto) > 0) {
			
			if(dao.update(dto) > 0) {
				out.println("<script>alert('글수정 성공');" 
							+"location.href='"+request.getContextPath()+"/work_detail.work?work_no="+request.getParameter("work_no")+"'; </script>");
			}
		}else { out.println("<script>alert('글수정 ERORR.'); history.go(-1); </script>");}
		

	}

}
