package com.caNhan.E_conomy.Service.Impl;

import com.caNhan.E_conomy.Dto.ResponseDto.RoleResponseDTO;
import com.caNhan.E_conomy.Dto.ResponseDto.UserResponseDTO;
import com.caNhan.E_conomy.Dto.RoleDTO;
import com.caNhan.E_conomy.Dto.UserDto;
import com.caNhan.E_conomy.Entity.Cart;
import com.caNhan.E_conomy.Entity.Roles;
import com.caNhan.E_conomy.Entity.User;
import com.caNhan.E_conomy.GlobalExeption.Exception.NoSuchCustomerExistsException;
import com.caNhan.E_conomy.Repository.RoleRepository;
import com.caNhan.E_conomy.Repository.UserRepository;
import com.caNhan.E_conomy.Service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.management.relation.Role;
import java.util.*;

@Service
public class UserSerivceImpl implements UserService {
    private UserRepository userRepository;
    private ModelMapper modelMapper;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;
    @Autowired
    public UserSerivceImpl(UserRepository userRepository,
                           ModelMapper modelMapper,
                           RoleRepository roleRepository,
                           PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserResponseDTO create(UserDto userDto) {
        Optional<User> existingUser = userRepository.findByPhoneNumber(userDto.getPhoneNumber());
        User user;
        if (existingUser.isPresent()) {
            user = existingUser.get();
        }
        else {
            user = new User();
            user.setFullName(userDto.getFullName());
            user.setPhoneNumber(userDto.getPhoneNumber());
            user.setEmail(userDto.getEmail());
            user.setPassword(passwordEncoder.encode(userDto.getPassword()));

            Cart cart = new Cart();
            cart.setUser(user);
            user.setCart(cart);
        }
        Roles defaultRole = roleRepository.findRolesByRoleName("USER")
                .orElseThrow(() -> new RuntimeException("Default role not found"));
        user.setRoles(new ArrayList<>(List.of(defaultRole)));
        User saveUser = userRepository.save(user);
        return modelMapper.map(saveUser, UserResponseDTO.class);
    }

    @Override
    public UserResponseDTO createAdmin(UserDto userDto) {
        Optional<User> existingUser = userRepository.findByPhoneNumber(userDto.getPhoneNumber());
        User user;
        if (existingUser.isPresent()) {
            user = existingUser.get();
        }
        else {
            user = new User();
            user.setFullName(userDto.getFullName());
            user.setPhoneNumber(userDto.getPhoneNumber());
            user.setEmail(userDto.getEmail());
            user.setPassword(passwordEncoder.encode(userDto.getPassword()));

            Cart cart = new Cart();
            cart.setUser(user);
            user.setCart(cart);
        }
        Roles defaultRole = roleRepository.findRolesByRoleName("ADMIN")
                .orElseThrow(() -> new RuntimeException("Default role not found"));
        user.setRoles(new ArrayList<>(List.of(defaultRole)));
        User saveUser = userRepository.save(user);
        return modelMapper.map(saveUser, UserResponseDTO.class);
    }
}
