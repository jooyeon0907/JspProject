package com.jooyeon.controller_ws;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jooyeon.dao.JYP_WS_DAO;
import com.jooyeon.dao.WS_Invitation_DAO;
import com.jooyeon.dto.JYP_Member_DTO;
import com.jooyeon.dto.JYP_WS_DTO;
import com.jooyeon.dto.Paging_Invi_DTO;
import com.jooyeon.dto.Paging_WS_DTO;
import com.jooyeon.dto.WS_Invitation_DTO;
import com.jooyeon.dto.WS_Memeber_DTO;
import com.jooyeon.frontcontroller.MAction;

public class WS_List implements MAction {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=utf-8");
		
		HttpSession session = request.getSession(true);

		//워크스페이스 리스트
		ArrayList<JYP_WS_DTO> ws_list = new ArrayList<JYP_WS_DTO>();
		JYP_WS_DAO dao = new JYP_WS_DAO();
		WS_Memeber_DTO dto = new WS_Memeber_DTO();
		int jyp_no = (int)session.getAttribute("jyp_no");
		
		dto.setJyp_no(jyp_no);

		
		///워크스페이스 paging//////////////////////////////
		//총 페이지 수 
		int pageTotal= dao.listCnt(dto); 
		// 한 화면에 보여줄 페이지의 수 -> 10으로 설정 
		int onepageLimit =10;
		// 하단 index의 개수 (버튼 개수)
		int pageAll =  (int) Math.ceil(pageTotal / (float)onepageLimit);
		
		int pstartNo=0; //현재페이지 (뷰단에서 파라미터 값으로 받아오기) 
		if(request.getParameter("pstartNo")!=null) {
		pstartNo = Integer.parseInt(request.getParameter("pstartNo")); }
		ws_list = dao.list10(dto, pstartNo);   
		
		int bottomList = 10; //하단 페이지 네비
		int bottom_current = (int)Math.ceil((pstartNo+1)/(float)onepageLimit);
		int bottom_start = (int)Math.floor((bottom_current-1)/(float)onepageLimit)*bottomList+1;
		int bottom_end = bottom_start+bottomList-1;
		if(pageAll<bottom_end) { bottom_end = pageAll;}
		
		
		//리스트 페이지로 넘어가기 
		request.setAttribute("ws_list", 
						new Paging_WS_DTO(pageTotal,onepageLimit,pageAll,pstartNo ,bottomList ,bottom_current ,
						bottom_start,bottom_end,ws_list ));

	///////////////////////////////////////////////////////////////////////////
		JYP_Member_DTO dto_invi = new JYP_Member_DTO();
		WS_Invitation_DAO dao_invi = new WS_Invitation_DAO();
		ArrayList<WS_Invitation_DTO> list_invi = new ArrayList<WS_Invitation_DTO>();
	
		// 초대한 날의 7일 뒤의 날짜가 현재 날짜보다 작으면 삭제 
		int ws_delAll = dao_invi.ws_delAll();
		System.out.println("WS_List에서 ws_delAll : " + ws_delAll);
		//워크스페이스 초대리스트
		String jyp_email = (String)session.getAttribute("jyp_email");
		dto_invi.setJyp_email(jyp_email);
		
		///초대리스트 paging//////////////////////////////
		//총 페이지 수 
		int pageTotal2= dao_invi.listCnt(dto_invi); 
		// 한 화면에 보여줄 페이지의 수 -> 10으로 설정 
		int onepageLimit2 =10;
		// 하단 index의 개수 (버튼 개수)
		int pageAll2 =  (int)Math.ceil(pageTotal2/(float)onepageLimit2);
		
		int pstartNo2=0; //현재페이지 (뷰단에서 파라미터 값으로 받아오기) 
		if(request.getParameter("pstartNo2")!=null) {
		pstartNo2 = Integer.parseInt(request.getParameter("pstartNo2")); }
		list_invi = dao_invi.list10(dto_invi, pstartNo2);   
		
		int bottomList2 = 10; //하단 페이지 네비
		int bottom_current2 = (int)Math.ceil((pstartNo2+1)/(float)onepageLimit2);
		int bottom_start2 = (int)Math.floor((bottom_current2-1)/(float)onepageLimit2)*bottomList2+1;
		int bottom_end2 = bottom_start2+bottomList2-1;
		if(pageAll2<bottom_end2) { bottom_end2 = pageAll2;}

		request.setAttribute("list_invi", 
						new Paging_Invi_DTO(pageTotal2,
									      onepageLimit2,
									      pageAll2,
									      pstartNo2,
									      bottomList2,
									      bottom_current2 ,
										  bottom_start2,
										  bottom_end2,
										  list_invi ));
		
		/////////////////////////////////////////////////////////////
		
		request.getRequestDispatcher("workspace/ws_list.jsp").forward(request, response);
		
		
		
		
		
		
	}

}
