package kr.yuhan.domain;

public class Criteria 
{
	private int page;
	private int perPageNum;
	private String memberHak;
	private String YUHAN_MESSAGE_FROM_MEMBER_NUMBER;

	public String getYUHAN_MESSAGE_FROM_MEMBER_NUMBER() 
	{
		return YUHAN_MESSAGE_FROM_MEMBER_NUMBER;
	}

	public void setYUHAN_MESSAGE_FROM_MEMBER_NUMBER(String yUHAN_MESSAGE_FROM_MEMBER_NUMBER) 
	{
		YUHAN_MESSAGE_FROM_MEMBER_NUMBER = yUHAN_MESSAGE_FROM_MEMBER_NUMBER;
	}

	public String getMemberHak() 
	{
		return memberHak;
	}

	public void setMemberHak(String memberHak)
	{
		this.memberHak = memberHak;
	}

	public int getPage() 
	{
		return page;
	}
	
	public Criteria()
	{
		page = 1;
		perPageNum = 10;
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
}
