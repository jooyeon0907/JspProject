package com.jooyeon.frontcontroller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.jooyeon.controller.MDelete;
import com.jooyeon.controller.MEdit;
import com.jooyeon.controller.MIdCheck;
import com.jooyeon.controller.MIdDouble;
import com.jooyeon.controller.MJoin;
import com.jooyeon.controller.MJoin_com;
import com.jooyeon.controller.MLogin;
import com.jooyeon.controller.MLogin2;
import com.jooyeon.controller.MLogout;
import com.jooyeon.controller.MMypage;
import com.jooyeon.controller.MNoImg;
import com.jooyeon.controller.MPass;
import com.jooyeon.controller_work.WAdd;
import com.jooyeon.controller_work.WDelete;
import com.jooyeon.controller_work.WDetail;
import com.jooyeon.controller_work.WEdit;
import com.jooyeon.controller_work.WEditview;
import com.jooyeon.controller_work.WList;

/**
 * Servlet implementation class JYP_Member_FrontController
 */
@WebServlet("*.members")
public class JYP_Member_FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public JYP_Member_FrontController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		JYP_Member_dao(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		JYP_Member_dao(request, response);
	}

	protected void JYP_Member_dao(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8 ");
		
		String path = request.getRequestURI().substring(request.getContextPath().length());
		String view="";
		MAction command = null;
		
		if(path.equals("/java_MiniProject.members")) {
			System.out.println("자바미니프로젝트");
			
			view = "/join_login/javaProject_JYP.jsp";
			request.getRequestDispatcher(view).forward(request, response);
		}
		
		 if(path.equals("/home.members")) {
			System.out.println("jyproject기본홈");
			
			view = "/join_login/jypHome.jsp";
			request.getRequestDispatcher(view).forward(request, response);
		}
		/////////////////////////////////////////////////
		else if(path.equals("/join_agree.members")) {
			System.out.println("1. 회원가입 동의");
			
			//동의 버튼누르면 회원가입 폼으로 넘김 
			view = "/join_login/join_agree.jsp";
			request.getRequestDispatcher(view).forward(request, response);
		}
		else if(path.equals("/join_view.members")) {
			System.out.println("2. 회원가입 폼 : " +request.getParameter("loginType")  );
			
			request.setAttribute("loginType", request.getParameter("loginType"));
			view = "/join_login/join.jsp";
			request.getRequestDispatcher(view).forward(request, response);
		}
		else if(path.equals("/join.members")) {
			System.out.println("3. 회원가입 기능");
			//가입하려는 회원 정보 저장하기
			command = new MJoin();
			command.execute(request, response);
		}
		else if(path.equals("/idDouble.members")) {
			System.out.println("3-1. 아이디 중복체크 기능");
			command = new MIdDouble();
			command.execute(request, response);
		}
		else if(path.equals("/idCheck.members")) {
			System.out.println("연동로그인시, 가입되어있는 아이디인지 체크");
			command = new MIdCheck();
			command.execute(request, response);
		}
		else if(path.equals("/join_com.members")) {
			System.out.println("4. 회원가입 완료"); 
			//회원가입 성공시 넘어와서 회원가입정보 보여줌 
			command = new MJoin_com();
			command.execute(request, response);
			
		}
		///////////////////////////////////////////////////
		//login
		else if(path.equals("/login_view.members")) {
			//http://joooo1234.cafe24.com/sample/login_view.members
			System.out.println("5. 로그인 폼");
			view = "/join_login/login.jsp";
			request.getRequestDispatcher(view).forward(request, response);
		}
		else if(path.equals("/login.members")) { 
		
			System.out.println("6. 로그인 기능");
			//데이터 빼오기
			command = new MLogin();
			command.execute(request, response);
			//로그인 성공시 마이페이지로 이동 
		}
		else if(path.equals("/login2.members")) { 
			
			System.out.println("6. 로그인 기능(워크스페이스 소속 여부)");
			command = new MLogin2();
			command.execute(request, response);
		}
		else if(path.equals("/logout.members")) {
			System.out.println("7. 로그아웃 기능");
			command = new MLogout();
			command.execute(request, response);
			//로그아웃하고 로그인페이지로 이동 
		}
		/////////////////////////////////////////////////////////
		//mypage
		else if(path.equals("/mypage_view.members")) {
			System.out.println("8. 마이페이지 폼");
			command = new MMypage();
			command.execute(request, response);
		}
		/*
		 * else if(path.equals("/mypage_edit_view.members")) {
		 * System.out.println("9. 마이페이지 수정폼"); //데이터 입력받기 command = new MEdit_view();
		 * command.execute(request, response);
		 * 
		 * }
		 */
		else if(path.equals("/mypage_edit.members")) {
			System.out.println("11. 마이페이지 수정기능");
			//입력한 데이터 받기 
			command = new MEdit();
			command.execute(request, response);
			//수정 완료 후 마이페이지로  (/mypage_view.members)
		}
		else if(path.equals("/mypage_profile.members")) {
			System.out.println("프로필 기본이미지 설정");
			//입력한 데이터 받기 
			command = new MNoImg();
			command.execute(request, response);
		}
		else if(path.equals("/mypage_pass_view.members")) {
			System.out.println("12. 비번 수정폼");
			view="/join_login/mypage_pass.jsp";
			request.getRequestDispatcher(view).forward(request, response);
		}
		else if(path.equals("/mypage_pass.members")) {
			System.out.println("13. 비번 수정기능");
			command = new MPass();
			command.execute(request, response);
		}

		else if(path.equals("/mypage_delete_view.members")) {
			System.out.println("12. 탈퇴하기 폼");
			view="/join_login/mypage_delete.jsp";
			request.getRequestDispatcher(view).forward(request, response);
		}
		else if(path.equals("/mypage_delete.members")) {
			System.out.println("14. 탈퇴 기능");
			command = new MDelete();
			command.execute(request, response);
		}
		
		
		
		
		
		
		////////////////////////////////////////////////////////////////////////////////////////
		/// .*works
		if(path.equals("/list.members")) {
		System.out.println("1. 업무리스트 뽑아오기");
		command = new WList();
		command.execute(request, response);
		view = "/project/workList/work_list.jsp";
		request.getRequestDispatcher(view).forward(request, response);
		}
		else if(path.equals("/work_add_view.members")) { //1
		System.out.println("업무추가하기 폼 ");
		view = "/project/workList/work_add.jsp";
		request.getRequestDispatcher(view).forward(request, response);
		}
		else if(path.equals("/work_add.members")) { //1
		System.out.println("업무추가하기 기능 ");
		command = new WAdd();
		command.execute(request, response);	
//		view = "/list.members";
//		request.getRequestDispatcher(view).forward(request, response);
		}	
		else if(path.equals("/work_detail.members")) { //1
		System.out.println("업무상세보기 폼 ");
		command = new WDetail();
		command.execute(request, response);
		
		view = "/project/workList/work_detail.jsp";
		request.getRequestDispatcher(view).forward(request, response);
		}
		else if(path.equals("/work_edit_view.members")) { //1
		System.out.println("업무수정하기 폼 ");
		command = new WEditview();
		command.execute(request, response);
			
		view = "/project/workList/work_edit.jsp";
		request.getRequestDispatcher(view).forward(request, response);
		}
		else if(path.equals("/work_edit.members")) { //1
		System.out.println("업무수정하기 기능");
		command = new WEdit();
		command.execute(request, response);
		}		
		else if(path.equals("/work_delete.members")) { //7
			//해당 번호의 데이터  삭제하기
			command = new WDelete();
			command.execute(request, response);
			
		//	view = "/board/list.jsp";
		//	request.getRequestDispatcher(view).forward(request, response);
		}
		
		
		
	}//end 






} //end class

