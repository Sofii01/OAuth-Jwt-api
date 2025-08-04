package com.app.oauthjwtapi.controllers;

import com.app.oauthjwtapi.dtos.UserRequestDto;
import com.app.oauthjwtapi.dtos.UserResponseDto;
import com.app.oauthjwtapi.models.entities.User;
import com.app.oauthjwtapi.services.interfaces.IUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserController {
    private final IUserService userService;

    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UserResponseDto> addUser(@RequestBody UserRequestDto request) {
        UserResponseDto user =  userService.create(request);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

}
