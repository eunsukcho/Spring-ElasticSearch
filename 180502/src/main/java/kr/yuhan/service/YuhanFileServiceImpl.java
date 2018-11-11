package kr.yuhan.service;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import kr.yuhan.domain.YuhanFileVO;
import kr.yuhan.domain.YuhanReportFileVO;
import kr.yuhan.persistence.YuhanFileDAO;

@Service
public class YuhanFileServiceImpl implements YuhanFileService{
	
	@Inject
	private YuhanFileDAO dao;
	
	@Override
	public void FileServerUp(String filePath, MultipartHttpServletRequest multipartRequest) {
		dao.fileServerUp(filePath, multipartRequest);
	}

	@Override
	public void fileDownload(String fileSaveUrl, String fileName) {
		dao.fileDownload(fileSaveUrl, fileName);
	}
	@Override
	public List<String> fileDBUpload(MultipartHttpServletRequest multipartRequest) {
		return dao.fileDbUp(multipartRequest);
	}
	@Override
	public YuhanFileVO readFile(Map<Integer, Integer> parameters) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public YuhanFileVO selectDataStatus(int hwno) {
		return dao.selectDataStatus(hwno);
	}
	@Override
	public int fileCount(int hwno) {
		return dao.fileCount(hwno);
	}
	@Override
	public List<YuhanFileVO> selectFileInfo(int hwno) {
		return dao.selectFileInfo(hwno);
	}

	@Override
	public void deleteFile(String saveFileName, String filePath) {
		dao.deleteFile(saveFileName, filePath);
	}

	@Override
	public void ReportFileServerUp(String reportFilePath, MultipartHttpServletRequest multipartRequest) {
		dao.ReportFileServerUp(reportFilePath, multipartRequest);
	}

	@Override
	public int ReportCount(int hwno, String studentID) {
		return dao.ReportCount(hwno, studentID);
	}

	@Override
	public List<YuhanReportFileVO> selectReportFileInfo(int hwno, String studentID) {
		return dao.selectReportFileInfo(hwno, studentID);
	}

	@Override
	public void reportDeleteFile(String saveFileName, String filePath) {
		dao.reportDeleteFile(saveFileName, filePath);
	}

	@Override
	public void reportAllDelete(int homeworkNo, String studentID, String filePath) {
		dao.reportAllDelete(homeworkNo, studentID, filePath);
	}

}
