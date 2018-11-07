package kr.yuhan.service;

import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.yuhan.domain.YuhanSubjectVO;
import kr.yuhan.persistence.YuhanSubjectDAO;

@Service
public class YuhanSubjectServiceImpl implements YuhanSubjectService 
{
	@Inject
	private YuhanSubjectDAO dao;
	
	@Override
	public List<YuhanSubjectVO> selectSubject(String memberGrade) 
	{
		// TODO Auto-generated method stub
		return dao.selectSubject(memberGrade);
	}

	@Override
	public void insertSubject(YuhanSubjectVO vo) 
	{
		// TODO Auto-generated method stub
		dao.insertSubject(vo);
	}

	@Override
	public List<YuhanSubjectVO> selectSubjectList(String memberID) 
	{
		// TODO Auto-generated method stub
		return dao.selectSubjectList(memberID);
	}

	@Override
	public List<YuhanSubjectVO> noSelectSubject(String memberID)
{
		// TODO Auto-generated method stub
		return dao.noSelectSubject(memberID);
	}

	@Override
	public void updateSubject(HashMap map) 
	{
		// TODO Auto-generated method stub
		dao.updateSubject(map);
	}

	@Override
	public String selectProNum(String memberID) 
	{
		// TODO Auto-generated method stub
		return dao.selectProNum(memberID);
	}

	@Override
	public void createSubject(YuhanSubjectVO vo) 
	{
		// TODO Auto-generated method stub
		dao.createSubject(vo);
	}

	@Override
	public List<YuhanSubjectVO> ProSelectSubject(String memberID) 
	{
		// TODO Auto-generated method stub
		return dao.ProSelectSubject(memberID);
	}

	@Override
	public int countStudent(int subjectNo) 
	{
		// TODO Auto-generated method stub
		return dao.countStudent(subjectNo);
	}

	@Override
	public List<YuhanSubjectVO> proSearchSubject(String subjectNo) 
	{
		// TODO Auto-generated method stub
		return dao.proSearchSubject(subjectNo);
	}

	@Override
	public void proUpdateSubject(YuhanSubjectVO vo) 
	{
		// TODO Auto-generated method stub
		dao.proUpdateSubject(vo);
	}
}
