package kr.yuhan.domain;

public class ReplyCriteria extends Criteria{
	private int hwno;
	ReplyVO repVO;

	public ReplyCriteria() {}
	
	public ReplyCriteria(int hwno, ReplyVO repVO) {
		this.hwno = hwno;
		this.repVO = repVO;
	}
	public int getHwno() {
		return hwno;
	}
	public void setHwno(int hwno) {
		this.hwno = hwno;
	}
	public ReplyVO getRepVO() {
		return repVO;
	}
	public void setRepVO(ReplyVO repVO) {
		this.repVO = repVO;
	}
	
	
}
