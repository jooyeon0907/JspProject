package com.jooyeon.controller_project;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jooyeon.dao.JYP_P_Member_DAO;
import com.jooyeon.dao.JYP_Project_DAO;
import com.jooyeon.dao.JYP_WS_DAO;
import com.jooyeon.dto.JYP_Project_DTO;
import com.jooyeon.dto.JYP_WS_DTO;
import com.jooyeon.dto.P_Member_DTO;
import com.jooyeon.frontcontroller.MAction;

public class P_Edit_View implements MAction {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8 ");
	
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession(true);
		
		 //권한체크  - 프로젝트과닐자, 워크스페이스 관리자
		 JYP_P_Member_DAO dao_pMem = new JYP_P_Member_DAO();
		 P_Member_DTO dto_pMem = new P_Member_DTO();
		 JYP_WS_DTO dto_ws = new JYP_WS_DTO();
		 JYP_WS_DAO dao_ws = new JYP_WS_DAO(); 
		 
		int jyp_no = (int)session.getAttribute("jyp_no");
		int p_no = Integer.parseInt(request.getParameter("p_no")); System.out.println("P_Edit_View에서 p_no:" + p_no);
		 dto_pMem.setP_no(p_no);
		 dto_pMem.setJyp_no(jyp_no);
		
		 //프로젝트 권한 확인 -return값: 관리자 =1, 일반멤버  =2 
		 int p_access = dao_pMem.p_access(dto_pMem);  System.out.println("P_Edit_View에서 p_access:" + p_access);
		 
		 //워크스페이스 관한 확인 
		 int ws_no = (int)session.getAttribute("ws_no");
		 dto_ws.setWs_no(ws_no); dto_ws = dao_ws.ws_info(dto_ws); //워크스페이스 정보담기
		 int jyp_no_ws = dto_ws.getJyp_no(); System.out.println("P_Edit_View 에서jyp_no_ws :" + jyp_no_ws);
		 
		//프로젝트 권한이 아니거나 워크스페이스 관리자가 아니면 설정페이지 들어갈 수 없음  
		 if(p_access==1 || jyp_no_ws==jyp_no ) {
			JYP_Project_DTO dto_pro = new JYP_Project_DTO();
			JYP_Project_DAO dao_pro = new JYP_Project_DAO();
			JYP_P_Member_DAO dao_mem = new JYP_P_Member_DAO();
			P_Member_DTO dto_mem = new P_Member_DTO();
			ArrayList<P_Member_DTO> list = new ArrayList<P_Member_DTO>();
			
			dto_pro.setP_no(p_no);	
			dto_mem.setP_no(p_no); dto_mem.setJyp_no(jyp_no);
			list= dao_mem.p_memList(dto_mem);
			
			
			request.setAttribute("dto_pro", dao_pro.p_info(dto_pro)); //프로젝트  정보 담기 		
			request.setAttribute("dto_mem",list); System.out.println(list);   
			request.setAttribute("dto_mem2",  dao_mem.p_memInfo(dto_mem));
		//	request.setAttribute("p_access", dao_mem.p_access(dto_mem));
		//	 out.println("<script>location.href='"+request.getContextPath()+"/project/p_edit.jsp';</script>"); 
			request.getRequestDispatcher("project/p_edit.jsp").forward(request, response);		
		 } 
		 else {
			 System.out.println("권한X");
		//	 request.getRequestDispatcher("/p_accError.pro").forward(request, response);
			 out.println("<script> alert('권한없음'); history.go(-1);</script>");
		} 
		 
		 
	}

}
