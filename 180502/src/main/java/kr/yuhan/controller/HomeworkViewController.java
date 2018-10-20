package kr.yuhan.controller;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.core.annotation.SynthesizedAnnotation;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import kr.yuhan.domain.Criteria;
import kr.yuhan.domain.ElasticVO;
import kr.yuhan.domain.ViewPageParamVo;
import kr.yuhan.domain.YuhanHomeworkVO;
import kr.yuhan.service.ElasticService;
import kr.yuhan.service.YuhanFileService;
import kr.yuhan.service.YuhanHomeworkService;
import kr.yuhan.service.YuhanMemberCheckService;

@Controller
public class HomeworkViewController {
	
	@Inject
	private YuhanHomeworkService service;
	
	@Inject
	private ElasticService elservice;
	
	@Inject
	private YuhanMemberCheckService memberService;
	
	@Inject
	private YuhanFileService fileService;
	
	Gson gson = new GsonBuilder().disableHtmlEscaping().setPrettyPrinting().create();
	
	@RequestMapping(value="/hwRegister", method = RequestMethod.GET)
	public void hwRegister(@RequestParam ("subjectID") int subjectID, Model model, HttpSession session) 
	{
		System.out.println("글 등록  과목 번호 : " +subjectID);
		
		model.addAttribute("loginMemberList", memberService.select_Member(session.getAttribute("sessionID").toString())); //메뉴바 접속 회원 이름 출력 서비스
		
		model.addAttribute("subjectID", subjectID);
	}
	
	@RequestMapping(value="/hwread", method = {RequestMethod.GET})
	public void hwread(@RequestParam ("_id") String _id, @RequestParam("hwno") int hwno, @RequestParam("subjectID") int subjectID, Model model, HttpServletRequest request, HttpSession session){
		System.out.println(hwno);
		model.addAttribute("subjectID", subjectID);
		
		if(fileService.fileCount(hwno) >= 1) { //첨부파일의 유무를 확인해주는 함수가 선언되어야한다.
			System.out.println("파일이 있습니다.");
			for (int i = 0; i < fileService.selectFileInfo(hwno).size(); i++) {
				System.out.println(fileService.selectFileInfo(hwno).get(i).getFileName());
			}
			
			model.addAttribute("file", fileService.selectFileInfo(hwno));
		}else {
			model.addAttribute("file", "첨부 파일이 없습니다.");
		}

		//fileService.selectDataStatus(hwno);
		model.addAttribute("hwno", hwno);
		model.addAttribute("_id", _id);
		model.addAttribute("elastic", elservice.readElastic(_id).get(0).get_source());
	}
	
