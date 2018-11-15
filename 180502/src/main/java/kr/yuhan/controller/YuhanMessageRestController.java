package kr.yuhan.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import kr.yuhan.domain.Criteria;
import kr.yuhan.domain.PageMaker;
import kr.yuhan.domain.PageMaker2;
import kr.yuhan.domain.SearchCriteria2;
import kr.yuhan.domain.YuhanMessageVO;
import kr.yuhan.service.YuhanMessageService;

@RestController
public class YuhanMessageRestController 
{
	@Inject
	YuhanMessageService messageService;
	
	@RequestMapping(value = "/messageHomeList", method=RequestMethod.GET)
	public List<YuhanMessageVO> messageHomeList(Model model, HttpSession session, Criteria cri)
	{
		cri.setMemberID(session.getAttribute("sessionID").toString());
		PageMaker maker = new PageMaker();
		maker.setCri(cri);
		maker.setTotalMessageCount(messageService.totalMessageCount(cri));
		
		model.addAttribute("maker", maker);
		
		
		return messageService.selectMessage(cri);
	}
	
	@RequestMapping(value = "/myMessageHomeList", method=RequestMethod.GET)
	public List<YuhanMessageVO> myMessageHomeList(Model model, HttpSession session, Criteria cri)
	{
		cri.setYUHAN_MESSAGE_FROM_MEMBER_NUMBER(session.getAttribute("sessionID").toString());
		PageMaker maker = new PageMaker();
		maker.setCri(cri);
		maker.setTotalMessageCount(messageService.totalMessageCount(cri));
		
		model.addAttribute("maker", maker);
		return messageService.selectSendMessage(cri);
	}
	
	@RequestMapping(value = "/searchMessage", method=RequestMethod.GET)
	public List<YuhanMessageVO> searchMessage(Model model, HttpSession session, @ModelAttribute("cri") SearchCriteria2 cri)
	{
		//model.addAttribute("list", service.listCriteria(cri));
				PageMaker2 maker = new PageMaker2();
				maker.setCri(cri);
				//maker.setTotalCount(service.totalCount(cri));
				
				cri.setMemberID(session.getAttribute("sessionID").toString());
				
				maker.setTotalCount(messageService.listSearchCount(cri));
				
				model.addAttribute("maker",maker);
				
				System.out.println("ddddddddddddddd : " + messageService.listSearch(cri));
				System.out.println("xxxxxxxxxxxxxxx : " + messageService.listSearchCount(cri));
		return messageService.listSearch(cri);
	}
	
	@RequestMapping(value = "/messageSaveList", method=RequestMethod.GET)
	public List<YuhanMessageVO> messageSaveList(Model model, HttpSession session, Criteria cri)
	{
		cri.setMemberID(session.getAttribute("sessionID").toString());
		PageMaker maker = new PageMaker();
		maker.setCri(cri);
		maker.setTotalMessageCount(messageService.totalSaveMessageCount(cri));
		
		model.addAttribute("maker", maker);
		
		
		return messageService.selectSaveMessage(cri);
	}
	
	@RequestMapping(value = "/test", method=RequestMethod.GET)
	public List<String> test(HttpServletRequest req, @RequestParam("abc") String abc)
	{
		/*
		ObjectMapper mapper = new ObjectMapper();
		
		Map<String, String> map1 = new HashMap<String, String>();
		
		try {
			map1 = mapper.readValue(test, new TypeReference<Map<String,String>>() {});
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(req.getContentType());
		System.out.println(req.getCharacterEncoding());
		System.out.println(map1.get("sigungu_se"));*/
		//map은 인터페이스라 new 선언이 되지 않음 그래서 HaspMap으로 선언
		//map은 중복데이터를 못 넣는다. key map.put("name", "zinzo"); 하고 또 map.put("name", "zz"); 넣으면 최근걸로 덮어씌어짐
		//key는 유일한 값을 가진다
		
		System.out.println(abc);
/*		HashMap<String, String> map = new HashMap<String, String>();
		
		
		
		map.put("name", "zinzo");
		map.put("age", "12");
		map.put("gender", "girl");*/
		
		List<String> list = new ArrayList<String>();
		
		list.add("1");
		list.add("2");
		list.add("3");
	
	
		return list;
	}
}