package idu.cs.service;

import idu.cs.domain.Question;
import idu.cs.domain.User;
import java.util.List;

public interface QuestionService {
	Question getUserById(long id); // primary key에 해당하는 id로  조회
	List<Question> getQuestions(); // 모든 사용자 조회
	
	List<Question> getQuestionsByUser(String user); // name으로 조회
	
	void saveUser(Question question); // 생성
	void updateUser(Question question); // 수정
	void deleteUser(Question question); // 삭제
}