	@RequestMapping(value="/hwList", method = RequestMethod.GET)
	public void hwList(@RequestParam("subjectID") int subjectID, Model model, HttpSession session, HttpServletRequest request, @ModelAttribute("cri") Criteria cri) {

		//메뉴바 접속 회원 이름 출력 서비스
		model.addAttribute("loginMemberList", memberService.select_Member(session.getAttribute("sessionID").toString()));
		
		String rate = (String) session.getAttribute("Rate"); //학생인지 교수인지 판별해주는 변수
		String memberClass = (String) session.getAttribute("memberClass"); //학생의 반을 저장
		System.out.println("memberClass : " + memberClass);

		String sessionID = (String) session.getAttribute("sessionID");
			
		YuhanHomeworkVO vo = new YuhanHomeworkVO();
		vo.setSessionID(sessionID);
		vo.setSubjectID(subjectID);
		vo.setSubjectID(subjectID);
		vo.setSubjectClass(memberClass); //회원 아이디와, 과목번호, 반으로 학생의 해당 과목의 과제 목록을 불러온다. //교수의 경우 해당 반을 선택해서 들어올 수 있게 구현한다.(후에 구현)
			
		model.addAttribute("subjectID", subjectID);
		System.out.println(subjectID);
		model.addAttribute("subjectList", service.selectSubjectData(Integer.toString(subjectID))); //과목 정보 출력 서비스
			
		model.addAttribute("hw", service.listHomework(vo)); //글 번호를 알기위해. hwread 페이지의 파라미터로 넘겨줄것임.
		System.out.println(service.listHomework(vo).get(0).getHwno());

		/** 밑 부분 검색 엔진 사용  **/
		String professorName = service.listHomeworkEla(String.valueOf(subjectID)).getProfessor(); // 검색엔진에서는 교수이름, 과목 이름, 학생의 반으로 리스트를 가져옴
		System.out.println("교수 이름 : " + professorName);
		String subjectName = service.listHomeworkEla(String.valueOf(subjectID)).getSubject();
		System.out.println("과목 이름 : " + subjectName);
			
		ElasticVO elvo = new ElasticVO(subjectName, professorName, memberClass); // Vo에 저장
			
		model.addAttribute("elastic", elservice.getElastic(elvo)); //반환반은 list를 화면에 뿌려준다.
		System.out.println(elservice.getElastic(elvo).get(0));
	}

	
	@RequestMapping(value="/hwPersonalList", method = RequestMethod.GET) /** 학생,교수 개인의 수강,담당 과목 **/
	public void hwPersonalList(Model model, HttpSession session, RedirectAttributes redir){
		
		model.addAttribute("loginMemberList", memberService.select_Member(session.getAttribute("sessionID").toString())); //메뉴바 접속 회원 이름 출력 서비스
		
		String rate = (String) session.getAttribute("Rate"); //학생과 교수를 구분
		String memberClass = (String) session.getAttribute("memberClass"); // 만약 학생이라면 반을 가져옴.
		
		String sessionNum; 
		String sessionID;
		
		System.out.println("rate : " + rate);
		model.addAttribute("rate", rate);
		
		sessionID = (String ) session.getAttribute("sessionID"); // 학생의 개인 과목 목록을 가져올 땐, 아이디를 사용하여 수강 테이블에서 검색한다.
		System.out.println(sessionID);

		model.addAttribute("sessionID", sessionID);
			
		model.addAttribute("hw", service.readClass(sessionID)); //학생 개인의 과목 목록을 가져옴

		System.out.println("과목 이름 확인 : " + service.readClass(sessionID).get(0).getSubjectName() + "  " + service.readClass(sessionID).get(1).getSubjectName());

	}
	
	@RequestMapping(value="/hwUpdate", method = RequestMethod.GET)
	public String hwUpdate(@RequestParam ("_id") String _id, @RequestParam("hwno") int hwno, @RequestParam("subjectID") int subjectID, Model model, HttpServletRequest request) throws ParseException{
		System.out.println("글 수정");
		System.out.println(hwno);

		model.addAttribute("_id", _id);
		model.addAttribute("hwno", hwno);
		model.addAttribute("subjectID", subjectID);
		
		model.addAttribute("elastic", elservice.readElastic(_id).get(0).get_source());
		
		String elstart = elservice.readElastic(_id).get(0).get_source().getStartdate();
		String[] array = elstart.split(" ");
		
		String elend = elservice.readElastic(_id).get(0).get_source().getEnddate();
		String[] array2 = elend.split(" ");
		
		model.addAttribute("start", array[0]);
		model.addAttribute("startTime", array[1]);
		model.addAttribute("end", array2[0]);
		model.addAttribute("endTime", array2[1]);
		return null;
	}
	
	@RequestMapping(value="/hwDelete", method = RequestMethod.GET)
	public String hwDelete(@RequestParam ("_id") String _id, @RequestParam("hwno") int hwno, @RequestParam("subjectID") int subjectID){
		System.out.println("글 삭제");
		service.deleteHomework(hwno);
		elservice.deleteElastic(_id);
		
		return "redirect:/hwList?subjectID="+subjectID;
	}
}
