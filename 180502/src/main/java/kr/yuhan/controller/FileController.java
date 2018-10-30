package kr.yuhan.controller;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.yuhan.domain.YuhanFileVO;
import kr.yuhan.service.YuhanFileService;

@RestController
public class FileController {
	
	@Inject
	private YuhanFileService service;
	
	YuhanFileVO vo = new YuhanFileVO();
	String filePath = "C:/YuhanFileTest";
	
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
}