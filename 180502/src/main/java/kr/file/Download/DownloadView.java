package kr.file.Download;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.servlet.view.AbstractView;

public class DownloadView extends AbstractView{

	public void Download() {
		setContentType("application/download; utf-8");
	}
	
	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		File file = (File)model.get("downloadFile");
		System.out.println("DownloadView --> file.getPath() : " + file.getPath());
        System.out.println("DownloadView --> file.getName() : " + file.getName());

        response.setContentType(getContentType());
        response.setContentLength((int)file.length());
        
        String browser = getBrowser(request);
        String dispositionPrefix = "attachment; filename=";
        String encodedFilename = null;
        String filename = file.getName();

        if (browser.equals("MSIE")) {
            encodedFilename = URLEncoder.encode(filename, "UTF-8").replaceAll("\\+", "%20");
	     } else if (browser.equals("Trident")) {       // IE11 문자열 깨짐 방지
	    	 encodedFilename = URLEncoder.encode(filename, "UTF-8").replaceAll("\\+", "%20");
	     } else if (browser.equals("Firefox")) {
	         encodedFilename = "\"" + new String(filename.getBytes("UTF-8"), "8859_1") + "\"";
	         encodedFilename = URLDecoder.decode(encodedFilename);
	     } else if (browser.equals("Opera")) {
	         encodedFilename = "\"" + new String(filename.getBytes("UTF-8"), "8859_1") + "\"";
	     } else if (browser.equals("Chrome")) {
	         StringBuffer sb = new StringBuffer();
	         	for (int i = 0; i < filename.length(); i++) {
	         		char c = filename.charAt(i);
	         			if (c > '~') {
	         				sb.append(URLEncoder.encode("" + c, "UTF-8"));
	                   } else {
	                        sb.append(c);
	                   }
	            }
	        encodedFilename = sb.toString();
	     } else if (browser.equals("Safari")){
	        encodedFilename = "\"" + new String(filename.getBytes("UTF-8"), "8859_1")+ "\"";
	        encodedFilename = URLDecoder.decode(encodedFilename);
	     }
	     else {
	            encodedFilename = "\"" + new String(filename.getBytes("UTF-8"), "8859_1")+ "\"";
	     }
	     response.setHeader("Content-Disposition", dispositionPrefix + encodedFilename);

	     if ("Opera".equals(browser)){
	    	 response.setContentType("application/octet-stream;charset=UTF-8");
	     }

        
	     response.setHeader("Content-Transfer-Encoding", "binary");
 
        OutputStream out = response.getOutputStream();
        FileInputStream fis = null;

        try {
            fis = new FileInputStream(file);
            FileCopyUtils.copy(fis, out);
        } finally {
            if(fis != null) {
                try {
                    fis.close();
                } catch(IOException ioe) {}
            }
        }
        out.flush();
 
        /**다운로드 후  다시 변경된 이름으로 변경  */
        if(request.getParameter("saveFileName") != null){
            File original_file= new File(file.getPath()); //원본 경로+파일
            String file_path  = original_file.toString().replace(file.getName(),""); //path 추출
            File stored_file  = new File(file_path+request.getParameter("saveFileName"));//변경 파일명
            original_file.renameTo(stored_file);//이름 변경
        }
	}
	
	public String getBrowser(HttpServletRequest request) {

        String header = request.getHeader("User-Agent");

	   if (header.indexOf("MSIE") > -1) {
	       return "MSIE";
	   } else if (header.indexOf("Trident") > -1) {   // IE11 문자열 깨짐 방지
	       return "Trident";
	   } else if (header.indexOf("Chrome") > -1) {
	       return "Chrome";
	   } else if (header.indexOf("Opera") > -1) {
	       return "Opera";
	   } else if (header.indexOf("Safari") > -1) {
	       return "Safari";
	   }
	   return "Firefox";
  }

}
