package kr.yuhan.persistence;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import kr.yuhan.domain.Criteria;
import kr.yuhan.domain.YuhanMemberVO;
import kr.yuhan.domain.YuhanMessageVO;

@Repository
public class YuhanMessageDAOImpl implements YuhanMessageDAO 
{
	@Inject
	private SqlSession sqlSession;
	static final String NAMESPACE = "kr.yuhan.mapper.YuhanMessageMapper";
	
	@Override
	public List<YuhanMemberVO> findUser() 
	{
		// TODO Auto-generated method stub
		return sqlSession.selectList(NAMESPACE + ".findUser");
	} //2018-10-11 이진주

	@Override
	public void sendMessage(YuhanMessageVO vo) 
	{
		// TODO Auto-generated method stub
		sqlSession.insert(NAMESPACE + ".sendMessage", vo);
	}
	
	@Override
	public String selectHak(String sessionID) 
	{
		// TODO Auto-generated method stub
		return sqlSession.selectOne(NAMESPACE + ".selectHak", sessionID);
	}

	@Override
	public List<YuhanMessageVO> selectMessage(Criteria cri) 
	{
		// TODO Auto-generated method stub
		return sqlSession.selectList(NAMESPACE + ".selectMessage", cri);
	}

	@Override
	public List<YuhanMessageVO> selectSendMessage(Criteria cri) 
	{
		// TODO Auto-generated method stub
		return sqlSession.selectList(NAMESPACE + ".selectSendMessage", cri);
	}

	@Override
	public List<YuhanMessageVO> selectMessageOne(String messageNum) 
	{
		// TODO Auto-generated method stub
		return sqlSession.selectList(NAMESPACE + ".selectMessageOne", messageNum);
	}

	@Override
	public void updateReadStatus(String messageNum) 
	{
		// TODO Auto-generated method stub
		sqlSession.update(NAMESPACE + ".updateReadStatus", messageNum);
	}

	@Override
	public String selectNoReadMessageCount(String memberID) 
	{
		// TODO Auto-generated method stub
		return sqlSession.selectOne(NAMESPACE + ".selectNoReadMessageCount", memberID);
	}

	@Override
	public List<YuhanMemberVO> listCriteria(Criteria cri) 
	{
		// TODO Auto-generated method stub
		return sqlSession.selectList(NAMESPACE + ".listCriteria", cri);
	}

	@Override
	public int totalMessageCount(Criteria cri) 
	{
		// TODO Auto-generated method stub
		return sqlSession.selectOne(NAMESPACE + ".totalMessageCount", cri);
	}

	@Override
	public List<YuhanMemberVO> listPage(int page) 
	{
		// TODO Auto-generated method stub
		if(page<=0)
		{
			page = 1;
		}
		
		page = (page-1) * 10; //시작 번호를 의미함
		return sqlSession.selectList(NAMESPACE + ".listPage");
	}

	@Override
	public String selectUser(String memberID) 
	{
		// TODO Auto-generated method stub
		return sqlSession.selectOne(NAMESPACE + ".selectUser", memberID);
	}
}
