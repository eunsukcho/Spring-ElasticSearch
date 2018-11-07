package kr.yuhan.persistence;

import java.util.List;

import kr.yuhan.domain.YuhanSubjectVO;

public interface YuhanMyPageDAO 
{
	public List<YuhanSubjectVO> selectReport(String memberID);
	public int selectReportCount(String memberID);
}
