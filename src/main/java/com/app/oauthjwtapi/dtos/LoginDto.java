package com.app.oauthjwtapi.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public class LoginDto {
    @Email
    @NotBlank
    private String email;
    @NotBlank
    @Size(min = 6, max = 16)
    private String password;
}
