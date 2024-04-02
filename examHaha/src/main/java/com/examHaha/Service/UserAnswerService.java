package com.examHaha.Service;

import com.examHaha.Model.Exam;
import com.examHaha.Model.UserAnswer;
import com.examHaha.Repository.UserAnswerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserAnswerService {
    @Autowired
    UserAnswerRepository userAnswerRepository;

    public UserAnswer createUserAnswer(UserAnswer userAnswer){
        return userAnswerRepository.save(userAnswer);
    }
}
