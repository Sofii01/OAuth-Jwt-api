package com.app.oauthjwtapi.services.interfaces;

import com.app.oauthjwtapi.dtos.RoleResponseDto;
import com.app.oauthjwtapi.models.entities.Role;

import java.util.List;

public interface IRoleService {
    RoleResponseDto findByName(String name);
    RoleResponseDto findById(Long id);
    RoleResponseDto save(String name);
    List<RoleResponseDto> findAll();
}
