package com.jooyeon.controller_work;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jooyeon.dao.P_Co_DAO;
import com.jooyeon.dto.P_Co_DTO;
import com.jooyeon.frontcontroller.MAction;

public class Comments implements MAction {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");    
		response.setContentType("text/html; charset=UTF-8");
		
		PrintWriter out = response.getWriter();
		
		P_Co_DAO dao = new P_Co_DAO();
		P_Co_DTO dto = new P_Co_DTO();
		
		int ws_no = Integer.parseInt(request.getParameter("ws_no"));
		int p_no = Integer.parseInt(request.getParameter("p_no"));
		int wl_no = Integer.parseInt(request.getParameter("wl_no"));
		int work_no = Integer.parseInt(request.getParameter("work_no"));
		int jyp_no = Integer.parseInt(request.getParameter("jyp_no"));
		String comment = request.getParameter("comment");
		String co_ip = InetAddress.getLocalHost().getHostAddress();
		//최대값 구하기 
		int max = dao.max_read(work_no); System.out.println("WComments에서 max : " + max);
		int co_group = ( (int)(Math.ceil(max/(float)1000))  )+1;  System.out.println("WComments에서 co_group: " + co_group);
		int co_step = co_group*1000;							 System.out.println("WComments에서 co_step: " + co_step);
		
		dto.setWs_no(ws_no);
		dto.setP_no(p_no);
		dto.setWl_no(wl_no);
		dto.setWork_no(work_no);
		dto.setJyp_no(jyp_no);
		dto.setComment(comment);
		dto.setCo_ip(co_ip);
		dto.setCo_group(co_group);
		dto.setCo_step(co_step);
		
		//댓글달기
		int result = dao.create(dto); 
		if(result>0){
			out.println("성공");
		}else {out.println("ERROR");}
		
		
	}

}
