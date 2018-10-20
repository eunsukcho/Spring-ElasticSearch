package kr.yuhan.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.yuhan.domain.TestLoginVO;
import kr.yuhan.persistence.TestLoginDAO;

@Controller
public class TestLoginController {
	
	@Inject
	private TestLoginDAO dao;
	
	@RequestMapping("/testLogin")
	public void testLogin() {
		
	}
	
	@RequestMapping(value = "/testLogin", method = RequestMethod.POST)
	public String testLogin2(TestLoginVO vo, HttpServletRequest request, HttpSession session) {
		System.out.println(request.getParameter("rate"));
	
		if(request.getParameter("rate").equals("s")) {
			int i = dao.checkStudentLogin(vo);
			if(i == 1) {
				System.out.println("학생 로그인");
			}
			
			System.out.println(dao.StudentData(vo));
			
			TestLoginVO vo1 = new TestLoginVO();
			vo1 = dao.StudentData(vo);
			System.out.println(vo1.getMemberHak() + " " + vo1.getMemberRating() + " " + vo1.getMemberID());
			
			session.setAttribute("Rate", vo1.getMemberRating());
			session.setAttribute("memberNum", vo1.getMemberHak());
			session.setAttribute("memberID", vo1.getMemberID());

		}else if(request.getParameter("rate").equals("p")){
			int i = dao.checkProfessorLogin(vo);
			if(i == 1) {
				System.out.println("교수 로그인");
			}
			
			System.out.println(dao.checkProfessorLogin(vo));
			
			TestLoginVO vo1 = new TestLoginVO();
			vo1 = dao.ProfessorData(vo);
			System.out.println(vo1.getProNo() + " " + vo1.getProRating() + " " + vo1.getProID());
			
			session.setAttribute("Rate", vo1.getProRating());
			session.setAttribute("memberNum", vo1.getProNo());
			session.setAttribute("memberID", vo1.getProID());
		}
		return "redirect:/hwPersonalList";
	}
}
