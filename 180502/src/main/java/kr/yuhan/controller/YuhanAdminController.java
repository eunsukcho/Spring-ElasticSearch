package kr.yuhan.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import kr.yuhan.service.YuhanAdminService;

@Controller
public class YuhanAdminController 
{
	@Inject 
	private YuhanAdminService adminService;
	
	@RequestMapping(value = "/adminSubject", method=RequestMethod.GET)
	public void adminSubject(Model model)
	{ 
		model.addAttribute("subjectList", adminService.adminSelectSubject());
	}
	
	@RequestMapping(value = "/adminSubjectAccess", method=RequestMethod.GET)
	public void adminSubjectAccess(Model model, @RequestParam("subjectNO") int subjectNO)
	{ 
		adminService.updateSubjectY(subjectNO);
		
		model.addAttribute("success", "ok");
	}
}
