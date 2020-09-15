package com.jooyeon.controller_cal;

import java.io.IOException;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jooyeon.dao.JYP_Work_DAO;
import com.jooyeon.dto.P_Work_DTO;
import com.jooyeon.frontcontroller.MAction;

public class CalBuild implements MAction {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		/////기본은 1, (현재날-홈으로 들어왔을때 ) 이전 버튼 눌렀으면 2 , 다음 버튼 눌렀으면 3 로 받아서 처리하기 
		int btn = Integer.parseInt(request.getParameter("btn")); 
		int yy=0, mm=0; ////처음 셋팅값 
		//버튼 눌러서 온 경우 값 담아서 옴 
		if(request.getParameter("yy")!=null) { yy = Integer.parseInt(request.getParameter("yy")); } 
		if(request.getParameter("mm")!=null) { mm = Integer.parseInt(request.getParameter("mm")); } 
		System.out.println("CalBuild에서 yy/mm : " + yy +" / " + mm);
		
	
		
		// 현재달 (mm=0일 때의 CurMon(금월), 구한 CurMon로  첫달의 mm인 startM ( 1-CurMon) , 마지막달의 mm인 lastM (12-CurMon)를 구한다.
		// mm == startM가 되면(작년으로 넘어가면) mm=lastM; yy--; 해주고 
		// mm == lastM이 되면 (내년으로 넘어가면) mm=startM; yy++; 해줌 		
		Calendar cal = Calendar.getInstance();
		int CurMon = cal.get(Calendar.MONTH)+1;  //금월의 숫자구하기 
		int startM =  1-CurMon; //첫 달의 mm 
		int lastM = 12-CurMon; // 마지막 달의 mm
		System.out.println("CalBuild에서 startM/lastM " + startM + " / " + lastM);
		if(btn==1) { //현재달 
			// 홈으로 오는 루트면 처음 셋팅 값으로 돌려놔야됨
			yy=0; mm=0;
		}else if(btn==2) { //이전달
			 mm--;
			 //작년으로 넘어갈때
			 if((mm+1)==startM){  mm=lastM; yy--;  }  
		}else if(btn==3) { //다음달
			 mm++;
			 //내년으로 넘어갈때
			 if((mm-1)==lastM) {mm=startM; yy++;}
		}
		
		
		

		/////////////////////////// [1] today
		Calendar today = Calendar.getInstance();
		today.add(today.YEAR,  yy);
		int year  = today.get(Calendar.YEAR);
		today.add(today.MONTH, mm);
		int month = today.get(Calendar.MONTH); //System.out.println("CalBuild에서 month : "  + month);
		int day   = today.get(Calendar.DATE);
		/////////////////////////// [2] 현재달의 1일 구하기
		Calendar dday = Calendar.getInstance();
		dday.add(dday.YEAR,  yy);
		dday.add(dday.MONTH,mm);
		dday.set(Calendar.DATE, 1); 				   //1)  현재 달 1일 셋팅 예) 2019-06-01  (토)
		int startyoil = dday.get(Calendar.DAY_OF_WEEK); 
		//2) 2019-06-01 의 요일 구하기 - 앞에 날짜만큼 blank 구하기 

		//3) DAY_OF_WEEK : 특정 날짜가 무슨 요일리턴 Calendar.SUNDAY 1 / Calendar.MONDAY 2 ... 토요일 7
		//		여기에서는 토요일이기때문에 7리턴
		int lastday = today.getActualMaximum(Calendar.DATE); 
		dday.set(Calendar.DATE, lastday); // 오늘날짜, 마지막일
		int lastyoil = dday.get(Calendar.DAY_OF_WEEK);   
		//4) java 해당월 마지막 날짜(일) 구하기
		// getActualMaximum : 날짜가 셋팅 된 Calender 가 가질수 있는 값
	
		String[] weektitle = { "SUN", "MON", "TUE", "WED", "THU", "FRI", "SAT" };
		request.setAttribute("weektitle", weektitle);
		request.setAttribute("year" , year);
		request.setAttribute("month", month); 
		request.setAttribute("day"  , day);
		request.setAttribute("startyoil"  , startyoil);
		request.setAttribute("lastday"  , lastday);
		request.setAttribute("lastyoil"  , lastyoil); 
		
		request.setAttribute("yy" , yy);
		request.setAttribute("mm", mm); 
		
		//금일/금월 날짜 
		request.setAttribute("currDay", cal.get(Calendar.DATE));
		request.setAttribute("curMon", CurMon);
		
		//년-월
//		if((month+1)<10) {
//			request.setAttribute("wday", year+"-0"+(month+1)+"-"); 
//		}else {request.setAttribute("wday", year+"-"+(month+1)+"-"); }
		
		//년월
		if((month+1)<10) {
			request.setAttribute("wdayN", year+"0"+(month+1)); 
		}else {request.setAttribute("wdayN",""+year+(month+1)); }
		
		
		//업무일정 정보뽑기
		HttpSession session = request.getSession(true);
		JYP_Work_DAO dao = new JYP_Work_DAO();
		
		int ws_no = (int)session.getAttribute("ws_no");
		request.setAttribute("list",dao.ws_listAll(ws_no)); ///업무정보 보내기 (워크스페이스의 전체업무 ) 
		System.out.println(dao.ws_listAll(ws_no));
	}

}
