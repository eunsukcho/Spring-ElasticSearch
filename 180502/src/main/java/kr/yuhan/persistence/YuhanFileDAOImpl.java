package kr.yuhan.persistence;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import java.util.Map;
import java.util.UUID;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import kr.yuhan.domain.YuhanFileVO;
import kr.yuhan.domain.YuhanReportFileVO;

@Repository
public class YuhanFileDAOImpl implements YuhanFileDAO{

	@Inject
	private SqlSession sqlSession;
	static final String NAMESPACE = "kr.yuhan.mapper.YuhanFileMapper";
	
	String filePath;
	String fileFullPath;
	private HttpServletResponse resp;
	private byte[] fileByte;
	Map<String, List<String>> fileName = new HashMap<String, List<String>>();
	
	@Override
	public void fileServerUp(String filePath, MultipartHttpServletRequest multipartRequest) {
		Iterator<String> itr = multipartRequest.getFileNames();
		List<String> oldNames = new ArrayList<String>();
		List<String> saveNames = new ArrayList<String>();
		StringBuffer sb = null;
		
		while (itr.hasNext()) {
			File targetDir = new File(filePath); //폴더 없으면 새로 생성
			if(!targetDir.exists()) {
				targetDir.mkdirs();
	        }
			MultipartFile mpf = multipartRequest.getFile(itr.next());
			sb = new StringBuffer();
			String oldFileName = mpf.getOriginalFilename();
			int FileSize = (int) mpf.getSize();
			String saveFileName = sb.append(new SimpleDateFormat("yyyyMMddhhmmss").format(System.currentTimeMillis()))
					.append(UUID.randomUUID().toString())
					.append(oldFileName.substring(oldFileName.lastIndexOf("."))).toString();
			String fileFullPath = filePath + "/" + saveFileName; // 파일 전체 경로
			
			//DB저장
			YuhanFileVO vo = new YuhanFileVO();
			vo.setHomeWorkNO(multipartRequest.getParameter("hwno"));
			vo.setProfessorNo(multipartRequest.getParameter("professorNo"));
			vo.setSubjectID(multipartRequest.getParameter("subjectID"));
			vo.setFileName(oldFileName);
			vo.setFileSize(FileSize);
			vo.setFileSaveUrl(fileFullPath);
			vo.setSaveFileName(saveFileName);
			vo.setFilePath(filePath);
			
			sqlSession.insert(NAMESPACE + ".fileDBUp", vo);
			try {
				// 파일 저장
				mpf.transferTo(new File(fileFullPath));
				oldNames.add(oldFileName);
				saveNames.add(saveFileName);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		fileName.put("oldNames", oldNames);
		fileName.put("saveNames", saveNames);
	}

	

	@Override
	public void fileDownload(String fileSaveUrl, String fileName) {
		try {
			fileByte = FileUtils.readFileToByteArray(new File(fileSaveUrl));
			resp.setContentType("application/octet-stream");
			resp.setContentLength(fileByte.length);
			resp.setHeader("Content-Disposition", "attachment; fileName=\"" + URLEncoder.encode(fileName, "UTF-8") + "\";");
			resp.setHeader("Content-Transfer-Encoding", "binary");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public List<String> fileDbUp(MultipartHttpServletRequest multipartRequest) {
		return null;
	}

	@Override
	public YuhanFileVO readFile(Map<Integer, Integer> parameters) {
		return null;
	}

	@Override
	public YuhanFileVO selectDataStatus(int hwno) {
		return sqlSession.selectOne(NAMESPACE+".selectDataStatus", hwno);
	}

	@Override
	public int fileCount(int hwno) {
		return sqlSession.selectOne(NAMESPACE+".fileCount", hwno);
	}

	@Override
	public List<YuhanFileVO> selectFileInfo(int hwno) {
		return sqlSession.selectList(NAMESPACE+".fileInfo", hwno);
	}

	@Override
	public void deleteFile(String saveFileName, String filePath) {
		
		sqlSession.delete(NAMESPACE+".fileDelete", saveFileName);
		
		String fileURL = filePath + "\\" + saveFileName;
		System.out.println("dao : " + fileURL);
		File file = new File(fileURL);
		
		if(file.exists() == true) {
			file.delete();
		}
	}

	@Override
	public void ReportFileServerUp(String filePath, MultipartHttpServletRequest multipartRequest) {
		Iterator<String> itr = multipartRequest.getFileNames();
		List<String> oldNames = new ArrayList<String>();
		List<String> saveNames = new ArrayList<String>();
		StringBuffer sb = null;
		
		while (itr.hasNext()) {
			File targetDir = new File(filePath); //폴더 없으면 새로 생성
			if(!targetDir.exists()) {
				targetDir.mkdirs();
	        }
			MultipartFile mpf = multipartRequest.getFile(itr.next());
			sb = new StringBuffer();
			String oldFileName = mpf.getOriginalFilename();
			int FileSize = (int) mpf.getSize();
			String saveFileName = sb.append(new SimpleDateFormat("yyyyMMddhhmmss").format(System.currentTimeMillis()))
					.append(UUID.randomUUID().toString())
					.append(oldFileName.substring(oldFileName.lastIndexOf("."))).toString();
			String fileFullPath = filePath + "/" + saveFileName; // 파일 전체 경로
			
			//DB저장
			YuhanReportFileVO vo = new YuhanReportFileVO();
			vo.setHomeWorkNO(multipartRequest.getParameter("hwno"));
			vo.setProfessorNo(multipartRequest.getParameter("professorNo"));
			vo.setSubjectID(multipartRequest.getParameter("subjectID"));
			vo.setFileName(oldFileName);
			vo.setFileSize(FileSize);
			vo.setFileSaveUrl(fileFullPath);
			vo.setSaveFileName(saveFileName);
			vo.setFilePath(filePath);
			vo.setMemberID(multipartRequest.getParameter("studentID"));
			
			sqlSession.insert(NAMESPACE + ".reportfileDBUp", vo);
			try {
				// 파일 저장
				mpf.transferTo(new File(fileFullPath));
				oldNames.add(oldFileName);
				saveNames.add(saveFileName);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		fileName.put("oldNames", oldNames);
		fileName.put("saveNames", saveNames);
	}

	@Override
	public int ReportCount(int hwno, String studentID) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("hwno", hwno);
		map.put("studentID", studentID);
		return sqlSession.selectOne(NAMESPACE + ".reportCount", map);
	}

	@Override
	public List<YuhanReportFileVO> selectReportFileInfo(int hwno, String studentID) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("hwno", hwno);
		map.put("studentID", studentID);
		
		return sqlSession.selectList(NAMESPACE+".fileReportInfo", map);
	}



	@Override
	public void reportDeleteFile(String saveFileName, String filePath) {
		sqlSession.delete(NAMESPACE+".reportFileDelete", saveFileName);
		
		String fileURL = filePath + "\\" + saveFileName;
		System.out.println("dao : " + fileURL);
		File file = new File(fileURL);
		
		if(file.exists() == true) {
			file.delete();
		}
	}



	@Override
	public void reportAllDelete(int homeworkNo, String studentID) {
		
	}

}
