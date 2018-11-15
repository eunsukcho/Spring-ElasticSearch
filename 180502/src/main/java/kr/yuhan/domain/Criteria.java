package kr.yuhan.domain;

public class Criteria 
{
	private int page;
	private int perPageNum;
	private String memberID;
	private String YUHAN_MESSAGE_FROM_MEMBER_NUMBER;
	private String YUHAN_MESSAGE_TO_MEMBER_NUMBER;
	
	private int repPage;
	private int repPageNum;
	
	public int getRepPage() {
		return repPage;
	}
	
	public void setRepPage(int repPage) {
		if(repPage<=0) 
		{
			this.repPage=1;
			return;
		}
		this.repPage = repPage;
	}
	
	public int getRepPageNum() {
		return repPageNum;
	}
	
	

	public String getYUHAN_MESSAGE_TO_MEMBER_NUMBER() {
		return YUHAN_MESSAGE_TO_MEMBER_NUMBER;
	}

	public void setYUHAN_MESSAGE_TO_MEMBER_NUMBER(String yUHAN_MESSAGE_TO_MEMBER_NUMBER) {
		YUHAN_MESSAGE_TO_MEMBER_NUMBER = yUHAN_MESSAGE_TO_MEMBER_NUMBER;
	}

	public void setRepPageNum(int repPageNum) {
		
		if(repPageNum <= 0 || repPageNum >100) 
		{
			this.repPageNum =5;
			return; //메소드 탈출
		}
		
		this.repPageNum = repPageNum;
	}
	
	public String getYUHAN_MESSAGE_FROM_MEMBER_NUMBER() 
	{
		return YUHAN_MESSAGE_FROM_MEMBER_NUMBER;
	}

	public void setYUHAN_MESSAGE_FROM_MEMBER_NUMBER(String yUHAN_MESSAGE_FROM_MEMBER_NUMBER) 
	{
		YUHAN_MESSAGE_FROM_MEMBER_NUMBER = yUHAN_MESSAGE_FROM_MEMBER_NUMBER;
	}

	public String getMemberID() 
	{
		return memberID;
	}

	public void setMemberID(String memberID)
	{
		this.memberID = memberID;
	}

	public int getPage() 
	{
		return page;
	}
	
	public Criteria()
	{
		page = 1;
		perPageNum = 10;
		
		repPage = 1;
		repPageNum = 5;
	}
	
	public void setPage(int page) 
	{
		if(page<=0) 
		{
			this.page=1;
			return;
		}
		this.page = page;
	}
	
	public int getPerPageNum() 
	{
		return perPageNum;
	}
	
	public void setPerPageNum(int perPageNum) 
	{
		if(perPageNum <= 0 || perPageNum >100) 
		{
			this.perPageNum =10;
			return; //메소드 탈출
		}
		
		this.perPageNum = perPageNum;
	}
	
	public int getPageStart() 
	{
		return (page-1)*perPageNum;
	}
	
	public int getRepPageStart() {
		return (repPage-1)*repPageNum;
	}
}