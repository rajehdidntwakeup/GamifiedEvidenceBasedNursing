package bswe.gamifiedevidencebasednursing.service;

import java.util.Collections;
import java.util.List;

import bswe.gamifiedevidencebasednursing.domain.Question;
import bswe.gamifiedevidencebasednursing.repository.QuestionRepository;
import org.springframework.stereotype.Service;

@Service
public class QuestionService {

  private QuestionRepository questionRepository;

  public QuestionService(QuestionRepository questionRepository) {
    this.questionRepository = questionRepository;
  }

  public List<Question> getRoomOfKnowledgeQuestionList() {
    List<Question> questionList = questionRepository.findAll();
    Collections.shuffle(questionList);
    return questionList.subList(0, 10);
  }
}
