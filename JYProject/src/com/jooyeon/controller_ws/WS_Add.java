package com.jooyeon.controller_ws;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jooyeon.dao.JYP_PW_Member_DAO;
import com.jooyeon.dao.JYP_P_Member_DAO;
import com.jooyeon.dao.JYP_Project_DAO;
import com.jooyeon.dao.JYP_WL_DAO;
import com.jooyeon.dao.JYP_WS_DAO;
import com.jooyeon.dao.JYP_WS_Member_DAO;
import com.jooyeon.dao.JYP_Work_DAO;
import com.jooyeon.dto.JYP_Member_DTO;
import com.jooyeon.dto.JYP_Project_DTO;
import com.jooyeon.dto.JYP_WS_DTO;
import com.jooyeon.dto.PW_Member_DTO;
import com.jooyeon.dto.P_Member_DTO;
import com.jooyeon.dto.P_WL_DTO;
import com.jooyeon.dto.P_Work_DTO;
import com.jooyeon.dto.WS_Memeber_DTO;
import com.jooyeon.frontcontroller.MAction;

public class WS_Add implements MAction {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=utf-8");
		
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession(true);
		
		JYP_WS_DTO dto = new JYP_WS_DTO();
		JYP_WS_DAO dao = new JYP_WS_DAO();
		
		String ws_name = request.getParameter("ws_name");
		int jyp_no =(int)session.getAttribute("jyp_no");
		String jyp_ip = InetAddress.getLocalHost().getHostAddress();
		
		dto.setWs_name(ws_name);
		dto.setJyp_no(jyp_no);
		dto.setWs_ip(jyp_ip);
//		System.out.println("세션 jyp_no : " + session.getAttribute("jyp_no"));
		
		int result = dao.ws_add(dto); //워크스페이스 생성하기 
		int ws_no = dao.ws_no(dto); //본인이 방금 만든 워크스페이스 번호 뽑기 ////////// 
		System.out.println("WS_Add에서 ws_no: " +ws_no);
		
		
		///////////////@@@@@@@@@@ 샘플 추가 (no 1로 맞추기) @@@@@ ///////////////////////////////////
		// -> jyp_project,p_member,p_worklist,p_work,pw_member 에 샘플데이터 한 개씩 넣어주기 
		int sample =0;  jyp_no =0;
		SimpleDateFormat date = new SimpleDateFormat("yyy-MM-dd");
		Calendar now = Calendar.getInstance();
		String nowTime = date.format(now.getTime()); System.out.println("WS_Add에서 nowTime: " + nowTime);
			

		
		JYP_Project_DAO   dao_pro = new JYP_Project_DAO();
		JYP_Project_DTO dto_pro = new JYP_Project_DTO();
		
		//워크스페이스 번호
		dto_pro.setWs_no(ws_no);
		//프로젝트이름
		dto_pro.setP_name("test");
		//프로젝트 정보
		dto_pro.setP_info("test"); 
		//프로젝트 공개여부 y/n
		dto_pro.setP_public("test");
		//프로젝트 생성한 회원 ip
		dto_pro.setP_ip(jyp_ip);
		//프로젝트 생성한 회원 번호
		dto_pro.setJyp_no(jyp_no);
		
		//프로젝트 생성하기 //
		sample+= dao_pro.add_pro(dto_pro);
		//방금 만든 프로젝트 번호 뽑기 
		int p_no = dao_pro.p_no(dto_pro); //#### 
		
		JYP_P_Member_DAO dao_pmem = new JYP_P_Member_DAO();
		P_Member_DTO dto_pmem = new P_Member_DTO();
		
	 	//워크스페이스 번호
	 	dto_pmem.setWs_no(ws_no);
		//프로젝트 번호
		dto_pmem.setP_no(p_no);
	 	//회원번호
	 	dto_pmem.setJyp_no(jyp_no);
		//관리자 권한
		dto_pmem.setP_access(1);
	 	//회원아이피
	 	dto_pmem.setP_Mip(jyp_ip);
		
	 	//프로젝트 멤버등록하기 // 
	 	sample+= dao_pmem.add_member(dto_pmem);
	 	
	 	
	 	
		JYP_WL_DAO 		  dao_wl = new JYP_WL_DAO();
		P_WL_DTO dto_wl = new P_WL_DTO();
		
		//워크스페이스 번호
		dto_wl.setWs_no(ws_no);
		//프로젝트 번호
		dto_wl.setP_no(p_no);
		//회원번호
		dto_wl.setJyp_no(jyp_no);
		//업무리스트 이름 
		dto_wl.setWl_name("test");
		//작성한 회원의 아이피 
		dto_wl.setWl_ip(jyp_ip);
		
		//업무리스트 생성//
		sample+= dao_wl.wl_add(dto_wl);
		//방금 만든 업무리스트 번호 뽑기
		int wl_no = dao_wl.wl_no(dto_wl);//###
		
		JYP_Work_DAO	  dao_work = new JYP_Work_DAO();
		P_Work_DTO dto_work = new P_Work_DTO();
		
		//워크페이스번호
		dto_work.setWs_no(ws_no);
		//프로젝트번호
		dto_work.setP_no(p_no);
		//업무리스트번호
		dto_work.setWl_no(wl_no);
		//업무작성한 회원 번호
		dto_work.setJyp_no(jyp_no);
		//업무 내용 ( work_content text not null)
		dto_work.setWork_content("test");
		//업무 시작일 (work_start date)
		dto_work.setWork_start(nowTime);
		//업무 마감일 ( worl_end date)
		dto_work.setWork_end(nowTime);
		//업무 작성한 멤버의 아이피  (work_ip varchar(100) not null)
		dto_work.setWork_ip(jyp_ip);
		//파일 첨부
		dto_work.setWork_file("no.jpg");
		
		//업무 생성//
		sample+= dao_work.write(dto_work);
		//방금 만든 work_no 뽑기
		int work_no = dao_work.work_no(dto_work);
		
		JYP_PW_Member_DAO dao_pwmem = new JYP_PW_Member_DAO();
		PW_Member_DTO dto_mem1 = new PW_Member_DTO();
		JYP_Member_DTO dto_mem2 = new JYP_Member_DTO();
		
		dto_mem1.setWs_no(ws_no); 
		dto_mem1.setP_no(p_no);
		dto_mem1.setWl_no(wl_no);
		dto_mem1.setWork_no(work_no);
		
		String jyp_email = (String) session.getAttribute("jyp_email"); //session 값 가져오기 
		dto_mem2.setJyp_email(jyp_email);
		
		dto_mem2.setJyp_no(jyp_no);
		dto_mem2.setJyp_ip(jyp_ip);
		
		//업무멤버생성//
		sample+= dao_pwmem.pw_member(dto_mem1, dto_mem2);
		
		
		System.out.print("WS_ Ass에서 sample : " + sample);
		
		/////////////////////////////////////////////////////
		
		
		if(result>0 && ws_no>0 && sample==5) {
			out.println("<script>alert('워크스페이스 생성완료!');"
					+ "location.href='"+request.getContextPath()+"/ws_member.ws?ws_no="+ws_no+"';</script>");
	
		}else { out.println("<script>alert('워크스페이스 생성ERROR\n 관리자에게 문의하세요.'); history.go(-1);</script>"); } 
		
	
		

	}

}
