package com.facul.cefet_store.dto;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class ProdutoDto {

    private Long id;

    private String name;

    private Long price;

    private String decription;

    private byte[] byteImg;

    private Long categoryId;

    private MultipartFile img;
}
