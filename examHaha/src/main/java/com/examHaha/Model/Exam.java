package com.examHaha.Model;

import com.examHaha.Model.Question;
import com.examHaha.Model.UserAnswer;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Exam {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int examId;

    private String examName;
    private int time;
    private String type;

//    @OneToMany(mappedBy = "exam")
//    @JsonIgnore
//    private List<ExamResult> examResults;
//
//    @OneToMany(mappedBy = "exam")
//    @JsonIgnore
//    private List<ExamAttempt> examAttempts;

//    @JsonIgnore
//    @OneToMany(mappedBy = "exam")
//    private List<Question> questions;
//
////    @JsonIgnore
////    @OneToOne(mappedBy = "exam")
////    private ExamStatistic examStatistic;
//
//    @JsonIgnore
//    @OneToMany(mappedBy = "exam")
//    private List<UserAnswer> userAnswers;
}
