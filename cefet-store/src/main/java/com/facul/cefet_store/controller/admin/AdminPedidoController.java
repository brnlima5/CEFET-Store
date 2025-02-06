package com.facul.cefet_store.controller.admin;

import com.facul.cefet_store.dto.AnalisePedido;
import com.facul.cefet_store.dto.PedidoDto;
import com.facul.cefet_store.services.jwt.admin.adminPedido.AdminPedidoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminPedidoController {

    private final AdminPedidoService adminPedidoService;

    @GetMapping("/pedidosFeitos")
    public ResponseEntity<List<PedidoDto>> getAllPedidos() {
        return ResponseEntity.ok(adminPedidoService.getAllPedidos());
    }

    @GetMapping("/pedido/{pedidoId}/{status}")
    public ResponseEntity<?> mudarStatusPedido(@PathVariable Long pedidoId, @PathVariable String status) {
        PedidoDto pedidoDto = adminPedidoService.mudarStatusPedido(pedidoId, status);

        if(pedidoDto == null) {
            return new ResponseEntity<>("Deu ruim", HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.status(HttpStatus.OK).body(pedidoDto);
    }

    @GetMapping("/pedido/analise")
    public ResponseEntity<AnalisePedido> getAnalise() {
        return ResponseEntity.ok(adminPedidoService.calcularAnalises());
    }

}
