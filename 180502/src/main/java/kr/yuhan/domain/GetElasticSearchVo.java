package kr.yuhan.domain;

public class GetElasticSearchVo {
	String _id;
	int totalCount;
	GetSourceVo _source;

	public GetElasticSearchVo(String _id, GetSourceVo _source) {
		this._id = _id;
		this._source = _source;
	}
	
	public GetElasticSearchVo() {}

	public String get_id() {
		return _id;
	}

	public void set_id(String _id) {
		this._id = _id;
	}

	public GetSourceVo get_source() {
		return _source;
	}

	public void set_source(GetSourceVo _source) {
		this._source = _source;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	
}
