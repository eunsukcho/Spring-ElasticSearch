package kr.yuhan.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import kr.yuhan.domain.ReportVO;
import kr.yuhan.domain.YuhanFileVO;
import kr.yuhan.service.YuhanFileService;

@RestController
public class FileController {
	
	@Inject
	private YuhanFileService service;
	
	YuhanFileVO vo = new YuhanFileVO();
	String filePath = "C:/YuhanFileTest";
	String reportFilePath = "C:/YuhanReportFile";
	
	@RequestMapping(value = "/fileUpload",  method = RequestMethod.POST)
	public String upload(MultipartHttpServletRequest multipartRequest, RedirectAttributes redir) {
		service.FileServerUp(filePath, multipartRequest);

		redir.addFlashAttribute("result", "Success");
		return "redirect:/hwList";
	}
	
	@RequestMapping(value = "/fileDelete", method = RequestMethod.POST)
	public ResponseEntity<?> delete(@RequestBody String saveFileName) {
		ResponseEntity<?> entity;
		
		System.out.println(saveFileName.substring(0, saveFileName.length()-1));

		try {
			service.deleteFile(saveFileName.substring(0, saveFileName.length()-1), filePath);
			entity = new ResponseEntity<Object>("SUCCESS", HttpStatus.OK);
		}catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
	
	@RequestMapping(value = "/reportFileUpload", method = RequestMethod.POST)
	public ResponseEntity<?> reportFileUpload(MultipartHttpServletRequest multipartRequest, RedirectAttributes redir, HttpSession session) {
		ResponseEntity<?> entity;
		System.out.println("학생과제파일");
		//System.out.println(multipartRequest.getParameter("studentID"));
		multipartRequest.setAttribute("studentID", (String) session.getAttribute("sessionID"));
		try {
			service.ReportFileServerUp(reportFilePath, multipartRequest);
			entity = new ResponseEntity<Object>("SUCCESS", HttpStatus.OK);
		}catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
	
	@RequestMapping(value = "/reportFileDelete", method = RequestMethod.POST)
	public ResponseEntity<?> reportdelete(@RequestBody String saveFileName) {
		ResponseEntity<?> entity;
		
		System.out.println(saveFileName.substring(0, saveFileName.length()-1));

		try {
			service.reportDeleteFile(saveFileName.substring(0, saveFileName.length()-1), reportFilePath);
			entity = new ResponseEntity<Object>("SUCCESS", HttpStatus.OK);
		}catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
	
	@RequestMapping(value="/deleteServerReport" , method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public ResponseEntity<?> deleteReport(@RequestBody ReportVO vo){
		
		ResponseEntity<?> entity;
		Gson gson = new GsonBuilder().setPrettyPrinting().create(); 

		System.out.println("과제 파일 삭제 정보");
		try {
			service.reportAllDelete(vo.getHomeworkNo(), vo.getStudentID(), reportFilePath);
			entity = new ResponseEntity<Object>("SUCCESS", HttpStatus.OK);
		}catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		
		return entity;
	}
	
}
