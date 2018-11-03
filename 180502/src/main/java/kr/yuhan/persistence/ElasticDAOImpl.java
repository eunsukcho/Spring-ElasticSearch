package kr.yuhan.persistence;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import kr.yuhan.domain.ElasticVO;
import kr.yuhan.domain.GetElasticSearchVo;
import kr.yuhan.domain.SearchCriteria;
import kr.yuhan.domain.YuhanHomeworkVO;
import net.sf.json.JSONObject;

@Repository
public class ElasticDAOImpl implements ElasticDAO{

	@Inject
	private SqlSession sqlSession;
	static final String NAMESPACE = "kr.yuhan.mapper.ElasticMapper";
	JsonObject jsonobject;
	URL url;
	HttpURLConnection urlconnection;
	OutputStream os;
	BufferedReader br;
	BufferedWriter bw;
	JSONObject jsonObject;

	@Override
	public String ProfessorName(YuhanHomeworkVO vo) {
		return sqlSession.selectOne(NAMESPACE + ".professorName", vo);
	}

	@Override
	public String SubjectName(YuhanHomeworkVO vo) {
		return sqlSession.selectOne(NAMESPACE + ".subjectName", vo);
	}

	@Override
	public void putElastic(ElasticVO elvo, Gson gson){
		
		try {
			url = new URL("http://124.5.189.204:32110/yuhan_homework/yuhan_homework");
			urlconnection = (HttpURLConnection) url.openConnection();
			urlconnection.setRequestMethod("POST");
			
			urlconnection.setRequestProperty("Accept", "application/json");
			urlconnection.setRequestProperty("Content-Type", "application/json");
			urlconnection.setRequestProperty("Content-Length", "length"); 
			
			urlconnection.setDoInput(true);
			urlconnection.setDoOutput(true);
			
			System.out.println(gson.toJson(elvo));
			os = urlconnection.getOutputStream();
			os.write(gson.toJson(elvo).getBytes("UTF-8"));
			os.flush();
			os.close();

			int responseCode = urlconnection.getResponseCode();
			System.out.println(responseCode);
			
			if(responseCode == 201) {
				System.out.println("성공입니다.");
			}else {
				System.out.println("실패입니다.");
				InputStream is = urlconnection.getInputStream();
	            br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
	            String line;
	
	            while ((line = br.readLine()) != null) {
	                System.out.println(line);
	            }
			}
		}catch (IOException e) {
            System.out.println("Error : ");
            e.printStackTrace();
        }

	}

	@Override
	public List<GetElasticSearchVo> getElastic(ElasticVO elvo) {
		List<GetElasticSearchVo> sourceList = new ArrayList<GetElasticSearchVo>();
		Gson gson = new GsonBuilder().disableHtmlEscaping().setPrettyPrinting().create();
		
		try {
			url = new URL("http://124.5.189.204:32110/yuhan_homework/yuhan_homework/_search");
			urlconnection = (HttpURLConnection) url.openConnection();
			
			urlconnection.setRequestMethod("POST");
			
			urlconnection.setRequestProperty("Accept", "application/json");
			urlconnection.setRequestProperty("Content-Type", "application/json");
			urlconnection.setRequestProperty("Content-Length", "length");
			
			urlconnection.setDoInput(true);
			urlconnection.setDoOutput(true);
            
			String query = "{\"query\": { \"bool\":{ \"must\" : [ {\"match\" : {\"professor\":\""+elvo.getProfessor().trim()+ "\"}}, {\"match\" : {\"subject\":\""+elvo.getSubject()+ "\"}},{\"match\" : {\"subjectclass\":\""+elvo.getSubjectclass()+ "\"}}]}},\"sort\":[{\"DetailDate\" : {\"order\" : \"desc\"}}]}"; 
            System.out.println(query);
            
			os = urlconnection.getOutputStream();
			os.write(query.getBytes("UTF-8"));
			os.flush();
			os.close();
			
			int responseCode = urlconnection.getResponseCode();
            System.out.println(responseCode);
            
            if(responseCode == 200){
                InputStream is = urlconnection.getInputStream();
                BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
                String line;
                String resultLine = "";
                
                System.out.println("조은숙조은숙조은숙");
                while((line = br.readLine()) != null){
                	resultLine += line;
                	//System.out.println(line);
                }
                System.out.println("조은숙조은숙조은숙");
                
                sourceList = gson.fromJson(gson.toJson(JSONObject.fromObject(JSONObject.fromObject(gson.fromJson(resultLine, Object.class)).get("hits")).getJSONArray("hits")), new TypeToken<List<GetElasticSearchVo>>() {}.getType());
                
            } else {
				System.out.println("실패입니다.");
				InputStream is = urlconnection.getInputStream();
	            br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
	            String line;
	
	            while ((line = br.readLine()) != null) {
	                //System.out.println(line);
	            }
			}
		}catch (IOException e) {
			System.out.println("Error : ");
            e.printStackTrace();
		}
		
		return sourceList;
	}

