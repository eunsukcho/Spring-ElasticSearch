package kr.yuhan.persistence;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import kr.yuhan.domain.YuhanMemberVO;
import kr.yuhan.domain.YuhanProfessorVO;

@Repository
public class YuhanMemberCheckDAOImpl implements YuhanMemberCheckDAO 
{
	@Inject
	private SqlSession sqlSession;
	static final String NAMESPACE = "kr.yuhan.mapper.YuhanMapper";

	@Override
	public String selectMember(String memberID) 
	{
		// TODO Auto-generated method stub
		return sqlSession.selectOne(NAMESPACE + ".selectMember", memberID);
	}
	
	@Override
	public String selectMemberClass(String memberID) {
		return sqlSession.selectOne(NAMESPACE + ".selectMemberClass", memberID);
	}
	
	@Override
	public String selectRate(String memberID) {
		return sqlSession.selectOne(NAMESPACE + ".selectRate", memberID);
	}
	
	@Override
	public void insertMember(YuhanMemberVO vo) 
	{
		// TODO Auto-generated method stub
		sqlSession.insert(NAMESPACE + ".insertMember", vo);
	}

	@Override
	public int selectIDPW(YuhanMemberVO vo) 
	{
		// TODO Auto-generated method stub
		return sqlSession.selectOne(NAMESPACE + ".selectIDPW", vo);
	}

	@Override
	public List<YuhanMemberVO> select_Member(String memberID) 
	{
		// TODO Auto-generated method stub
		return sqlSession.selectList(NAMESPACE + ".select_Member", memberID);
	}//2018.10.09 이진주

	/***************************ZEON****************************/
	@Override
	public void insertMember_pro(YuhanMemberVO vo) {
		sqlSession.insert(NAMESPACE + ".insertMember_pro", vo);
		
	}

	@Override
	public List<YuhanProfessorVO> professor_check() {
		// TODO Auto-generated method stub
		return sqlSession.selectList(NAMESPACE+".professor_check");
	}

	@Override
	public void check_pro(YuhanProfessorVO vo) {
		// TODO Auto-generated method stub
		sqlSession.update(NAMESPACE+".check_pro",vo);
	}

	@Override
	public void insertPro(YuhanProfessorVO vo) {
		sqlSession.insert(NAMESPACE + ".insertPro", vo);
		
	}

	//login
	@Override
	public int loginPro(YuhanMemberVO vo) {
		return sqlSession.selectOne(NAMESPACE + ".loginPro", vo);
	}

	@Override
	public YuhanProfessorVO professorNum(String id) {
		return sqlSession.selectOne(NAMESPACE + ".professorNum", id);
	}
}
