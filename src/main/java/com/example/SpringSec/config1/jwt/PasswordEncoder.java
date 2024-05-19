package com.example.SpringSec.config1.jwt;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * The PasswordEncoder class is used to encode the password before storing it in the database
 * and to match the password entered by the user with the password stored in the database during authentication
 *
 * @author sijasntu
 * @version 1.0
 */
@Component
public class PasswordEncoder {
    @Value("${password.salt}")
    private String salt;

    public String encodePassword(String password) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String saltedPassword = password + salt;
        return passwordEncoder.encode(saltedPassword);
    }

    public boolean matches(String enteredPassword, String storedHashedPassword) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String saltedEnteredPassword = enteredPassword + salt;
        return !passwordEncoder.matches(saltedEnteredPassword, storedHashedPassword);
    }
}
