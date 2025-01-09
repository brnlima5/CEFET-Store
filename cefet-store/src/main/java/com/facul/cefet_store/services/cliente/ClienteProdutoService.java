package com.facul.cefet_store.services.cliente;

import com.facul.cefet_store.dto.ProdutoDto;

import java.util.List;

public interface ClienteProdutoService {

    List<ProdutoDto> searchProductByTitle(String title);

    List<ProdutoDto> getAllProducts();
}
