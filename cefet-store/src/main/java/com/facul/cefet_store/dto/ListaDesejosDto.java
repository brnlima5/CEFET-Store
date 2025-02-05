package com.facul.cefet_store.dto;

import lombok.Data;

@Data
public class ListaDesejosDto {

    private Long userId;

    private Long productId;

    private Long id;

    private String productName;

    private String productDescription;

    private byte[] returnedImg;

    private Long price;
}
