package kr.yuhan.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.yuhan.domain.ReportVO;
import kr.yuhan.persistence.YuhanReportDAO;

@Service
public class YuhanReportServiceImpl implements YuhanReportService{

	@Inject
	private YuhanReportDAO dao;
	
	@Override
	public void insertReport(ReportVO vo) {
		dao.insertReport(vo);
	}

	@Override
	public ReportVO selectReportInfo(ReportVO vo) {
		return dao.selectReportInfo(vo);
	}

	@Override
	public int reportContentCount(int hwno, String studentID) {
		return dao.reportContentCount(hwno, studentID);
	}
}
