package kr.yuhan.controller;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import kr.yuhan.service.YuhanMemberCheckService;

@RestController
public class YuhanJoinController {
	
	@Inject
	private YuhanMemberCheckService service;
	
	@RequestMapping(value = "/checkid/{memberID}", method = RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> checkSignup(@PathVariable("memberID") String memberID) {
    	ResponseEntity<Map<String, Object>> entity;
    	
        System.out.println(memberID);
        
        try {
        	Map<String, Object> map = new HashMap<>();
        	map.put("result", service.idcheck(memberID));
        	entity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
        } catch(Exception e) {
        	Map<String, Object> map = new HashMap<>();
        	map.put("BAD", "BAD");
        	e.printStackTrace();
        	return new ResponseEntity<Map<String, Object>>(map, HttpStatus.BAD_REQUEST);
        }
        
        return entity;
//        
//        String rowcount = service.idcheck(memberID);
//        
//       System.out.println(memberID);
//       System.out.println(rowcount);
//       
//        return String.valueOf(rowcount);
    }

    @RequestMapping(value = "/checkidP", method = RequestMethod.POST)
    public String checkSignupP(HttpServletRequest request, Model model) {
        String proID = request.getParameter("proID");
        String rowcount = service.idcheckP(proID);
       
        
        
       System.out.println(proID);
       System.out.println(rowcount);
       
        return String.valueOf(rowcount);
    }

}
