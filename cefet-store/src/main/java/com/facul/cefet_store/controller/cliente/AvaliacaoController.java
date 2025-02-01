package com.facul.cefet_store.controller.cliente;

import com.facul.cefet_store.dto.AvaliacaoDto;
import com.facul.cefet_store.dto.ProdutosPedidosDto;
import com.facul.cefet_store.services.cliente.avaliacao.AvaliacaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/cliente")
public class AvaliacaoController {

    private final AvaliacaoService avaliacaoService;

    @GetMapping("/produtos-pedidos/{pedidoId}")
    public ResponseEntity<ProdutosPedidosDto> getProdutosPedidosById(@PathVariable Long pedidoId) {
        return ResponseEntity.ok(avaliacaoService.getProdutosPedidosById(pedidoId));
    }

    @PostMapping("/avaliacao")
    public ResponseEntity<?> getAvaliacao(@ModelAttribute AvaliacaoDto avaliacaoDto) throws IOException {
        AvaliacaoDto avaliacaoDto1 = avaliacaoService.getAvaliacao(avaliacaoDto);

        if(avaliacaoDto1 == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Deu ruim");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(avaliacaoDto1);
    }

}