	@Override
	public List<GetElasticSearchVo> getElasticCriteria(SearchCriteria criteria) {
		List<GetElasticSearchVo> sourceList = new ArrayList<GetElasticSearchVo>();
		GetElasticSearchVo elvo =  new GetElasticSearchVo();
		Gson gson = new GsonBuilder().disableHtmlEscaping().setPrettyPrinting().create();
		int totalCount;
		String query = "";
		String searchType = criteria.getSearchType();
		System.out.println("dao SearchType : " + searchType);
		String keyword = criteria.getKeyword();
		
		try {
			url = new URL("http://124.5.189.204:32110/yuhan_homework/yuhan_homework/_search");
			urlconnection = (HttpURLConnection) url.openConnection();
			urlconnection.setRequestMethod("POST");
			urlconnection.setRequestProperty("Accept", "application/json");
			urlconnection.setRequestProperty("Content-Type", "application/json");
			urlconnection.setRequestProperty("Content-Length", "length");
			urlconnection.setDoInput(true);
			urlconnection.setDoOutput(true);
            
			if(searchType == null) {
				System.out.println("null query");
				query = "{\"query\": { \"bool\":{ \"must\" : [{\"match\" : {\"professor\":\""+criteria.getElasticVO().getProfessor().trim()+"\"}},{\"match\" : {\"subject\":\""+criteria.getElasticVO().getSubject()+"\"}},{\"match\" : {\"subjectclass\":\""+criteria.getElasticVO().getSubjectclass()+"\"}}]}}, \"sort\":[{\"DetailDate\" : {\"order\" : \"desc\"}}], \"size\":\"" + criteria.getPerPageNum()+ "\", \"from\" : " + criteria.getPageStart() + "}";
			}
			else if(searchType.equals("title")) {
				System.out.println("title query");
				query = "{\"query\": { \"bool\":{ \"must\" : [{\"match\" : {\"professor\":\""+criteria.getElasticVO().getProfessor().trim()+"\"}}, {\"match\" : {\"subject\":\""+criteria.getElasticVO().getSubject()+"\"}}, {\"match\" : {\"subjectclass\":\""+criteria.getElasticVO().getSubjectclass()+"\"}},{\"match\" : {\"title\":\""+keyword+"\"}}]}}, \"sort\":[{\"DetailDate\" : {\"order\" : \"desc\"}}], \"size\" : \"" + criteria.getPerPageNum()+ "\" , \"from\" : " + criteria.getPageStart() + "}"; 
			}
			else if(searchType.equals("content")) {
				System.out.println("content query");
				query = "{\"query\": { \"bool\":{ \"must\" : [{\"match\" : {\"professor\":\""+criteria.getElasticVO().getProfessor().trim()+"\"}}, {\"match\" : {\"subject\":\""+criteria.getElasticVO().getSubject()+"\"}}, {\"match\" : {\"subjectclass\":\""+criteria.getElasticVO().getSubjectclass()+"\"}}, {\"match\" : {\"content\":\""+keyword+"\"}}]}}, \"sort\":[{\"DetailDate\" : {\"order\" : \"desc\"}}], \"size\" : \"" + criteria.getPerPageNum()+ "\" , \"from\" : " + criteria.getPageStart() + "}"; 
			}

			System.out.println("페이지 쿼리 : " + query);
			os = urlconnection.getOutputStream();
			os.write(query.getBytes("UTF-8"));
			os.flush();
			os.close();
			
			int responseCode = urlconnection.getResponseCode();
            System.out.println(responseCode);
            
            if(responseCode == 200){
                InputStream is = urlconnection.getInputStream();
                BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
                String line;
                String resultLine = "";
                
                //System.out.println("조은숙조은숙조은숙");
                while((line = br.readLine()) != null){
                	resultLine += line;
                	//System.out.println(line);
                }
               //System.out.println("조은숙조은숙조은숙");

                totalCount = JSONObject.fromObject(JSONObject.fromObject(gson.fromJson(resultLine, Object.class)).get("hits")).getInt("total");
                sourceList = gson.fromJson(gson.toJson(JSONObject.fromObject(JSONObject.fromObject(gson.fromJson(resultLine, Object.class)).get("hits")).getJSONArray("hits")), new TypeToken<List<GetElasticSearchVo>>() {}.getType());

                elvo.setTotalCount(totalCount);
                
                sourceList.add(elvo);
                
                //System.out.println("제목 : " + sourceList.get(0).get_source().getTitle());
                //System.out.println("개수 : " + sourceList.get(sourceList.size()-1).getTotalCount());
            } else {
				System.out.println("실패입니다.");
				InputStream is = urlconnection.getInputStream();
	            br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
	            String line;
	
	            while ((line = br.readLine()) != null) {
	                //System.out.println(line);
	            }
			}
		}catch (IOException e) {
			System.out.println("Error : ");
            e.printStackTrace();
		}
		
		return sourceList;
	}
	
