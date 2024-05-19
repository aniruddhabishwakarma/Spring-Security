package com.example.SpringSec.model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Validated
public class User {

    @NotNull
    @NotEmpty
    @Size(min = 2, max = 20, message = "Full name must be 2 to 20")
    private String fullName;
    @NotNull(message = "username is required")
    @Size(min = 7, max = 10, message = "username must be at least 7 characters")
    private String username;
    @NotNull
    @Size(min = 10, max= 10,message = "Contact must be of 10 numbers")
    private String contact;
    @NotNull
    @Size(min = 5, max = 10,message = "Password must be of 8 t0 10 length")
    private String password;

}
