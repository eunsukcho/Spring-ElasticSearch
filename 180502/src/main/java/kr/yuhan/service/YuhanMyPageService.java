package kr.yuhan.service;

import java.util.List;

import kr.yuhan.domain.YuhanSubjectVO;

public interface YuhanMyPageService 
{
	public List<YuhanSubjectVO> selectReport(String memberID);
	public int selectReportCount(String memberID);
}
