package kr.yuhan.domain;

public class YuhanReportFileVO {
	String HomeWorkNO;
	int FileNo;
	int SubjectNO;
	String FileName;
	String saveFileName;
	String filePath;
	int FileSize;
	String FileSaveUrl;
	String professorNo;
	String memberID;
	String subjectID;

	public String getMemberID() {
		return memberID;
	}
	public void setMemberID(String memberID) {
		this.memberID = memberID;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public String getSaveFileName() {
		return saveFileName;
	}
	public void setSaveFileName(String saveFileName) {
		this.saveFileName = saveFileName;
	}
	public String getHomeWorkNO() {
		return HomeWorkNO;
	}
	public void setHomeWorkNO(String string) {
		HomeWorkNO = string;
	}
	public int getFileNo() {
		return FileNo;
	}
	public void setFileNo(int fileNo) {
		FileNo = fileNo;
	}
	public int getSubjectNO() {
		return SubjectNO;
	}
	public void setSubjectNO(int subjectNO) {
		SubjectNO = subjectNO;
	}
	public String getFileName() {
		return FileName;
	}
	public void setFileName(String fileName) {
		FileName = fileName;
	}
	public int getFileSize() {
		return FileSize;
	}
	public void setFileSize(int fileSize) {
		FileSize = fileSize;
	}
	public String getFileSaveUrl() {
		return FileSaveUrl;
	}
	public void setFileSaveUrl(String fileSaveUrl) {
		FileSaveUrl = fileSaveUrl;
	}
	public String getProfessorNo() {
		return professorNo;
	}
	public void setProfessorNo(String professorNo) {
		this.professorNo = professorNo;
	}
	public String getSubjectID() {
		return subjectID;
	}
	public void setSubjectID(String subjectID) {
		this.subjectID = subjectID;
	}
	
	
	
}
