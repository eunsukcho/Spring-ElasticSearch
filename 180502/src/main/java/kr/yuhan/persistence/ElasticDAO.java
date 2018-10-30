package kr.yuhan.persistence;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

import com.google.gson.Gson;

import kr.yuhan.domain.ElasticVO;
import kr.yuhan.domain.GetElasticCriteria;
import kr.yuhan.domain.GetElasticSearchVo;
import kr.yuhan.domain.SearchCriteria;
import kr.yuhan.domain.YuhanHomeworkVO;

public interface ElasticDAO {
	public String ProfessorName(YuhanHomeworkVO vo);
	public String SubjectName(YuhanHomeworkVO vo);
	
	public void putElastic(ElasticVO elvo ,Gson gson) throws MalformedURLException, IOException;
	public List<GetElasticSearchVo> getElastic(ElasticVO elvo);
	public List<GetElasticSearchVo> getElasticCriteria(SearchCriteria criteria);
	public List<GetElasticSearchVo> readElastic(String _id);
	public String UpdateElastic(GetElasticSearchVo elaSearchVO);
	public void deleteElastic(String _id);
}
