package kr.yuhan.service;

import java.util.HashMap;
import java.util.List;

import kr.yuhan.domain.YuhanSubjectVO;

public interface YuhanSubjectService 
{
	public List<YuhanSubjectVO> selectSubject(String memberGrade);
	
	public void insertSubject(YuhanSubjectVO vo);
	
	public List<YuhanSubjectVO> selectSubjectList(String memberID);
	
	public List<YuhanSubjectVO> noSelectSubject(String memberID);
	
	public void updateSubject(HashMap map);
	
	public String selectProNum(String memberID);
	
	public void createSubject(YuhanSubjectVO vo);
	
	public List<YuhanSubjectVO> ProSelectSubject(String memberID);
	
	public int countStudent (int subjectNo);
	
	public List<YuhanSubjectVO> proSearchSubject(String subjectNo);
	
	public void proUpdateSubject(YuhanSubjectVO vo);
}
