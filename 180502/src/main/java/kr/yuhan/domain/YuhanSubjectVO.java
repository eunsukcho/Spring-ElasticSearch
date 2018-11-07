package kr.yuhan.domain;

public class YuhanSubjectVO 
{
	private int YUHAN_SUBJECT_NUMBER;
	private String YUHAN_SUBJECT_NAME;
	private String YUHAN_SUBJECT_HAK;
	private String YUHAN_SUBJECT_PRO;
	private String YUHAN_SUBJECT_CLASS_ROOM;
	private String proEmail;
	private String proName;
	
	private String memberID;
	private int subjectNO;
	private int subjectCount;
	
	
	public String getMemberID() {
		return memberID;
	}
	public void setMemberID(String memberID) {
		this.memberID = memberID;
	}
	public int getSubjectNO() {
		return subjectNO;
	}
	public void setSubjectNO(int subjectNO) {
		this.subjectNO = subjectNO;
	}
	public int getSubjectCount() {
		return subjectCount;
	}
	public void setSubjectCount(int subjectCount) {
		this.subjectCount = subjectCount;
	}
	public int getYUHAN_SUBJECT_NUMBER() {
		return YUHAN_SUBJECT_NUMBER;
	}
	public void setYUHAN_SUBJECT_NUMBER(int yUHAN_SUBJECT_NUMBER) {
		YUHAN_SUBJECT_NUMBER = yUHAN_SUBJECT_NUMBER;
	}
	public String getYUHAN_SUBJECT_NAME() {
		return YUHAN_SUBJECT_NAME;
	}
	public void setYUHAN_SUBJECT_NAME(String yUHAN_SUBJECT_NAME) {
		YUHAN_SUBJECT_NAME = yUHAN_SUBJECT_NAME;
	}
	public String getYUHAN_SUBJECT_HAK() {
		return YUHAN_SUBJECT_HAK;
	}
	public void setYUHAN_SUBJECT_HAK(String yUHAN_SUBJECT_HAK) {
		YUHAN_SUBJECT_HAK = yUHAN_SUBJECT_HAK;
	}
	public String getYUHAN_SUBJECT_PRO() {
		return YUHAN_SUBJECT_PRO;
	}
	public void setYUHAN_SUBJECT_PRO(String yUHAN_SUBJECT_PRO) {
		YUHAN_SUBJECT_PRO = yUHAN_SUBJECT_PRO;
	}
	public String getYUHAN_SUBJECT_CLASS_ROOM() {
		return YUHAN_SUBJECT_CLASS_ROOM;
	}
	public void setYUHAN_SUBJECT_CLASS_ROOM(String yUHAN_SUBJECT_CLASS_ROOM) {
		YUHAN_SUBJECT_CLASS_ROOM = yUHAN_SUBJECT_CLASS_ROOM;
	}
	public String getProEmail() {
		return proEmail;
	}
	public void setProEmail(String proEmail) {
		this.proEmail = proEmail;
	}
	public String getProName() {
		return proName;
	}
	public void setProName(String proName) {
		this.proName = proName;
	}
	@Override
	public String toString() {
		return "YuhanSubjectVO [YUHAN_SUBJECT_NUMBER=" + YUHAN_SUBJECT_NUMBER + ", YUHAN_SUBJECT_NAME="
				+ YUHAN_SUBJECT_NAME + ", YUHAN_SUBJECT_HAK=" + YUHAN_SUBJECT_HAK + ", YUHAN_SUBJECT_PRO="
				+ YUHAN_SUBJECT_PRO + ", YUHAN_SUBJECT_CLASS_ROOM=" + YUHAN_SUBJECT_CLASS_ROOM + ", proEmail="
				+ proEmail + ", proName=" + proName + "]";
	}

}
