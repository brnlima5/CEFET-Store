package com.facul.cefet_store.dto;

import lombok.Data;

@Data
public class ItensCarrinhoDto {

    private Long id;

    private Long price;

    private Long quantity;

    private Long productId;

    private Long orderId;

    private Long userId;

    private String productName;

    private byte[] returnedImg;

}
