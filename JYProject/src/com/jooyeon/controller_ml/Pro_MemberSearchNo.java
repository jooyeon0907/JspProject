package com.jooyeon.controller_ml;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.jooyeon.dao.WS_Invitation_DAO;
import com.jooyeon.dto.PW_Member_DTO;
import com.jooyeon.dto.P_Member_DTO;
import com.jooyeon.dto.WS_Memeber_DTO;
import com.jooyeon.frontcontroller.MAction;

public class Pro_MemberSearchNo implements MAction {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
	
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession(true);
		
		WS_Invitation_DAO dao = new WS_Invitation_DAO();
		P_Member_DTO dto_p = new P_Member_DTO();
		PW_Member_DTO dto_pw = new PW_Member_DTO();
		
		int p_no = (int)session.getAttribute("p_no");
		int work_no = Integer.parseInt(request.getParameter("work_no"));

		dto_p.setP_no(p_no);
		dto_pw.setWork_no(work_no);
		
		Gson gson = new Gson();
		JsonObject list = new JsonObject();
		list.add("members", dao.pro_noMem(dto_pw, dto_p)); //담아서
		out.println(gson.toJson(list)); //검색된 회원 정보 보내기
		out.close();

	}

}
