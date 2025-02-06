package com.facul.cefet_store.controller;

import com.facul.cefet_store.dto.PedidoDto;
import com.facul.cefet_store.services.cliente.carrinho.CarrinhoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class RastreioPedidoController {

    private final CarrinhoService carrinhoService;

    @GetMapping("/pedido/{trackingId}")
    public ResponseEntity<PedidoDto> buscarPedidoByTrackingId(@PathVariable UUID trackingId) {
        PedidoDto pedidoDto = carrinhoService.buscarPedidoByTrackingId(trackingId);

        if(pedidoDto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(pedidoDto);
    }
}
