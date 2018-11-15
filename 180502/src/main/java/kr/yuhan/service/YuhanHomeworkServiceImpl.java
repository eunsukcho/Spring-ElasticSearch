package kr.yuhan.service;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.yuhan.domain.Criteria;
import kr.yuhan.domain.ElasticVO;
import kr.yuhan.domain.ReportVO;
import kr.yuhan.domain.YuhanClass;
import kr.yuhan.domain.YuhanFileVO;
import kr.yuhan.domain.YuhanHomeworkVO;
import kr.yuhan.domain.YuhanSubjectVO;
import kr.yuhan.persistence.YuhanFileDAO;
import kr.yuhan.persistence.YuhanHomeworkDAO;

@Service
public class YuhanHomeworkServiceImpl implements YuhanHomeworkService {

	@Inject
	private YuhanHomeworkDAO dao;
	@Inject
	private YuhanFileDAO filedao;
	
	@Override
	public void insertHomework(YuhanHomeworkVO vo) { //과제 올리기
		dao.insertHomework(vo);
	}

	@Override
	public ElasticVO listHomeworkEla(String subjectID) {
		return dao.listHomeworkEla(subjectID);
	}
	
	@Override
	public List<YuhanHomeworkVO> listHomework(YuhanHomeworkVO vo) { //과제 목록 보기
		return dao.listHomework(vo);
	}
	
	@Override
	public List<YuhanHomeworkVO> listProfessorHomework(YuhanHomeworkVO vo) {
		return dao.listProfessorHomework(vo);
	}

	@Override
	public YuhanHomeworkVO readHomework(Map<Integer, Integer> parameters) { // 과제 상세 내용 보기
		YuhanHomeworkVO vo = dao.readHomework(parameters);
		if (vo.getDataStatus().equals("Y"))
		{
			System.out.println("파일있음");
			filedao.readFile(parameters);
		}
			
		return dao.readHomework(parameters);
	}
	
	@Override
	public List<YuhanClass> readClass(String memberID) { //수강 과목 목록
		return dao.readClass(memberID);
	}
	
	@Override
	public List<YuhanClass> readProfesserClass(String memberNum) {
		return dao.readProfesserClass(memberNum);
	}
	
	//페이징
	@Override
	public List<YuhanHomeworkVO> listCriteriaHomework(Criteria cri) {
		return dao.listCriteria(cri);
	}
	
	@Override
	public int listCountCriteria(Criteria cri) {
		return dao.countPaging(cri);
	}
	//

	@Override
	public int selectHwno(YuhanHomeworkVO vo) {
		return dao.selectHwno(vo);
	}

	@Override
	public List<YuhanSubjectVO> selectSubjectData(String subjectID) {
		return dao.selectSubjectData(subjectID);
	}

	@Override
	public YuhanHomeworkVO selectHomeworkData(int hwno) {
		return dao.selectHomeworkData(hwno);
	}

	@Override
	public void updateHomework(YuhanHomeworkVO vo) {
		dao.updateHomework(vo);
	}

	@Override
	public void deleteHomework(int hwno) {
		dao.deleteHomework(hwno);
	}

	@Override
	public List<Object> selectHak(String professorNum) {
		return dao.selectHak(professorNum);
	}

	@Override
	public List<YuhanClass> selectClass(String memberNum) {
		return dao.selectClass(memberNum);
	}

	@Override
	public List<YuhanHomeworkVO> H_List(String memberID) {
		// TODO Auto-generated method stub
		return dao.H_List(memberID);
	}

	@Override
	public List<YuhanHomeworkVO> H_ListC(String memberID) {
		// TODO Auto-generated method stub
		 return dao.H_ListC(memberID);
	}

	@Override
	public List<YuhanHomeworkVO> H_ListM(String memberID) {
		// TODO Auto-generated method stub
		return dao.H_ListM(memberID);
	}

	@Override
	public List<YuhanHomeworkVO> H_ListMore(String memberID) {
		// TODO Auto-generated method stub
		return dao.H_ListMore(memberID);
	}
}
