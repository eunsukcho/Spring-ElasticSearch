package kr.yuhan.persistence;

import java.util.List;

import kr.yuhan.domain.ReportVO;

public interface YuhanReportDAO {
	public void insertReport(ReportVO vo);
	public ReportVO selectReportInfo(ReportVO vo);
	public int reportContentCount(int hwno, String studentID);//학생이 올린 과제가 몇개가 있는지
	
	public List<ReportVO> reportStudentCheck(int hwno);
	public ReportVO reportDetailView(int no);
	public String reportStudentID(int no);
}
