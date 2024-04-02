package com.examHaha.Controller;

import com.examHaha.Model.User;
import com.examHaha.Repository.ExamRepository;
import com.examHaha.Service.ExamService;
import com.examHaha.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/v1")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class UserController {

    private final UserService userService;

    private final ExamService examService;
    @Autowired
    private ExamRepository examRepository;


    @GetMapping("/users/{username}")
    public ResponseEntity<User> getUserByUsername(@PathVariable String username) {
        Optional<User> optionalCustomer = userService.findUserByUsername(username);
        if (optionalCustomer.isPresent()) {
            return ResponseEntity.ok(optionalCustomer.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/register")
    public ResponseEntity<User> registerCustomer(@RequestBody User user) {
        ResponseEntity<User> response = null;

        try {
            User savedUser = userService.createUser(user);
            if (savedUser != null) {
                response = ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
            } else {
                // Username đã tồn tại, trả về lỗi 409 Conflict
                response = ResponseEntity.status(HttpStatus.CONFLICT).body(null);
            }
        } catch (Exception exception) {
            response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
        return response;
    }

    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody User user) {
        User loggedInUser = userService.loginUser(user.getUsername(), user.getPassword());
        if (loggedInUser != null) {
            // Login successful, return the logged-in customer
            return ResponseEntity.ok(loggedInUser);
        } else {
            // Login failed
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
    }

    @GetMapping("/listUser")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        if (!users.isEmpty()) {
            return ResponseEntity.ok(users);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @PutMapping("/edit/{username}")
    public ResponseEntity<User> updateCustomer(@PathVariable("username") String username, @RequestBody User userDetails) {
        User updatedUser = userService.updateUser(username, userDetails);
        if (updatedUser != null) {
            return ResponseEntity.ok(updatedUser);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/delete/{username}")
    public ResponseEntity<Void> deleteUser(@PathVariable("username") String username) {
        userService.deleteUser(username);
        return ResponseEntity.noContent().build();
    }


}
