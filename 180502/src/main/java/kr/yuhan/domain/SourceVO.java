package kr.yuhan.domain;

public class SourceVO {
	String _index;
	//String _source;
	
	public SourceVO(String _index) {
		super();
		this._index = _index;
		//this._source = _source;
	}
	public String get_index() {
		return _index;
	}
	public void set_index(String _index) {
		this._index = _index;
	}
//	public String get_source() {
//		return _source;
//	}
//	public void set_source(String _source) {
//		this._source = _source;
//	}
//	
	
}
