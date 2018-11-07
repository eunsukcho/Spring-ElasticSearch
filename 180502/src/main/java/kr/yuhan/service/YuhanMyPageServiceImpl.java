package kr.yuhan.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.yuhan.domain.YuhanSubjectVO;
import kr.yuhan.persistence.YuhanMyPageDAO;

@Service
public class YuhanMyPageServiceImpl implements YuhanMyPageService 
{
	@Inject
	public YuhanMyPageDAO dao;
	
	@Override
	public List<YuhanSubjectVO> selectReport(String memberID) 
	{
		// TODO Auto-generated method stub
		return dao.selectReport(memberID);
	}

	@Override
	public int selectReportCount(String memberID) 
	{
		// TODO Auto-generated method stub
		return dao.selectReportCount(memberID);
	}

}
