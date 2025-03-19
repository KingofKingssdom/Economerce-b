package com.caNhan.E_conomy.Service;

import com.caNhan.E_conomy.Dto.UserDto;
import com.caNhan.E_conomy.Entity.Cart;
import com.caNhan.E_conomy.Entity.User;
import com.caNhan.E_conomy.Repository.CartRepository;
import com.caNhan.E_conomy.Repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {
    private UserRepository userRepository;
    private CartRepository cartRepository;
    private ModelMapper modelMapper;
    private PasswordEncoder passwordEncoder;
    @Autowired
    public UserService(UserRepository userRepository, CartRepository cartRepository, ModelMapper modelMapper, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.cartRepository = cartRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
    }
    public User saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        Cart cart = new Cart();
        cart.setUser(user);
        cartRepository.save(cart);
        return user;
    }
    public List<UserDto> findAllUser() {
        List<User> users = userRepository.findAll();
        List<UserDto> userDtos = new ArrayList<>();
        for(User user: users){
            UserDto userDto = modelMapper.map(user,UserDto.class);
            userDtos.add(userDto);
        }
        return userDtos;
    }
    public UserDto findUserById(int id){
        User user = userRepository.findById(id).orElseThrow();
        UserDto userDto = modelMapper.map(user,UserDto.class);
        return userDto;
    }
    public User findUserId (int id){
        return  userRepository.findById(id).orElseThrow();
    }
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

}
