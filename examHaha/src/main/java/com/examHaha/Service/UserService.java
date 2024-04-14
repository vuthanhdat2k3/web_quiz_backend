package com.examHaha.Service;

import com.examHaha.Model.User;
import com.examHaha.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    UserRepository userRepository;

    public User createUser(User user){
        if (userRepository.existsByUsername(user.getUsername())) {
            return null; // Nếu username đã tồn tại, trả về null
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public User loginUser(String username, String password, String Role) {
        Optional<User> optionalCustomer = userRepository.findByUsername(username);
        if (optionalCustomer.isPresent()) {
            User user = optionalCustomer.get();
            if (passwordEncoder.matches(password, user.getPassword()) && Role.equals("user")) {
                return user; // Trả về khách hàng nếu mật khẩu khớp
            }
        }
        return null; // Trả về null nếu đăng nhập thất bại
    }

    public User loginAdmin(String username, String password, String Role) {
        Optional<User> optionalCustomer = userRepository.findByUsername(username);
        if (optionalCustomer.isPresent()) {
            User user = optionalCustomer.get();
            if (passwordEncoder.matches(password, user.getPassword()) && Role.equals("admin")) {
                return user; // Trả về khách hàng nếu mật khẩu khớp
            }
        }
        return null; // Trả về null nếu đăng nhập thất bại
    }


    public boolean existsByUsername(String username) {
        // Kiểm tra xem username đã tồn tại trong cơ sở dữ liệu hay chưa
        return userRepository.existsByUsername(username);
    }

    public List<User> getAllUsers() {
        return (List<User>) userRepository.findAll();
    }
    public User updateUser(String username, User userDetails) {
        Optional<User> optionalCustomer = userRepository.findByUsername(username);
        if (optionalCustomer.isPresent()) {
            User existingUser = optionalCustomer.get();
            existingUser.setEmail(userDetails.getEmail());
            existingUser.setPassword(passwordEncoder.encode(userDetails.getPassword()));
            return userRepository.save(existingUser);
        } else {
            return null;
        }

    }

    public void deleteUser(String username) {
        int id = userRepository.findByUsername(username).get().getUserId();
        userRepository.deleteById(id);
    }


    public Optional<User> findUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
