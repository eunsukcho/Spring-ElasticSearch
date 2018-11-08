package kr.yuhan.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import kr.yuhan.domain.Criteria;
import kr.yuhan.domain.ReplyCriteria;
import kr.yuhan.domain.ReplyVO;

@Repository
public class ReplyDAOImpl implements ReplyDAO{

	@Inject
	SqlSession sqlSession;
	static final String NAMESPACE = "kr.yuhan.mapper.ReplyMapper";
	
	@Override
	public void insertRep(ReplyVO repVo) {
		sqlSession.insert(NAMESPACE + ".insertRep", repVo);
	}

	@Override
	public List<ReplyVO> selectRep(ReplyVO repVo) {
		return sqlSession.selectList(NAMESPACE + ".selectRep", repVo);
	}

	@Override
	public List<ReplyVO> selectRepPage(ReplyVO repVo) {
		return sqlSession.selectList(NAMESPACE + ".selectRepPage", repVo);
	}

	@Override
	public void deleteRep(int repNo) {
		sqlSession.delete(NAMESPACE + ".deleteRep", repNo);
	}

	@Override
	public int totalRep(ReplyVO repVo) {
		return sqlSession.selectOne(NAMESPACE + ".totalRep", repVo);
	}

	@Override
	public void updateRep(ReplyVO vo) {
		sqlSession.update(NAMESPACE + ".updateRep", vo);
	}

	@Override
	public List<ReplyVO> listPage(Integer hwno, Criteria cri) {
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("hwno", hwno);
		paramMap.put("cri", cri);
		return sqlSession.selectList(NAMESPACE + ".listPage", paramMap);
	}

	@Override
	public int count(Integer hwno) {
		return sqlSession.selectOne(NAMESPACE + ".count", hwno);
	}
	
}
