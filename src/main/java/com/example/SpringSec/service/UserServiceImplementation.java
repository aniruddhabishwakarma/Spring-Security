package com.example.SpringSec.service;

import com.example.SpringSec.config1.jwt.JwtTokenUtil;
import com.example.SpringSec.config1.jwt.PasswordEncoder;
import com.example.SpringSec.entity.Roles;
import com.example.SpringSec.entity.UserEntity;
import com.example.SpringSec.exception.ExceptionResponse;
import com.example.SpringSec.model.AuthResponse;
import com.example.SpringSec.model.Login;
import com.example.SpringSec.model.User;
import com.example.SpringSec.repository.RoleRepository;
import com.example.SpringSec.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImplementation implements UserService, UserDetailsService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final JwtTokenUtil jwtTokenUtil;

    @Override
    public ResponseEntity<?> registerUser(User user) {

        if (userRepository.existsByUsername(user.getUsername())) {
            throw new RuntimeException("Username already exists: " + user.getUsername());
        }

        UserEntity userEntity = new UserEntity();
        userEntity.setFullName(user.getFullName());
        userEntity.setUsername(user.getUsername());
        userEntity.setContact(userEntity.getContact());
        userEntity.setPassword(new PasswordEncoder().encodePassword(user.getPassword()));
        Roles roles = roleRepository.findByName("ROLE_ADMIN").get();
        Roles roles1 = roleRepository.findByName("ROLE_ADMIN_CREATE_PRODUCT").get();
        userEntity.setRoles(List.of(roles,roles1));
        return new ResponseEntity<>(loginUser(userRepository.save(userEntity)), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Object> login(Login login) {
        Optional<UserEntity> userDetails = userRepository.findByUsername(login.getUsername());
        if (userDetails.isEmpty() || new PasswordEncoder().matches(login.getPassword(), userDetails.get().getPassword())) {
            return ResponseEntity.status(401).body(new ExceptionResponse(401, "Please check your username or password."));
        }
        return ResponseEntity.ok(loginUser(userDetails.get()));
    }

    private AuthResponse loginUser(UserEntity userEntity) {
        return new AuthResponse(jwtTokenUtil.generateToken(userEntity));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserEntity> userEntityOptional = userRepository.findByUsername(username);
        if (userEntityOptional.isPresent()) {
            return userEntityOptional.get();
        }
        throw new UsernameNotFoundException("username " + username + " not found.");
    }
}
