package com.app.oauthjwtapi.services.impl;

import com.app.oauthjwtapi.dtos.RoleResponseDto;
import com.app.oauthjwtapi.mappers.IRoleMapper;
import com.app.oauthjwtapi.models.entities.Role;
import com.app.oauthjwtapi.repositories.IRoleRepository;
import com.app.oauthjwtapi.services.interfaces.IRoleService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl implements IRoleService {
    private final IRoleRepository roleRepository;
    private final IRoleMapper mapper;

    public RoleServiceImpl(IRoleRepository roleRepository, IRoleMapper mapper) {
        this.roleRepository = roleRepository;
        this.mapper = mapper;
    }

    @Override
    public RoleResponseDto findByName(String name) {
        Role role = roleRepository.findByName(name).orElseThrow(); // exception roleNotFound

        return mapper.toDto(role);
    }

    @Override
    public RoleResponseDto findById(Long id) {
        Role role = roleRepository.findById(id).orElseThrow();
        return mapper.toDto(role);
    }

    @Override
    public RoleResponseDto save(String name) {
        //validar nombre si ya existe, pasar a upper case para guardar
        Role role = new Role();
        role.setName(name);
        roleRepository.save(role);
        return mapper.toDto(role);
    }

    @Override
    public List<RoleResponseDto> findAll() {
        List<Role> roles = roleRepository.findAll();
        return roles.stream().map(mapper::toDto).collect(Collectors.toList());
    }
}
