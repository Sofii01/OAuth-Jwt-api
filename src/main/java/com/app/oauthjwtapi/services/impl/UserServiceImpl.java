package com.app.oauthjwtapi.services.impl;

import com.app.oauthjwtapi.dtos.UserRequestDto;
import com.app.oauthjwtapi.dtos.UserResponseDto;
import com.app.oauthjwtapi.mappers.IUserMapper;
import com.app.oauthjwtapi.models.entities.User;
import com.app.oauthjwtapi.repositories.IUserRepository;
import com.app.oauthjwtapi.services.interfaces.IUserService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements IUserService {
    private final IUserRepository userRepository;
    private final IUserMapper mapper;
    private final BCryptPasswordEncoder encoder;

    public UserServiceImpl(IUserRepository userRepository, IUserMapper mapper, BCryptPasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.mapper = mapper;
        this.encoder = encoder;
    }

    @Override
    public UserResponseDto findById(Long id) {
        User user = userRepository.findById(id).orElseThrow(); // exception UserNotFound
        return mapper.toDto(user);
    }

    @Override
    public UserResponseDto create(UserRequestDto request) {
        Optional<User> v = userRepository.findByEmail(request.getEmail());
        if(v.isPresent()) {
            throw new RuntimeException(); //exception alreadyExistis
        }
        //validar si existe otro email igual
        User user = new User();
        user.setEmail(request.getEmail());
        //y si la recibo cifrada?
        String encodedPassword = encoder.encode(request.getPassword());
        user.setPassword(encodedPassword);
        //user.getRoles().isEmpty(); hacer que agregue el nuevo rol a la lista y lanzar una exception en caso de que se quiera agregar una ya existente
        return null;
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
