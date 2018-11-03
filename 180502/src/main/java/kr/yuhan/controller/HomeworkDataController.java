package kr.yuhan.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import kr.yuhan.domain.ElasticVO;
import kr.yuhan.domain.GetElasticSearchVo;
import kr.yuhan.domain.GetSourceVo;
import kr.yuhan.domain.ReportVO;
import kr.yuhan.domain.SearchCriteria;
import kr.yuhan.domain.YuhanHomeworkVO;
import kr.yuhan.service.ElasticService;
import kr.yuhan.service.YuhanHomeworkService;

@RestController
@RequestMapping("/homeworkdata")
public class HomeworkDataController {

	@Inject
	private YuhanHomeworkService service;
	
	@Inject
	private ElasticService elservice;
	
	@RequestMapping(value="/addhw" , method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public ResponseEntity<?> register(@RequestBody YuhanHomeworkVO vo) throws ParseException {
		ResponseEntity<?> entity;
		Gson gson = new GsonBuilder().setPrettyPrinting().create(); 
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.ENGLISH); // DB, 엘라스틱 서치에 올릴 시작 날짜, 끝날짜
		SimpleDateFormat formatTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH); // DB에 올릴 날짜
		SimpleDateFormat ElasticFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH); //엘라스틱 서치에 적재될 올린 날짜 타입 (List부분)
		
		Date start = format.parse(vo.getStart());
		Date end = format.parse(vo.getEnd()); // 웹 부분에서 넘어온 시작 날짜와 끝 날짜를 Date형식으로 변환해줌 (DB)
		Date today = new Date(System.currentTimeMillis()); // 현재 시간을 저장 
		String todayString = formatTime.format(today); // 현재 시간을 String 형식으로 변환
	
		vo.setTotalStart(start);
		vo.setTotalEnd(end);
		vo.setToday(today);
		vo.setFormatToday(todayString); //vo에 setting
		//System.out.println("adfaf : " + vo.getSubjectClass());
		
		/********** Elastic Search에 필요한 데이터 변환  **********/
		String proName = elservice.ProfessorName(vo); // 교수 이름
		String subjectName = elservice.SubjectName(vo); // 과목 이름
		
		String Date = ElasticFormat.format(today); // List에 보여질 날짜 형식
		String DetailDate = format.format(today); // 상세 View에 보여질 날짜 형식
		String ElaStart = format.format(start); // 상세 View에 보여질 시작 날짜 형식
		String ElaEnd = format.format(end); // 상세 View에 보여질 끝 날짜 형식
		
		String tmp = vo.getContent();
		String content = tmp.trim();
		
		ElasticVO elvo = new ElasticVO(vo.getTitle(), Date, DetailDate, ElaStart, ElaEnd, content, subjectName, proName, vo.getSubjectClass());

		try {
			service.insertHomework(vo);
			elservice.putElastic(elvo, gson);
			entity = new ResponseEntity<Object>(service.selectHwno(vo), HttpStatus.OK);
		}catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	
		return entity;
	}
	
	@RequestMapping(value="/updatehw" , method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public ResponseEntity<?> updatehw(@RequestBody YuhanHomeworkVO vo , SearchCriteria cri, RedirectAttributes rttr) throws ParseException {
		ResponseEntity<?> entity;
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.ENGLISH); // DB, 엘라스틱 서치에 올릴 시작 날짜, 끝날짜
		SimpleDateFormat formatTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH); // DB에 올릴 날짜
		SimpleDateFormat ElasticFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH); //엘라스틱 서치에 적재될 올린 날짜 타입 (List부분)
		
		Date start = format.parse(vo.getStart());
		Date end = format.parse(vo.getEnd()); // 웹 부분에서 넘어온 시작 날짜와 끝 날짜를 Date형식으로 변환해줌 (DB)
		Date today = new Date(System.currentTimeMillis()); // 현재 시간을 저장 
		String todayString = formatTime.format(today); // 현재 시간을 String 형식으로 변환
		
		vo.setTotalStart(start);
		vo.setTotalEnd(end);
		vo.setToday(today);
		vo.setFormatToday(todayString);
	
		/** Elastic Search Update **/
		String Date = ElasticFormat.format(today); // List에 보여질 날짜 형식
		String DetailDate = format.format(today); // 상세 View에 보여질 날짜 형식
		String ElaStart = format.format(start); // 상세 View에 보여질 시작 날짜 형식
		String ElaEnd = format.format(end); // 상세 View에 보여질 끝 날짜 형식
		
		GetSourceVo source = new GetSourceVo(vo.getTitle(), ElaStart, ElaEnd, vo.getContent());
		
		GetElasticSearchVo elvo = new GetElasticSearchVo(vo.get_id(), source);
		
		//System.out.println("여기까지?");
		try {
			service.updateHomework(vo);
			elservice.UpdateElastic(elvo);
			//System.out.println("성공");
			entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
		}catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
	
	@RequestMapping(value="/addReport" , method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public ResponseEntity<?> addReort(@RequestBody ReportVO vo){
		
		ResponseEntity<?> entity;
		Gson gson = new GsonBuilder().setPrettyPrinting().create(); 

		try {
			service.insertReport(vo);
			entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
		}catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		
		return entity;
	}
	
	@RequestMapping(value="/reportInfo" , method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public ResponseEntity<?> reportInfo(@RequestBody ReportVO vo){
		
		ResponseEntity<?> entity;
		Gson gson = new GsonBuilder().setPrettyPrinting().create(); 

		System.out.println("과제 제출 정보");
		try {
			entity = new ResponseEntity<Object>(service.selectReportInfo(vo), HttpStatus.OK);
		}catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		
		return entity;
	}
}
