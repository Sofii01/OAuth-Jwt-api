package com.app.oauthjwtapi.services.interfaces;


import com.app.oauthjwtapi.models.entities.User;

import java.util.List;

public interface IUserService {
    User findById(Long id);
    User create(User user);
    List<User> findAll();
    User findByEmail(String email);
}
