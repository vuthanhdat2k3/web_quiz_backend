package com.examHaha.Repository;

import com.examHaha.Model.Question;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface QuestionRepository extends CrudRepository<Question, Integer> {
    List<Question> findByExamId(int examId);

    List<Question> findByQuestionId(int questionId);

    void deleteByExamId(int examId);

}
