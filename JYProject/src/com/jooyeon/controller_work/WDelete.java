package com.jooyeon.controller_work;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jooyeon.dao.JYP_PW_Member_DAO;
import com.jooyeon.dao.JYP_P_Member_DAO;
import com.jooyeon.dao.JYP_WS_DAO;
import com.jooyeon.dao.JYP_Work_DAO;
import com.jooyeon.dto.JYP_WS_DTO;
import com.jooyeon.dto.PW_Member_DTO;
import com.jooyeon.dto.P_Member_DTO;
import com.jooyeon.dto.P_Work_DTO;
import com.jooyeon.frontcontroller.MAction;

public class WDelete implements MAction {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		PrintWriter out = response.getWriter();
		HttpSession session =request.getSession(true);
		
		 //권한체크 
		//워크스페이스 관리자번호 체크
		//워크스페이스 정보빼오기 
		JYP_WS_DAO dao_ws = new JYP_WS_DAO();
		JYP_WS_DTO dto_ws = new JYP_WS_DTO();
		
		int ws_no = (int)session.getAttribute("ws_no");
		dto_ws.setWs_no(ws_no);
		dto_ws = dao_ws.ws_info(dto_ws);
		int jyp_no_ws = dto_ws.getJyp_no(); System.out.println("jyp_no_ws: " + jyp_no_ws); 
		
		
		 //프로젝트 관리자 권한체크
		 JYP_P_Member_DAO dao_pMem = new JYP_P_Member_DAO();
		 P_Member_DTO dto_pMem = new P_Member_DTO();
		 
		 int p_no = (int)session.getAttribute("p_no"); // session 값 받기    
		 int jyp_no = (int)session.getAttribute("jyp_no"); // session 값 받기
		 dto_pMem.setP_no(p_no);
		 dto_pMem.setJyp_no(jyp_no);
		 
		 //프로젝트 권한 확인 -return값: 관리자 =1, 일반멤버  =2 
		 int p_access = dao_pMem.p_access(dto_pMem); 
				
		 //업무배정 멤버인지 체크 
		 P_Work_DTO dto_work = new P_Work_DTO();
		 JYP_Work_DAO dao_work = new JYP_Work_DAO();		
		 
		 int work_no = Integer.parseInt(request.getParameter("work_no"));
		 dto_work.setWork_no(work_no);
		 dto_work.setJyp_no(jyp_no);

		 int work_mem = dao_work.work_mem(dto_work);  //업무배정 멤버 체크
		 	
		 System.out.println("WDelete에서 work_no : " +work_no);
		//관리자 권한이거나 업무리스트 작성자이거나 업무배정 멤버이면 삭제가능
		 if(jyp_no_ws==jyp_no || p_access==1 || work_mem >0){
			 PW_Member_DTO dto_pw = new PW_Member_DTO(); dto_pw.setWork_no(work_no);
			 JYP_PW_Member_DAO dao_pw = new JYP_PW_Member_DAO();
			 int result2 = dao_pw.work_memDelAll(dto_pw); //삭제하는 업무에 속한 멤버들도 삭제됨 
			 int result = dao_work.work_delete(dto_work); // 업무삭제
			 System.out.println("WDelete에서 업무삭제/업무멤버삭제 : " +result   +" / " +result2);
			 if(result>0) {
				 out.println("<script>alert('업무삭제완료');"
				 		+ "location.href='"+request.getContextPath()+"/list.work';</script>"); 
			 }else {  out.println("<script>alert('업무삭제ERROR'); history.go(-1);</script>");}
			 
		 }else { 
		//System.out.println("WEditview : 권한X");
		  out.println("<script>alert('권한없음'); history.go(-1);</script>"); }
		 

		 
		 
		 
		 
		 
		 
	}

} // end class
