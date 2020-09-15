package com.jooyeon.frontcontroller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jooyeon.controller_project.P_AccError;
import com.jooyeon.controller_project.P_Add;
import com.jooyeon.controller_project.P_Delete;
import com.jooyeon.controller_project.P_Edit;
import com.jooyeon.controller_project.P_Edit_View;
import com.jooyeon.controller_project.P_MemDelete;
import com.jooyeon.controller_project.P_Member;
import com.jooyeon.controller_project.P_Member2;
import com.jooyeon.controller_project.P_Public;
import com.jooyeon.controller_project.P_list;

/**
 * Servlet implementation class JYP_Work_FrontController
 */
@WebServlet("*.pro")
public class JYP_PRO_FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public JYP_PRO_FrontController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		JYP_Project_dao(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		JYP_Project_dao(request, response);
	}

	protected void JYP_Project_dao(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8 ");
		
		String path = request.getRequestURI().substring(request.getContextPath().length());
	//	String view="";
		MAction command = null;
		
		
		
		if(path.equals("/pro_list.pro")) {
			System.out.println("■ 프로젝트 리스트 뽑아서 프로젝트 페이지로  ");
			command = new P_list();
			command.execute(request, response);
			
		}
		else if(path.equals("/p_add.pro")) {
			System.out.println("■ 프로젝트 생성하기 기능");
			command = new P_Add();
			command.execute(request, response);
			}
		else if(path.equals("/p_member2.pro")) {
			System.out.println("■ 프로젝트 멤버추가 (p_add후 넘어감) ");
			command = new P_Member2();
			command.execute(request, response);
			}
		else if(path.equals("/p_member.pro")) {
			System.out.println("■ 프로젝트 멤버추가 (추가버튼 눌렀을때 넘어감)");
			command = new P_Member();
			command.execute(request, response);
			}
		else if(path.equals("/p_memDelete.pro")) {
			System.out.println("■ 프로젝트 멤버삭제");
			command = new P_MemDelete();
			command.execute(request, response);
			}
		else if(path.equals("/p_public.pro")) {
			System.out.println("■ 프로젝트 공개여부 - 비공개시 멤버만 볼 수 있음 ");
			command = new P_Public();
			command.execute(request, response);
			}
		else if(path.equals("/p_edit_view.pro")) {
			System.out.println("■ 프로젝트 수정하기 폼 ");
			command = new P_Edit_View();
			command.execute(request, response);
			
	//		request.getRequestDispatcher("project/p_edit.jsp").forward(request, response);
			}
		else if(path.equals("/p_edit.pro")) {
			System.out.println("■ 프로젝트 수정하기 기능 ");
			command = new P_Edit();
			command.execute(request, response);
			}
		else if(path.equals("/p_delete.pro")) {
			System.out.println("■ 프로젝트 삭제하기 기능 ");
			command = new P_Delete();
			command.execute(request, response);
			}
		else if(path.equals("/p_accError.pro")) {
			System.out.println("■ 프로젝트 관리자 권한 X ");
			command = new P_AccError();
			command.execute(request, response);
			}
	
		
		
		
		
	}
	
	
	
}
