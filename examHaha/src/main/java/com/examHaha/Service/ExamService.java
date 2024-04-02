package com.examHaha.Service;

import com.examHaha.Model.Exam;
import com.examHaha.Model.Question;
import com.examHaha.Repository.ExamRepository;
import com.examHaha.Repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class ExamService {
    @Autowired
    ExamRepository examRepository;
    @Autowired
    QuestionRepository questionRepository;
    @Autowired
    QuestionService questionService;

    public Exam createExam(Exam exam){
        if (examRepository.existsByExamName(exam.getExamName())) {
            return null; // Nếu exam đã tồn tại, trả về null
        }
        return examRepository.save(exam);
    }

    public List<Exam> findExamByExamName(String examName){
        return examRepository.findByExamName(examName);
    }

    public ResponseEntity<?> getExamByExamName(String examName) {
        List<Exam> exam = examRepository.findByExamName(examName);

        return ResponseEntity.ok(exam);
    }

    public List<Exam> getAllExams() {
        return (List<Exam>) examRepository.findAll();
    }

    public void deleteExamByExamName(String examName) {
        List<Exam> exams = examRepository.findByExamName(examName);
        int id = exams.get(0).getExamId();
        questionService.deleteByExamId(id);
        examRepository.deleteById(id);
    }

}
