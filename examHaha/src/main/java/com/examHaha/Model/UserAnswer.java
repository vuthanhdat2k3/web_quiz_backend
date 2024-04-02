package com.examHaha.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "UserAnswer")
public class UserAnswer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userAnswerId;

    private String selectedAnswer;

    private int userId;
    private int examId;
    private int questionId;

    public UserAnswer(int userId, int examId, int questionId, String selectedAnswer) {
        this.selectedAnswer = selectedAnswer;
        this.userId = userId;
        this.examId = examId;
        this.questionId = questionId;
    }
}
