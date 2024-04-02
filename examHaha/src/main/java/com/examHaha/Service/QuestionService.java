package com.examHaha.Service;

import com.examHaha.Model.Question;
import com.examHaha.Repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class QuestionService {
    @Autowired
    QuestionRepository questionRepository;

    public Question createQuestion(Question question){
        return questionRepository.save(question);
    }

    public List<Question> getAllQuestions() {
        return (List<Question>) questionRepository.findAll();
    }

    public void deleteByExamId(int examId) {
        List<Question> questions = getAllQuestions();
        for(int i = 0 ; i < questions.size() ; i++){
            if(questions.get(i).getExamId() == examId){
                int id = questions.get(i).getQuestionId();
                questionRepository.deleteById(id);
            }
        }
    }
}
