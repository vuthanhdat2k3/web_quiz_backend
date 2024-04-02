package com.examHaha.Controller;

import com.examHaha.Model.Exam;
import com.examHaha.Model.Question;
import com.examHaha.Repository.QuestionRepository;
import com.examHaha.Service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@ComponentScan
public class QuestionController {
    private final QuestionService questionService;
    private final QuestionRepository questionRepository;

    @PutMapping("/editQuestion/{examId}")
    public ResponseEntity<?> updateQuestionByExamId(@PathVariable int examId, @RequestBody Question questionDetails) {
        List<Question> questions = questionRepository.findByExamId(examId);
        if (!questions.isEmpty()) {
            for (Question question : questions) {
                // Kiểm tra nếu questionDetails có trùng với question trong danh sách questions
                if (question.getQuestionId() == questionDetails.getQuestionId()) {
                    // Cập nhật thông tin của question
                    question.setContent(questionDetails.getContent());
                    question.setOptionA(questionDetails.getOptionA());
                    question.setOptionB(questionDetails.getOptionB());
                    question.setOptionC(questionDetails.getOptionC());
                    question.setOptionD(questionDetails.getOptionD());
                    question.setOptionCorrect(questionDetails.getOptionCorrect());

                    // Lưu question đã cập nhật
                    Question updatedQuestion = questionRepository.save(question);
                    return ResponseEntity.ok(updatedQuestion);
                }
            }
            // Nếu không tìm thấy question trong danh sách
            return ResponseEntity.notFound().build();
        } else {
            // Nếu không tìm thấy danh sách question
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Không tìm thấy câu hỏi cho kỳ thi với id: " + examId);
        }
    }

    @PostMapping("/createQuestion")
    public ResponseEntity<Question> createExam(@RequestBody Question question) {
        ResponseEntity<Question> response = null;

        try {
            Question savedQuestion = questionService.createQuestion(question);
            if(savedQuestion != null) {
                response = ResponseEntity.status(HttpStatus.CREATED).body(savedQuestion);
            } else {
                response = ResponseEntity.status(HttpStatus.CONFLICT).body(null);
            }
        }catch (Exception exception) {
            response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
        return response;
    }
    @GetMapping("/listQuestion")
    public ResponseEntity<List<Question>> getAllQuestion() {
        List<Question> questions = questionService.getAllQuestions();
        if (!questions.isEmpty()) {
            return ResponseEntity.ok(questions);
        } else {
            return ResponseEntity.noContent().build();
        }
    }
    @DeleteMapping("/deleteQuestion/{examId}")
    public ResponseEntity<String> deleteQuestionsByExamId(@PathVariable int examId) {
        try {
            questionService.deleteByExamId(examId);
            return ResponseEntity.ok().body("Questions for exam with ID " + examId + " have been successfully deleted.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to delete questions for exam with ID " + examId + ".");
        }
    }
    @DeleteMapping("/deleteQuestionById/{questionId}")
    public ResponseEntity<String> deleteQuestionById(@PathVariable int questionId) {
        try {
            questionRepository.deleteById(questionId);
            return ResponseEntity.ok().body("Question with ID " + questionId + " has been successfully deleted.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to delete question with ID " + questionId + ".");
        }
    }


}
