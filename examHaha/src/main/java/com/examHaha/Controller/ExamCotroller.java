package com.examHaha.Controller;

import com.examHaha.Repository.ExamRepository;
import com.examHaha.Service.ExamService;
import com.examHaha.Model.Exam;
import com.examHaha.Model.Question;
import com.examHaha.Repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ExamCotroller {

    private final ExamService examService;
    @Autowired
    private ExamRepository examRepository;
    @Autowired
    private QuestionRepository questionRepository;

    @PostMapping("/createExam")
    public ResponseEntity<Exam> createExam(@RequestBody Exam exam) {
        ResponseEntity<Exam> response = null;

        try {
            Exam savedExam = examService.createExam(exam);
            if(savedExam != null) {
                response = ResponseEntity.status(HttpStatus.CREATED).body(savedExam);
            } else {
                response = ResponseEntity.status(HttpStatus.CONFLICT).body(null);
            }
        }catch (Exception exception) {
            response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
        return response;
    }

    @GetMapping("/exams/{examName}")
    public ResponseEntity<?> getExamByExamName(@PathVariable String examName) {
        List<Exam> exams = examRepository.findByExamName(examName);
        if (exams.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Không tìm thấy kỳ thi với tên: " + examName);
        } else {
            return ResponseEntity.ok(exams);
        }
    }
    @GetMapping("/exams1/{examId}")
    public ResponseEntity<?> getExamByExamId(@PathVariable int examId) {
        List<Exam> exams = examRepository.findByExamId(examId);
        if (exams.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Không tìm thấy kỳ thi với id: " + examId);
        } else {
            return ResponseEntity.ok(exams);
        }
    }




    @GetMapping("/listExam")
    public ResponseEntity<List<Exam>> getAllExams() {
        List<Exam> exams = examService.getAllExams();
        if (!exams.isEmpty()) {
            return ResponseEntity.ok(exams);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    //Don't use
    @PutMapping("/editExam/{examName}")
    public ResponseEntity<Exam> updateExam(@PathVariable String examName, @RequestBody Exam examDetails) {
        List<Exam> exams = examRepository.findByExamName(examName);
        if (!exams.isEmpty()) {
            Exam existingExam = exams.get(0); // Lấy exam đầu tiên từ danh sách
            existingExam.setExamName(examDetails.getExamName());
            existingExam.setTime(examDetails.getTime());
            existingExam.setType(examDetails.getType());

            final Exam updatedExam = examRepository.save(existingExam);
            return ResponseEntity.ok(updatedExam);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @DeleteMapping("/deleteExam/{examName}")
    public ResponseEntity<String> deleteExamByExamName(@PathVariable("examName") String examName) {
        examService.deleteExamByExamName(examName);
        return ResponseEntity.ok().body("Exam with name " + examName + " have been successfully deleted.");

    }



}
