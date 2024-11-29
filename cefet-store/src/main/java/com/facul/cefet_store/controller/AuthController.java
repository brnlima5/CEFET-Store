package com.facul.cefet_store.controller;

import com.facul.cefet_store.dto.AuthenticationRequest;
import com.facul.cefet_store.entity.Usuario;
import com.facul.cefet_store.repository.UsuarioRepository;
import com.facul.cefet_store.utils.JwtUtil;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private AuthenticationManager authenticationManager;

    private final UserDetailsService userDetailsService;

    private final UsuarioRepository usuarioRepository;

    private final JwtUtil jwtUtil;

    public static final String TOKEN_PREFIX = "Bearer ";

    public static final String HEADER_STRING = "Authorization";


    @PostMapping("/authenticate")
    public void createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest, HttpServletResponse response) throws IOException, JSONException {

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    authenticationRequest.getUsername(),
                    authenticationRequest.getPassword()));
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("Usuário ou senha incorretos.");
        }

        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
        Optional<Usuario> optionalUsuario = usuarioRepository.findFirstByEmail(userDetails.getUsername());
        final String jwt = jwtUtil.generateToken(userDetails.getUsername());

        if(optionalUsuario.isPresent()) {
            response.getWriter().write(new JSONObject()
                    .put("idUsuario", optionalUsuario.get().getId())
                    .put("cargo", optionalUsuario.get().getRole())
                    .toString());
        }

        response.addHeader(HEADER_STRING, TOKEN_PREFIX + jwt);
    }
}
