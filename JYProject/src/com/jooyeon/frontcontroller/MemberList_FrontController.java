package com.jooyeon.frontcontroller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jooyeon.controller_ml.JYP_MemberSearch;
import com.jooyeon.controller_ml.ML_List;
import com.jooyeon.controller_ml.Pro_MemberSearch;
import com.jooyeon.controller_ml.Pro_MemberSearchNo;
import com.jooyeon.controller_ml.SendEmail;
import com.jooyeon.controller_ml.WS_MemberSearch;
import com.jooyeon.controller_ml.WS_MemberSearchNo;

/**
 * Servlet implementation class MemberList_FrontController
 */
@WebServlet("*.mem")
public class MemberList_FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberList_FrontController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		memberList(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		memberList(request, response);
	}

	protected void memberList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String path = request.getRequestURI().substring(request.getContextPath().length());
		MAction command =null;
		
		
		if(path.equals("/member_list.mem")) {
			System.out.println("■ 멤버주소록 홈 ");
			command = new ML_List();
			command.execute(request, response);
		}
		else if(path.equals("/jyp_memSearch.mem")) {
			System.out.println("■ 초대할 멤버 검색(jyproject에 가입된 유저 검색) ");
			command = new JYP_MemberSearch();
			command.execute(request, response);	
		}
		else if(path.equals("/ws_memSearch.mem")) {
			System.out.println("■ 초대할 멤버 검색(워크스페이스에 가입된 유저 검색 -프로젝트멤버) ");
			command = new WS_MemberSearch();
			command.execute(request, response);	
		}
		else if(path.equals("/ws_memSearchNo.mem")) {
			System.out.println("■ 초대할 멤버 검색(워크스페이스에 가입된 유저 검색-프로젝트 비멤버) ");
			command = new WS_MemberSearchNo();
			command.execute(request, response);	
		}
		else if(path.equals("/pro_memSearch.mem")) {
			System.out.println("■ 초대할 멤버 검색(프로젝트에 가입된 유저 검색 -업무담당 멤버) ");
			command = new Pro_MemberSearch();
			command.execute(request, response);	
		}
		else if(path.equals("/pro_memSearchNo.mem")) {
			System.out.println("■ 초대할 멤버 검색(프로젝트에 가입된 유저 검색-업무담당 비멤버) ");
			command = new Pro_MemberSearchNo();
			command.execute(request, response);	
		}
		
		else if(path.equals("/email.mem")) {
			System.out.println("■ 이메일 보내기 ");
			command = new SendEmail();
			command.execute(request, response);	
		}
		
		
		
		
		
	}
	
	
	
}
