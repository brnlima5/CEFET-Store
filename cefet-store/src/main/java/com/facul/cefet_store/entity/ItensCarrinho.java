package com.facul.cefet_store.entity;

import com.facul.cefet_store.dto.ItensCarrinhoDto;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Data
public class ItensCarrinho {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long price;

    private Long quantity;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "produto_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Produto product;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "usuario_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Usuario user;

    //?????
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pedido_id")
    @JsonBackReference
    private Pedido order;

    public ItensCarrinhoDto getCarrinhoDto() {
        ItensCarrinhoDto itensCarrinhoDto = new ItensCarrinhoDto();

        itensCarrinhoDto.setId(id);
        itensCarrinhoDto.setPrice(price);
        itensCarrinhoDto.setProductId(product.getId());
        itensCarrinhoDto.setQuantity(quantity);
        itensCarrinhoDto.setUserId(user.getId());
        itensCarrinhoDto.setProductName(product.getName());
        itensCarrinhoDto.setReturnedImg(product.getImg());

        return itensCarrinhoDto;
    }
}
