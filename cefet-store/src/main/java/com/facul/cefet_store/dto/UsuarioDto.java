package com.facul.cefet_store.dto;

import com.facul.cefet_store.enums.CargoUsuario;
import lombok.Data;

@Data
public class UsuarioDto {

    private Long id;

    private String email;

    private String name;

    private CargoUsuario role;
}
