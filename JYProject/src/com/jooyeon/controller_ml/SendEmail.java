package com.jooyeon.controller_ml;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jooyeon.frontcontroller.MAction;

public class SendEmail implements MAction {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		String subject =request.getParameter("subject");
		String content =request.getParameter("content");
		String to =request.getParameter("get_email");
		String from = request.getParameter("send_email"); System.out.println("send_email : " + from);
		String from_pass =  request.getParameter("send_pass");
		///////////////////////////////////////////////////////
		/***네이버메일 환경설정에서 가져온 정보 *
		 IMAP 서버명 : imap.naver.comSMTP 서버명 : smtp.naver.comIMAP 
		필요SMTP 포트 : 587, 
		아이디 : jooyeon0907
		비밀번호 : 네이버 로그인 비밀번호
		 * */
		String host="smtp.naver.com";
		final String user =from; //보내는 쪽의 메일 설정 
		final String password =from_pass; 
		
	    Properties props = new Properties();
	    props.put("mail.smtp.host", host);
	    props.put("mail.smtp.auth", "true");
	    props.put("mail.smtp.post", "587");
	    
	    Session session = Session.getInstance(props, new javax.mail.Authenticator() {
	    		protected PasswordAuthentication getPasswordAuthentication() {
	    			return new PasswordAuthentication(user,password); 
	    	}
    	} );
	    
	 //  System.out.println(1);
	    
	   ///////////////////////////////
	    try {
	        MimeMessage msg = new MimeMessage(session);
	        msg.setFrom( new InternetAddress(user));
	        msg.addRecipient( Message.RecipientType.TO, new InternetAddress(to) );
	     //   msg.setRecipients(Message.RecipientType.TO, "you@example.com");   충돌나서 위에 코드로 
	        msg.setSubject(subject);
	    //    msg.setSentDate(new Date());
	        msg.setText(content);
	        Transport.send(msg);
	        out.println("<script>alert('메세지 전송완료!'); "
	        		+ "location.href='"+request.getContextPath()+"/member_list.mem';</script>");
	    } catch (MessagingException mex) {
	    	 out.println("<script>alert('정보가 잘못 입력되었습니다.'); history.go(-1);</script>");
	        System.out.println("send failed, exception: " + mex);
	    }
		
		
		
		

	}

}
