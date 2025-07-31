package com.app.oauthjwtapi.services.interfaces;

import com.app.oauthjwtapi.models.entities.Role;

import java.util.List;

public interface IRoleService {
    Role findByName(String name);
    Role findById(Long id);
    Role save(Role role);
    List<Role> findAll();
}
