package idu.cs.service;

import java.util.List;

import org.springframework.stereotype.Service;

import idu.cs.domain.Question;

@Service("questionService") //이줄이 없으면 빈즈객채로 만들어져 주입이 되지 안흠, 이건 빈즈객체 만드는 것임
public class QuestionServiceImpl implements QuestionService {

	@Override
	public Question getUserById(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Question> getQuestions() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Question> getQuestionsByUser(String user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveUser(Question question) {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateUser(Question question) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteUser(Question question) {
		// TODO Auto-generated method stub

	}

}
