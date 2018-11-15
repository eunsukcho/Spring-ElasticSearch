package kr.yuhan.service;

import java.util.List;

import kr.yuhan.domain.YuhanMemberVO;
import kr.yuhan.domain.YuhanProfessorVO;

public interface YuhanMemberCheckService 
{
	public String selectMember(String memberID);
	public String selectMemberClass(String memberID);
	public String selectRate(String memberID);
	
	public void insertMember(YuhanMemberVO vo);
	
	public int selectIDPW(YuhanMemberVO vo);
	
	public List<YuhanMemberVO> select_Member(String memberID); //2018.10.09 이진주

	/*******************ZEON***********************/
	public void insertMember_pro(YuhanMemberVO vo);
	public void insertPro(YuhanProfessorVO vo);
	public List<YuhanProfessorVO> professor_check();
	public void check_pro(YuhanProfessorVO vo);
	
	//login
	public int loginPro(YuhanMemberVO vo);
	public YuhanProfessorVO professorNum(String id);
	/**********************************************/

	public String idcheck(String memberID);
	public String idcheckP(String proID);
	
	public List<YuhanMemberVO> selectUserGrade(String memberID);

}
