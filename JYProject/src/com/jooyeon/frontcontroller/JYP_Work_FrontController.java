package com.jooyeon.frontcontroller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jooyeon.controller_work.CoDelete;
import com.jooyeon.controller_work.CoEdit;
import com.jooyeon.controller_work.CoReply;
import com.jooyeon.controller_work.CommentList;
import com.jooyeon.controller_work.Comments;
import com.jooyeon.controller_work.WAdd;
import com.jooyeon.controller_work.WCheck;
import com.jooyeon.controller_work.WChecked;
import com.jooyeon.controller_work.WDelete;
import com.jooyeon.controller_work.WDetail;
import com.jooyeon.controller_work.WEdit;
import com.jooyeon.controller_work.WEditview;
import com.jooyeon.controller_work.WFileList;
import com.jooyeon.controller_work.WL_Delete;
import com.jooyeon.controller_work.WL_Name;
import com.jooyeon.controller_work.WL_add;
import com.jooyeon.controller_work.WList;
import com.jooyeon.controller_work.WMemDelete;
import com.jooyeon.controller_work.WMember;
import com.jooyeon.controller_work.WMember2;
import com.jooyeon.controller_work.WUnChecked;

/**
 * Servlet implementation class JYP_Work_FrontController
 */
@WebServlet("*.work")
public class JYP_Work_FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public JYP_Work_FrontController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		JYP_Work_dao(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		JYP_Work_dao(request, response);
	}

	protected void JYP_Work_dao(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8 ");
		
		String path = request.getRequestURI().substring(request.getContextPath().length());
		String view="";
		MAction command = null;
		
		//p_worklist
		if(path.equals("/list.work")) { 
		System.out.println("■ 업무리스트 뽑아오기");
		command = new WList();
		command.execute(request, response);
	//	view = "/workspace/workList/work_list.jsp";   
	//	view = "/workList/worklist.jsp";
		request.getRequestDispatcher("/workList/worklist.jsp").forward(request, response);
		}
		else if(path.equals("/worklist_add.work")) { //1
		System.out.println("■ 업무리스트 추가하기 기능");
		command = new WL_add();
		command.execute(request, response);
		}
		else if(path.equals("/worklist_name.work")) { //1
		System.out.println("■ 업무리스트 이름수정 기능");
		command = new WL_Name();
		command.execute(request, response);
		}	
		else if(path.equals("/worklist_delete.work")) { //1
			System.out.println("■ 업무리스트 삭제하기 기능");
			command = new WL_Delete();
			command.execute(request, response);
			
		}	

		
		else if(path.equals("/fileList.work")) { //1
			System.out.println("■ 파일리스트");
			command = new WFileList();
			command.execute(request, response);
			}
		
		/////////////////////////////////////////////////////
		//p_work
		
		else if(path.equals("/work_add_view.work")) { //1
		System.out.println("■ 업무 추가하기 폼 ");
		view = "/workList/work_add.jsp";
		request.getRequestDispatcher(view).forward(request, response);
		}
		else if(path.equals("/work_add.work")) { //1
		System.out.println("■ 업무추가하기 기능 ");
		command = new WAdd();
		command.execute(request, response);	
//		view = "/list.work";
//		request.getRequestDispatcher(view).forward(request, response);
		}	
		else if(path.equals("/work_member.work")) { //
			System.out.println("■ 업무 멤버추가 기능 ");
			command = new WMember();
			command.execute(request, response);
		}
		else if(path.equals("/work_member2.work")) { //
			System.out.println("■ 업무 멤버추가 기능(ajax)");
			command = new WMember2();
			command.execute(request, response);
		}
		else if(path.equals("/work_detail.work")) { //
		System.out.println("■ 업무상세보기 폼 ");
		command = new WDetail();
		command.execute(request, response);
		
	//	view = "/workList/work_detail.jsp";
		view = "/workList/work_view.jsp";
		request.getRequestDispatcher(view).forward(request, response);
		}
		else if(path.equals("/work_edit_view.work")) { //
		System.out.println("■ 업무수정하기 폼 ");
		command = new WEditview();
		command.execute(request, response);
		
		}
		else if(path.equals("/work_edit.work")) { //
		System.out.println("■ 업무수정하기 기능");
		command = new WEdit();
		command.execute(request, response);
		}
		else if(path.equals("/work_delete.work")) { //
			//해당 번호의 데이터  삭제하기
			System.out.println("■ 업무삭제하기 기능");
			command = new WDelete();
			command.execute(request, response);
			
		//	view = "/board/list.jsp";
		//	request.getRequestDispatcher(view).forward(requesㅈt, response);
		}	
		else if(path.equals("/work_finish.work")) { //아직
			System.out.println("■ 업무완료 기능");
		//	command = new WFinish();
		//	command.execute(request, response);
		}	
		else if(path.equals("/workCheck.work")) {
			System.out.println("■ 업무완료시 체크 TEST");
			command = new WCheck();
			command.execute(request, response);
		}
		else if(path.equals("/check.work")) {
			System.out.println("■ 업무완료 y표시");
			command = new WChecked();
			command.execute(request, response);
		}
		else if(path.equals("/unCheck.work")) {
			System.out.println("■ 업무완미완료 n표시");
			command = new WUnChecked();
			command.execute(request, response);
		}
		/////////////////////////////////////////////////////////////
		
		else if(path.equals("/work_memDelete.work")) {
			System.out.println("■ 업무멤버삭제하기");
			command = new WMemDelete();
			command.execute(request, response);
		}	
		
		
		
		////////////////////////////////////////////////////////
		//코멘트
		else if(path.equals("/add_comments.work")) {
			System.out.println("■ 코멘트달기");
			command = new Comments();
			command.execute(request, response);
		}	
		else if(path.equals("/commentList.work")) {
			System.out.println("■ 코멘트리스트 출력");
			command = new CommentList();
			command.execute(request, response);
		}	
		else if(path.equals("/commentEdit.work")) {
			System.out.println("■ 코멘트 수정");
			command = new CoEdit();
			command.execute(request, response);
		}
		else if(path.equals("/commentRe.work")) {
			System.out.println("■ 코멘트 리플달기");
			command = new CoReply();
			command.execute(request, response);
		}
		else if(path.equals("/commentdel.work")) {
			System.out.println("■ 코멘트 삭제");
			command = new CoDelete();
			command.execute(request, response);
		}
		
		
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
}
