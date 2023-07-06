//package com.example.demo.error_handlers;
//
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.AccessDeniedException;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
//
//@ControllerAdvice
//public class ErrorHandler extends ResponseEntityExceptionHandler {
//    @ExceptionHandler(AuthenticationException.class)
//    @ResponseBody
//    public ResponseEntity<String> handleAuthenticationException(Exception ex){
//        return ResponseEntity.status(401).body("not authenticated");
//    }
//
//    @ExceptionHandler(AccessDeniedException.class)
//    @ResponseBody
//    public ResponseEntity<String> handleAccessDeniedException(Exception ex){
//        return ResponseEntity.status(403).body("not authorized");
//    }
//}
