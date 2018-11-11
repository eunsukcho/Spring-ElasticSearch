package kr.yuhan.service;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import kr.yuhan.domain.YuhanFileVO;
import kr.yuhan.domain.YuhanHomeworkVO;
import kr.yuhan.domain.YuhanReportFileVO;

public interface YuhanFileService {
	public void FileServerUp(String filePath, MultipartHttpServletRequest multipartRequest); // 첨부 파일을 서버에 저장
	public void fileDownload(String fileSaveUrl, String fileName); // 파일 다운로드
	public List<String> fileDBUpload(MultipartHttpServletRequest multipartRequest); // 첨부 파일의 정보를 DB에 저장
	
	public YuhanFileVO readFile(Map<Integer, Integer> parameters); // 파일의 정보를 읽어옴
	public YuhanFileVO selectDataStatus(int hwno); // 첨부파일의 존재 유무
	
	public int fileCount(int hwno);
	public List<YuhanFileVO> selectFileInfo(int hwno);
	
	public void deleteFile(String saveFileName, String filePath);

	/**Report File**/
	public void ReportFileServerUp(String filePath, MultipartHttpServletRequest multipartRequest); // 첨부 파일을 서버에 저장
	public int ReportCount(int hwno, String studentID);
	public List<YuhanReportFileVO> selectReportFileInfo(int hwno, String studentID);
	public void reportDeleteFile(String saveFileName, String filePath);
	public void reportAllDelete(int homeworkNo, String studentID, String filePath);
}
