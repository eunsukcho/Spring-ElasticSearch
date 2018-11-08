package kr.yuhan.persistence;

import java.util.List;

import kr.yuhan.domain.Criteria;
import kr.yuhan.domain.ReplyCriteria;
import kr.yuhan.domain.ReplyVO;

public interface ReplyDAO {
	
	public void insertRep(ReplyVO repVo);
	public List<ReplyVO> selectRep(ReplyVO repVo);
	public List<ReplyVO> selectRepPage(ReplyVO repVo);
	public void deleteRep(int repNo);
	public int totalRep(ReplyVO repVo);
	public void updateRep(ReplyVO vo);
	
	/**페이징**/
	public List<ReplyVO> listPage(Integer hwno, Criteria cri);
	public int count(Integer hwno);
}
