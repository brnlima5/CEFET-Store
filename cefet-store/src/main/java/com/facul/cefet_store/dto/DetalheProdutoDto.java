package com.facul.cefet_store.dto;

import lombok.Data;

import java.util.List;

@Data
public class DetalheProdutoDto {

    private ProdutoDto produtoDto;

    private List<AvaliacaoDto> avaliacaoDtoList;

    private List<FAQDto> faqDtoList;
}
