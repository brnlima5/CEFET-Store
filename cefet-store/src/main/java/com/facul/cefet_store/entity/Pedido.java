package com.facul.cefet_store.entity;

import com.facul.cefet_store.dto.PedidoDto;
import com.facul.cefet_store.enums.StatusPedido;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@Table(name = "pedidos")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String orderDescription;

    private Date date;

    private Long amount;

    private String address;

    private String payment;

    private StatusPedido orderStatus;

    private Long totalAmount;

    private Long discount;

    private UUID trackingId;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "usuario_id", referencedColumnName = "id")
    private Usuario user;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "cupom_id", referencedColumnName = "id")
    private Cupom cupom;

    //?????
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "order")
    @JsonManagedReference
    private List<ItensCarrinho> cartItems;

    public PedidoDto getPedidoDto() {
        PedidoDto pedidoDto = new PedidoDto();

        pedidoDto.setId(id);
        pedidoDto.setDescription(orderDescription);
        pedidoDto.setAddress(address);
        pedidoDto.setTrackingId(trackingId);
        pedidoDto.setAmount(amount);
        pedidoDto.setDate(date);
        pedidoDto.setOrderStatus(orderStatus);
        pedidoDto.setUserName(user.getName());

        if(cupom != null) {
            pedidoDto.setCupomName(cupom.getName());
        }
        return pedidoDto;
    }

}


