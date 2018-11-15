package kr.yuhan.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import kr.yuhan.domain.YuhanSubjectVO;
import kr.yuhan.service.YuhanHomeworkService;
import kr.yuhan.service.YuhanSubjectService;

@Controller
public class YuhanSelectSubjectController 
{
	@Inject 
	private YuhanSubjectService subjectService;
	
	@RequestMapping(value = "/selectSubject", method=RequestMethod.GET)
	public void selectSubject(Model model, @RequestParam("memberGrade") String memberGrade)
	{ 
		model.addAttribute("selectSubject", subjectService.selectSubject(memberGrade));
	}
	
	@RequestMapping(value = "/selectSubject", method=RequestMethod.POST)
	public String selectSubjectPost(HttpServletRequest request, HttpSession session, YuhanSubjectVO vo, Model model)
	{ 
		// ���� �̻�
		
		HashMap<String, Object> parameter = new HashMap<String, Object>();
		Set<Entry<String, Object>> set = parameter.entrySet();
		Iterator<Entry<String, Object>> itr = set.iterator();
		
	      Enumeration names = request.getParameterNames();
	      
	      int i = 0;
	      while( names.hasMoreElements() ) {
	         String key = names.nextElement().toString();
	         String value = null;
			try {
				value = URLDecoder.decode(request.getParameter(key), "UTF-8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	         
	         parameter.put( key, value );
	         vo.setMemberID(session.getAttribute("sessionID").toString());
	         vo.setSubjectNO(Integer.parseInt(value.toString()));
	         
	         subjectService.insertSubject(vo);
	      }
	      
	      model.addAttribute("success", "ok");
		
		return "/selectSubject";
	}
	
	@RequestMapping(value = "/updateSubject", method=RequestMethod.GET)
	public void updateSubject(HttpSession session, Model model)
	{
		model.addAttribute("selectSubjectList", subjectService.selectSubjectList(session.getAttribute("sessionID").toString()));
		model.addAttribute("noSelectSubjectList", subjectService.noSelectSubject(session.getAttribute("sessionID").toString()));
	}
	
	@RequestMapping(value = "/updateSubject", method=RequestMethod.POST)
	   public String updateSubjectPost(HttpSession session, Model model, HttpServletRequest request)
	   {
	      HashMap<String, Object> parameter = new HashMap<String, Object>();
	         Enumeration names = request.getParameterNames();
	         List valueList = new ArrayList();
	         HashMap map = new HashMap();
	         while( names.hasMoreElements())
	         {
	            String key = names.nextElement().toString();
	            String value = null;
	         try 
	         {
	            value = URLDecoder.decode(request.getParameter(key), "UTF-8");
	            
	            valueList.add(Integer.parseInt(value));
	            
	         }
	         catch (UnsupportedEncodingException e) 
	         {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	         }
	            
	         parameter.put( key, value );
	         map.put("memberID", session.getAttribute("sessionID").toString());
	         map.put("subjectNO", value);
	         subjectService.updateAddSubject(map);
	         map.clear();
	      }
	      map.put("memberID", session.getAttribute("sessionID").toString());
	      map.put("whereString", valueList);
	      subjectService.updateSubject(map);
	      
	      
	      model.addAttribute("result", "ok");
	         
	         return "/updateSubject";
	   }
	
	@RequestMapping(value = "/createSubject", method=RequestMethod.GET)
	public void createSubject()
	{
		
	}
	
	@RequestMapping(value = "/createSubject", method=RequestMethod.POST)
	public void createSubjectPost(@RequestParam("subjectName") String subjectName, @RequestParam("classNum") String classNum, @RequestParam("memberGrade") String memberGrade, YuhanSubjectVO vo, HttpSession session)
	{
		System.out.println("�o��");
		
		vo.setYUHAN_SUBJECT_NAME(subjectName);
		vo.setYUHAN_SUBJECT_CLASS_ROOM(classNum);
		vo.setYUHAN_SUBJECT_HAK(memberGrade);
		String subjectProNum = subjectService.selectProNum(session.getAttribute("sessionID").toString()).toString();
		System.out.println(subjectProNum);
		vo.setYUHAN_SUBJECT_PRO(subjectProNum);
		
		subjectService.createSubject(vo);
	}
	
	@RequestMapping(value = "/manageSubject", method=RequestMethod.GET)
	public void manageSubject(Model model, HttpSession session, YuhanSubjectVO vo)
	{
		int tempNum = 0;
		if(subjectService.selectProNum(session.getAttribute("sessionID").toString()) == null)
		{
			model.addAttribute("result", "fail");
		}
		else
		{
			String subjectProNum = subjectService.selectProNum(session.getAttribute("sessionID").toString()).toString();
			
			
			
			for(int i = 0; i <= subjectService.ProSelectSubject(subjectProNum).size(); i++)
			{
				tempNum = subjectService.ProSelectSubject(subjectProNum).get(0).getYUHAN_SUBJECT_NUMBER();
				vo.setSubjectCount(tempNum);
			}
			
			model.addAttribute("subjectList", subjectService.ProSelectSubject(subjectProNum));
		}
	}
	
	@RequestMapping(value = "/proUpdateSubject", method=RequestMethod.GET)
	public void proUpdateSubject(HttpSession session, Model model, @RequestParam("subjectNo") String subjectNo)
	{
		model.addAttribute("subjectResult", subjectService.proSearchSubject(subjectNo));
	}
	
	@RequestMapping(value = "/proUpdateSubject", method=RequestMethod.POST)
	public void proUpdateSubjectPost(HttpSession session, Model model, HttpServletRequest request, YuhanSubjectVO vo)
	{
		vo.setYUHAN_SUBJECT_NUMBER(Integer.parseInt(request.getParameter("subjectNo")));
		vo.setYUHAN_SUBJECT_NAME(request.getParameter("subjectName").toString());
		vo.setYUHAN_SUBJECT_CLASS_ROOM(request.getParameter("subjectClass").toString());
		
		subjectService.proUpdateSubject(vo);
		
		model.addAttribute("success", "ok");
	}
}
