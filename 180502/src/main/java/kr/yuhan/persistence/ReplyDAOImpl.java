package kr.yuhan.persistence;

import java.util.List;

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
	
}
