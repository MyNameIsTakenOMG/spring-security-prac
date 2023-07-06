//package com.example.demo.error_handlers;
//
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.web.AuthenticationEntryPoint;
//import org.springframework.stereotype.Component;
//import org.springframework.web.servlet.HandlerExceptionResolver;
//
//import java.io.IOException;
//
//@Component("DelegateAuthenticationEntryPoint")
//public class DelegateAuthenticationEntryPoint implements AuthenticationEntryPoint {
//    @Autowired
//    @Qualifier("handlerExceptionResolver")
//    private HandlerExceptionResolver handlerExceptionResolver;
//    @Override
//    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
//        handlerExceptionResolver.resolveException(request,response,null,authException);
//    }
//}
