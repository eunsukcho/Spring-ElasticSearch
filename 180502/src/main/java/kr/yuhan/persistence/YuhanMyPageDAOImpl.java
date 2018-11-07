package kr.yuhan.persistence;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import kr.yuhan.domain.YuhanSubjectVO;

@Repository
public class YuhanMyPageDAOImpl implements YuhanMyPageDAO 
{
	@Inject
	private SqlSession sqlSession;
	static final String NAMESPACE = "kr.yuhan.mapper.YuhanMyPageMapper";

	@Override
	public List<YuhanSubjectVO> selectReport(String memberID) 
	{
		// TODO Auto-generated method stub
		return sqlSession.selectList(NAMESPACE + ".selectReport", memberID);
	}

	@Override
	public int selectReportCount(String memberID) 
	{
		// TODO Auto-generated method stub
		return sqlSession.selectOne(NAMESPACE + ".selectReportCount", memberID);
	}

}
