package com.jooyeon.dto;

import java.util.ArrayList;

public class Paging_WM_DTO {

	private int pageTotal;// 전체 게시판의 갯수 (글의 갯수) 12
	private int onepageLimit;// 한 페이지당 보여줄 레코드 수 
	private int pageAll;// 하단 index의 갯수 12/10 : 2 
	private int pstartNo;// db에서 가져올 번호 (1번 버튼시 0, 2번버튼 10 )
	private int bottomList;// 하단페이지네비게이션(10) 
	private int bottom_current;// 하단페이지네이 - 현재페이지 네비번호
	private int bottom_start;// 하단페이지네비 - 현재페이지기준 - 시작페이지 네비번호
	private int bottom_end;// 하단페이지네비 - 현재페이지기준 - 끝페이지 네비번호
	ArrayList<JYP_Member_DTO> list;
	public int getPageTotal() {
		return pageTotal;
	}
	public void setPageTotal(int pageTotal) {
		this.pageTotal = pageTotal;
	}
	public int getOnepageLimit() {
		return onepageLimit;
	}
	public void setOnepageLimit(int onepageLimit) {
		this.onepageLimit = onepageLimit;
	}
	public int getPageAll() {
		return pageAll;
	}
	public void setPageAll(int pageAll) {
		this.pageAll = pageAll;
	}
	public int getPstartNo() {
		return pstartNo;
	}
	public void setPstartNo(int pstartNo) {
		this.pstartNo = pstartNo;
	}
	public int getBottomList() {
		return bottomList;
	}
	public void setBottomList(int bottomList) {
		this.bottomList = bottomList;
	}
	public int getBottom_current() {
		return bottom_current;
	}
	public void setBottom_current(int bottom_current) {
		this.bottom_current = bottom_current;
	}
	public int getBottom_start() {
		return bottom_start;
	}
	public void setBottom_start(int bottom_start) {
		this.bottom_start = bottom_start;
	}
	public int getBottom_end() {
		return bottom_end;
	}
	public void setBottom_end(int bottom_end) {
		this.bottom_end = bottom_end;
	}
	public ArrayList<JYP_Member_DTO> getList() {
		return list;
	}
	public void setList(ArrayList<JYP_Member_DTO> list) {
		this.list = list;
	}
	public Paging_WM_DTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Paging_WM_DTO(int pageTotal, int onepageLimit, int pageAll, int pstartNo, int bottomList, int bottom_current,
			int bottom_start, int bottom_end, ArrayList<JYP_Member_DTO> list) {
		super();
		this.pageTotal = pageTotal;
		this.onepageLimit = onepageLimit;
		this.pageAll = pageAll;
		this.pstartNo = pstartNo;
		this.bottomList = bottomList;
		this.bottom_current = bottom_current;
		this.bottom_start = bottom_start;
		this.bottom_end = bottom_end;
		this.list = list;
	}
	
	
	
	
	
	
	
	
	
	
	
	
}//end class
