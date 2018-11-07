package kr.yuhan.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.yuhan.domain.YuhanSubjectVO;
import kr.yuhan.persistence.YuhanAdminDAO;

@Service
public class YuhanAdminServiceImpl implements YuhanAdminService 
{
	@Inject
	private YuhanAdminDAO subjectDAO;
	
	@Override
	public List<YuhanSubjectVO> adminSelectSubject() 
	{
		// TODO Auto-generated method stub
		return subjectDAO.adminSelectSubject();
	}

	@Override
	public void updateSubjectY(int subjectNo) 
	{
		// TODO Auto-generated method stub
		subjectDAO.updateSubjectY(subjectNo);
	}

}
