package com.caNhan.E_conomy.Controller;

import com.caNhan.E_conomy.Custom.CustomUserDetail;
import com.caNhan.E_conomy.Dto.RequestDto.ReqLoginDto;
import com.caNhan.E_conomy.Dto.ResponseDto.LoginResponseDTO;
import com.caNhan.E_conomy.Dto.ResponseDto.ResUserDto;
import com.caNhan.E_conomy.Dto.RequestDto.ReqUserDto;
import com.caNhan.E_conomy.Response.ResponseData;
import com.caNhan.E_conomy.Service.Impl.LoginServiceImpl;
import com.caNhan.E_conomy.Service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private UserService userService;
//    private AuthenticationManager authenticationManager;
    private LoginServiceImpl loginService;
    @Autowired
    public AuthController(UserService userService, LoginServiceImpl loginService) {
        this.userService = userService;
        this.loginService = loginService;
    }
    @PostMapping("/register/{roleName}")
    private ResponseEntity<?> saveUser(@ModelAttribute ReqUserDto reqUserDto,
                                        @PathVariable String roleName){
        ResUserDto resUserDto = userService.createUser(reqUserDto, roleName);
        ResponseData responseData = new ResponseData(
                HttpStatus.OK.value(),
                "Data retrieved successfully",
                resUserDto
        );
        return ResponseEntity.ok(responseData);

    }

    @PostMapping("/login")
    private ResponseEntity<?> login(@ModelAttribute ReqLoginDto reqLoginDto){
        String text = loginService.authenticateUser(reqLoginDto);
        return ResponseEntity.ok(text);
    }
//    @PostMapping("/login")
//    private ResponseEntity<?> login (@RequestBody ReqLoginDto login, HttpServletRequest request) {
//        UsernamePasswordAuthenticationToken token =
//                new UsernamePasswordAuthenticationToken(login.getPhoneNumber(), login.getPassword());
//        Authentication authentication = authenticationManager.authenticate(token);
//        SecurityContext securityContext = SecurityContextHolder.getContext();
//        securityContext.setAuthentication(authentication);
//
//        //  lưu SecurityContext vào HttpSession
//        HttpSession session = request.getSession(true);
//        session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, securityContext);
//
//        CustomUserDetail userDetails = (CustomUserDetail) authentication.getPrincipal();
//
//        LoginResponseDTO loginResponseDTO = new LoginResponseDTO();
//        loginResponseDTO.setId(userDetails.getId());
//        loginResponseDTO.setFullName(userDetails.getUsername());
//        session.setAttribute("USER_ID", userDetails.getId());
//        return ResponseEntity.ok(loginResponseDTO);
//    }
//
//    @PostMapping("/admin/login")
//    private ResponseEntity<?> loginAdmin (@RequestBody ReqLoginDto login, HttpServletRequest request) {
//        UsernamePasswordAuthenticationToken token =
//                new UsernamePasswordAuthenticationToken(login.getPhoneNumber(), login.getPassword());
//        Authentication authentication = authenticationManager.authenticate(token);
//        SecurityContext securityContext = SecurityContextHolder.getContext();
//        securityContext.setAuthentication(authentication);
//
//        //  lưu SecurityContext vào HttpSession
//        HttpSession session = request.getSession(true);
//        session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, securityContext);
//
//        CustomUserDetail userDetails = (CustomUserDetail) authentication.getPrincipal();
//
//        LoginResponseDTO loginResponseDTO = new LoginResponseDTO();
//        loginResponseDTO.setId(userDetails.getId());
//        loginResponseDTO.setFullName(userDetails.getUsername());
//        session.setAttribute("USER_ID", userDetails.getId());
//        return ResponseEntity.ok(loginResponseDTO);
//    }
}
