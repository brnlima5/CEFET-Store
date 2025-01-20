package com.facul.cefet_store.dto;

import com.facul.cefet_store.entity.ItensCarrinho;
import com.facul.cefet_store.enums.StatusPedido;
import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
public class PedidoDto {

    private Long id;

    private String description;

    private Date date;

    private Long amount;

    private String address;

    private String payment;

    private StatusPedido orderStatus;

    private Long totalAmount;

    private Long discount;

    private UUID trackingId;

    private String userName;

    private List<ItensCarrinhoDto> cartItems;

    private String cupomName;
}
