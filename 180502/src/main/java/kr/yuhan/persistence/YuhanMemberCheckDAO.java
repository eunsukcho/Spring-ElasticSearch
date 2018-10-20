package kr.yuhan.persistence;

import java.util.List;

import kr.yuhan.domain.YuhanMemberVO;

public interface YuhanMemberCheckDAO 
{
	public String selectMember(String memberID);
	public String selectMemberClass(String memberID);
	public void insertMember(YuhanMemberVO vo);
	public int selectIDPW(YuhanMemberVO vo);
	public List<YuhanMemberVO> select_Member(String memberID); //2018.10.09 이진주
}
