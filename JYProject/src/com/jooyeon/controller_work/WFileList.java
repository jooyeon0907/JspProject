package com.jooyeon.controller_work;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jooyeon.dao.JYP_Work_DAO;
import com.jooyeon.dto.P_Work_DTO;
import com.jooyeon.dto.Paging_File_DTO;
import com.jooyeon.frontcontroller.MAction;

public class WFileList implements MAction {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		HttpSession session =request.getSession(true);
		
		ArrayList<P_Work_DTO> list = new ArrayList<P_Work_DTO>();
		JYP_Work_DAO dao_work = new JYP_Work_DAO();
		P_Work_DTO dto_work = new P_Work_DTO();  
		int p_no  = (int)session.getAttribute("p_no"); dto_work.setP_no(p_no); //System.out.println("WFileList에서 p_no : " + p_no);
//		request.setAttribute("list", dao_work.work_listAll2(p_no));//업무
		
		//페이징///////////////////////
		//총 페이지수
		int pageTotal= dao_work.listCnt(p_no); 
		// 한 화면에 보여줄 페이지의 수 -> 10으로 설정 
		int onepageLimit =10;
		// 하단 index의 개수 (버튼 개수)
		int pageAll =  (int)Math.ceil(pageTotal/(float)onepageLimit);
		
		int pstartNo=0; //현재페이지 (뷰단에서 파라미터 값으로 받아오기) 
		if(request.getParameter("pstartNo")!=null) {
			pstartNo = Integer.parseInt(request.getParameter("pstartNo")); } 
		list = dao_work.list10(dto_work, pstartNo);   System.out.println("WFileList에서 list: " + list);
		
		int bottomList = 10; //하단 페이지 네비
		int bottom_current = (int)Math.ceil((pstartNo+1)/(float)onepageLimit);
		int bottom_start = (int)Math.floor((bottom_current-1)/(float)onepageLimit)*bottomList+1;
		int bottom_end = bottom_start+bottomList-1;
		if(pageAll<bottom_end) { bottom_end = pageAll;}
		
		
		//리스트 페이지로 넘어가기 
				request.setAttribute("fileList", 
											new Paging_File_DTO(pageTotal,onepageLimit,pageAll,pstartNo ,bottomList ,bottom_current ,
											bottom_start,bottom_end, list ));
		request.getRequestDispatcher("/workList/fileList.jsp").forward(request, response);

	}

}
