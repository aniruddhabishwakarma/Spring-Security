package com.example.SpringSec.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ValidationResponse {
    private List<FieldError> fieldErrors;
}

