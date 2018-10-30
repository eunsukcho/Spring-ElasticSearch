package kr.yuhan.service;

import java.util.List;

import com.google.gson.Gson;

import kr.yuhan.domain.ElasticVO;
import kr.yuhan.domain.GetElasticCriteria;
import kr.yuhan.domain.GetElasticSearchVo;
import kr.yuhan.domain.SearchCriteria;
import kr.yuhan.domain.YuhanHomeworkVO;

public interface ElasticService {
	public String ProfessorName(YuhanHomeworkVO vo); //교수이름
	public String SubjectName(YuhanHomeworkVO vo); //과목이름
	
	public void putElastic(ElasticVO elvo, Gson gson); //엘라스틱 서치에 데이터 적재
	public List<GetElasticSearchVo> getElastic(ElasticVO elvo); // 과제 목록의 리스트를 가져옴
	public List<GetElasticSearchVo> getElasticCriteria(SearchCriteria criteria); // 과제 목록의 리스트를 가져옴
	public List<GetElasticSearchVo> readElastic(String _id); // 상세 View의 내용을 가져옴
	public String UpdateElastic(GetElasticSearchVo elaSearchVO);
	public void deleteElastic(String _id);
}
