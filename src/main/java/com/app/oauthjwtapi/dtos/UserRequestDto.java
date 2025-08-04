package com.app.oauthjwtapi.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class UserRequestDto {
    private String email;
    private String password;
    private List<String> roles;
}
