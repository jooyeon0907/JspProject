package com.jooyeon.controller_work;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.jooyeon.dao.P_Co_DAO;
import com.jooyeon.frontcontroller.MAction;

public class CommentList implements MAction {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//미완성 ( 안쓸듯)
		
		request.setCharacterEncoding("UTF-8");    
		response.setContentType("text/html; charset=UTF-8");
		
		PrintWriter out = response.getWriter();
		
		P_Co_DAO dao = new P_Co_DAO();
		
		int work_no = Integer.parseInt(request.getParameter("work_no")); 
		System.out.println("CommentList에서 work_no : " + work_no);
		
		Gson gson = new Gson();
		JsonObject list = new JsonObject();
		list.add("co_list", dao.listAll_ajax(work_no));
		out.println(gson.toJson(list));
		out.close();

	}

}
