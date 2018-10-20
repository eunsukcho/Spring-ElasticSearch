package kr.yuhan.domain;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

public class PageMaker 
{
	private int totalMessageCount;
	private int endPage;
	private int startPage;
	private boolean prev;
	private boolean next;
	private String memberHak;
	private String YUHAN_MESSAGE_FROM_MEMBER_NUMBER;
	
	private int displayPageNum = 10;
	private Criteria cri;
	
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
	
	public int getTotalMessageCount() 
	{
		return totalMessageCount;
	}
	
	public void setTotalMessageCount(int totalCount) 
	{
		this.totalMessageCount = totalCount;
		calcData();
	}
	
	public int getEndPage() 
	{
		return endPage;
	}
	
	public void setEndPage(int endPage) 
	{
		this.endPage = endPage;
	}
	
	public int getStartPage() 
	{
		return startPage;
	}
	
	public void setStartPage(int startPage) 
	{
		this.startPage = startPage;
	}
	public boolean isPrev() 
	{
		return prev;
	}
	
	public void setPrev(boolean prev) 
	{
		this.prev = prev;
	}
	
	public boolean isNext() 
	{
		return next;
	}
	
	public void setNext(boolean next) 
	{
		this.next = next;
	}
	
	public int getDisplayPageNum() 
	{
		return displayPageNum;
	}
	
	public void setDisplayPageNum(int displayPageNum) 
	{
		this.displayPageNum = displayPageNum;
	}
	
	public Criteria getCri() 
	{
		return cri;
	}
	
	public void setCri(Criteria cri) 
	{
		this.cri = cri;
	}
	
	private void calcData()
	{
		endPage = (int)(Math.ceil(cri.getPage() / (double)displayPageNum) * displayPageNum);
		startPage = endPage - displayPageNum + 1;
		int tempEndPage = (int)(Math.ceil(totalMessageCount/(double)cri.getPerPageNum()));
		
		if(endPage > tempEndPage)
		{
			endPage = tempEndPage;
		}
		
		prev = startPage == 1 ? false : true;
		
		next = endPage * cri.getPerPageNum() >= totalMessageCount ? false : true;
	}
	
	private String encoding(String keyword)
	{
		if(keyword==null || keyword.trim().length() == 0)
		{
			return "";
		}
		
		try 
		{
			return URLEncoder.encode(keyword, "UTF-8");
		} 
		catch (UnsupportedEncodingException e) 
		{
			// TODO Auto-generated catch block
			return "";
		}
	}
	
	public String makeQuery(int page) 
	{
		  UriComponents uriComponents = UriComponentsBuilder.newInstance().queryParam("page", page)
		          .queryParam("perPageNum", cri.getPerPageNum())
		          .build();
		  return uriComponents.toString();
	}
}
