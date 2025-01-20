package com.facul.cefet_store.services.cliente.carrinho;

import com.facul.cefet_store.dto.AddProdutoCarrinhoDto;
import com.facul.cefet_store.dto.PedidoDto;
import org.springframework.http.ResponseEntity;

public interface CarrinhoService {

    ResponseEntity<?> addProductToCart(AddProdutoCarrinhoDto addProdutoCarrinhoDto);

    PedidoDto getCartByUserId(Long userId);

    PedidoDto applyCupom(Long userId, String code);
}