	@Override
	public List<GetElasticSearchVo> readElastic(String _id) {
		List<GetElasticSearchVo> sourceList = new ArrayList<GetElasticSearchVo>();
		Gson gson = new GsonBuilder().disableHtmlEscaping().setPrettyPrinting().create();
		
		try {
			url = new URL("http://124.5.189.204:32110/yuhan_homework/yuhan_homework/_search");
			urlconnection = (HttpURLConnection) url.openConnection();
			
			urlconnection.setRequestMethod("POST");
			
			urlconnection.setRequestProperty("Accept", "application/json");
			urlconnection.setRequestProperty("Content-Type", "application/json");
			urlconnection.setRequestProperty("Content-Length", "length");
			
			urlconnection.setDoInput(true);
			urlconnection.setDoOutput(true);
            
			String query = "{\"query\": {\"match\":{\"_id\":\""+_id+"\"}}, \"_source\":[\"title\" , \"DetailDate\", \"startdate\", \"enddate\", \"content\", \"professor\", \"subject\"]}";
            System.out.println(query);
            
			os = urlconnection.getOutputStream();
			os.write(query.getBytes("UTF-8"));
			os.flush();
			os.close();
			
			int responseCode = urlconnection.getResponseCode();
            System.out.println(responseCode);
            
            if(responseCode == 200){
                InputStream is = urlconnection.getInputStream();
                BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
                String line;
                String resultLine = "";
                
                while((line = br.readLine()) != null){
                	resultLine += line;
                	//System.out.println(line);
                }
                sourceList = gson.fromJson(gson.toJson(JSONObject.fromObject(JSONObject.fromObject(gson.fromJson(resultLine, Object.class)).get("hits")).getJSONArray("hits")), new TypeToken<List<GetElasticSearchVo>>() {}.getType());
                
            } else {
				System.out.println("실패입니다.");
				InputStream is = urlconnection.getInputStream();
	            br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
	            String line;
	
	            while ((line = br.readLine()) != null) {
	                System.out.println(line);
	            }
			}
		}catch (IOException e) {
			System.out.println("Error : ");
            e.printStackTrace();
		}
		
		return sourceList;
	}

	@Override
	public String UpdateElastic(GetElasticSearchVo elaSearchVO) {
		List<GetElasticSearchVo> sourceList = new ArrayList<GetElasticSearchVo>();
		Gson gson = new GsonBuilder().disableHtmlEscaping().setPrettyPrinting().create();
		String urlquery = "http://124.5.189.204:32110/yuhan_homework/yuhan_homework/"+ elaSearchVO.get_id() +"/_update";
		System.out.println(urlquery);
		
		try {
			url = new URL(urlquery);
			urlconnection = (HttpURLConnection) url.openConnection();
			
			urlconnection.setRequestMethod("POST");
			
			urlconnection.setRequestProperty("Accept", "application/json");
			urlconnection.setRequestProperty("Content-Type", "application/json");
			urlconnection.setRequestProperty("Content-Length", "length");
			
			urlconnection.setDoInput(true);
			urlconnection.setDoOutput(true);
            
			String query = "{\"doc\":{\"title\":\""+elaSearchVO.get_source().getTitle()+"\", \"startdate\":\""+elaSearchVO.get_source().getStartdate()+"\",\"enddate\":\""+elaSearchVO.get_source().getEnddate()+"\", \"content\":\""+elaSearchVO.get_source().getContent().trim()+"\" }}";
  
			System.out.println(query);

			os = urlconnection.getOutputStream();
			os.write(query.getBytes("UTF-8"));
			os.flush();
			os.close();
			
			int responseCode = urlconnection.getResponseCode();
            System.out.println("updateResultCode : " + responseCode);
            
            if(responseCode == 200){
                InputStream is = urlconnection.getInputStream();
                BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
                String line;
                String resultLine = "";
                
                System.out.println("Update");
                while((line = br.readLine()) != null){
                	resultLine += line;
                	//System.out.println(line);
                }
                
            } else {
				System.out.println("실패입니다.");
				InputStream is = urlconnection.getInputStream();
	            br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
	            String line;
	
	            while ((line = br.readLine()) != null) {
	                System.out.println(line);
	            }
			}
		}catch (IOException e) {
			System.out.println("Error : ");
            e.printStackTrace();
		}
		
		return "success";
	}

	@Override
	public void deleteElastic(String _id) {
		String urlquery = "http://124.5.189.204:32110/yuhan_homework/yuhan_homework/"+_id;
		System.out.println(urlquery);
		
		try {
			url = new URL(urlquery);
			urlconnection = (HttpURLConnection) url.openConnection();
			
			urlconnection.setRequestMethod("DELETE");
			
			int responseCode = urlconnection.getResponseCode();
            System.out.println("updateResultCode : " + responseCode);
            
            if(responseCode == 200){
                InputStream is = urlconnection.getInputStream();
                BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
                String line;
                String resultLine = "";
                
                System.out.println("Delete");
                while((line = br.readLine()) != null){
                	resultLine += line;
                	System.out.println(line);
                }

            } else {
				System.out.println("실패입니다.");
				InputStream is = urlconnection.getInputStream();
	            br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
	            String line;
	
	            while ((line = br.readLine()) != null) {
	                System.out.println(line);
	            }
			}
		}catch (IOException e) {
			System.out.println("Error : ");
            e.printStackTrace();
		}
	}

	
}



