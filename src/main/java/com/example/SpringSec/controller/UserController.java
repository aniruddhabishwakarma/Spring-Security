package com.example.SpringSec.controller;

import com.example.SpringSec.model.AuthResponse;
import com.example.SpringSec.model.Login;
import com.example.SpringSec.model.User;
import com.example.SpringSec.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;


    @PostMapping("auth/register")
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        System.out.println(user);
        return userService.registerUser(user);
    }

    @PostMapping("auth/login")
    public ResponseEntity<?> myLogin(@RequestBody Login login) {
        System.out.println(login);

        return userService.login(login);

    }

    @GetMapping("/ping")
    public ResponseEntity<?> ping() {
        return ResponseEntity.ok("pong");
    }
}
