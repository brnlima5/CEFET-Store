package com.facul.cefet_store.dto;

import lombok.Data;

@Data
public class FazerPedidoDto {

    private Long userId;

    private String address;

    private String orderDescription;
}
