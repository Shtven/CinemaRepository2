package com.shtven.cinema.services;

import com.shtven.cinema.DTO.Mapping.UserMapper;
import com.shtven.cinema.DTO.Request.LoginRequest;
import com.shtven.cinema.DTO.Request.RegisterRequest;
import com.shtven.cinema.DTO.Responsive.AuthResponse;
import com.shtven.cinema.DTO.Responsive.UserResponse;
import com.shtven.cinema.Model.User;
import com.shtven.cinema.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserMapper userMapper;

    public UserResponse register(RegisterRequest request) {
        Optional<User> usr = userRepository.findByEmail(request.getEmail());

        if(usr.isPresent()){
            throw new RuntimeException("User with email " + userRequest.getEmail() + " already exists.");
        }else{
            User newUser = userMapper(request);
            userRepository.save(newUser);

            return userMapper
        }
    }


    public AuthResponse login(LoginRequest request) {

    }
}

