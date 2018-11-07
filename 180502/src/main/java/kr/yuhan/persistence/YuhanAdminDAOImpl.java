package kr.yuhan.persistence;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import kr.yuhan.domain.YuhanSubjectVO;

@Repository
public class YuhanAdminDAOImpl implements YuhanAdminDAO 
{
	@Inject
	private SqlSession sqlSession;
	
	static final String SUBJECT_NAMESPACE = "kr.yuhan.mapper.YuhanAdminMapper";
	
	@Override
	public List<YuhanSubjectVO> adminSelectSubject() 
	{
		// TODO Auto-generated method stub
		return sqlSession.selectList(SUBJECT_NAMESPACE + ".adminSelectSubject");
	}

	@Override
	public void updateSubjectY(int subjectNo) 
	{
		// TODO Auto-generated method stub
		sqlSession.update(SUBJECT_NAMESPACE + ".updateSubjectY", subjectNo);
	}
}
