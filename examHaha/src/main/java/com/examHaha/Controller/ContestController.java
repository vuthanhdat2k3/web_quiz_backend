package com.examHaha.Controller;

import com.examHaha.Model.Contest;
import com.examHaha.Model.Exam;
import com.examHaha.Repository.ContestRepository;
import com.examHaha.Service.ContestService;
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
public class ContestController {
    @Autowired
    ContestService contestService;
    @Autowired
    ContestRepository contestRepository;

    @PostMapping("/createContest")
    public ResponseEntity<Contest> createContest(@RequestBody Contest contest) {
        ResponseEntity<Contest> response = null;

        try {
            Contest savedContest = contestService.createContest(contest);
            if(savedContest != null) {
                response = ResponseEntity.status(HttpStatus.CREATED).body(savedContest);
            } else {
                response = ResponseEntity.status(HttpStatus.CONFLICT).body(null);
            }
        }catch (Exception exception) {
            response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
        return response;
    }

    @PutMapping("/updateContest/{contestId}")
    public ResponseEntity<?> updateContest(@PathVariable int contestId, @RequestBody Contest updatedContest) {
//        // Kiểm tra xem cuộc thi có tồn tại không
//        if (!contestRepository.existsById(contestId)) {
//            return new ResponseEntity<>("Contest not found with id: " + contestId, HttpStatus.NOT_FOUND);
//        }

        // Lấy ra cuộc thi cần cập nhật từ cơ sở dữ liệu
        Contest existingContest = contestRepository.findById(contestId).orElse(null);

        // Cập nhật thông tin của cuộc thi
        if (existingContest != null) {
            existingContest.setFullName(updatedContest.getFullName());
            existingContest.setMaSV(updatedContest.getMaSV());
            existingContest.setScore(updatedContest.getScore());
            existingContest.setExamName(updatedContest.getExamName());
            existingContest.setUserId(updatedContest.getUserId());

            // Lưu cuộc thi đã cập nhật vào cơ sở dữ liệu
            contestRepository.save(existingContest);

            return new ResponseEntity<>("Contest updated successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Failed to update contest", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/contests/{examName}")
    public ResponseEntity<?> getContestByExamName(@PathVariable String examName) {
        List<Contest> contests = contestRepository.findByExamName(examName);
        if (contests.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Không tìm thấy kỳ thi với tên: " + examName);
        } else {
            return ResponseEntity.ok(contests);
        }
    }
}
