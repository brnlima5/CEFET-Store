package com.facul.cefet_store.services.cliente.carrinho;

import com.facul.cefet_store.dto.AddProdutoCarrinhoDto;
import com.facul.cefet_store.dto.FazerPedidoDto;
import com.facul.cefet_store.dto.PedidoDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CarrinhoService {

    ResponseEntity<?> addProductToCart(AddProdutoCarrinhoDto addProdutoCarrinhoDto);

    PedidoDto getCartByUserId(Long userId);

    PedidoDto applyCupom(Long userId, String code);

    PedidoDto increaseProductQuantity(AddProdutoCarrinhoDto addProdutoCarrinhoDto);

    PedidoDto decreaseProductQuantity(AddProdutoCarrinhoDto addProdutoCarrinhoDto);

    public PedidoDto fazerPedido(FazerPedidoDto fazerPedidoDto);

    List<PedidoDto> getPedidosFeitos(Long userId);
}
