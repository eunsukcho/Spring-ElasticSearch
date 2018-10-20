package kr.yuhan.domain;

public class YuhanFileVO {
	String HomeWorkNO;
	int FileNo;
	int SubjectNO;
	String FileName;
	int FileSize;
	String FileSaveUrl;
	String professorNo;
	String subjectID;
	
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
