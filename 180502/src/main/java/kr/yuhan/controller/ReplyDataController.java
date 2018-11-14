package kr.yuhan.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import kr.yuhan.domain.Criteria;
import kr.yuhan.domain.PageMaker;
import kr.yuhan.domain.ReplyCriteria;
import kr.yuhan.domain.ReplyVO;
import kr.yuhan.service.ReplyService;

@RestController
public class ReplyDataController {
	
	@Inject
	private ReplyService service;
	
	@RequestMapping(value = "/addRep", method = RequestMethod.POST, produces = "application/json; charset=utf-8" )
	public ResponseEntity<?> addRep(@RequestBody ReplyVO repVo, @ModelAttribute("cri") ReplyCriteria cri) {
		ResponseEntity<?> entity;
		Gson gson = new GsonBuilder().setPrettyPrinting().create(); 
		System.out.println("addRep : " + repVo.getProfessorNO());
		cri.setHwno(repVo.getHwno());
		service.insertRep(repVo);
		
		try {
			//System.out.println(gson.toJson(service.selectRep(repVo)));
			entity = new ResponseEntity<Object>(gson.toJson(service.selectRepPage(repVo)), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		
		return entity;
	}

	@RequestMapping(value = "/loadRep", method = RequestMethod.POST)
	public ResponseEntity<?> loadRep(@RequestBody ReplyVO repVo){
		ResponseEntity<?> entity;
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		
		Criteria cri = new Criteria();
		cri.setRepPage(repVo.getRepPage());
		
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		
		Map<String, Object> map = new HashMap<String, Object>();
		List<ReplyVO> list = service.selectRepPage(repVo);
		
		map.put("list", list);
		
		int replyCount = service.count(repVo.getHwno(), repVo.getStudentID());
		pageMaker.setRepTotalCount(replyCount);
		
		map.put("pageMaker", pageMaker);

		System.out.println("하하"+repVo.getHwno());
		
		try {
			entity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return entity; 
	}
	
	@RequestMapping(value = "/delRep/{repNo}", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	public ResponseEntity<?> delRep(@PathVariable("repNo") int repNo) {
		ResponseEntity<?> entity;
		try {
			service.deleteRep(repNo);
			entity = new ResponseEntity<Object>("SUCCESS", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return entity;
	} 
	
	@RequestMapping(value = "/totalRep", method = RequestMethod.POST, produces = "application/json; charset=utf-8" )
	public ResponseEntity<?> totalRep(@RequestBody ReplyVO repVo, Model model){
		ResponseEntity<?> entity;
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		
		System.out.println("하하"+repVo.getHwno());

		try {
			entity = new ResponseEntity<Object>(service.totalRep(repVo), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return entity; 
	}
	
	@RequestMapping(value = "/updateRep/{repNo}", method = {RequestMethod.PUT, RequestMethod.PATCH}, produces = "application/json; charset=utf-8")
	public ResponseEntity<?> updateRep(@PathVariable("repNo") int repNo, @RequestBody ReplyVO vo){
		ResponseEntity<?> entity;
		try {
			vo.setRepNo(repNo);
			service.updateRep(vo);
			entity = new ResponseEntity<Object>("SUCCESS", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
	
	
}
