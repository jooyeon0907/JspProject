package com.jooyeon.controller_ws;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.jooyeon.dao.WS_Invitation_DAO;
import com.jooyeon.dto.JYP_WS_DTO;
import com.jooyeon.dto.Paging_Invi_DTO;
import com.jooyeon.dto.WS_Invitation_DTO;
import com.jooyeon.frontcontroller.MAction;

public class WS_InviList implements MAction {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		HttpSession session = request.getSession(true);	
		PrintWriter out = response.getWriter();
		
		WS_Invitation_DAO dao = new WS_Invitation_DAO();
		JYP_WS_DTO dto = new JYP_WS_DTO();
		int ws_no = (int)session.getAttribute("ws_no");
		dto.setWs_no(ws_no);
		
		Gson gson = new Gson();
		JsonObject inviList = new JsonObject();
		inviList.add("list", dao.ws_inviListAll(dto));  ///담아서
//		System.out.println("WS_InviList : " +dao.ws_inviListAll(dto));
		out.println(gson.toJson(inviList));			//보내기
		out.close();

		
		///초대리스트 paging//////////////////////////////
		ArrayList<WS_Invitation_DTO> list_invi = new ArrayList<WS_Invitation_DTO>();
		WS_Invitation_DTO dto_invi = new WS_Invitation_DTO(); dto_invi.setWs_no(ws_no);
			//총 페이지 수 		
			int pageTotal2= dao.listCnt2(dto_invi); 
			// 한 화면에 보여줄 페이지의 수 -> 10으로 설정 
			int onepageLimit2 =10;
			// 하단 index의 개수 (버튼 개수)
			int pageAll2 =  (int)Math.ceil(pageTotal2/(float)onepageLimit2);
			
			int pstartNo2=0; //현재페이지 (뷰단에서 파라미터 값으로 받아오기) 
			if(request.getParameter("pstartNo2")!=null) {
			pstartNo2 = Integer.parseInt(request.getParameter("pstartNo2")); }
			list_invi = dao.invi_list10(dto_invi, pstartNo2);  
			
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
		
		
		
		
		
	}

}
