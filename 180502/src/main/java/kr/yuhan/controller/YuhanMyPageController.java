package kr.yuhan.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.yuhan.service.YuhanMyPageService;

@Controller
@RequestMapping(value = "/myPage")
public class YuhanMyPageController 
{
	@Inject
	public YuhanMyPageService myPageService;
	
	@RequestMapping(value = "/main", method=RequestMethod.GET)
	public void main(HttpSession session, Model model)
	{
		if(myPageService.selectReportCount("Zz") == 0)
		{
			model.addAttribute("result", "null");
		}
		else
		{
			model.addAttribute("resultList", myPageService.selectReport("zz"));
		}
	}
}
