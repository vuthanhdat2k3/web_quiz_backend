package com.examHaha.Controller;

import com.examHaha.Model.Exam;
import com.examHaha.Model.UserAnswer;
import com.examHaha.Service.UserAnswerService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/v1")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class UserAnswerController {

    @Autowired
    UserAnswerService userAnswerService;

    @PostMapping("/postAnswer")
    public ResponseEntity<UserAnswer> createUserAnswer(@RequestBody UserAnswer userAnswer) {
        ResponseEntity<UserAnswer> response = null;

        try {
            UserAnswer savedUserAnswer = userAnswerService.createUserAnswer(userAnswer);
            if(savedUserAnswer != null) {
                response = ResponseEntity.status(HttpStatus.CREATED).body(savedUserAnswer);
            } else {
                response = ResponseEntity.status(HttpStatus.CONFLICT).body(null);
            }
        }catch (Exception exception) {
            response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
        return response;
    }
}
