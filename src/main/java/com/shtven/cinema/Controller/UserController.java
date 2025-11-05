package com.shtven.cinema.Controller;

import com.shtven.cinema.DTO.Request.LoginRequest;
import com.shtven.cinema.DTO.Request.RegisterRequest;
import com.shtven.cinema.DTO.Responsive.AuthResponse;
import com.shtven.cinema.DTO.Responsive.UserResponse;
import com.shtven.cinema.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/usuarios")
public class UserController {

    @Autowired
    private UserService userService;


    // REGISTRO CORREGIDO
    @PostMapping("/registro")
    public ResponseEntity<UserResponse> registrar(@Valid @RequestBody RegisterRequest request) {
        UserResponse response = userService.register(request);
        return ResponseEntity.status(201).body(response);
    }

    // LOGIN
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody LoginRequest request) {
        AuthResponse response = userService.login(request);
        return ResponseEntity.ok(response);
    }
}
