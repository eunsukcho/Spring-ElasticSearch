package kr.yuhan.domain;

public class ReplyVO {
	private int repNo;
	private int hwno;
	private String professorNO;
	private String professorName;
	private String comment;
	private String repDate;
	
	/** 페이징 **/
	private int repPage;
	private int repPerPageNum;
	
	public ReplyVO() {
		repPage = 1;
		repPerPageNum = 5;
	}
	
	public ReplyVO(int hwno) {
		this.hwno = hwno;
	}

	public ReplyVO(int repNo, int hwno, String professorNO, String professorName, String comment, String repDate) {
		this.repNo = repNo;
		this.hwno = hwno;
		this.professorNO = professorNO;
		this.professorName = professorName;
		this.comment = comment;
		this.repDate = repDate;
	}

	public String getProfessorName() {
		return professorName;
	}

	public void setProfessorName(String professorName) {
		this.professorName = professorName;
	}

	public int getRepNo() {
		return repNo;
	}
	public void setRepNo(int repNo) {
		this.repNo = repNo;
	}
	public int getHwno() {
		return hwno;
	}
	public void setHwno(int hwno) {
		this.hwno = hwno;
	}
	public String getProfessorNO() {
		return professorNO;
	}
	public void setProfessorNO(String professorNO) {
		this.professorNO = professorNO;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getRepDate() {
		return repDate;
	}
	public void setRepDate(String repDate) {
		this.repDate = repDate;
	}
	
	/** 댓글 페이징 **/
	public int getRepPage() {
		return repPage;
	}

	public void setRepPage(int repPage) {
		if(repPage<=0) 
		{
			this.repPage=1;
			return;
		}
		this.repPage = repPage;
	}

	public int getRepPerPageNum() {
		return repPerPageNum;
	}

	public void setRepPerPageNum(int repPerPageNum) {
		
		if(repPerPageNum <= 0 || repPerPageNum >100){
			this.repPerPageNum = 5;
			return; 
		}
		this.repPerPageNum = repPerPageNum;
	}
	
	public int getRepPageStart() 
	{
		return (repPage-1)*repPerPageNum;
	}
	
}
