package kr.yuhan.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.yuhan.domain.Criteria;
import kr.yuhan.domain.ReplyCriteria;
import kr.yuhan.domain.ReplyVO;
import kr.yuhan.persistence.ReplyDAO;

@Service
public class ReplyServiceImpl implements ReplyService{

	@Inject
	private ReplyDAO dao;
	
	@Override
	public void insertRep(ReplyVO repVo) {
		dao.insertRep(repVo);
	}

	@Override
	public List<ReplyVO> selectRep(ReplyVO repVo) {
		return dao.selectRep(repVo);
	}

	@Override
	public List<ReplyVO> selectRepPage(ReplyVO repVo) {
		return dao.selectRepPage(repVo);
	}

	@Override
	public void deleteRep(int repNo) {
		dao.deleteRep(repNo);
	}

	@Override
	public int totalRep(ReplyVO repVo) {
		return dao.totalRep(repVo);
	}

	@Override
	public void updateRep(int repNo) {
		dao.updateRep(repNo);
	}

}
