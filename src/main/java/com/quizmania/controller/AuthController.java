package com.quizmania.controller;

import com.quizmania.dto.AuthRequest;
import com.quizmania.dto.AuthResponse;
import com.quizmania.entity.User;
import com.quizmania.service.Impl.UserInfoUserDetailsService;
import com.quizmania.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@CrossOrigin("*")
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserInfoUserDetailsService userInfoUserDetailsService;

    @Autowired
    private AuthResponse authResponse;

    @PostMapping("/authenticate")
    public AuthResponse authenticateAndGetToken(@RequestBody AuthRequest authRequest){
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUserName(), authRequest.getPassword()));
        if(authenticate.isAuthenticated()){
            authResponse.setToken(jwtService.generateToken(authRequest.getUserName()));
            return authResponse;
        }else{
            throw new UsernameNotFoundException("Invalid User Request !!");
        }

    }

    @GetMapping("/current-user")
    public User getCurrentUser(Principal principal){
        return (User)this.userInfoUserDetailsService.loadUserByUsername(principal.getName());

    }
}
