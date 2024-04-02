package com.examHaha.Repository;

import com.examHaha.Model.Contest;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContestRepository extends CrudRepository<Contest, Integer> {
    List<Contest> findByUserId(int userId);
    List<Contest> findByContestId(int contestId);

    boolean existsByContestId(int contestId);

    List<Contest> findByExamName(String examName);
}
