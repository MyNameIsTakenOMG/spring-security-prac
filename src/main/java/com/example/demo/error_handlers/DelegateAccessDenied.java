//package com.example.demo.error_handlers;
//
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.security.access.AccessDeniedException;
//import org.springframework.security.web.access.AccessDeniedHandler;
//import org.springframework.stereotype.Component;
//import org.springframework.web.servlet.HandlerExceptionResolver;
//
//import java.io.IOException;
//
//@Component("DelegateAccessDenied")
//public class DelegateAccessDenied implements AccessDeniedHandler {
//    @Autowired
//    @Qualifier("handlerExceptionResolver")
//    private HandlerExceptionResolver handlerExceptionResolver;
//    @Override
//    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
//        handlerExceptionResolver.resolveException(request,response,null,accessDeniedException);
//    }
//}
