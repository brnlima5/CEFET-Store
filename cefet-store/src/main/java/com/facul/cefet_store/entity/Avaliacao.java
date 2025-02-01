package com.facul.cefet_store.entity;

import com.facul.cefet_store.dto.AvaliacaoDto;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Data
public class Avaliacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long rating;

    @Lob
    private String description;

    @Lob
    @Column(columnDefinition = "longblob")
    private byte[] img;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "usuario_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Usuario user;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "produto_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Produto product;

    public AvaliacaoDto getDto() {
        AvaliacaoDto avaliacaoDto = new AvaliacaoDto();

        avaliacaoDto.setId(id);
        avaliacaoDto.setRating(rating);
        avaliacaoDto.setDescription(description);
        avaliacaoDto.setReturnedImg(img);
        avaliacaoDto.setProductId(product.getId());
        avaliacaoDto.setUserId(user.getId());
        avaliacaoDto.setUsername(user.getName());

        return avaliacaoDto;
    }
}
