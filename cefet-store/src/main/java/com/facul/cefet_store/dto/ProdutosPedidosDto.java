package com.facul.cefet_store.dto;

import lombok.Data;

import java.util.List;

@Data
public class ProdutosPedidosDto {

    private List<ProdutoDto> produtoDtoList;

    private Long valorPedido;
}
