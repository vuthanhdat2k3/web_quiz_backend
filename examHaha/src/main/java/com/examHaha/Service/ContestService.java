package com.examHaha.Service;

import com.examHaha.Model.Contest;
import com.examHaha.Model.Exam;
import com.examHaha.Repository.ContestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContestService {
    @Autowired
    ContestRepository contestRepository;

    public Contest createContest(Contest contest){
        return contestRepository.save(contest);
    }
}
