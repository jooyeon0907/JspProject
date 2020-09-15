package com.jooyeon.controller;

import java.util.Calendar;
import java.util.Scanner;

public class Cal {


public static void menu() {
	
	System.out.println("00.[MENU]판\n"
					 + "1.윤년 판단\n"  //년도 입력받고 윤년인지 아닌지 판단해서 출력 
					 + "2.서기1년~금일까지\n" //년,월,일 입력받고 총 일수 출력 
					 + "3.내가 살아온 날수(1)\n" //태어난 년,월,일 입력받고 총 일수 출력
					 + "4.내가 살아온 날수(2)-만나이까지\n" //태어난 년,월,일 입력받고 총 일수 (만 년,개월,일)
					 + "5.show1 - 금월의 달력 하나만 출력\n" // 월 입력받고 해당 월만 출력
					 + "6.show2 - 방향키(▶) 다음달 출력\n" // 현재 보고 있는 달의 다음 달 출력 
					 + "7.show3 - 방향키(◀) 이전달 출력\n" // 현재 보고 있는 달의 이전 달 출력
					 + "8.showall1 - 이번년도 전체 출력\n"// 년도 입력 받고 해당 년도 전체 달 출력 
					 + "9.showall2 - 방향키(▲) 이전년도 전체 출력\n" // 현재 보고 있는 년도의 이전 년도 출력
					 + "10.showall3 - 방향키(▼) 다음년도 전체 출력\n"); //현재 보고 있는 년도의 다음 년도 출력  
	
}
//윤년 판단
public static boolean leap(int year){ 
	boolean res = (year%4==0 && year%100!=0 || year%400==0)? true : false;
	return res;
}
//윤년 갯수
public static int yunCnt(int birYear, int nowYear) {
	int  cnt =0;
	for(int i=birYear; i<=nowYear; i++) {
		if(leap(i)) {cnt++;}
	}
	return cnt;
}

//2.서기1년~금일까지
public static int cal(int year, int month, int date) {
	int total =0;
	int[] mList = {0,31,28,31,30,31,30,31,31,30,31,30,31};
	
	//year 
	for(int i=1; i<year; i++) {total += leap(i)? 366 : 365;}
	
	//month
	//윤년이라면
	mList[2] = leap(year)? 29: 28;
	
	for(int i=0; i<month; i++) {total += mList[i];}
	
	//date
	total += date;
	
	return total;
	
}


//3.내가 살아온 날수(1)\n	
public static int birth(int birYear, int birMonth, int birDay) {

	Calendar cal= Calendar.getInstance();
	int today = cal(cal.get(1), cal.get(2)+ 1, cal.get(5) ); //서기 1년~현재 날까지의 총 일수 
	int birth = cal(birYear, birMonth, birDay ); //서기 1년~태어난 날까지의 총 일수
	int birTotal = today-birth+1; 

	return birTotal;
}


//4.내가 살아온 날수(2)-만나이까지
public static void menu_4(int birYear, int birMonth, int birDay) {
	//한달은 30일 기준으로 함 
	Calendar cal= Calendar.getInstance();
	int age = birth( birYear, birMonth,  birDay)/365;
	int day = birth( birYear, birMonth,  birDay)%365;
	day -=yunCnt(birYear,cal.get(1));
	int m=0;
	int[] mList = {0,31,28,31,30,31,30,31,31,30,31,30,31};
//	System.out.println(yunCnt(birYear,cal.get(1)));



	for(int i=0; i<mList.length; i++) {
		
		day-=mList[i];		
		if(day <mList[i]) {m=i; break;}
	}


	System.out.println("만 " + age + "년 " +  m + "개월 " + day + "일");
			
}


//월 출력 
public static int cal(int year, int month) {
	int total =0;
	int[] mList = {0,31,28,31,30,31,30,31,31,30,31,30,31};
	
	//year 
	for(int i=1; i<year; i++) {total += leap(i)? 366 : 365;}
	
	//month
	//윤년이라면
	mList[2] = leap(year)? 29: 28;
	
	for(int i=0; i<month; i++) {total += mList[i];}

	return total;
	
}
//5.show1 - 금월의 달력 하나만 출력 
//필요한거 -> 년도 월 입력 받아야됨 , (현재 달 제외한 서기1년부터 현재까지 총 일수 )   
public static void menu_5(int year, int month) {
	int[] mList = {0,31,28,31,30,31,30,31,31,30,31,30,31};
	int week =0;
	String[] yoil = {"일","월","화","수","목","금","토"};
	//week = 총일수 % 7 
	week = (cal(year,month)+1)%7;
//	System.out.println("week : " + week);
	System.out.println(year+"년");
	System.out.println("======================="+month+"월=======================");
	//시작 요일 전에 빈칸 채워주기 
	for(int i=0; i<yoil.length; i++) {System.out.print(yoil[i]+"\t");}System.out.println();
	for(int i=0; i<week; i++) {System.out.print("\t");}
		
	//달력에 숫자 채우기
	for(int i=1; i<=mList[month]; i++) { 
		System.out.print(i + "\t");
		week++;
		if(week%7==0) {System.out.println();}
	}
	
}

//6.show2 - 방향키(▶) 다음달 출력
public static void menu_6(int year, int month) {
	//다음 달 출력되려면 ? 총 일수에 저번달의 일수를 더
	
	int[] mList = {0,31,28,31,30,31,30,31,31,30,31,30,31};
	int week =0;
	String[] yoil = {"일","월","화","수","목","금","토"};
	//week = 총일수 % 7 
	week =  (cal(year,month+1)+1) %7;
//	System.out.println("week : " + week);
	System.out.println("======================="+(month+1)+"월=======================");
	//시작 요일 전에 빈칸 채워주기 
	for(int i=0; i<yoil.length; i++) {System.out.print(yoil[i]+"\t");}System.out.println();
	for(int i=0; i<week; i++) {System.out.print("\t");}
		
	//달력에 숫자 채우기
	for(int i=1; i<=mList[month+1]; i++) { 
		System.out.print(i + "\t");
		week++;
		if(week%7==0) {System.out.println();}
	}
	
}


//07. show3 - 방향키(◀)  이전달 출력
public static void menu_7(int year, int month) {
	//다음 달 출력되려면 ? 총 일수에 저번달의 일수를 더
	
	int[] mList = {0,31,28,31,30,31,30,31,31,30,31,30,31};
	int week =0;
	String[] yoil = {"일","월","화","수","목","금","토"};
	//week = 총일수 % 7 
	week = (cal(year,month-1)+1)%7;
	System.out.println(year+"년");
	System.out.println("======================="+(month-1)+"월=======================");
	//시작 요일 전에 빈칸 채워주기 
	for(int i=0; i<yoil.length; i++) {System.out.print(yoil[i]+"\t");}System.out.println();
	for(int i=0; i<week; i++) {System.out.print("\t");}
		
	//달력에 숫자 채우기
	for(int i=1; i<=mList[month-1]; i++) { 
		System.out.print(i + "\t");
		week++;
		if(week%7==0) {System.out.println();}
	}
	
}

//년 출력 
public static int cal(int year) {
	int total =0;
	int[] mList = {0,31,28,31,30,31,30,31,31,30,31,30,31};
	
	//year 
	for(int i=1; i<year; i++) {total += leap(i)? 366 : 365;}
	
	return total;
	
}

//08. showall1 -이번년도 다출력   
public static void menu_8(int year) {
	int[] mList = {0,31,28,31,30,31,30,31,31,30,31,30,31};
	int week =0, total = cal(year)+1;
	//윤년일 때 2월은 29일
	if(year%4==0 && year%100!=0 || year%400==0) {mList[2] = 29;}
	
	System.out.println(year + "년");
	for(int m=1; m<=12; m++){

		//요일구하기
		String [] yoil = {"일","월","화","수","목","금","토",};
		week = total%7; //days%7은 마지막요일이되므로 시작일 하루를 더해주면 시작일의 요일이 된다. 
		//
		System.out.println("==========================="+m+"월===========================");
		for(int i=0; i<yoil.length; i++) { System.out.print(yoil[i] + "\t");} System.out.println();
		
		//요일 시작전에 빈칸 채우기 
		for(int i=0; i<week; i++) {System.out.print("\t"); }
		
		//달력에 해당 일수 채우기 
		for(int i=1; i<=mList[m]; i++) {
			System.out.print(i+"\t");  
			week++; //1일씩 채워질때마다 요일도 한번씩 지나가므로 week를 1씩 증가시켜줌.
			if(week%7==0) {System.out.println();}
			//마지막요일마다 줄바꿔주기 (1 2 3 4 5 6 0  < 0일때 줄바꿈 ) 
			//- 일주일은 7일이므로 week/7했을때 딱 떨어지면  한주가 딱 돌았다는 뜻(즉, week%7=0일때 일주일 채워진 상태)
		}
		System.out.println();
		total+=mList[m]; //요일 변동을 위해 days에 지나간 월의 일수를 더해준다. 
						//해당 월이 지나간 일수만큼 요일도 지나갔기에 days에 채워준 후 week에 반영하여 그 다음달 첫요일을 알아낼 수 있다.
	}
}

//09. showall2 -방향키(▲)  이전년도 다출력 
public static void menu_9(int year) {
	
	int[] mList = {0,31,28,31,30,31,30,31,31,30,31,30,31};
	int week =0, total = cal(year-1)+1;
	//윤년일 때 2월은 29일
	if((year-1)%4==0 && (year-1)%100!=0 || (year-1)%400==0) {mList[2] = 29;}
	
	System.out.println((year-1) + "년");
	for(int m=1; m<=12; m++){

		//요일구하기
		String [] yoil = {"일","월","화","수","목","금","토",};
		week = total%7; //days%7은 마지막요일이되므로 시작일 하루를 더해주면 시작일의 요일이 된다. 
		System.out.println("==========================="+m+"월===========================");
		for(int i=0; i<yoil.length; i++) { System.out.print(yoil[i] + "\t");} System.out.println();
		
		//요일 시작전에 빈칸 채우기 
		for(int i=0; i<week; i++) {System.out.print("\t"); }
		
		//달력에 해당 일수 채우기 
		for(int i=1; i<=mList[m]; i++) {
			System.out.print(i+"\t");  
			week++; //1일씩 채워질때마다 요일도 한번씩 지나가므로 week를 1씩 증가시켜줌.
			if(week%7==0) {System.out.println();}
			//마지막요일마다 줄바꿔주기 (1 2 3 4 5 6 0  < 0일때 줄바꿈 ) 
			//- 일주일은 7일이므로 week/7했을때 딱 떨어지면  한주가 딱 돌았다는 뜻(즉, week%7=0일때 일주일 채워진 상태)
		}
		System.out.println("\n");
		total+=mList[m]; //요일 변동을 위해 days에 지나간 월의 일수를 더해준다. 
						//해당 월이 지나간 일수만큼 요일도 지나갔기에 days에 채워준 후 week에 반영하여 그 다음달 첫요일을 알아낼 수 있다.
	
		
	}
}



//10. showall3 -방향키(▼)  다음년도 다출력 
public static void menu_10(int year) {
	
	int[] mList = {0,31,28,31,30,31,30,31,31,30,31,30,31};
	int week =0, total = cal(year+1)+1;
	//윤년일 때 2월은 29일
	if((year+1)%4==0 && (year+1)%100!=0 || (year+1)%400==0) {mList[2] = 29;}
	
	System.out.println((year+1) + "년");
	for(int m=1; m<=12; m++){

		//요일구하기
		String [] yoil = {"일","월","화","수","목","금","토",};
		week = total%7; //days%7은 마지막요일이되므로 시작일 하루를 더해주면 시작일의 요일이 된다. 
		System.out.println("==========================="+m+"월===========================");
		for(int i=0; i<yoil.length; i++) { System.out.print(yoil[i] + "\t");} System.out.println();
		
		//요일 시작전에 빈칸 채우기 
		for(int i=0; i<week; i++) {System.out.print("\t"); }
		
		//달력에 해당 일수 채우기 
		for(int i=1; i<=mList[m]; i++) {
			System.out.print(i+"\t");  
			week++; //1일씩 채워질때마다 요일도 한번씩 지나가므로 week를 1씩 증가시켜줌.
			if(week%7==0) {System.out.println();}
			//마지막요일마다 줄바꿔주기 (1 2 3 4 5 6 0  < 0일때 줄바꿈 ) 
			//- 일주일은 7일이므로 week/7했을때 딱 떨어지면  한주가 딱 돌았다는 뜻(즉, week%7=0일때 일주일 채워진 상태)
		}
		System.out.println("\n");
		total+=mList[m]; //요일 변동을 위해 days에 지나간 월의 일수를 더해준다. 
						//해당 월이 지나간 일수만큼 요일도 지나갔기에 days에 채워준 후 week에 반영하여 그 다음달 첫요일을 알아낼 수 있다.
	}
}



public static void main(String[] args) {

	Scanner sc = new Scanner(System.in);
	int num,year,month,day;
	int y = 0,m = 0;
	
		menu();
		
	for(;;) {
		System.out.print("메뉴 선택 (1~10) > ");
		num= sc.nextInt();
		
		if(num==1) {
			
			System.out.println("1.윤년 판단");
			System.out.print("년도를 입력 해주세요 > "); year = sc.nextInt();
			if(leap(year)) {System.out.println(year + "년은 윤년입니다."); }
			else {System.out.println(year + "년은 평년입니다.");}
			}
		else if(num ==2) {
			System.out.println("2.서기1년~금일까지");
			System.out.print("년도 입력> "); year = sc.nextInt();
			System.out.print(" 월  입력> "); month = sc.nextInt();
			System.out.print(" 일  입력> "); day = sc.nextInt();
			System.out.println("서기 1년 ~" + year + "년 " + month + "월 " + day + "일까지의 총 일수는 " + cal(year,month,day) + "일 입니다.");
		}
		else if(num ==3) {
			System.out.println("3.내가 살아온 날수(1)");
			System.out.print("태어난 년도 입력> "); year = sc.nextInt();
			System.out.print("태어난  월  입력> "); month = sc.nextInt();
			System.out.print("태어난  일  입력> "); day = sc.nextInt();
			System.out.println(birth(year,month,day) +"일 살아옴");
		}
		else if(num ==4) {
			System.out.println("4.내가 살아온 날수(2) -만 나이");
			System.out.print("태어난 년도 입력> "); year = sc.nextInt();
			System.out.print("태어난  월  입력> "); month = sc.nextInt();
			System.out.print("태어난  일  입력> "); day = sc.nextInt();
			menu_4(year,month,day);
			
		}
		else if(num ==5) {
			System.out.println("5.show1 - 금월의 달력 하나만 출력");
			System.out.print("년도 입력> "); year = sc.nextInt();
			System.out.print(" 월  입력> "); month = sc.nextInt();
			y=year; m=month;
			menu_5(year, month);
		}
		else if(num ==6) {
			System.out.println("06. show2 - 방향키(▶)  다음달 출력");
			if(y==0 && m==0) {System.out.println("현재 달을 입력해주세요 (5번)");}
			else { if(m==12) {y=y+1; m=0;}
					menu_6(y, m); m++; }
		}
		else if(num ==7) {
			System.out.println("07. show3 - 방향키(◀)  이전달 출력");
			if(y==0 && m==0) {System.out.println("현재 달을 입력해주세요 (5번)");}
			else { if(m==1) {y=y-1; m=13;}
					menu_7(y, m);  m--;	}
				
		}
		else if(num ==8) {
			y=0;m=0;
			System.out.println("08. showall1 -이번년도 전체 출력");
			System.out.print("년도 입력> "); year = sc.nextInt();
			y=year;
			menu_8(year);
		}
		else if(num ==9) {
			System.out.println("09. showall2 -방향키(▲)  이전년도 다출력 ");
			if(y==0) {System.out.println("현재 년도를 입력해주세요 (8번)");}
			else { menu_9(y);  y--;	}
		}
		else if(num ==10) {
			System.out.println("10. showall3 -방향키(▼)  다음년도 다출력 ");
			if(y==0) {System.out.println("현재 년도를 입력해주세요 (8번)");}
			else { menu_10(y);  y++;	}
		}
		
		
		
		System.out.println();
	}//for문 종료
		

	
	}//end main
}//end class
