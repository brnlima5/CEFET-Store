package com.facul.cefet_store.entity;

import com.facul.cefet_store.dto.ProdutoDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Data
@Table(name = "produto")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Long price;

    @Lob
    private String description;

    @Lob
    @Column(columnDefinition = "longblob")
    private byte[] img;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "categoria_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Categoria categoria;


    public ProdutoDto getDto() {
        ProdutoDto produtoDto = new ProdutoDto();
        produtoDto.setId(id);
        produtoDto.setName(name);
        produtoDto.setPrice(price);
        produtoDto.setDescription(description);
        produtoDto.setByteImg(img);
        produtoDto.setCategoryId(categoria.getId());
        return produtoDto;
    }


}
