package kr.yuhan.persistence;


import java.util.List;
import java.util.Map;

import kr.yuhan.domain.Criteria;
import kr.yuhan.domain.ElasticVO;
import kr.yuhan.domain.YuhanClass;
import kr.yuhan.domain.YuhanHomeworkVO;
import kr.yuhan.domain.YuhanSubjectVO;

public interface YuhanHomeworkDAO {
	public void insertHomework(YuhanHomeworkVO vo); //과제 등록
	public List<YuhanHomeworkVO> listHomework(YuhanHomeworkVO vo); // 게시글의 글 번호를 출력
	public List<YuhanHomeworkVO> listProfessorHomework(YuhanHomeworkVO vo); // 교수의 과제 목록 (현재는 필요없음)
	public YuhanHomeworkVO readHomework(Map<Integer, Integer> parameters); //과제의 상세 View
	public List<YuhanClass> readClass(String memberID); //학생의 수강 과목을 출력
	public List<YuhanClass> readProfesserClass(String memberNum); //교수의 담당 과목을 출력
	public int selectHwno(YuhanHomeworkVO vo); // 파일 테이블에 저장하기 위한 글번호
	
	///--페이징 (DB로 페이징 안함)
	public List<YuhanHomeworkVO> listPage(int page);
	public List<YuhanHomeworkVO> listCriteria(Criteria cri);
	public int countPaging(Criteria cri);
	//
	
	public List<YuhanSubjectVO> selectSubjectData(String subjectID); //2018-10-16 이진주-과목 정보 출력
	
	public ElasticVO listHomeworkEla(String subjectID); //엘라스틱 서치에 필요한 교수 이름, 과목 이름 등을 가져오기 위함
	
	public YuhanHomeworkVO selectHomeworkData(int hwno); //update 기본 세팅(GET)
	public void updateHomework(YuhanHomeworkVO vo);
	public void deleteHomework(int hwno);
	
	public List<Object> selectHak(String professorNum); //교수일 경우 몇 학년까지 담당하는지 
	public List<YuhanClass> selectClass(String memberNum); //해당 과목에 몇 반까지 있는지
	
	
}
