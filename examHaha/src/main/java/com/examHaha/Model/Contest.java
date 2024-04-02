package com.examHaha.Model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Contest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int contestId;

    private String fullName;
    private String maSV;
    private double score;
    private String examName;
    private int userId;
}
