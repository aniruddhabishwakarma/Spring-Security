//package com.example.SpringSec.exception;
//
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.validation.BindingResult;
//import org.springframework.validation.FieldError;
//import org.springframework.web.bind.MethodArgumentNotValidException;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.RestControllerAdvice;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@RestControllerAdvice
//public class GlobalExceptionHandler {
////    @ExceptionHandler(MethodArgumentNotValidException.class)
////    public ValidationResponse validationException(MethodArgumentNotValidException ex) {
////        ValidationResponse validationResponse = new ValidationResponse();
////        BindingResult bindingResult = ex.getBindingResult();
////        List<FieldError> fieldError = bindingResult.getFieldErrors();
////        validationResponse.setFieldErrors(fieldError);
////        return validationResponse;
////    }
//
//    @ExceptionHandler(UsernameNotFoundException.class)
//    public ResponseEntity<ExceptionResponse> userNotFound(UsernameNotFoundException ex){
//        ExceptionResponse exceptionResponse = new ExceptionResponse(HttpStatus.NOT_FOUND.value(),ex.getMessage());
//        return new ResponseEntity<>(exceptionResponse,HttpStatus.NOT_FOUND);
//
//    }
//
//    @ExceptionHandler(RuntimeException.class)
//    public ResponseEntity<ExceptionResponse> handleRunTimeException(RuntimeException ex){
//        ExceptionResponse exceptionResponse = new ExceptionResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage());
//        return new ResponseEntity<>(exceptionResponse,HttpStatus.INTERNAL_SERVER_ERROR);
//
//    }
//    @ExceptionHandler(AuthenticationCredentialsNotFoundException.class)
//    public ResponseEntity<ExceptionResponse> handleAuthenticationException
//            (AuthenticationCredentialsNotFoundException ex){
//        ExceptionResponse exceptionResponse =
//                new ExceptionResponse(HttpStatus.UNAUTHORIZED.value(), ex.getMessage());
//        return new ResponseEntity<>(exceptionResponse,HttpStatus.UNAUTHORIZED);
//    }
//
//    @ExceptionHandler(IllegalArgumentException.class)
//    public ResponseEntity<ExceptionResponse> handleIllegalArgument(
//            IllegalArgumentException ex){
//        ExceptionResponse exceptionResponse =
//                new ExceptionResponse(HttpStatus.UNAUTHORIZED.value(), ex.getMessage());
//        return new ResponseEntity<>(exceptionResponse,HttpStatus.UNAUTHORIZED);
//    }
//
//}
