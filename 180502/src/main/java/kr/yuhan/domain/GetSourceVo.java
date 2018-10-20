package kr.yuhan.domain;

public class GetSourceVo {
	String title;
	String date;
	String DetailDate;
	String startdate;
	String enddate;
	String content;
	String subject;
	String professor;
	String subjectclass;
	
	public GetSourceVo(String title, String date, String DetailDate, String startdate, String enddate, String content,String subject, String professor, String subjectclass) {
		this.title = title;
		this.date = date;
		this.DetailDate = DetailDate;
		this.startdate = startdate;
		this.enddate = enddate;
		this.content = content;
		this.subject = subject;
		this.professor = professor;
		this.subjectclass = subjectclass;
	}

	public GetSourceVo() {
	}

	
	public GetSourceVo(String title, String startdate, String enddate, String content) {
		super();
		this.title = title;
		this.startdate = startdate;
		this.enddate = enddate;
		this.content = content;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getDetailDate() {
		return DetailDate;
	}

	public void setDetailDate(String detailDate) {
		this.DetailDate = detailDate;
	}

	public String getStartdate() {
		return startdate;
	}

	public void setStartdate(String startdate) {
		this.startdate = startdate;
	}

	public String getEnddate() {
		return enddate;
	}

	public void setEnddate(String enddate) {
		this.enddate = enddate;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getProfessor() {
		return professor;
	}

	public void setProfessor(String professor) {
		this.professor = professor;
	}

	public String getSubjectclass() {
		return subjectclass;
	}

	public void setSubjectclass(String subjectclass) {
		this.subjectclass = subjectclass;
	}
	
	
	
	
}
