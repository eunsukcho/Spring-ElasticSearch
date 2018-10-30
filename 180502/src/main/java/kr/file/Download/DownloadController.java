package kr.file.Download;

import java.io.File;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class DownloadController implements ApplicationContextAware {
	
	private WebApplicationContext context = null;

	@RequestMapping("/download.do")
	public ModelAndView dowonload1(@RequestParam("filePath") String filePath, @RequestParam("fileName") String fileName,
			@RequestParam("saveFileName") String saveFileName, HttpServletResponse response) throws Exception{
		String originPath = filePath + "\\" + fileName; //원본 이름
		String fullPath = filePath + "\\" + saveFileName; //저장 이름
		
		System.out.println("파일 다운 : " + fullPath);
		System.out.println("파일 다운2:" + originPath);
//		File file = new File(fullPath);
		
		File downloadFile = null;
		if(fileName != null) {
			File file_original = new File(originPath);
			System.out.println("파일 원본 " + file_original);
			File file_stored = new File(fullPath);
			System.out.println("저장 이름 " + file_stored);
			
			try{
                if( file_stored.exists() ) {
                	System.out.println("이름 변경");
                	file_stored.renameTo(file_original);//이름 변경
                }    
           }catch(Exception e){
              
               
           }
			downloadFile = new File(originPath);
		}else {
			downloadFile = new File(fullPath);
		}
		return new ModelAndView("download", "downloadFile", downloadFile);
	}
	@Override
	public void setApplicationContext(ApplicationContext arg0) throws BeansException {
		 this.context = (WebApplicationContext)arg0;
	}
	
}
