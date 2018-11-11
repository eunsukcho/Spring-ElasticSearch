package kr.yuhan.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import kr.yuhan.domain.ReportVO;

@Repository
public class YuhanReportDAOImpl implements YuhanReportDAO{
	@Inject
	private SqlSession sqlSession;
	static final String NAMESPACE = "kr.yuhan.mapper.YuhanReportMapper";
	
	@Override
	public void insertReport(ReportVO vo) {
		sqlSession.insert(NAMESPACE + ".insertReport", vo);
	}

	@Override
	public ReportVO selectReportInfo(ReportVO vo) {
		return sqlSession.selectOne(NAMESPACE + ".selectReportInfo", vo);
	}
	
	@Override
	public int reportContentCount(int hwno, String studentID) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("hwno", hwno);
		map.put("studentID", studentID);
		return sqlSession.selectOne(NAMESPACE + ".reportContentCount", map);
	}

	@Override
	public List<ReportVO> reportStudentCheck(int hwno) {
		return sqlSession.selectList(NAMESPACE + ".reportStudentCheck", hwno);
	}

	@Override
	public ReportVO reportDetailView(int no) {
		System.out.println("dao no : " + no);
		return sqlSession.selectOne(NAMESPACE + ".reportDetailView", no);
	}

	@Override
	public String reportStudentID(int no) {
		return sqlSession.selectOne(NAMESPACE + ".reportStudentID", no);
	}

	@Override
	public void reportUpdate(ReportVO vo) {
		sqlSession.update(NAMESPACE + ".reportUpdate", vo);
	}

	@Override
	public void reportDelete(int no) {
		sqlSession.delete(NAMESPACE + ".reportDelete", no);
	}
}
