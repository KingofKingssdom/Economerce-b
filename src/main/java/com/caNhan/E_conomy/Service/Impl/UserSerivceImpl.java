package com.caNhan.E_conomy.Service.Impl;

import com.caNhan.E_conomy.Dto.ResponseDto.ResUserDto;
import com.caNhan.E_conomy.Dto.RequestDto.ReqUserDto;
import com.caNhan.E_conomy.Entity.Cart;
import com.caNhan.E_conomy.Entity.Roles;
import com.caNhan.E_conomy.Entity.User;
import com.caNhan.E_conomy.GlobalExeption.Exception.NoSuchCustomerExistsException;
import com.caNhan.E_conomy.Repository.RoleRepository;
import com.caNhan.E_conomy.Repository.UserRepository;
import com.caNhan.E_conomy.Service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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
    public ResUserDto createUser(ReqUserDto reqUserDto, String roleName) {
        Optional<Roles> rolesOptional = roleRepository.findRolesByRoleName(roleName);
        if(rolesOptional.isEmpty()){
            throw new NoSuchCustomerExistsException("Role not found with role name " + roleName);
        }
            User user = new User();
            user.setFullName(reqUserDto.getFullName());
            user.setPhoneNumber(reqUserDto.getPhoneNumber());
            user.setEmail(reqUserDto.getEmail());
            user.setPassword(passwordEncoder.encode(reqUserDto.getPassword()));

            Cart cart = new Cart();
            cart.setUser(user);
            user.setCart(cart);
            user.setRoles(new ArrayList<>(List.of(rolesOptional.get())));
        User saveUser = userRepository.save(user);
        return modelMapper.map(saveUser, ResUserDto.class);
    }

}
