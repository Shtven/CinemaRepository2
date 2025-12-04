package com.shtven.cinema.Controller;

import com.shtven.cinema.DTO.Request.LoginRequest;
import com.shtven.cinema.Model.Users;
import com.shtven.cinema.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<Users> registrar(@Valid @RequestBody Users request) {
        Users newUsers = userService.register(request);
        return ResponseEntity.status(201).body(newUsers);
    }

    @PostMapping("/signin")
    public ResponseEntity<Users> login(@Valid @RequestBody LoginRequest request) {
        Users logined = userService.login(request);
        return ResponseEntity.ok(logined);
    }
}
