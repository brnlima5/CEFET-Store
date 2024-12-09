package com.facul.cefet_store.services.jwt;

import com.facul.cefet_store.entity.Usuario;
import com.facul.cefet_store.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class DetalhesUsuarioServiceImpl implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Usuario> optionalUsuario = usuarioRepository.findFirstByEmail(username);
        if(optionalUsuario.isEmpty()) throw new UsernameNotFoundException("Nome de usuário não encontrado.", null);
        return new org.springframework.security.core.userdetails.User(
                optionalUsuario.get().getEmail(),
                optionalUsuario.get().getPassword(),
                new ArrayList<>());
    }
}
