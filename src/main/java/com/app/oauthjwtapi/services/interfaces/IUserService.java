package com.app.oauthjwtapi.services.interfaces;


import com.app.oauthjwtapi.dtos.UserRequestDto;
import com.app.oauthjwtapi.dtos.UserResponseDto;
import com.app.oauthjwtapi.models.entities.User;

import java.util.List;

public interface IUserService {
    UserResponseDto findById(Long id);
    UserResponseDto create(UserRequestDto request);
    List<UserResponseDto> findAll();
    UserResponseDto findByEmail(String email);
}
