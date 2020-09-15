package com.jooyeon.controller_alarm;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jooyeon.dao.UpdateDAO;
import com.jooyeon.dto.JYP_Project_DTO;
import com.jooyeon.dto.P_Work_DTO;
import com.jooyeon.frontcontroller.MAction;

public class WorkDate implements MAction {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		
//		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession(true);
		
	 	UpdateDAO dao = new UpdateDAO();
	 	P_Work_DTO dto_work = new P_Work_DTO();
	 	ArrayList<P_Work_DTO> list = new ArrayList<P_Work_DTO>();
	 	
	 	int ws_no  = (int)session.getAttribute("ws_no"); ///session값 불러오기
	 	int jyp_no = (int)session.getAttribute("jyp_no");//session 값 불러오기
	 	
	 	/////업무 미리알림/////////////////////
	 	dto_work.setWs_no(ws_no); dto_work.setJyp_no(jyp_no);
	 	list = dao.workAlarm(dto_work);
	 	request.setAttribute("list_alarm", list);
	 //	System.out.println("WorkDate에서 list_alarm: " + list);
	 	/////end 업무 미리알림/////////////////////
		
	 	
	 	/////업무 시작일/마감일/////////////////////
	 	dto_work.setWs_no(ws_no);  dto_work.setJyp_no(jyp_no);
	 	list = dao.workDate(dto_work);
		request.setAttribute("list_work", list);
		//System.out.println("WorkDate에서 list_work: " + list);
 		/////end 업무 시작일/마감일/////////////////////
	 	
	 	
		/////프로젝트 시작일/마감일/////////////////////
		JYP_Project_DTO dto_pro = new JYP_Project_DTO();
		ArrayList<JYP_Project_DTO> list_pro = new  ArrayList<JYP_Project_DTO>();
		
		dto_pro.setWs_no(ws_no);  dto_pro.setJyp_no(jyp_no);
		list_pro = dao.p_list(dto_pro);
		request.setAttribute("list_pro", list_pro);
	//	System.out.println("WorkDate에서 list_pro: " + list_pro);
		/////end 프로젝트 시작일/마감일/////////////////////
	 	
	 	

		

	}

}
