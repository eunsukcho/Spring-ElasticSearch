package kr.yuhan.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import kr.yuhan.domain.ElasticVO;
import kr.yuhan.domain.GetElasticSearchVo;
import kr.yuhan.domain.PageMaker;
import kr.yuhan.domain.ReplyCriteria;
import kr.yuhan.domain.ReplyVO;
import kr.yuhan.domain.ReportVO;
import kr.yuhan.domain.SearchCriteria;
import kr.yuhan.domain.YuhanClass;
import kr.yuhan.domain.YuhanHomeworkVO;
import kr.yuhan.domain.YuhanSubjectVO;
import kr.yuhan.service.ElasticService;
import kr.yuhan.service.ReplyService;
import kr.yuhan.service.YuhanFileService;
import kr.yuhan.service.YuhanHomeworkService;
import kr.yuhan.service.YuhanMemberCheckService;
import kr.yuhan.service.YuhanReportService;

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
	
	@Inject
	private YuhanReportService reportService;
	
	@Inject
	private ReplyService reService;
	
	Gson gson = new GsonBuilder().disableHtmlEscaping().setPrettyPrinting().create();
	
	@RequestMapping(value="/hwRegister", method = RequestMethod.GET)
	public void hwRegister(@RequestParam ("subjectID") int subjectID, Model model, HttpSession session, HttpServletRequest request) 
	{
		//System.out.println("글 등록  과목 번호 : " +subjectID);
		
		model.addAttribute("loginMemberList", memberService.select_Member(session.getAttribute("sessionID").toString())); //메뉴바 접속 회원 이름 출력 서비스
		model.addAttribute("subjectID", subjectID);
		model.addAttribute("professorNo", session.getAttribute("professorNum"));
		model.addAttribute("proName", session.getAttribute("proName"));
		model.addAttribute("selectClass", request.getParameter("selectClass"));
	}
	
	@RequestMapping(value="/hwread", method = {RequestMethod.GET})
	public void hwread( @RequestParam("hwno") int hwno, @RequestParam("subjectID") int subjectID, 
			@ModelAttribute("cri") SearchCriteria cri, @ModelAttribute("recri") ReplyCriteria recri, 
			HttpSession session, HttpServletRequest request, Model model){
		String _id = request.getParameter("_id");
		if(_id == null) {
			System.out.println("마이페이지에서 넘어왔습니다.");
		}
		model.addAttribute("loginMemberList", memberService.select_Member(session.getAttribute("sessionID").toString())); //메뉴바 접속 회원 이름 출력 서비스
		List<YuhanSubjectVO> list = service.selectSubjectData(Integer.toString(subjectID));
		String selectClass = "";
		String studentID = "";
		int reportCount;
		int reportContentCount;
		System.out.println("교수번호 : " + list.get(0).getYUHAN_SUBJECT_PRO());
		System.out.println("교수이름 : " + list.get(0).getProName());
		
		System.out.println(hwno);
		model.addAttribute("subjectID", subjectID); // 해당 과목 번호
		model.addAttribute("hwno", hwno); // 게시글의 고유 번호
		model.addAttribute("_id", _id); // 게시글의 고유 id값 
		model.addAttribute("elastic", elservice.readElastic(_id).get(0).get_source()); //상세 View의 과제 내용을 출력
		model.addAttribute("professorNo", list.get(0).getYUHAN_SUBJECT_PRO()); // 해당 과목 교수 번호
		model.addAttribute("proName", list.get(0).getProName()); //해당 과목 교수 이름
		model.addAttribute("selectClass", request.getParameter("selectClass"));

		String rate = (String) session.getAttribute("Rate"); //학생인지 교수인지 판별해주는 변수
		if(rate.equals("S")) { // 학생일 경우
			selectClass = (String) session.getAttribute("memberClass"); //학생의 반을 저장
			studentID = (String) session.getAttribute("sessionID"); // 로그인한 학생의 과제 제출 여부를 확인하기 위해
			model.addAttribute("studentID", studentID); 
			reportCount = fileService.ReportCount(hwno, studentID); // 학생이 해당 과제에 파일을 제출했을 경우
			if(reportCount >= 1) {
				System.out.println("학생의 과제 파일 제출 : " + reportCount);
				model.addAttribute("reportFile", fileService.selectReportFileInfo(hwno, studentID));
			}else {
				model.addAttribute("reportFile", "제출 파일이 없습니다.");
			}
			
			reportContentCount = reportService.reportContentCount(hwno, studentID); //학생이 파일을 제외한 글로 과제를 올렸을 때
			model.addAttribute("report", reportContentCount);
			model.addAttribute("replyCount", reService.count(hwno, studentID));
	
		}else { // 교수일경우
			selectClass = request.getParameter("selectClass");
		}
		model.addAttribute("rate", rate);
		model.addAttribute("selectClass", selectClass);

		if(fileService.fileCount(hwno) >= 1) { //첨부파일의 유무를 확인해주는 함수가 선언되어야한다. (교수가 올린 참고자료) 
			model.addAttribute("file", fileService.selectFileInfo(hwno));
		}else {
			model.addAttribute("file", "첨부 파일이 없습니다.");
		}
	}
	
	@RequestMapping(value="/hwReport", method = RequestMethod.GET)
	public void hwReport(HttpSession session, HttpServletRequest request, @RequestParam("subjectID") int subjectID, @ModelAttribute("cri") SearchCriteria cri, Model model) {
		List<YuhanSubjectVO> list = service.selectSubjectData(Integer.toString(subjectID));
		String selectClass = "";
		String studentID = "";
		System.out.println("교수번호 : " + list.get(0).getYUHAN_SUBJECT_PRO());
		System.out.println("교수이름 : " + list.get(0).getProName());
		
		List<GetElasticSearchVo> searchList = elservice.readElastic(request.getParameter("_id"));

		model.addAttribute("professorNo", list.get(0).getYUHAN_SUBJECT_PRO());
		model.addAttribute("_id", request.getParameter("_id"));
		model.addAttribute("hwno", request.getParameter("hwno"));
		model.addAttribute("subjectID", subjectID);
		model.addAttribute("page", cri.getPage());
		model.addAttribute("perPageNum", cri.getPerPageNum());
		model.addAttribute("searchType", cri.getSearchType());
		model.addAttribute("keyword", cri.getKeyword());
		model.addAttribute("elastic", searchList.get(0).get_source());
		model.addAttribute("studentID", session.getAttribute("sessionID")); 
		model.addAttribute("selectClass", (String) session.getAttribute("memberClass"));
		
		System.out.println("리포트 : " + session.getAttribute("sessionID"));
		
		String elstart = searchList.get(0).get_source().getStartdate();
		String[] array = elstart.split(" ");
		
		String elend = searchList.get(0).get_source().getEnddate();
		String[] array2 = elend.split(" ");
		
		model.addAttribute("start", array[0]);
		model.addAttribute("startTime", array[1]);
		model.addAttribute("end", array2[0]);
		model.addAttribute("endTime", array2[1]);
	}
	
	@RequestMapping(value="/hwReportCheck", method = RequestMethod.GET)
	public void hwReportCheck(HttpSession session, HttpServletRequest request, @RequestParam("subjectID") int subjectID, @ModelAttribute("cri") SearchCriteria cri, Model model) {
		System.out.println("과제 체크 : " + request.getParameter("reportNo"));
		int no = Integer.parseInt(request.getParameter("reportNo"));
		List<GetElasticSearchVo> searchList = elservice.readElastic(request.getParameter("_id"));
		List<YuhanSubjectVO> list = service.selectSubjectData(Integer.toString(subjectID));
		ReportVO vo = reportService.reportDetailView(no);
		System.out.println("숫자로 변환 : " + no);
		String selectClass = "";
		String studentID = "";
		System.out.println("교수번호 : " + list.get(0).getYUHAN_SUBJECT_PRO());
		System.out.println("교수이름 : " + list.get(0).getProName());

		model.addAttribute("professorNo", list.get(0).getYUHAN_SUBJECT_PRO()); 
		model.addAttribute("proName", list.get(0).getProName());
		model.addAttribute("_id", request.getParameter("_id"));
		model.addAttribute("hwno", request.getParameter("hwno"));
		model.addAttribute("subjectID", subjectID);
		model.addAttribute("page", cri.getPage());
		model.addAttribute("perPageNum", cri.getPerPageNum());
		model.addAttribute("searchType", cri.getSearchType());
		model.addAttribute("keyword", cri.getKeyword());
		model.addAttribute("elastic", searchList.get(0).get_source());
		model.addAttribute("studentID", vo.getStudentID()); 
		model.addAttribute("reportNo", no);
		model.addAttribute("selectClass", request.getParameter("selectClass"));
		
		model.addAttribute("reportInfo", vo);
		
		System.out.println("제출 내용 : " + vo.getContent());
		System.out.println("제출 아이디 : " + vo.getStudentID());
		System.out.println("제출 시간 : " + vo.getReportDate());
		
		int reportCount = fileService.ReportCount(vo.getHomeworkNo(), vo.getStudentID()); // 학생이 해당 과제에 파일을 제출했을 경우
		if(reportCount >= 1) {
			System.out.println("학생의 과제 파일 제출 : " + reportCount);
			model.addAttribute("reportFile", fileService.selectReportFileInfo(vo.getHomeworkNo(), vo.getStudentID()));
		}else {
			model.addAttribute("reportFile", "제출 파일이 없습니다.");
		}
	}
	
	@RequestMapping(value="/hwList", method = RequestMethod.GET)
	public void hwList(@RequestParam("subjectID") int subjectID, @ModelAttribute("cri") SearchCriteria cri, Model model, HttpSession session, HttpServletRequest request) {
		String selectClass = "";
		int professorNum;
		String sessionID = ""; // 학생 ID
		
		model.addAttribute("loginMemberList", memberService.select_Member(session.getAttribute("sessionID").toString())); //메뉴바 접속 회원 이름 출력 서비스
		model.addAttribute("subjectList", service.selectSubjectData(Integer.toString(subjectID))); //과목 정보 출력 서비스
		model.addAttribute("subjectID", subjectID);
		
		String rate = (String) session.getAttribute("Rate"); //학생인지 교수인지 판별해주는 변수
		model.addAttribute("rate", rate);
		if(rate.equals("S")) {
			selectClass = (String) session.getAttribute("memberClass"); //학생의 반을 저장
			sessionID = (String) session.getAttribute("sessionID");
			YuhanHomeworkVO vo = new YuhanHomeworkVO(subjectID, selectClass, sessionID); //회원 아이디와, 과목번호, 반으로 학생의 해당 과목의 과제 목록을 불러온다. 
			model.addAttribute("hw", service.listHomework(vo)); //글 번호를 알기위해. hwread 페이지의 파라미터로 넘겨줄것임.
		}else {
			professorNum = Integer.parseInt((String) session.getAttribute("professorNum")); // 접속 교수 번호
			System.out.println("파라미터 반 : " + request.getParameter("selectClass"));
			selectClass = request.getParameter("selectClass"); // 교수가 클릭한 반
			YuhanHomeworkVO provo = new YuhanHomeworkVO(selectClass, professorNum, subjectID); //교수의 경우 해당 반을 선택해서 들어올 수 있게 구현한다.
			model.addAttribute("hw", service.listProfessorHomework(provo));
		} 
		model.addAttribute("selectClass", selectClass);
		System.out.println("selectClass : " + selectClass);

		System.out.println("과목 번호 :" + subjectID);

		//System.out.println("글 번호 :"+service.listHomework(vo).get(0).getHwno());

		/** 밑 부분 검색 엔진 사용 (검색엔진에서는 교수이름, 과목 이름, 학생의 반으로 리스트를 가져옴) - 실제 데이터를 가져오는 부분  **/
		String professorName = service.listHomeworkEla(String.valueOf(subjectID)).getProfessor(); // 검색엔진에서 필요한 정보를 가져옴
		System.out.println("교수 이름 : " + professorName);
		String subjectName = service.listHomeworkEla(String.valueOf(subjectID)).getSubject(); // 검색엔진에서 필요한 정보를 가져옴
		//System.out.println("과목 이름 : " + subjectName);

		ElasticVO elvo = new ElasticVO(subjectName, professorName, selectClass); // Vo에 저장
		
		cri.setElasticVO(elvo);
		if (cri.getSearchType() == "") { // 검색을 하지 않을땐 검색 타입에 null을 세팅해줌.
			cri.setSearchType(null);
		} 
		List<GetElasticSearchVo> searchvo = elservice.getElasticCriteria(cri); // 매개 변수 cri를 이용해 검색과 페이징이 가능하도록 과제 목록을 출력한다.

		model.addAttribute("elastic", searchvo);

		/** 페이징 구현 **/ 
		PageMaker maker = new PageMaker();
		maker.setCri(cri);
		for (int i = 0; i < searchvo.size()-1; i++) {
			System.out.println(searchvo.get(i).get_source().getTitle());
		}
		System.out.println("글 개수 : "+searchvo.get(searchvo.size()-1).getTotalCount());
		maker.setTotalCount(searchvo.get(searchvo.size()-1).getTotalCount());
		model.addAttribute("maker", maker);
		model.addAttribute("totalCount", searchvo.get(searchvo.size()-1).getTotalCount());
	}

	
	@RequestMapping(value="/hwPersonalList", method = RequestMethod.GET) /**로그인한 사람의 수강, 담당 과목 **/
	public void hwPersonalList(Model model, HttpSession session, RedirectAttributes redir){
		String professorNum; //교수 번호
		String sessionID; //학생 아이디
		
		model.addAttribute("loginMemberList", memberService.select_Member(session.getAttribute("sessionID").toString())); //메뉴바 접속 회원 이름 출력 서비스
		
		String rate = (String) session.getAttribute("Rate"); //학생과 교수를 구분
		if(rate.equals("S")) { // 로그인한 사람이 학생일 경우
			System.out.println("학생 입니다.");
			String memberClass = (String) session.getAttribute("memberClass"); // 만약 학생이라면 반을 가져옴.
			System.out.println("학생의 반 : " + memberClass);
			sessionID = (String ) session.getAttribute("sessionID"); // 학생의 개인 과목 목록을 가져올 땐, 아이디를 사용하여 수강 테이블에서 검색한다.
			model.addAttribute("selectClass", memberClass);
			model.addAttribute("rate", rate);
			model.addAttribute("sessionID", sessionID);
			model.addAttribute("hw", service.readClass(sessionID)); //학생 개인의 과목 목록 출력
		}
		else if(rate.equals("P")) { // 로그인한 사람이 교수일 경우
			System.out.println("교수입니다.");
			professorNum = (String) session.getAttribute("professorNum"); //교수번호 저장
			model.addAttribute("rate", rate);
			model.addAttribute("sessionID", professorNum);
			model.addAttribute("hw", service.readProfesserClass(professorNum)); //교수의 담당 과목 목록 출력
			System.out.println("학년 리스트 : " + service.selectHak(professorNum));
			model.addAttribute("hak", service.selectHak(professorNum)); // 교수가 담당하는 학년의 범위 출력
			model.addAttribute("subjectClass", service.selectClass(professorNum)); //교수가 담당하는 반의 범위 출력
		
		}

	}
	
	@RequestMapping(value="/hwUpdate", method = RequestMethod.GET)
	public void hwUpdate(HttpServletRequest request, @RequestParam("subjectID") int subjectID, @ModelAttribute("cri") SearchCriteria cri, Model model) throws ParseException{

		List<GetElasticSearchVo> searchList = elservice.readElastic(request.getParameter("_id"));

		List<YuhanSubjectVO> list = service.selectSubjectData(Integer.toString(subjectID));
		System.out.println("교수번호 : " + list.get(0).getYUHAN_SUBJECT_PRO());
		System.out.println("교수이름 : " + list.get(0).getProName());
		
		model.addAttribute("_id", request.getParameter("_id"));
		model.addAttribute("hwno", request.getParameter("hwno"));
		model.addAttribute("selectClass", request.getParameter("selectClass"));
		model.addAttribute("subjectID", subjectID);
		model.addAttribute("professorNo", list.get(0).getYUHAN_SUBJECT_PRO());
		model.addAttribute("proName", list.get(0).getProName());
		model.addAttribute("page", cri.getPage());
		model.addAttribute("perPageNum", cri.getPerPageNum());
		model.addAttribute("searchType", cri.getSearchType());
		model.addAttribute("keyword", cri.getKeyword());
		
		String hwno = (String)request.getParameter("hwno");
		if(fileService.fileCount(Integer.parseInt(hwno)) >= 1) { //첨부파일의 유무를 확인해주는 함수가 선언되어야한다.
			System.out.println("파일이 있습니다.");
			model.addAttribute("file", fileService.selectFileInfo(Integer.parseInt(hwno)));
		}else {
			model.addAttribute("file", "첨부 파일이 없습니다.");
		}

		model.addAttribute("elastic", searchList.get(0).get_source());
		
		String elstart = searchList.get(0).get_source().getStartdate();
		String[] array = elstart.split(" ");
		
		String elend = searchList.get(0).get_source().getEnddate();
		String[] array2 = elend.split(" ");
		
		model.addAttribute("start", array[0]);
		model.addAttribute("startTime", array[1]);
		model.addAttribute("end", array2[0]);
		model.addAttribute("endTime", array2[1]);
	}
	
	@RequestMapping(value="/hwDelete", method = RequestMethod.GET)
	public String hwDelete(@RequestParam ("_id") String _id, @RequestParam("hwno") int hwno, @RequestParam("subjectID") int subjectID, SearchCriteria cri, RedirectAttributes rttr){
		//System.out.println("글 삭제");
		service.deleteHomework(hwno);
		elservice.deleteElastic(_id);
		
		rttr.addAttribute("page", cri.getPage());
		rttr.addAttribute("perPageNum", cri.getPerPageNum());
		rttr.addAttribute("searchType", cri.getSearchType());
		rttr.addAttribute("keyword", cri.getKeyword());
		
		rttr.addFlashAttribute("result", "Delete");
		
		return "/hwList?subjectID="+subjectID;
	}
	
	@RequestMapping(value="/hwReportUpdate", method = RequestMethod.GET)
	public void hwReportUpdate(HttpSession session ,HttpServletRequest request, @RequestParam("subjectID") int subjectID, @ModelAttribute("cri") SearchCriteria cri, Model model) throws ParseException{
		List<YuhanSubjectVO> list = service.selectSubjectData(Integer.toString(subjectID));
		int reportCount;
		int hwno = Integer.parseInt(request.getParameter("hwno"));
		int no = Integer.parseInt(request.getParameter("reportNo"));
		String studentID = (String) session.getAttribute("sessionID");
		String _id = request.getParameter("_id");
				
		System.out.println("교수번호 : " + list.get(0).getYUHAN_SUBJECT_PRO());
		System.out.println("교수이름 : " + list.get(0).getProName());
		
		model.addAttribute("_id", _id);
		model.addAttribute("hwno", request.getParameter("hwno"));
		model.addAttribute("selectClass", request.getParameter("selectClass"));
		model.addAttribute("subjectID", subjectID);
		model.addAttribute("professorNo", list.get(0).getYUHAN_SUBJECT_PRO());
		model.addAttribute("page", cri.getPage());
		model.addAttribute("perPageNum", cri.getPerPageNum());
		model.addAttribute("searchType", cri.getSearchType());
		model.addAttribute("keyword", cri.getKeyword());
		model.addAttribute("no", no);
		model.addAttribute("studentID", studentID);
		
		reportCount = fileService.ReportCount(hwno, studentID); // 학생이 해당 과제에 파일을 제출했을 경우
		if(reportCount >= 1) {
			System.out.println("학생의 과제 파일 제출 : " + reportCount);
			model.addAttribute("reportFile", fileService.selectReportFileInfo(hwno, studentID));
		}else {
			model.addAttribute("reportFile", "제출 파일이 없습니다.");
		}
		
		ReportVO vo = new ReportVO(no, studentID);
		model.addAttribute("studentReport", reportService.reportDetailView(no));
		System.out.println("content : " + reportService.reportDetailView(no));
		model.addAttribute("elastic",  elservice.readElastic(_id).get(0).get_source());
	}
	
	@RequestMapping(value="/MyPage_", method = RequestMethod.GET) /**로그인한 사람의 수강, 담당 과목 **/
	   public void MyPage_(Model model, HttpSession session, RedirectAttributes redir){
	      String sessionID; //학생 아이디
	      
	      model.addAttribute("loginMemberList", memberService.select_Member(session.getAttribute("sessionID").toString())); //메뉴바 접속 회원 이름 출력 서비스
	      
	      String rate = (String) session.getAttribute("Rate"); //학생과 교수를 구분
	      if(rate.equals("S")) { // 로그인한 사람이 학생일 경우
	         System.out.println("학생 입니다.");
	         String memberClass = (String) session.getAttribute("memberClass"); // 만약 학생이라면 반을 가져옴.
	         System.out.println("학생의 반 : " + memberClass);
	         sessionID = (String ) session.getAttribute("sessionID"); // 학생의 개인 과목 목록을 가져올 땐, 아이디를 사용하여 수강 테이블에서 검색한다.
	         model.addAttribute("selectClass", memberClass);
	         model.addAttribute("rate", rate);
	         model.addAttribute("sessionID", sessionID);
	         model.addAttribute("list", service.H_List(sessionID)); //학생 개인의 과목 목록 출력
	         model.addAttribute("listc", service.H_ListC(sessionID));
	         model.addAttribute("listm", service.H_ListM(sessionID));
	      }
	}
}
