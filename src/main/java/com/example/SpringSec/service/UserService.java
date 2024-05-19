package com.example.SpringSec.service;

import com.example.SpringSec.model.AuthResponse;
import com.example.SpringSec.model.Login;
import com.example.SpringSec.model.User;
import com.example.SpringSec.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface UserService {


    ResponseEntity<?> registerUser(User user);

    ResponseEntity<Object> login(Login login);
}
