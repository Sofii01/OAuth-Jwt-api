package com.app.oauthjwtapi.mappers;

import com.app.oauthjwtapi.dtos.UserResponseDto;
import com.app.oauthjwtapi.models.entities.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IUserMapper {
    UserResponseDto toDto(User user);
    User toEntity(UserResponseDto userResponseDto);
}
