package com.app.oauthjwtapi.oauth;

import com.app.oauthjwtapi.jwt.CustomUserDetailsService;
import com.app.oauthjwtapi.jwt.JwtService;
import com.app.oauthjwtapi.services.interfaces.IUserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class OAuth2LoginSuccessHandler implements AuthenticationSuccessHandler {

    private final IUserService userService;
    private final JwtService jwtService;
    private final CustomUserDetailsService userDetailsService;

    public OAuth2LoginSuccessHandler(IUserService userService, JwtService jwtService, CustomUserDetailsService userDetailsService) {
        this.userService = userService;
        this.jwtService = jwtService;
        this.userDetailsService = userDetailsService;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
        String email = oAuth2User.getAttribute("email");

        // Crea el usuario si no existe
        UserDetails user = userDetailsService.loadOrCreateUserFromOAuth(email);

        // Genera JWT
        String token = jwtService.generateToken(user);

        // Devuelve el token (puede ser redirección o JSON, según tu frontend)
        response.setContentType("application/json");
        response.getWriter().write("{\"token\":\"" + token + "\"}");
    }

}
