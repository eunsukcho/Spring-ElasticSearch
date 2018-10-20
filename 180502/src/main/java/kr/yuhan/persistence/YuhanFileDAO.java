package kr.yuhan.persistence;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import kr.yuhan.domain.YuhanFileVO;
import kr.yuhan.domain.YuhanHomeworkVO;

public interface YuhanFileDAO {
	public void fileServerUp(MultipartHttpServletRequest multipartRequest);
	public void fileDownload(String fileSaveUrl, String fileName);
	public List<String> fileDbUp(MultipartHttpServletRequest multipartRequest);
	
	public YuhanFileVO readFile(Map<Integer, Integer> parameters); //
	public YuhanFileVO selectDataStatus(int hwno); // 첨부파일의 존재 유무
	
	public int fileCount(int hwno);
	public List<YuhanFileVO> selectFileInfo(int hwno);
}
