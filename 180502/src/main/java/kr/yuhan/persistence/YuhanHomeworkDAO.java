package kr.yuhan.persistence;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import kr.yuhan.domain.Criteria;
import kr.yuhan.domain.ElasticVO;
import kr.yuhan.domain.ReportVO;
import kr.yuhan.domain.YuhanClass;
import kr.yuhan.domain.YuhanFileVO;
import kr.yuhan.domain.YuhanHomeworkVO;
import kr.yuhan.domain.YuhanSubjectVO;

public interface YuhanHomeworkDAO {
	public void insertHomework(YuhanHomeworkVO vo);
	public ElasticVO listHomeworkEla(String subjectID);
	public List<YuhanHomeworkVO> listHomework(YuhanHomeworkVO vo);
	public List<YuhanHomeworkVO> listProfessorHomework(Map<String, Object> map);
	public YuhanHomeworkVO readHomework(Map<Integer, Integer> parameters);
	public List<YuhanClass> readClass(String memberID);
	public List<YuhanClass> readProfesserClass(String memberNum);
	public int selectHwno(YuhanHomeworkVO vo);
	//
	public List<YuhanHomeworkVO> listPage(int page);
	public List<YuhanHomeworkVO> listCriteria(Criteria cri);
	public int countPaging(Criteria cri);
	//
	
	public List<YuhanSubjectVO> selectSubjectData(String subjectID); //2018-10-16 이진주
	
	public YuhanHomeworkVO selectHomeworkData(int hwno);
	public void updateHomework(YuhanHomeworkVO vo);
	public void deleteHomework(int hwno);
	
	public void insertReport(ReportVO vo);
	public ReportVO selectReportInfo(ReportVO vo);
}
