package com.caNhan.E_conomy.Controller;

import com.caNhan.E_conomy.Dto.ResponseDto.UserResponseDTO;
import com.caNhan.E_conomy.Dto.UserDto;
import com.caNhan.E_conomy.Entity.User;
import com.caNhan.E_conomy.Response.ResponseData;
import com.caNhan.E_conomy.Service.UserService;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {
//    private UserService userService;
//    //private PasswordEncoder passwordEncoder;
//    @Autowired
//    public UserController(UserService userService) {
//        this.userService = userService;
//    }
//    @PostMapping("/register")
//    private ResponseEntity<?> saveUser(@ModelAttribute UserDto userDto){
//        UserResponseDTO userResponseDTO = userService.create(userDto);
//        ResponseData responseData = new ResponseData(
//                HttpStatus.OK.value(),
//                "Tạo user thành công",
//                userResponseDTO
//        );
//        return ResponseEntity.ok(responseData);
//
//    }
//    @GetMapping("/getAll")
//    public ResponseEntity<?> getAllUser(){
//        List<UserDto> userDtos = userService.findAllUser();
//        return ResponseEntity.ok(userDtos);
//    }
//    @GetMapping("{id}")
//    public ResponseEntity<?> getUserById(@PathVariable int id) {
//        UserDto userDto = userService.findUserById(id);
//        return ResponseEntity.ok(userDto);
//    }
////    @PostMapping("/login")
////    public ResponseEntity<?> login(@RequestBody User user, HttpSession session) {
////        Optional<User> existingUser = userService.findByEmail(user.getEmail());
////        if (existingUser.isPresent() && passwordEncoder.matches(user.getPassword(), existingUser.get().getPassword())) {
////            session.setAttribute("user", existingUser.get()); // Lưu user vào session
////            return ResponseEntity.ok(existingUser);
////        }
////        return ResponseEntity.status(401).body("Email hoặc mật khẩu không đúng!");
////    }
//
//    @GetMapping("/me")
//    public ResponseEntity<?> getCurrentUser(HttpSession session) {
//        User user = (User) session.getAttribute("user");
//        if (user == null) {
//            return ResponseEntity.status(401).body("Chưa đăng nhập!");
//        }
//        return ResponseEntity.ok(new UserResponse(user.getId(), user.getFirstName()));
//    }
//    public record UserResponse(int id, String firstName) {}

}
