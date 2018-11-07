package kr.yuhan.persistence;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import kr.yuhan.domain.Criteria;
import kr.yuhan.domain.ElasticVO;
import kr.yuhan.domain.ReportVO;
import kr.yuhan.domain.YuhanClass;
import kr.yuhan.domain.YuhanFileVO;
import kr.yuhan.domain.YuhanHomeworkVO;
import kr.yuhan.domain.YuhanSubjectVO;

@Repository
public class YuhanHomeworkDAOImpl implements YuhanHomeworkDAO{

	@Inject
	private SqlSession sqlSession;
	static final String NAMESPACE = "kr.yuhan.mapper.YuhanHomeworkMapper";
	
	@Override
	public void insertHomework(YuhanHomeworkVO vo){ // 과제 올리기
		sqlSession.insert(NAMESPACE + ".insertHomework", vo);
	}
	
	@Override
	public ElasticVO listHomeworkEla(String subjectID) {
		return sqlSession.selectOne(NAMESPACE + ".listHomeworkEla", subjectID);
	}
	
	@Override
	public List<YuhanHomeworkVO> listHomework(YuhanHomeworkVO vo) { // 과제 목록 보기
		return sqlSession.selectList(NAMESPACE + ".listHomework", vo);
	}
	
	@Override
	public List<YuhanHomeworkVO> listProfessorHomework(YuhanHomeworkVO vo) {
		return sqlSession.selectList(NAMESPACE + ".listProfessorHomework", vo);
	}
	
	@Override
	public YuhanHomeworkVO readHomework(Map<Integer, Integer> parameters) { // 과제 상세 내용 보기	
		return sqlSession.selectOne(NAMESPACE + ".readHomework", parameters);
	}
	
	@Override
	public List<YuhanClass> readClass(String memberID) { //수강 과목 목록
		return sqlSession.selectList(NAMESPACE + ".readStudentClass", memberID);
	}
	
	@Override
	public List<YuhanClass> readProfesserClass(String memberNum) {
		return sqlSession.selectList(NAMESPACE + ".readProfessorClass", memberNum);
	}
	
	//
	@Override
	public List<YuhanHomeworkVO> listPage(int page) {
		if(page <= 0) {
			page = 1;
		}
		page = (page - 1) * 10;
		return sqlSession.selectList(NAMESPACE + ".listPageHomework", page);
	}
	
	@Override
	public List<YuhanHomeworkVO> listCriteria(Criteria cri) {
		return sqlSession.selectList(NAMESPACE + ".listCriteriaHomework", cri);
	}
	
	@Override
	public int countPaging(Criteria cri) {
		return sqlSession.selectOne(NAMESPACE + ".countPaging", cri);
	}
//
	@Override
	public int selectHwno(YuhanHomeworkVO vo) {
		System.out.println("dao : " + sqlSession.selectOne(NAMESPACE + ".selectHwno", vo));
		return sqlSession.selectOne(NAMESPACE + ".selectHwno", vo);
	}

	@Override
	public List<YuhanSubjectVO> selectSubjectData(String subjectID) {
		return sqlSession.selectList(NAMESPACE + ".selectSubjectData", subjectID);
	}

	@Override
	public YuhanHomeworkVO selectHomeworkData(int hwno) {
		return sqlSession.selectOne(NAMESPACE + ".selectHomeworkData", hwno);
	}

	@Override
	public void updateHomework(YuhanHomeworkVO vo) {
		sqlSession.update(NAMESPACE + ".updateHomework", vo);
	}

	@Override
	public void deleteHomework(int hwno) {
		sqlSession.update(NAMESPACE + ".deleteHomework", hwno);
	}

	@Override
	public List<Object> selectHak(String professorNum) {
		return sqlSession.selectList(NAMESPACE + ".selectHak", professorNum);
	}

	@Override
	public List<YuhanClass> selectClass(String memberNum) {
		return sqlSession.selectList(NAMESPACE + ".selectClass", memberNum);
	}

	
}
