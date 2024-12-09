package com.facul.cefet_store.services.jwt.auth;

import com.facul.cefet_store.dto.SignupRequest;
import com.facul.cefet_store.dto.UsuarioDto;
import com.facul.cefet_store.entity.Usuario;
import com.facul.cefet_store.enums.CargoUsuario;
import com.facul.cefet_store.repository.UsuarioRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    private BCryptPasswordEncoder bCryptPasswordEncoder;


    public UsuarioDto createUser(SignupRequest signupRequest) {
        Usuario usuario = new Usuario();

        usuario.setEmail(signupRequest.getEmail());
        usuario.setName(signupRequest.getName());
        usuario.setPassword(new BCryptPasswordEncoder().encode(signupRequest.getPassword()));
        usuario.setRole(CargoUsuario.CLIENTE);
        Usuario createdUser = usuarioRepository.save(usuario);

        UsuarioDto usuarioDto = new UsuarioDto();
        usuarioDto.setId(createdUser.getId());

        return usuarioDto;
    }

    public Boolean hasUserWithEmail(String email) {
        return usuarioRepository.findFirstByEmail(email).isPresent();
    }

    @PostConstruct
    public void createAdminAccount() {
        Usuario adminAccount = usuarioRepository.findByRole(CargoUsuario.ADMIN);

        if(null == adminAccount) {
            Usuario usuario = new Usuario();
            usuario.setEmail("admin@test.com");
            usuario.setName("admin");
            usuario.setRole(CargoUsuario.ADMIN);
            usuario.setPassword(new BCryptPasswordEncoder().encode("admin"));
            usuarioRepository.save(usuario);
        }
    }
}
