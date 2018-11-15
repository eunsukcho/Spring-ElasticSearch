package kr.yuhan.domain;

public class SearchCriteria2 extends Criteria
{
	private String searchType;
	private String keyword;
	private String memberID;
	
	public String getMemberID() 
	{
		return memberID;
	}

	public void setMemberID(String memberID) 
	{
		this.memberID = memberID;
	}

	public String getSearchType() 
	{
		return searchType;
	}
	
	public void setSearchType(String searchType) 
	{
		this.searchType = searchType;
	}
	
	public String getKeyword() 
	{
		return keyword;
	}
	
	public void setKeyword(String keyword) 
	{
		this.keyword = keyword;
	}

	@Override
	public String toString() 
	{
		return super.toString() + " SearchCriteria [searchType=" + searchType + ", keyword=" + keyword + "]";
		//부모 Criteria 값과 함께 출력이 될 수 있도록 한다
	}
}
