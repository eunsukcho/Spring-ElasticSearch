package kr.yuhan.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.yuhan.domain.Criteria;
import kr.yuhan.domain.YuhanMemberVO;
import kr.yuhan.domain.YuhanMessageVO;
import kr.yuhan.persistence.YuhanMessageDAO;

@Service
public class YuhanMessageServiceImpl implements YuhanMessageService 
{
	@Inject
	private YuhanMessageDAO dao;
	
	@Override
	public List<YuhanMemberVO> findUser() 
	{
		// TODO Auto-generated method stub
		return dao.findUser();
	}

	@Override
	public void sendMessage(YuhanMessageVO vo) 
	{
		// TODO Auto-generated method stub
		dao.sendMessage(vo);
	}
	
	@Override
	public String selectHak(String sessionID) 
	{
		// TODO Auto-generated method stub
		return dao.selectHak(sessionID);
	}

	@Override
	public List<YuhanMessageVO> selectMessage(Criteria cri) 
	{
		// TODO Auto-generated method stub
		return dao.selectMessage(cri);
	}

	@Override
	public List<YuhanMessageVO> selectSendMessage(Criteria cri) 
	{
		// TODO Auto-generated method stub
		return dao.selectSendMessage(cri);
	}

	@Override
	public List<YuhanMessageVO> selectMessageOne(String messageNum) 
	{
		// TODO Auto-generated method stub
		return dao.selectMessageOne(messageNum);
	}

	@Override
	public void updateReadStatus(String messageNum) 
	{
		// TODO Auto-generated method stub
		dao.updateReadStatus(messageNum);
	}

	@Override
	public String selectNoReadMessageCount(String memberID) 
	{
		// TODO Auto-generated method stub
		return dao.selectNoReadMessageCount(memberID);
	}

	@Override
	public List<YuhanMemberVO> listCriteria(Criteria cri) 
	{
		// TODO Auto-generated method stub
		return dao.listCriteria(cri);
	}

	@Override
	public int totalMessageCount(Criteria cri) 
	{
		// TODO Auto-generated method stub
		return dao.totalMessageCount(cri);
	}

	@Override
	public String selectUser(String memberID) 
	{
		// TODO Auto-generated method stub
		return dao.selectUser(memberID);
	}

	@Override
	public void updateDeleteMessageStatus(String messageNum) {
		dao.updateDeleteMessageStatus(messageNum);
	}
}
