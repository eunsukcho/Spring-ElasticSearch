package kr.yuhan.service;

import java.util.List;

import kr.yuhan.domain.YuhanSubjectVO;

public interface YuhanAdminService 
{
	public List<YuhanSubjectVO> adminSelectSubject();
	
	public void updateSubjectY(int subjectNo);
}
