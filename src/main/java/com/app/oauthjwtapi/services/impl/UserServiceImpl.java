package com.app.oauthjwtapi.services.impl;

import com.app.oauthjwtapi.dtos.UserRequestDto;
import com.app.oauthjwtapi.dtos.UserResponseDto;
import com.app.oauthjwtapi.mappers.IUserMapper;
import com.app.oauthjwtapi.models.entities.Role;
import com.app.oauthjwtapi.models.entities.User;
import com.app.oauthjwtapi.repositories.IRoleRepository;
import com.app.oauthjwtapi.repositories.IUserRepository;
import com.app.oauthjwtapi.services.interfaces.IUserService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements IUserService {
    private final IUserRepository userRepository;
    private final IUserMapper mapper;
    private final BCryptPasswordEncoder encoder;
    private final IRoleRepository roleRepository;

    public UserServiceImpl(IUserRepository userRepository, IUserMapper mapper, BCryptPasswordEncoder encoder, IRoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.mapper = mapper;
        this.encoder = encoder;
        this.roleRepository = roleRepository;
    }

    @Override
    public UserResponseDto findById(Long id) {
        User user = userRepository.findById(id).orElseThrow(); // exception UserNotFound
        return mapper.toDto(user);
    }

    @Override
    public UserResponseDto create(UserRequestDto request) {
        //validar si existe otro email igual
        userRepository.findByEmail(request.getEmail()).ifPresent(user -> {
            throw new RuntimeException(); //exception alreadyExistis
        });
        User user = new User();
        user.setEmail(request.getEmail());
        String encodedPassword = encoder.encode(request.getPassword());
        user.setPassword(encodedPassword);
        Set<Role> roles = request.getRoles().stream()
                .map(name -> roleRepository.findByName(name)
                        .orElseThrow(() -> new RuntimeException("Role not found: " + name)))
                .collect(Collectors.toSet());


        // Validar roles duplicados en el mismo request
        if (roles.size() != request.getRoles().size()) {
            throw new RuntimeException("Roles must be unique");
        }
        user.setRoles(roles);
        User saved = userRepository.save(user);

        return mapper.toDto(saved);
    }

    @Override
    public List<UserResponseDto> findAll() {
        List<User> users = userRepository.findAll();
        return users.stream().map(mapper::toDto).collect(Collectors.toList());
    }

    @Override
    public UserResponseDto findByEmail(String email) {
        User user = userRepository.findByEmail(email).orElseThrow();// exception userNotFound
        return mapper.toDto(user);
    }
}
