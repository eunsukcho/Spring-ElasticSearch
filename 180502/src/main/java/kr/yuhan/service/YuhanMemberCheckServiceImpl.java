package kr.yuhan.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.yuhan.domain.YuhanMemberVO;
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

	

}
