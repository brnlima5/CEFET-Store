package com.facul.cefet_store.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AnalisePedido {

    private Long adicionado;

    private Long enviado;

    private Long entregue;

    private Long pedidosMesAtual;

    private Long pedidosMesAnterior;

    private Long ganhoMesAtual;

    private Long ganhoMesAnterior;
}
