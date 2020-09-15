package com.jooyeon.controller_ws;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.jooyeon.dao.JYP_WS_DAO;
import com.jooyeon.dto.JYP_Member_DTO;
import com.jooyeon.frontcontroller.MAction;

public class WS_ListBar implements MAction {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		PrintWriter out = response.getWriter();
		
		JYP_WS_DAO dao = new JYP_WS_DAO();
		JYP_Member_DTO dto = new JYP_Member_DTO();
		
		int jyp_no = Integer.parseInt(request.getParameter("jyp_no"));
		System.out.println("WS_ListBar에서 jyp_no : " + jyp_no);
		dto.setJyp_no(jyp_no);
		
		Gson gson = new Gson();
		JsonObject ws_list = new JsonObject();
		ws_list.add("ws_list", dao.ws_list_ajax(dto));
		out.println(gson.toJson(ws_list));
		request.setAttribute("wsListst", gson.toJson(ws_list));
		
	}

}
