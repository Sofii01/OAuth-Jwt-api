package com.app.oauthjwtapi.controllers;

import com.app.oauthjwtapi.dtos.AuthDto;
import com.app.oauthjwtapi.dtos.LoginDto;
import com.app.oauthjwtapi.dtos.UserRequestDto;
import com.app.oauthjwtapi.dtos.UserResponseDto;
import com.app.oauthjwtapi.jwt.CustomUserDetailsService;
import com.app.oauthjwtapi.jwt.JwtService;
import com.app.oauthjwtapi.services.interfaces.IUserService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final JwtService jwtService;
    private final AuthenticationManager authManager;
    private final CustomUserDetailsService customUserDetailsService;
    private final IUserService userService;

    public AuthController(JwtService jwtService, AuthenticationManager authManager, CustomUserDetailsService customUserDetailsService, IUserService userService) {
        this.jwtService = jwtService;
        this.authManager = authManager;
        this.customUserDetailsService = customUserDetailsService;
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthDto> login( @Valid @RequestBody LoginDto loginDto){
        authManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword())
        );
        UserDetails userDetails = customUserDetailsService.loadUserByUsername(loginDto.getEmail());
        String token = jwtService.generateToken(userDetails);
        return ResponseEntity.ok(new AuthDto(token));
    }

    @PostMapping("/signup")
    public ResponseEntity<UserResponseDto> register(@Valid @RequestBody UserRequestDto request){
        UserResponseDto user =  userService.create(request);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }
    //
    @GetMapping("/oauth2/authorize/google")
    public void redirectToGoogle(HttpServletResponse response) throws IOException {
        response.sendRedirect("/oauth2/authorization/google");
    }
}
