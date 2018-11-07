package kr.yuhan.persistence;

import kr.yuhan.domain.ReportVO;

public interface YuhanReportDAO {
	public void insertReport(ReportVO vo);
	public ReportVO selectReportInfo(ReportVO vo);
	public int reportContentCount(int hwno, String studentID);//학생이 올린 과제가 몇개가 있는지
}
