package kr.yuhan.domain;

import java.util.Date;

public class ReportVO {
	
	private int no;
	private int homeworkNo;
	private String studentID;
	private Date date;
	private String reportDate;
	private String content;

	public ReportVO() {}
	public ReportVO(int no, int homeworkNo, String studentID, Date date, String content) {
		this.no = no;
		this.homeworkNo = homeworkNo;
		this.studentID = studentID;
		this.date = date;
		this.content = content;
	}
	
	public String getReportDate() {
		return reportDate;
	}
	public void setReportDate(String reportDate) {
		this.reportDate = reportDate;
	}
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public int getHomeworkNo() {
		return homeworkNo;
	}
	public void setHomeworkNo(int homeworkNo) {
		this.homeworkNo = homeworkNo;
	}
	public String getStudentID() {
		return studentID;
	}
	public void setStudentID(String studentID) {
		this.studentID = studentID;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
}
