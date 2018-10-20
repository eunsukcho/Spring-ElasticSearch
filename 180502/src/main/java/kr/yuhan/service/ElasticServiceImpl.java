package kr.yuhan.service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.google.gson.Gson;

import kr.yuhan.domain.ElasticVO;
import kr.yuhan.domain.GetElasticSearchVo;
import kr.yuhan.domain.YuhanHomeworkVO;
import kr.yuhan.persistence.ElasticDAO;

@Service
public class ElasticServiceImpl implements ElasticService{

	@Inject
	private ElasticDAO dao;
	
	@Override
	public String ProfessorName(YuhanHomeworkVO vo) {
		return dao.ProfessorName(vo);
	}

	@Override
	public String SubjectName(YuhanHomeworkVO vo) {
		return dao.SubjectName(vo);
	}

	@Override
	public void putElastic(ElasticVO elvo, Gson gson){
		try {
			dao.putElastic(elvo, gson);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<GetElasticSearchVo> getElastic(ElasticVO elvo) {
		return dao.getElastic(elvo);
	}

	@Override
	public List<GetElasticSearchVo> readElastic(String _id) {
		return dao.readElastic(_id);
	}

	@Override
	public String UpdateElastic(GetElasticSearchVo elaSearchVO) {
		return dao.UpdateElastic(elaSearchVO);
	}

	@Override
	public void deleteElastic(String _id) {
		dao.deleteElastic(_id);
	}

}
