package com.examHaha.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int questionId;

    private String content;
    private String optionA;
    private String optionB;
    private String optionC;
    private String optionD;
    private String optionCorrect;
    private int examId;
//    private int contestId;
//
//    @ManyToOne
//    @JsonIgnore
//    @JoinColumn(name = "examId", insertable = false, updatable=false)
//    private Exam exam;
//
//    @OneToMany
//    @JsonIgnore
//    @JoinColumn(name = "questionId", insertable = false, updatable=false)
//    private List<UserAnswer> userAnswers;
}
