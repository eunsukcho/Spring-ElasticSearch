package kr.yuhan.domain;

import java.util.Date;

public class YuhanHomeworkVO {
	private String _id;
	private int hwno;
	private String title;
	private int professorno;
	private int subjectID;
	private String content;
	private String endStatus;
	private String dataStatus;
	private String deleteStatus;
	private String subjectClass;
	private String sessionID;
	//DB에 들어갈 내용
	
	private Date today; // DB와 연결되는 Upload Day
	private String formatToday; // Date to String에 필요함
	
	private String start; 
	private String end;
	private String startdayTime;
	private String enddayTime;
	//**ajax에서 보낼 때 받아주는 변수
	
	private Date totalStart;
	private Date totalEnd;
	//직접 DB와 연결되는 변수
	
	private String subject;
	private String memberClass;
	
	
	public YuhanHomeworkVO() {}

	public YuhanHomeworkVO(int subjectID, String subjectClass, String sessionID) {
		this.subjectID = subjectID;
		this.subjectClass = subjectClass;
		this.sessionID = sessionID;
	}

	public YuhanHomeworkVO(String subjectClass, int professorno, int subjectID) {
		this.professorno = professorno;
		this.subjectID = subjectID;
		this.subjectClass = subjectClass;
	}



	//이하 getter/setter
	
	public String get_id() {
		return _id;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getMemberClass() {
		return memberClass;
	}

	public void setMemberClass(String memberClass) {
		this.memberClass = memberClass;
	}

	public void set_id(String _id) {
		this._id = _id;
	}
	
	public int getHwno() {
		return hwno;
	}

	public void setHwno(int hwno) {
		this.hwno = hwno;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getProfessorno() {
		return professorno;
	}

	public void setProfessorno(int professorno) {
		this.professorno = professorno;
	}

	public int getSubjectID() {
		return subjectID;
	}

	public void setSubjectID(int subjectID) {
		this.subjectID = subjectID;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getEndStatus() {
		return endStatus;
	}

	public void setEndStatus(String endStatus) {
		this.endStatus = endStatus;
	}

	public String getDataStatus() {
		return dataStatus;
	}

	public void setDataStatus(String dataStatus) {
		this.dataStatus = dataStatus;
	}

	public String getDeleteStatus() {
		return deleteStatus;
	}

	public void setDeleteStatus(String deleteStatus) {
		this.deleteStatus = deleteStatus;
	}

	public Date getToday() {
		return today;
	}

	public void setToday(Date today) {
		this.today = today;
	}

	public String getFormatToday() {
		return formatToday;
	}

	public void setFormatToday(String formatToday) {
		this.formatToday = formatToday;
	}

	public String getStart() {
		return start;
	}

	public void setStart(String start) {
		this.start = start;
	}

	public String getEnd() {
		return end;
	}

	public void setEnd(String end) {
		this.end = end;
	}

	public String getStartdayTime() {
		return startdayTime;
	}

	public void setStartdayTime(String startdayTime) {
		this.startdayTime = startdayTime;
	}

	public String getEnddayTime() {
		return enddayTime;
	}

	public void setEnddayTime(String enddayTime) {
		this.enddayTime = enddayTime;
	}

	public Date getTotalStart() {
		return totalStart;
	}

	public void setTotalStart(Date totalStart) {
		this.totalStart = totalStart;
	}

	public Date getTotalEnd() {
		return totalEnd;
	}

	public void setTotalEnd(Date totalEnd) {
		this.totalEnd = totalEnd;
	}

	public String getSubjectClass() {
		return subjectClass;
	}

	public void setSubjectClass(String subjectClass) {
		this.subjectClass = subjectClass;
	}

	public String getSessionID() {
		return sessionID;
	}

	public void setSessionID(String sessionID) {
		this.sessionID = sessionID;
	}
	
	
}
