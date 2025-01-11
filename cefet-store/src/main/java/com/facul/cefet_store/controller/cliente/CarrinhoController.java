package com.facul.cefet_store.controller.cliente;

import com.facul.cefet_store.dto.AddProdutoCarrinhoDto;
import com.facul.cefet_store.dto.PedidoDto;
import com.facul.cefet_store.services.cliente.carrinho.CarrinhoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cliente")
@RequiredArgsConstructor
public class CarrinhoController {

    private final CarrinhoService carrinhoService;

    @PostMapping("/carrinho")
    public ResponseEntity<?> addProdutoCarrinho(@RequestBody AddProdutoCarrinhoDto addProdutoCarrinhoDto) {
        return carrinhoService.addProductToCart(addProdutoCarrinhoDto);
    }

    @GetMapping("/carrinho/{userId}")
    public ResponseEntity<?> getCarrinhoByUserId(@PathVariable Long userId) {
        PedidoDto pedidoDto = carrinhoService.getCartByUserId(userId);
        return ResponseEntity.status(HttpStatus.OK).body(pedidoDto);
    }
}
