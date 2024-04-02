package com.examHaha.Repository;


import com.examHaha.Model.Exam;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExamRepository extends CrudRepository<Exam, Integer> {
    List<Exam> findByExamName(String examName);
    List<Exam> findByType(String type);
    boolean existsByExamName(String examName);

    List<Exam> findByExamId(int examId);

    void deleteByExamName(String examName);

}
