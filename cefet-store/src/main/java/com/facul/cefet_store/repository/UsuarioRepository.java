package com.facul.cefet_store.repository;

import com.facul.cefet_store.entity.Usuario;
import com.facul.cefet_store.enums.CargoUsuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findFirstByEmail(String email);

    Usuario findByRole(CargoUsuario cargoUsuario);
}
