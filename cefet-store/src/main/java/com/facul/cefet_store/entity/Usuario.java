package com.facul.cefet_store.entity;

import com.facul.cefet_store.enums.CargoUsuario;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    private String name;

    private String password;

    private CargoUsuario role;

    @Lob
    @Column(columnDefinition = "longblob")
    private byte[] img;

}
