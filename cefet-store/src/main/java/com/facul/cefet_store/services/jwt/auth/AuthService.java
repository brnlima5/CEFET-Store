package com.facul.cefet_store.services.jwt.auth;

import com.facul.cefet_store.dto.SignupRequest;
import com.facul.cefet_store.dto.UsuarioDto;

public interface AuthService {
    UsuarioDto createUser(SignupRequest signupRequest);
    Boolean hasUserWithEmail(String email);
}
