package kr.yuhan.domain;

public class ViewPageParamVo {
	String dbDocumentNo;
	String elasticSearchDocumentNo;
	
	public ViewPageParamVo() {
	}

	public ViewPageParamVo(String dbDocumentNo, String elasticSearchDocumentNo) {
		this.dbDocumentNo = dbDocumentNo;
		this.elasticSearchDocumentNo = elasticSearchDocumentNo;
	}

	public String getDbDocumentNo() {
		return dbDocumentNo;
	}

	public void setDbDocumentNo(String dbDocumentNo) {
		this.dbDocumentNo = dbDocumentNo;
	}

	public String getElasticSearchDocumentNo() {
		return elasticSearchDocumentNo;
	}

	public void setElasticSearchDocumentNo(String elasticSearchDocumentNo) {
		this.elasticSearchDocumentNo = elasticSearchDocumentNo;
	}
	
}
