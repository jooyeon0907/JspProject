package com.jooyeon.controller_ws;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jooyeon.dao.JYP_Member_DAO;
import com.jooyeon.dao.JYP_PW_Member_DAO;
import com.jooyeon.dao.JYP_P_Member_DAO;
import com.jooyeon.dao.JYP_WS_DAO;
import com.jooyeon.dao.JYP_WS_Member_DAO;
import com.jooyeon.dto.JYP_Member_DTO;
import com.jooyeon.dto.JYP_WS_DTO;
import com.jooyeon.dto.PW_Member_DTO;
import com.jooyeon.dto.P_Member_DTO;
import com.jooyeon.dto.WS_Memeber_DTO;
import com.jooyeon.frontcontroller.MAction;

public class WS_MemDelete implements MAction {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession(true);

		JYP_Member_DTO dto_mem = new JYP_Member_DTO();
		JYP_Member_DAO dao_mem = new JYP_Member_DAO();
		
		//워크스페이스 관리자번호와 유저번호가 일치하는지, 그 번호가의 아이디비번이 입력한 아이디비번이랑 일치하는지 
		
		//유저 정보 빼오기
		String jyp_email = (String)session.getAttribute("jyp_email");   
		
		dto_mem.setJyp_email(jyp_email);
		dto_mem = dao_mem.userinfo(dto_mem);
		
		int jyp_no = dto_mem.getJyp_no();
		String jyp_pass =dto_mem.getJyp_pass(); //System.out.println("WS_Delete 에서 jyp_pass: " + jyp_pass);

		
		int ws_no = (int)session.getAttribute("ws_no");
		String emailCheck = request.getParameter("jyp_email"); //request 받은 값
			System.out.println("WS_Delete에서 emailCheck: "+emailCheck) ;
	    String passCheck = request.getParameter("jyp_pass"); //resquest 받은값 
	    	System.out.println("WS_Delete에서 passCheck: "+passCheck) ;
    	
	    //이메일비번 통과시 탈퇴
    	if(jyp_email.equals(emailCheck) && jyp_pass.equals(passCheck) ){
    		//ws_member
			JYP_WS_Member_DAO dao_wm = new JYP_WS_Member_DAO();
			WS_Memeber_DTO dto_wm = new WS_Memeber_DTO();
			dto_wm.setWs_no(ws_no); dto_wm.setJyp_no(jyp_no);  
			
			//p_member
			JYP_P_Member_DAO dao_pm = new JYP_P_Member_DAO();
			P_Member_DTO dto_pm = new P_Member_DTO();
			dto_pm.setWs_no(ws_no); dto_pm.setJyp_no(jyp_no);
			
			//pw_member
			JYP_PW_Member_DAO dao_pwm = new JYP_PW_Member_DAO();
			PW_Member_DTO dto_pwm = new PW_Member_DTO();
			dto_pwm.setWs_no(ws_no); dto_pwm.setJyp_no(jyp_no);
			
			//멤버삭제하기
			int pwm_delete = dao_pwm.ws_workmemDel(dto_pwm);	 System.out.println("WS_Delete에서 pwm_delete: "+pwm_delete) ;
			int pm_delete = dao_pm.ws_pmDelete(dto_pm);		 System.out.println("WS_Delete에서 pm_delete: "+pm_delete) ;
			int wm_delete = dao_wm.ws_memDelete(dto_wm);   System.out.println("WS_Delete에서 wm_delete: "+wm_delete) ;
			
			if(wm_delete>0 || pm_delete>0 || pwm_delete>0){ 
				session.removeAttribute("ws_no"); session.removeAttribute("ws_name");   //session값 지우기 
				out.println("<script>alert('탈퇴 성공');"
					+ "location.href='"+request.getContextPath()+"/login2.members';</script>");}
			else{ out.println("<script>alert('탈퇴 ERROR'); history.go(-1);</script>" );}
    	}else {
    		out.println("<script>alert('정보입력이 잘못되었습니다.'); history.go(-1);</script>" );
    	}
		

	}

}
