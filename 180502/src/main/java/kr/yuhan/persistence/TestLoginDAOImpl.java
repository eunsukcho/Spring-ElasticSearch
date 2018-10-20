package kr.yuhan.persistence;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import kr.yuhan.domain.TestLoginVO;

@Repository
public class TestLoginDAOImpl implements TestLoginDAO{
	
	@Inject
	private SqlSession sqlSession;
	static final String NAMESPACE = "kr.yuhan.mapper.TestLoginMapper";
	
	@Override
	public int checkStudentLogin(TestLoginVO vo) {

		return sqlSession.selectOne(NAMESPACE + ".checkStudentLogin", vo);
	}
	
	@Override
	public TestLoginVO StudentData(TestLoginVO vo) {
		return sqlSession.selectOne(NAMESPACE + ".StudentData", vo);
	}

	@Override
	public int checkProfessorLogin(TestLoginVO vo) {
		return sqlSession.selectOne(NAMESPACE + ".checkProfessorLogin", vo);
	}

	@Override
	public TestLoginVO ProfessorData(TestLoginVO vo) {
		return sqlSession.selectOne(NAMESPACE + ".ProfessorData", vo);
	}
}
