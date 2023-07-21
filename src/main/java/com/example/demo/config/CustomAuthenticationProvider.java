//package com.example.demo.config;
//
//import com.example.demo.entity.Authority;
//import com.example.demo.entity.Customer;
//import com.example.demo.repository.CustomerRepo;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.AuthenticationProvider;
//import org.springframework.security.authentication.BadCredentialsException;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Component;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Set;
//
//@Component
//public class CustomAuthenticationProvider implements AuthenticationProvider {
//    @Autowired
//    private CustomerRepo customerRepo;
//    @Autowired
//    private PasswordEncoder passwordEncoder;
//    @Override
//    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
//        String username = authentication.getName();
//        String pwd = authentication.getCredentials().toString();
//        List<Customer> customers = customerRepo.findByEmail(username);
//        if(customers.size()>0){
//            if(passwordEncoder.matches(pwd,customers.get(0).getPwd())){
////                List<GrantedAuthority> authorities = new ArrayList<>();
////                authorities.add(new SimpleGrantedAuthority(customers.get(0).getRole()));
//                return new UsernamePasswordAuthenticationToken(username,pwd,getGrantedAuthority(customers.get(0).getAuthorities()));
//            }else {
//                throw new BadCredentialsException("invalid password");
//            }
//        }else{
//            throw new BadCredentialsException("no user found");
//        }
//    }
//
//    private List<GrantedAuthority> getGrantedAuthority(Set<Authority> authorities){
//        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
//        for(var authority : authorities){
//            grantedAuthorities.add(new SimpleGrantedAuthority(authority.getName()));
//        }
//        return grantedAuthorities;
//    }
//
//    @Override
//    public boolean supports(Class<?> authentication) {
//        return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
//    }
//}


// as a resource server, we reply on KeyCloak to do authentication