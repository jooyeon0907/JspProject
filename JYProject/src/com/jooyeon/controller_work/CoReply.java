package com.jooyeon.controller_work;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jooyeon.dao.P_Co_DAO;
import com.jooyeon.dto.P_Co_DTO;
import com.jooyeon.frontcontroller.MAction;

public class CoReply implements MAction {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
	
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession(true);
		
		int co_no = Integer.parseInt(request.getParameter("co_no"));   System.out.println("CoReply에서 co_no: " +co_no);
		
		//1. 데이터 가져오기
		P_Co_DAO dao = new P_Co_DAO();
		P_Co_DTO dto = new P_Co_DTO(); dto.setCo_no(co_no);
		P_Co_DTO now = dao.coInfo(dto);
		
		int prev = ((int)Math.ceil(now.getCo_step()/(float)1000)-1)*1000;
		int curr = now.getCo_step();
		
		//2. 기존에 답변글이 달려있다면 : co_step=co-step-1해주기 
		dao.update_reply(prev, curr);
		/*
		 "insert into p_comments "
		+"(ws_no, p_no, wl_no, work_no, jyp_no, comment ,co_ip,co_group ,co_step,co_indent) "
		+"values (?,?,?,?,?,?,?,?,?,?) "); 
		 **/
		
		//답글 달 정보 
		int ws_no = Integer.parseInt(request.getParameter("ws_no"));
		int p_no = Integer.parseInt(request.getParameter("p_no"));
		int wl_no = Integer.parseInt(request.getParameter("wl_no"));
		int work_no = Integer.parseInt(request.getParameter("work_no"));
		int jyp_no = (int)session.getAttribute("jyp_no");
		String comment = request.getParameter("comment"); System.out.println("CoReply에서 comment : " + comment);
		String co_ip = InetAddress.getLocalHost().getHostAddress();
		
		P_Co_DTO re_dto = new P_Co_DTO();
		re_dto.setWs_no(ws_no);
		re_dto.setP_no(p_no);
		re_dto.setWl_no(wl_no);
		re_dto.setWork_no(work_no);
		re_dto.setJyp_no(jyp_no);
		re_dto.setComment(comment);
		re_dto.setCo_ip(co_ip);
		re_dto.setCo_group(now.getCo_group());
		re_dto.setCo_step(now.getCo_step()-1);
		re_dto.setCo_indent(now.getCo_indent()+1);
		
		//답글달기
		int result = dao.create_re(re_dto);
		
		if(result>0) { out.println("성공");  }
		else if(result>0) {	out.println("ERROR");}
		
		
		

	}

}
