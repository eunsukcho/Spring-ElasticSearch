package kr.yuhan.persistence;

import java.util.List;

import kr.yuhan.domain.YuhanSubjectVO;

public interface YuhanAdminDAO 
{
	public List<YuhanSubjectVO> adminSelectSubject();
	
	public void updateSubjectY(int subjectNo);
}
