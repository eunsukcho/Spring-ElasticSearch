package kr.yuhan.persistence;

import kr.yuhan.domain.TestLoginVO;

public interface TestLoginDAO {
	
	public int checkStudentLogin(TestLoginVO vo);
	public TestLoginVO StudentData(TestLoginVO vo);
	public int checkProfessorLogin(TestLoginVO vo);
	public TestLoginVO ProfessorData(TestLoginVO vo);
}
