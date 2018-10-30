package kr.yuhan.domain;

import java.util.List;

public class GetElasticCriteria {
	String _id;
	List<GetSourceVo> _source;
	double totalCount;

	public GetElasticCriteria() {}

	public GetElasticCriteria(String _id, List<GetSourceVo> _source, double totalCount) {
		this._id = _id;
		this._source = _source;
		this.totalCount = totalCount;
	}

	public GetElasticCriteria(List<GetSourceVo> _source, double totalCount) {
		this._source = _source;
		this.totalCount = totalCount;
	}

	public String get_id() {
		return _id;
	}

	public void set_id(String _id) {
		this._id = _id;
	}

	public List<GetSourceVo> get_source() {
		return _source;
	}

	public void set_source(List<GetSourceVo> _source) {
		this._source = _source;
	}

	public double getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(double totalCount) {
		this.totalCount = totalCount;
	}

}
