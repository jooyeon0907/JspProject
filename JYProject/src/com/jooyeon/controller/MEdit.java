package com.jooyeon.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jooyeon.dao.JYP_Member_DAO;
import com.jooyeon.dto.JYP_Member_DTO;
import com.jooyeon.frontcontroller.MAction;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class MEdit implements MAction {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		JYP_Member_DTO dto = new JYP_Member_DTO();
		JYP_Member_DAO dao = new JYP_Member_DAO();
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession(true);
		
		String path ="/upload/";
		path = request.getServletContext().getRealPath(path);
		MultipartRequest multi = new MultipartRequest(request, path, 1024*1024*15, "UTF-8",new DefaultFileRenamePolicy());
		String jyp_profile = multi.getFilesystemName("jyp_profile");
		String oldfile = multi.getParameter("oldfile");
		if(jyp_profile==null) {System.out.println("파일 업로드 안됨"); jyp_profile =oldfile;}
		System.out.println("MEdit에서 file : " + jyp_profile);
		
		
		dto.setJyp_email((String)session.getAttribute("jyp_email"));
		dto.setJyp_name(multi.getParameter("jyp_name"));
		dto.setJyp_tel(multi.getParameter("jyp_tel"));
		dto.setDept(multi.getParameter("dept"));
		dto.setPosition(multi.getParameter("position"));
		dto.setBirth(multi.getParameter("birth"));
		dto.setJyp_profile(jyp_profile);
		
		int result = dao.user_edit(dto);
	//	System.out.println("result : " + result);
		if(result>0){
			session.setAttribute("jyp_profile", dao.userinfo(dto).getJyp_profile()); //변경한 프로필사진 
			out.println("<script>alert('회원정보 수정완료!');"
					+ " location.href='"+request.getContextPath()+"/mypage_view.members';</script>");
		}
		else {out.println("<script>alert('회원정보 수정ERORR!'); history.go(-1); </script>"); }
		
		
		

	}

}
