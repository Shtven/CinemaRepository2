package com.shtven.cinema.Controller;

import com.shtven.cinema.Configuration.JwtService;
import com.shtven.cinema.DTO.Request.LoginRequest;
import com.shtven.cinema.Model.Users;
import com.shtven.cinema.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private JwtService jwtService;

    @PostMapping("/signup")
    public ResponseEntity<Users> registrar(@Valid @RequestBody Users request) {
        Users newUsers = userService.register(request);
        return ResponseEntity.status(201).body(newUsers);
    }

    @PostMapping("/signin")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest request) {
        Users logined = userService.login(request);

        String token = jwtService.generateToken(logined.getEmail(), logined.getIdUser(), logined.getRole());

        Map<String, Object> body = new HashMap<>();
        body.put("token", token);
        body.put("role", logined.getRole());

        return ResponseEntity.ok(body);
    }
}
