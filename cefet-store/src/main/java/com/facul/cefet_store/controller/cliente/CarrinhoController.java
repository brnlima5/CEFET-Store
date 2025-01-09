package com.facul.cefet_store.controller.cliente;

import com.facul.cefet_store.dto.AddProdutoCarrinhoDto;
import com.facul.cefet_store.services.cliente.carrinho.CarrinhoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/cliente")
@RequiredArgsConstructor
public class CarrinhoController {

    private final CarrinhoService carrinhoService;

    @PostMapping("/carrinho")
    public ResponseEntity<?> addProdutoCarrinho(@RequestBody AddProdutoCarrinhoDto addProdutoCarrinhoDto) {
        return carrinhoService.addProductToCart(addProdutoCarrinhoDto);
    }
}
