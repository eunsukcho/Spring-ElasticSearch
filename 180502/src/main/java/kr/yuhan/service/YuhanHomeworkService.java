package kr.yuhan.service;

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

public interface YuhanHomeworkService {
	public void insertHomework(YuhanHomeworkVO vo) throws ParseException; //과제 등록
	public List<YuhanHomeworkVO> listHomework(YuhanHomeworkVO vo); //학생의 과제 목록
	public List<YuhanHomeworkVO> listProfessorHomework(Map<String, Object> map); // 교수의 과제 목록
	public YuhanHomeworkVO readHomework(Map<Integer, Integer> parameters); //과제의 상세 View
	public List<YuhanClass> readClass(String memberID); // 학생의 수강 과목 목록
	public List<YuhanClass> readProfesserClass(String memberNum); //교수의 수강 과목 목록
	public int selectHwno(YuhanHomeworkVO vo); // 파일 DB에 저장하기 위한 글번호
	
	///--페이징
	public List<YuhanHomeworkVO> listCriteriaHomework(Criteria cri);
	public int listCountCriteria(Criteria cri);
	//
	
	public List<YuhanSubjectVO> selectSubjectData(String subjectID); //2018-10-16 이진주
	
	public ElasticVO listHomeworkEla(String string); //엘라스틱 서치에 필요한 교수 이름, 과목 이름 등을 가져오기 위함
	
	public YuhanHomeworkVO selectHomeworkData(int hwno); //update 기본 세팅
	public void updateHomework(YuhanHomeworkVO vo);
	public void deleteHomework(int hwno);
	
	public void insertReport(ReportVO vo);
	public ReportVO selectReportInfo(ReportVO vo);
}
