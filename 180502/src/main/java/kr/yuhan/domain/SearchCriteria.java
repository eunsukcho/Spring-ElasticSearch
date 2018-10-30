package kr.yuhan.domain;

public class SearchCriteria extends Criteria{
	private String searchType;
	private String keyword;
	ElasticVO elasticVO;
	
	public SearchCriteria() {}
	
	public SearchCriteria(ElasticVO elasticVO) {
		this.elasticVO = elasticVO;
	}

	public SearchCriteria(String searchType, String keyword, ElasticVO elasticVO) {
		this.searchType = searchType;
		this.keyword = keyword;
		this.elasticVO = elasticVO;
	}
	
	public ElasticVO getElasticVO() {
		return elasticVO;
	}
	public void setElasticVO(ElasticVO elasticVO) {
		this.elasticVO = elasticVO;
	}
	public String getSearchType() {
		return searchType;
	}
	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
}
