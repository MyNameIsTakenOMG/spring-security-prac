package com.example.demo.filter;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.crypto.SecretKey;
import java.io.IOException;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class JWTTokenGenerateFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication!=null){
            String jwt = Jwts.builder()
                    .setIssuer("demo")
                    .setSubject("jwtToken")
                    .claim("username",authentication.getName())
                    .claim("authorities",populateAuthorities(authentication.getAuthorities()))
                    .setIssuedAt(new Date())
                    .setExpiration(new Date(new Date().getTime()+30000000))
                    .signWith(SignatureAlgorithm.HS256,JWTConstants.JWT_KEY)
                    .compact();
            response.setHeader(JWTConstants.JWT_HEADER,jwt);
        }
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        return !request.getServletPath().equals("/user");
    }

    private String populateAuthorities(Collection<? extends GrantedAuthority> collection){
        Set<String> authoritiesSet = new HashSet<>();
        for(GrantedAuthority grantedAuthority : collection){
            authoritiesSet.add(grantedAuthority.getAuthority());
        }
        return String.join(",",authoritiesSet);
    }
}
