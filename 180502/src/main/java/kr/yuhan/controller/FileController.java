package kr.yuhan.controller;

import java.io.File;
import java.net.URLEncoder;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.yuhan.domain.YuhanFileVO;

import kr.yuhan.service.YuhanFileService;
import kr.yuhan.service.YuhanHomeworkService;

@RestController
public class FileController {
	
	@Inject
	private YuhanFileService service;
	
	YuhanFileVO vo = new YuhanFileVO();
	
	@RequestMapping(value = "/fileUpload",  method = RequestMethod.POST)
	public String upload(MultipartHttpServletRequest multipartRequest, RedirectAttributes redir) {
		System.out.println("글 번호 " + multipartRequest.getParameter("hwno"));
		
		service.FileServerUp(multipartRequest);
	
		service.fileDBUpload(multipartRequest);
		
		redir.addFlashAttribute("result", "Success");
		return "redirect:/hwList";
	}
	
	@RequestMapping(value = "/fileDownload", method = RequestMethod.GET)
	public String download(HttpServletResponse response, @RequestParam("url") String url, @RequestParam("fileName") String fileName) {
		System.out.println(url);
	
	    byte[] fileByte;
	    
		try {
			fileByte = FileUtils.readFileToByteArray(new File(url));
			response.setContentType("application/octet-stream");
			response.setContentLength(fileByte.length);
			
			response.setHeader("Content-Disposition", "attachment; fileName=\"" + URLEncoder.encode(fileName, "UTF-8") + "\";");
            response.setHeader("Content-Transfer-Encoding", "binary");
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
}
