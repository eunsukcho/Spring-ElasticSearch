package kr.yuhan.persistence;

import java.io.File;
import java.io.InputStream;
import java.net.URLEncoder;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.MultipartRequest;

import kr.yuhan.domain.YuhanFileVO;
import kr.yuhan.domain.YuhanHomeworkVO;

@Repository
public class YuhanFileDAOImpl implements YuhanFileDAO{

	@Inject
	private SqlSession sqlSession;
	static final String NAMESPACE = "kr.yuhan.mapper.YuhanFileMapper";
	String filePath;
	String fileFullPath;
	
	@Override
	public void fileServerUp(MultipartHttpServletRequest multipartRequest) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd", Locale.ENGLISH);
		Date today = new Date(System.currentTimeMillis());
		String todayString = dateFormat.format(today);
		
		filePath = "C:/fileUploadtest" + "-" + todayString;
		int i = 0;
		
		Iterator<String> itr = multipartRequest.getFileNames();
		
		while(itr.hasNext()) {
			i++;
			MultipartFile mpf = multipartRequest.getFile(itr.next());
			System.out.println("길이 : " + multipartRequest.getContentLength());
			String originalFileName = mpf.getOriginalFilename(); //fileName
			
			fileFullPath = filePath+"/"+originalFileName; //fileURL
			
			File targetDir = new File(filePath); //폴더 없으면 새로 생성
			if(!targetDir.exists()) {
				targetDir.mkdirs();
	        }
			
			try {
				mpf.transferTo(new File(fileFullPath));	
				System.out.println("originalFilename => "+originalFileName);
	            System.out.println("fileFullPath => "+fileFullPath);
	        } catch (Exception e) {
	            System.out.println("postTempFile_ERROR======>"+fileFullPath);
	            e.printStackTrace();
	        }
		}
		System.out.println(i);
		
	}

	@Override
	public void fileDownload(String fileSaveUrl, String fileName) {
	    
	}
	
	@Override
	public List<String> fileDbUp(MultipartHttpServletRequest multipartRequest) {
		String filePath = "C:/test3";
		String FileURL = null;
		ArrayList<String> FileUrlList = new ArrayList<String>();
		
		Iterator<String> itr = multipartRequest.getFileNames();
		while(itr.hasNext()) {
			MultipartFile mpf = multipartRequest.getFile(itr.next());
			
			String FileName = mpf.getOriginalFilename(); 
			int FileSize = (int) mpf.getSize();
			
			FileURL = filePath + "/" + FileName;
			FileUrlList.add(FileURL);
			
			System.out.println("daofile : " + multipartRequest.getParameter("hwno"));
			
			YuhanFileVO vo = new YuhanFileVO();
			vo.setHomeWorkNO(multipartRequest.getParameter("hwno"));
			vo.setProfessorNo(multipartRequest.getParameter("professorNo"));
			vo.setSubjectID(multipartRequest.getParameter("subjectID"));
			vo.setFileName(FileName);
			vo.setFileSize(FileSize);
			vo.setFileSaveUrl(fileFullPath);
			
			sqlSession.insert(NAMESPACE + ".fileDBUp", vo);
		}
		for (int i = 0; i < FileUrlList.size(); i++) {
			System.out.println(FileUrlList.get(i));
		}
		return FileUrlList;
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

	

}
