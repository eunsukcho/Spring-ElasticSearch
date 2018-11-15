package kr.yuhan.domain;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

public class PageMaker2 
{
	private int totalCount;
	private int endPage;
	private int startPage;
	private boolean prev;
	private boolean next;
	
	private int displayPageNum=10; //�븳�솕硫댁뿉 蹂댁뿬吏��뒗 �럹�씠吏� 媛쒖닔
	private Criteria cri;//�쁽�옱 �럹�씠吏� 踰덊샇
	
	
	
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
		calcData();
	}
	public int getEndPage() {
		return endPage;
	}
	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}
	public int getStartPage() {
		return startPage;
	}
	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}
	public boolean isPrev() {
		return prev;
	}
	public void setPrev(boolean prev) {
		this.prev = prev;
	}
	public boolean isNext() {
		return next;
	}
	public void setNext(boolean next) {
		this.next = next;
	}
	public int getDisplayPageNum() {
		return displayPageNum;
	}
	public void setDisplayPageNum(int displayPageNum) {
		this.displayPageNum = displayPageNum;
	}
	public Criteria getCri() {
		return cri;
	}
	public void setCri(Criteria cri) {
		this.cri = cri;
	}
	
	private void calcData() {
		endPage = (int)(Math.ceil(cri.getPage() / (double)displayPageNum) * displayPageNum);
		startPage = endPage-displayPageNum+1;
		int tempEndPage = (int)(Math.ceil( totalCount/(double)cri.getPerPageNum()));
		
		if(endPage > tempEndPage) {
			endPage = tempEndPage;
		}
		
		prev = startPage == 1?false:true;
		
		next = endPage * cri.getPerPageNum() >= totalCount ? false : true;
	}
	
	private String encoding(String keyword) 
	{
		  if(keyword==null || keyword.trim().length()==0) 
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
		 
	public String makeSearch(int page) 
	{
		  UriComponents uriComponents = UriComponentsBuilder.newInstance().queryParam("page", page)
		          .queryParam("perPageNum", cri.getPerPageNum())
		          .queryParam("searchType", ((SearchCriteria)cri).getSearchType())
		          .queryParam("keyword", encoding(((SearchCriteria)cri).getKeyword()))
		          .build();
		   
		  return uriComponents.toUriString();
	} 
}