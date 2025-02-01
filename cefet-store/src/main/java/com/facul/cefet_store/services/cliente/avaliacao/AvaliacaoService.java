package com.facul.cefet_store.services.cliente.avaliacao;

import com.facul.cefet_store.dto.AvaliacaoDto;
import com.facul.cefet_store.dto.ProdutosPedidosDto;

import java.io.IOException;

public interface AvaliacaoService {

    ProdutosPedidosDto getProdutosPedidosById(Long pedidoId);

    AvaliacaoDto getAvaliacao(AvaliacaoDto avaliacaoDto) throws IOException;
}
