package com.examHaha.Repository;

import com.examHaha.Model.UserAnswer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserAnswerRepository extends CrudRepository<UserAnswer, Integer> {

    List<UserAnswer> findByQuestionId(int questionId);
}
