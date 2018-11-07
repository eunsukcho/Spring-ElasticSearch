package kr.yuhan.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.yuhan.domain.YuhanMemberVO;
import kr.yuhan.domain.YuhanProfessorVO;
import kr.yuhan.persistence.YuhanMemberCheckDAO;

@Service
public class YuhanMemberCheckServiceImpl implements YuhanMemberCheckService {

	@Inject
	private YuhanMemberCheckDAO dao;
	
	public String selectMember(String memberID) {
		return dao.selectMember(memberID);
	}
	
	@Override
	public String selectMemberClass(String memberID) {
		return dao.selectMemberClass(memberID);
	}

	@Override
	public String selectRate(String memberID) {
		return dao.selectRate(memberID);
	}

	@Override
	public void insertMember(YuhanMemberVO vo) {
		dao.insertMember(vo);
	}

	@Override
	public int selectIDPW(YuhanMemberVO vo) {
		return dao.selectIDPW(vo);
	}

	@Override
	public List<YuhanMemberVO> select_Member(String memberID) 
	{
		return dao.select_Member(memberID);
	}//2018.10.09 이진주

	/***********************ZEON***********************/
	@Override
	public void insertMember_pro(YuhanMemberVO vo) {
		// TODO Auto-generated method stub
		dao.insertMember_pro(vo);
	}

	@Override
	public void insertPro(YuhanProfessorVO vo) {
		dao.insertPro(vo);
	}

	@Override
	public List<YuhanProfessorVO> professor_check() {
		
		return dao.professor_check();
	}

	@Override
	public void check_pro(YuhanProfessorVO vo) {
		dao.check_pro(vo);
	}

	@Override
	public int loginPro(YuhanMemberVO vo) {
		return dao.loginPro(vo);
	}

	@Override
	public YuhanProfessorVO professorNum(String id) {
		return dao.professorNum(id);
	}
}
