package kr.yuhan.persistence;

import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import kr.yuhan.domain.YuhanSubjectVO;

@Repository
public class YuhanSubjectDAOImpl implements YuhanSubjectDAO 
{
	@Inject
	private SqlSession sqlSession;
	static final String NAMESPACE = "kr.yuhan.mapper.YuhanSubjectMapper";
	
	@Override
	public List<YuhanSubjectVO> selectSubject(String memberGrade) 
	{
		// TODO Auto-generated method stub
		return sqlSession.selectList(NAMESPACE + ".selectSubject", memberGrade);
	}

	@Override
	public void insertSubject(YuhanSubjectVO vo) 
	{
		// TODO Auto-generated method stub
		sqlSession.insert(NAMESPACE + ".insertSubject", vo);
	}

	@Override
	public List<YuhanSubjectVO> selectSubjectList(String memberID)
	{
		// TODO Auto-generated method stub
		return sqlSession.selectList(NAMESPACE + ".selectSubjectList", memberID);
	}

	@Override
	public List<YuhanSubjectVO> noSelectSubject(String memberID) 
	{
		// TODO Auto-generated method stub
		return sqlSession.selectList(NAMESPACE + ".noSelectSubject", memberID);
	}

	@Override
	public void updateSubject(HashMap map) 
	{
		// TODO Auto-generated method stub
		sqlSession.update(NAMESPACE + ".updateSubject", map);
	}

	@Override
	public String selectProNum(String memberID) 
	{
		// TODO Auto-generated method stub
		return sqlSession.selectOne(NAMESPACE + ".selectProNum", memberID);
	}

	@Override
	public void createSubject(YuhanSubjectVO vo) 
	{
		// TODO Auto-generated method stub
		sqlSession.insert(NAMESPACE + ".createSubject", vo);
	}

	@Override
	public List<YuhanSubjectVO> ProSelectSubject(String memberID) 
	{
		// TODO Auto-generated method stub
		return sqlSession.selectList(NAMESPACE + ".ProSelectSubject", memberID);
	}

	@Override
	public int countStudent(int subjectNo) 
	{
		// TODO Auto-generated method stub
		return sqlSession.selectOne(NAMESPACE + ".countStudent", subjectNo);
	}

	@Override
	public List<YuhanSubjectVO> proSearchSubject(String subjectNo) 
	{
		// TODO Auto-generated method stub
		return sqlSession.selectList(NAMESPACE + ".proSearchSubject", subjectNo);
	}

	@Override
	public void proUpdateSubject(YuhanSubjectVO vo) 
	{
		// TODO Auto-generated method stub
		sqlSession.update(NAMESPACE + ".proUpdateSubject", vo);
	}
	
	@Override
	public void updateAddSubject(HashMap map) 
	{
		// TODO Auto-generated method stub
		sqlSession.update(NAMESPACE + ".updateAddSubject", map);
	}
}
