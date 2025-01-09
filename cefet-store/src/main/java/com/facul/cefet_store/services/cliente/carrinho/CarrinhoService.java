package com.facul.cefet_store.services.cliente.carrinho;

import com.facul.cefet_store.dto.AddProdutoCarrinhoDto;
import org.springframework.http.ResponseEntity;

public interface CarrinhoService {

    ResponseEntity<?> addProductToCart(AddProdutoCarrinhoDto addProdutoCarrinhoDto);
}
