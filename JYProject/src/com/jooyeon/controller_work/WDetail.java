package com.jooyeon.controller_work;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jooyeon.dao.JYP_PW_Member_DAO;
import com.jooyeon.dao.JYP_Work_DAO;
import com.jooyeon.dao.P_Co_DAO;
import com.jooyeon.dto.PW_Member_DTO;
import com.jooyeon.dto.P_Work_DTO;
import com.jooyeon.frontcontroller.MAction;

public class WDetail implements MAction {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8 ");

		HttpSession session = request.getSession(true);
		PrintWriter out = response.getWriter();
		
		JYP_Work_DAO dao = new JYP_Work_DAO();
		JYP_PW_Member_DAO dao_pwm = new JYP_PW_Member_DAO();
		PW_Member_DTO dto_pwm = new PW_Member_DTO();	
		P_Work_DTO dto_w  =new P_Work_DTO();
		
		int editRe = 1;
		if(request.getParameter("editRe")!=null) { 
			editRe = Integer.parseInt(request.getParameter("editRe"));
			System.out.println("WDetail에서 editRe : " + editRe);
		}
		int work_no = Integer.parseInt(request.getParameter("work_no"));
		int jyp_no = (int)session.getAttribute("jyp_no");
		dto_pwm.setJyp_no(jyp_no); dto_pwm.setWork_no(work_no);//System.out.println("WDetail jyp_no : " + jyp_no);
		dto_w.setWork_no(work_no);
		
		
		//업무확인 흔적남기기 (pw 멤버가 볼때만 해당 ) 
		//우선 해당업무의 멤버인지 체크 
		int memCheck = dao_pwm.memCheck(dto_pwm);
		if(memCheck>0) { //업무의 멤버라면 확인 흔적 남기게 하기 
			// work_check가 n인지 확인
			dto_pwm = dao_pwm.memInfo(dto_pwm);
			String work_check = dto_pwm.getWork_check(); System.out.println("WDetail에서 work_check: " + work_check);
			if(work_check.equals("n")) { //업무미확인으로 되어있다면
				//업무확인로 바꿔주기  (y)
				int result = dao_pwm.workCheck(dto_pwm);
				if(result>0) {System.out.println("업무확인체크");}
				else if(result>0) {
					//System.out.println("업무확인체크 ERROR");
					out.println("<script>alert('업무확인체크 ERROR'); histroy.go(-1);</script>");}
			}
		}// end if
		
		
		
		
		request.setAttribute("dto", dao.work_detail(dto_w,dto_pwm)); //업무상세내용 
		System.out.println("WDetail: " + dao.work_detail(dto_w,dto_pwm));
		request.setAttribute("writer", dao.writer(work_no)); //업무 작성자 이름  
		request.setAttribute("memList", dao_pwm.pw_memberList(dto_pwm)); // 멤버리스트 (멤버추가/삭제할때 이용)

		
		//코멘트 리스트
		P_Co_DAO dao_co = new P_Co_DAO();
		request.setAttribute("co_list", dao_co.listAll(work_no));
		request.setAttribute("editRe", editRe);   //코멘트에서 수정을 했다면  menu2(코멘트 탭)에 active를 주기 위함 
		
		

	}

}


