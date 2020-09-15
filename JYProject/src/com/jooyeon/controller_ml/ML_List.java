package com.jooyeon.controller_ml;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jooyeon.dao.JYP_Member_DAO;
import com.jooyeon.dao.JYP_WS_DAO;
import com.jooyeon.dao.JYP_WS_Member_DAO;
import com.jooyeon.dto.JYP_Member_DTO;
import com.jooyeon.dto.JYP_WS_DTO;
import com.jooyeon.dto.Paging_WM_DTO;
import com.jooyeon.dto.WS_Memeber_DTO;
import com.jooyeon.frontcontroller.MAction;

public class ML_List implements MAction {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8 ");
		
		HttpSession session = request.getSession(true);
		
		WS_Memeber_DTO dto = new WS_Memeber_DTO();
		JYP_WS_Member_DAO dao = new JYP_WS_Member_DAO();
		ArrayList<JYP_Member_DTO> list =  new ArrayList<JYP_Member_DTO>();
		
		int ws_no = (int)session.getAttribute("ws_no");
		dto.setWs_no(ws_no);
		
		////////워크스페이스 관리자 번호도 넘겨줘야됨 
		JYP_WS_DTO dto_ws = new JYP_WS_DTO();
		JYP_WS_DAO dao_ws = new JYP_WS_DAO();
		JYP_Member_DTO dto_mem = new JYP_Member_DTO();
		JYP_Member_DAO dao_mem = new JYP_Member_DAO();
		dto_ws.setWs_no(ws_no);  dto_ws = dao_ws.ws_info(dto_ws); //ws정보뽑기
		int ws_admin = dto_ws.getJyp_no();
		dto_mem.setJyp_no(ws_admin); 
		String ws_email = dao_mem.jyp_email(dto_mem); //관리자의 이메일뽑기
		//해당 워크스페이스 관리자 번호,이메일 넘겨주기
		request.setAttribute("ws_admin", ws_admin);
		request.setAttribute("ws_email", ws_email);
		
		////////////////////////////paging/////
		//총 페이지 수 
		int pageTotal= dao.listCnt(dto); 
		// 한 화면에 보여줄 페이지의 수 -> 10으로 설정 
		int onepageLimit =10;
		// 하단 index의 개수 (버튼 개수)
		int pageAll =  (int)Math.ceil(pageTotal/(float)onepageLimit);
		
		int pstartNo=0; //현재페이지 (뷰단에서 파라미터 값으로 받아오기) 
		if(request.getParameter("pstartNo")!=null) {
			pstartNo = Integer.parseInt(request.getParameter("pstartNo")); }
		list = dao.list10(dto, pstartNo);   
		
		int bottomList = 10; //하단 페이지 네비
		int bottom_current = (int)Math.ceil((pstartNo+1)/(float)onepageLimit);
		int bottom_start = (int)Math.floor((bottom_current-1)/(float)onepageLimit)*bottomList+1;
		int bottom_end = bottom_start+bottomList-1;
		if(pageAll<bottom_end) { bottom_end = pageAll;}
	
		
		//리스트 페이지로 넘어가기 
		request.setAttribute("memberList", 
									new Paging_WM_DTO(pageTotal,onepageLimit,pageAll,pstartNo ,bottomList ,bottom_current ,
									bottom_start,bottom_end,list ));
		request.getRequestDispatcher("memberList/memberList.jsp").forward(request, response);
		
		
		
		
		
		
		

	}

}
