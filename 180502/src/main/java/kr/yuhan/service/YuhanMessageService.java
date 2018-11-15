package kr.yuhan.service;

import java.util.List;

import kr.yuhan.domain.Criteria;
import kr.yuhan.domain.PageMaker;
import kr.yuhan.domain.SearchCriteria2;
import kr.yuhan.domain.YuhanMemberVO;
import kr.yuhan.domain.YuhanMessageVO;

public interface YuhanMessageService 
{
public List<YuhanMemberVO> findUser(); //2018-10-11 이진주
	
	public void sendMessage(YuhanMessageVO vo); //2018-10-13 이진주
	
	public String selectHak(String sessionID);
	
	public List<YuhanMessageVO> selectMessage(Criteria cri); //2018-10-13 이진주
	
	public List<YuhanMessageVO> selectSendMessage(Criteria cri); //2018-10-13 
	
	public List<YuhanMessageVO> selectMessageOne(String messageNum); //2018-10-14
	
	public void updateReadStatus(String messageNum); //2018-10-14
	
	public String selectNoReadMessageCount(String memberID); //2018-10-14
	
	public List<YuhanMemberVO> selectUser(String memberID); //2018-10-16
	
	public void updateDeleteMessageStatus(String messageNum); //2018-10-16
	
	public List<YuhanMemberVO> listCriteria(Criteria cri);
	public int totalMessageCount(Criteria cri);
	
	public String selectID(String memberHak);
	
	public List<YuhanMessageVO> listSearch(SearchCriteria2 cri);
	public int listSearchCount(SearchCriteria2 cri);
	
	public void saveMessage(String messageNum);
	
	public List<YuhanMessageVO> selectSaveMessage(Criteria cri);
	public int totalSaveMessageCount(Criteria cri);
	
	/** @author 진주 @since 2018-11-28  */
	public int selectMessageCount(String memberID);
}