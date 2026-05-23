package com.caNhan.E_conomy.Service.Impl;

import com.caNhan.E_conomy.Config.JwtTokenProvider;
import com.caNhan.E_conomy.Dto.RequestDto.ReqLoginDto;
import com.caNhan.E_conomy.Entity.User;
import com.caNhan.E_conomy.GlobalExeption.Exception.NoSuchCustomerExistsException;
import com.caNhan.E_conomy.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoginServiceImpl {
    private PasswordEncoder passwordEncoder;
    private UserRepository userRepository;
    private JwtTokenProvider jwtTokenProvider;
    @Autowired
    public LoginServiceImpl(PasswordEncoder passwordEncoder, UserRepository userRepository, JwtTokenProvider jwtTokenProvider) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.jwtTokenProvider = jwtTokenProvider;
    }
    public String authenticateUser(ReqLoginDto reqLoginDto){
        Optional<User> userOptional = userRepository.findByEmail(reqLoginDto.getEmail());
        if(userOptional.isEmpty()){
            throw new NoSuchCustomerExistsException("User not found with email " + reqLoginDto.getEmail());
        }
        User user = userOptional.get();
        boolean isPasswordMatch = passwordEncoder.matches(reqLoginDto.getPassword(), user.getPassword());
        if(!isPasswordMatch){
            throw new NoSuchCustomerExistsException("Password not found. Please check again");
        }
        return jwtTokenProvider.generateToken(user.getEmail(), user.getFullName(),user.getId());
    }
}
