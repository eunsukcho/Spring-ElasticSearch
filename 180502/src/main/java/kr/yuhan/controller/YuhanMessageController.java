package kr.yuhan.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import kr.yuhan.domain.Criteria;
import kr.yuhan.domain.PageMaker;
import kr.yuhan.domain.YuhanMemberVO;
import kr.yuhan.domain.YuhanMessageVO;
import kr.yuhan.service.YuhanMemberCheckService;
import kr.yuhan.service.YuhanMessageService;

@Controller
public class YuhanMessageController 
{
	@Inject
	private YuhanMessageService messageService;
	
	@Inject
	private YuhanMemberCheckService memberService;
	
	@RequestMapping(value = "/messageHome", method=RequestMethod.GET)
	public void messageHome(HttpSession session, Model model, YuhanMemberVO vo, Criteria cri)
	{
		/***************** 로그인 세션 설정 ******************/
		if(session.getAttribute("sessionID") == null)
		{
			model.addAttribute("model_SessionID", "No");
		}
		else
		{
			model.addAttribute("loginMemberList", memberService.select_Member(session.getAttribute("sessionID").toString()));
			
			/*************** 쪽지 불러오기 ************** 2018-10-13 이진주 */
			
			//model.addAttribute("messageList", messageService.selectMessage(messageService.selectHak(session.getAttribute("sessionID").toString())));
			cri.setMemberHak(messageService.selectHak(session.getAttribute("sessionID").toString()));
			PageMaker maker = new PageMaker();
			maker.setCri(cri);
			maker.setTotalMessageCount(messageService.totalMessageCount(cri));
			
			model.addAttribute("maker", maker);
			
			model.addAttribute("dummy", messageService.selectMessage(cri));
			model.addAttribute("noReadMessageCount", messageService.selectNoReadMessageCount(session.getAttribute("sessionID").toString()));
			
			System.out.println(maker.getEndPage());
		}
	}/************************************************* 2018.10.09 이진주 */
	
	@RequestMapping(value = "/messageSend", method=RequestMethod.GET)
	public String messageSend(HttpSession session, Model model, @RequestParam("messageTo") String YUHAN_MESSAGE_TO_MEMBER_NUMBER)
	{
		/***************** 로그인 세션 설정 ******************/
		if(session.getAttribute("sessionID") == null)
		{
			model.addAttribute("model_SessionID", "No");
			
			return "/header";
		}
		else
		{
			if(!YUHAN_MESSAGE_TO_MEMBER_NUMBER.equals(null))
			{
				//답장 구현
				model.addAttribute("selectUser", messageService.selectUser(YUHAN_MESSAGE_TO_MEMBER_NUMBER));
			}
			else
			{
				model.addAttribute("loginMemberList", memberService.select_Member(session.getAttribute("sessionID").toString()));
				
				model.addAttribute("userList", messageService.findUser());
			}
		}
		/************************************************* 2018.10.11 이진주 */
		
		return "/messageSend";
	}
	
	@RequestMapping(value = "/messageSend", method=RequestMethod.POST)
	public String messageSendPost(@RequestParam("YUHAN_MESSAGE_SUBJECT") String YUHAN_MESSAGE_SUBJECT, @RequestParam("YUHAN_MESSAGE_CONTENT") String YUHAN_MESSAGE_CONTENT, @RequestParam("YUHAN_MESSAGE_TO_MEMBER_NUMBER") String YUHAN_MESSAGE_TO_MEMBER_NUMBER, YuhanMessageVO vo, HttpSession session, Model model)
	{
		int checkFlag = 0;
		
		StringTokenizer item = new StringTokenizer(YUHAN_MESSAGE_TO_MEMBER_NUMBER, "(|, |)");
		
		for (int i = 0; item.hasMoreTokens(); i++) 
		{
			if(checkFlag == 0)
			{
				String toHak = item.nextToken().toString();
				vo.setYUHAN_MESSAGE_FROM_MEMBER_NUMBER(session.getAttribute("sessionID").toString());
				vo.setYUHAN_MESSAGE_TO_MEMBER_NUMBER(toHak);
				vo.setYUHAN_MESSAGE_CONTENT(YUHAN_MESSAGE_CONTENT);
				vo.setYUHAN_MESSAGE_SUBJECT(YUHAN_MESSAGE_SUBJECT);
				
				checkFlag = 1;
				
				messageService.sendMessage(vo);
				
				System.out.println(i + "번째");
				System.out.println(vo.getYUHAN_MESSAGE_FROM_MEMBER_NUMBER());
				System.out.println(vo.getYUHAN_MESSAGE_TO_MEMBER_NUMBER());
				System.out.println(vo.getYUHAN_MESSAGE_SUBJECT());
				System.out.println(vo.getYUHAN_MESSAGE_CONTENT());
				
				//쪽지 보내기 구현//
				
			}
			else
			{
				item.nextToken();
				checkFlag = 0;
			}
		}
		
		model.addAttribute("messageSend", "Ok");
		
		return "/messageSend";
	}
	
	/** @author 진주 2018-10-13*/
	@RequestMapping(value = "/message", method=RequestMethod.GET) 
	public String message(@RequestParam("messageNum") String YUHAN_MESSAGE_NUMBER, @RequestParam("messageTo") String YUHAN_MESSAGE_TO_MEMBER_NUMBER, HttpSession session, Model model, YuhanMessageVO vo)
	{
		//1. 로그인을 했는지 확인
		if(session.getAttribute("sessionID") == null)
		{
			model.addAttribute("model_SessionID", "No");
			
			return "/header";
		}
		else
		{
			//2. 쪽지 보낸 사람이랑 로그인 정보랑 맞는지 확인
			if(messageService.selectHak(session.getAttribute("sessionID").toString()).equals(YUHAN_MESSAGE_TO_MEMBER_NUMBER))
			{
				//3. 쪽지 출력
				model.addAttribute("messageList", messageService.selectMessageOne(YUHAN_MESSAGE_NUMBER));
				model.addAttribute("messageTo", YUHAN_MESSAGE_TO_MEMBER_NUMBER);
				messageService.updateReadStatus(YUHAN_MESSAGE_NUMBER);
			}
			else
			{
				model.addAttribute("noFromUser", "No");
			}
		}
		
		return "/message";
	}
}
