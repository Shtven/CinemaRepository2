package com.shtven.cinema.DTO.Mapping;

import com.shtven.cinema.DTO.Request.RegisterRequest;
import com.shtven.cinema.DTO.Responsive.UserResponse;
import com.shtven.cinema.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {


    @Autowired
    private PasswordEncoder passwordEncoder;

    public User toEntity(RegisterRequest req) {
        User user = new User();
        user.setName(req.getName());
        user.setPassword(passwordEncoder.encode(req.getPassword()));
        user.setRole("CLIENTE");
        user.setEmail(req.getEmail());
        return user;
    }


    public UserResponse toResponse(User user) {
        UserResponse usr = new UserResponse();

        usr.setId(user.getId());
        usr.setName(user.getName());
        usr.setEmail(user.getEmail());


        return usr;
    }
}
