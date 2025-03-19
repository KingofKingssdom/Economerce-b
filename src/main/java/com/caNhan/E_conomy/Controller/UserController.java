package com.caNhan.E_conomy.Controller;

import com.caNhan.E_conomy.Dto.UserDto;
import com.caNhan.E_conomy.Entity.Cart;
import com.caNhan.E_conomy.Entity.User;
import com.caNhan.E_conomy.Service.UserService;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(User.class);
    private UserService userService;
    private PasswordEncoder passwordEncoder;
    @Autowired
    public UserController(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }
    @PostMapping("/add")
    public ResponseEntity<?> saveUser(@RequestBody User user){
        if (userService.findByEmail(user.getEmail()).isPresent()){
            return ResponseEntity.badRequest().body("Email đã được sử dụng!");
        }
        User saveUser = userService.saveUser(user);
        return ResponseEntity.ok(saveUser);
    }
    @GetMapping("/getAll")
    public ResponseEntity<?> getAllUser(){
        List<UserDto> userDtos = userService.findAllUser();
        return ResponseEntity.ok(userDtos);
    }
    @GetMapping("{id}")
    public ResponseEntity<?> getUserById(@PathVariable int id) {
        UserDto userDto = userService.findUserById(id);
        return ResponseEntity.ok(userDto);
    }
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user, HttpSession session) {
        Optional<User> existingUser = userService.findByEmail(user.getEmail());
        if (existingUser.isPresent() && passwordEncoder.matches(user.getPassword(), existingUser.get().getPassword())) {
            session.setAttribute("user", existingUser.get()); // Lưu user vào session
            return ResponseEntity.ok(existingUser);
        }
        return ResponseEntity.status(401).body("Email hoặc mật khẩu không đúng!");
    }

    @GetMapping("/me")
    public ResponseEntity<?> getCurrentUser(HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return ResponseEntity.status(401).body("Chưa đăng nhập!");
        }
        return ResponseEntity.ok(new UserResponse(user.getId(), user.getFirstName()));
    }
    public record UserResponse(int id, String firstName) {}

}
