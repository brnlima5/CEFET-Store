package com.facul.cefet_store.entity;

import com.facul.cefet_store.dto.ListaDesejosDto;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Data
@Entity
public class ListaDesejos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "produto_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Produto product;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "usuario_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Usuario user;


    public ListaDesejosDto getListaDesejosDto() {
        ListaDesejosDto listaDesejosDto = new ListaDesejosDto();

        listaDesejosDto.setId(id);
        listaDesejosDto.setProductId(product.getId());
        listaDesejosDto.setReturnedImg(product.getImg());
        listaDesejosDto.setProductName(product.getName());
        listaDesejosDto.setProductDescription(product.getDescription());
        listaDesejosDto.setPrice(product.getPrice());
        listaDesejosDto.setUserId(user.getId());

        return listaDesejosDto;
    }
}
