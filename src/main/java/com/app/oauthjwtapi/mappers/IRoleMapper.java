package com.app.oauthjwtapi.mappers;

import com.app.oauthjwtapi.dtos.RoleResponseDto;
import com.app.oauthjwtapi.models.entities.Role;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IRoleMapper {
    RoleResponseDto toDto(Role role);
}
