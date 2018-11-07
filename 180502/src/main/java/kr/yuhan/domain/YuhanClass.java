package kr.yuhan.domain;

public class YuhanClass {
	
	private int studentID;
	private int subjectID;
	private String subjectName;
	private int subjectNo;
	private int subjectClass;
	private String yuhan_subject_hak;
	String memberClass;

	public String getMemberClass() {
		return memberClass;
	}
	public void setMemberClass(String memberClass) {
		this.memberClass = memberClass;
	}
	public String getYuhan_subject_hak() {
		return yuhan_subject_hak;
	}
	public void setYuhan_subject_hak(String yuhan_subject_hak) {
		this.yuhan_subject_hak = yuhan_subject_hak;
	}
	public int getSubjectClass() {
		return subjectClass;
	}
	public void setSubjectClass(int subjectClass) {
		this.subjectClass = subjectClass;
	}
	public int getSubjectNo() {
		return subjectNo;
	}
	public void setSubjectNo(int subjectNo) {
		this.subjectNo = subjectNo;
	}
	public int getStudentID() {
		return studentID;
	}
	public void setStudentID(int studentID) {
		this.studentID = studentID;
	}
	public int getSubjectID() {
		return subjectID;
	}
	public void setSubjectID(int subjectID) {
		this.subjectID = subjectID;
	}
	public String getSubjectName() {
		return subjectName;
	}
	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}
	

}
