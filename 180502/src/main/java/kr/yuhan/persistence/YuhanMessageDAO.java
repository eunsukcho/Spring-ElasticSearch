package kr.yuhan.persistence;

import java.util.List;

import kr.yuhan.domain.Criteria;
import kr.yuhan.domain.YuhanMemberVO;
import kr.yuhan.domain.YuhanMessageVO;

public interface YuhanMessageDAO 
{
	public List<YuhanMemberVO> findUser(); //2018-10-11 이진주
	
	public void sendMessage(YuhanMessageVO vo); //2018-10-13 이진주
	
	public String selectHak(String sessionID);
	
	public List<YuhanMessageVO> selectMessage(Criteria cri); //2018-10-13 
	
	public List<YuhanMessageVO> selectSendMessage(Criteria cri); //2018-10-13 
	
	public List<YuhanMessageVO> selectMessageOne(String messageNum); //2018-10-14
	
	public void updateReadStatus(String messageNum); //2018-10-14
	
	public String selectNoReadMessageCount(String memberID); //2018-10-14
	
	public String selectUser(String memberID); //2018-10-16
	
	public List<YuhanMemberVO> listPage(int page);
	public List<YuhanMemberVO> listCriteria(Criteria cri);
	public int totalMessageCount(Criteria cri);
}
