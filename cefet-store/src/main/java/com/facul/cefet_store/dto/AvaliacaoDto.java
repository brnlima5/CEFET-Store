package com.facul.cefet_store.dto;

import com.facul.cefet_store.entity.Produto;
import com.facul.cefet_store.entity.Usuario;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.web.multipart.MultipartFile;

@Data
public class AvaliacaoDto {
    private Long id;

    private Long rating;

    private String description;

    private MultipartFile img;

    private byte[] returnedImg;

    private Long userId;

    private Long productId;

    private String username;
}
