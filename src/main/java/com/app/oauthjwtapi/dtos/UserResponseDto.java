package com.app.oauthjwtapi.dtos;

import com.app.oauthjwtapi.models.entities.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public class UserResponseDto {
    private Long id;
    private String email;
    private String password;
    private List<Role> roles;
}
