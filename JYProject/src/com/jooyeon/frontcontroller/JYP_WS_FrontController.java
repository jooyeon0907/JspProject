package com.jooyeon.frontcontroller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jooyeon.controller_ws.WS_Accept;
import com.jooyeon.controller_ws.WS_Add;
import com.jooyeon.controller_ws.WS_Address;
import com.jooyeon.controller_ws.WS_DelList;
import com.jooyeon.controller_ws.WS_Delete;
import com.jooyeon.controller_ws.WS_Edit;
import com.jooyeon.controller_ws.WS_Edit_View;
import com.jooyeon.controller_ws.WS_Home;
import com.jooyeon.controller_ws.WS_InviDelete;
import com.jooyeon.controller_ws.WS_InviList;
import com.jooyeon.controller_ws.WS_InviRefuse;
import com.jooyeon.controller_ws.WS_Invitation;
import com.jooyeon.controller_ws.WS_List;
import com.jooyeon.controller_ws.WS_ListBar;
import com.jooyeon.controller_ws.WS_MemDelete;
import com.jooyeon.controller_ws.WS_MemDelete2;
import com.jooyeon.controller_ws.WS_Member;

/**
 * Servlet implementation class JYP_Work_FrontController
 */
@WebServlet("*.ws")
public class JYP_WS_FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public JYP_WS_FrontController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		JYP_WS_dao(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		JYP_WS_dao(request, response);
	}

	protected void JYP_WS_dao(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8 ");
		
		String path = request.getRequestURI().substring(request.getContextPath().length());
		String view="";
		MAction command = null;
		
		
		/// 워크스페이스
		if(path.equals("/ws_add_view.ws")) {
			System.out.println("■ 워크스페이스 생성하기 폼");
			view = "/workspace/ws_add.jsp";
			request.getRequestDispatcher(view).forward(request, response);
		}
		else if(path.equals("/ws_add_view2.ws")) {
			System.out.println("■ 워크스페이스 생성하기 폼2");
			view = "/workspace/ws_add2.jsp";
			request.getRequestDispatcher(view).forward(request, response);
			}
		else if(path.equals("/ws_add.ws")) {
			System.out.println("■ 워크스페이스 생성하기 기능");
			command = new WS_Add();
			command.execute(request, response);
			}
		else if(path.equals("/ws_list.ws")) {
			System.out.println("■ 워크스페이스 리스트  ");
			command = new WS_List();
			command.execute(request, response);

			}
		else if(path.equals("/ws_listBar.ws")) {
			System.out.println("■ 워크스페이스 리스트 (상단)  ");
			command = new WS_ListBar();
			command.execute(request, response);
			
			}
		else if(path.equals("/ws_home.ws")) {
			System.out.println("■ 워크스페이스 기본홈 폼 ");
			command = new WS_Home();
			command.execute(request, response);
			}
		else if(path.equals("/ws_member.ws")) {
			System.out.println("■ 워크스페이스 멤버추가 ");
			command = new WS_Member();
			command.execute(request, response);
			}
		else if(path.equals("/ws_edit_view.ws")) {
			System.out.println("■ 워크스페이스 수정하기 폼  ");
			command = new WS_Edit_View();
			command.execute(request, response);
		//	request.getRequestDispatcher("workspace/ws_edit.jsp").forward(request, response);
			}
		else if(path.equals("/ws_edit.ws")) {
			System.out.println("■ 워크스페이스 수정하기 기능(이름변경) ");
			command = new WS_Edit();
			command.execute(request, response);
			//request.getRequestDispatcher("workspace/ws_edit.jsp").forward(request, response);
			}
		else if(path.equals("/ws_address.ws")) {
			System.out.println("■ 워크스페이스 주소 등록하기 ");
			command = new WS_Address();
			command.execute(request, response);
			}
		
		else if(path.equals("/ws_delete.ws")) {
			System.out.println("■ 워크스페이스 삭제하기 기능 ");
			command = new WS_Delete();
			command.execute(request, response);
			}
		else if(path.equals("/ws_Memdelete.ws")) {
			System.out.println("■ 워크스페이스 탈퇴하기 기능 ");
			command = new WS_MemDelete();
			command.execute(request, response);
			}
		else if(path.equals("/ws_Memdelete2.ws")) {
			System.out.println("■ 워크스페이스 멤버삭제(관리자가삭제하는 경우) ");
			command = new WS_MemDelete2();
			command.execute(request, response);
			}
		////////////////////////////////////////////////////////////////
		else if(path.equals("/ws_Invitation.ws")) {
			System.out.println("■ 워크스페이스 초대리스트에 등록하기 ) ");
			command = new WS_Invitation();
			command.execute(request, response);	
			
		}		
		else if(path.equals("/ws_InviListAll_.ws")) {
			System.out.println("■ 워크스페이스 초대리스트 페이지) ");
			request.getRequestDispatcher("memberList/invitation_list.jsp").forward(request, response);
			
		}	
		else if(path.equals("/ws_InviListAll.ws")) {
			System.out.println("■ 워크스페이스 초대리스트 전부 출력) ");
			command = new WS_InviList();
			command.execute(request, response);	
			
		}	
		else if(path.equals("/ws_Accept.ws")) {
			System.out.println("■ 워크스페이스 초대리스트 수락하기) ");
			command = new WS_Accept();
			command.execute(request, response);	
			
		}
		else if(path.equals("/ws_InviRefuse.ws")) {
			System.out.println("■ 워크스페이스 초대리스트 거절하기 ) ");
			command = new WS_InviRefuse();
			command.execute(request, response);	
			
		}
		else if(path.equals("/ws_Invidelete.ws")) {
			System.out.println("■ 워크스페이스 초대리스트 삭제하기 ) ");
			command = new WS_InviDelete();
			command.execute(request, response);	
			
		}
		
		else if(path.equals("/ws_delList.ws")) {
			System.out.println("■ 워크스페이스 탈퇴리스트) ");
		//	command = new WS_DelList();
		//	command.execute(request, response);	
			request.getRequestDispatcher("/memberList/delete_list.jsp").forward(request, response);
		}
		
		
		
		
		
		
		
		
		
		
		
		
	}
	
	
	
}
