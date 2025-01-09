package com.facul.cefet_store.controller.cliente;

import com.facul.cefet_store.dto.ProdutoDto;
import com.facul.cefet_store.services.cliente.ClienteProdutoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/cliente")
@RequiredArgsConstructor
public class ClienteProdutoController {

    private final ClienteProdutoService clienteProdutoService;

    @GetMapping("/produtos")
    public ResponseEntity<List<ProdutoDto>> getAllProducts() {
        List<ProdutoDto> produtoDtos = clienteProdutoService.getAllProducts();
        return ResponseEntity.ok(produtoDtos);
    }

    @GetMapping("/search/{name}")
    public ResponseEntity<List<ProdutoDto>> getAllProductsByName(@PathVariable String name) {
        List<ProdutoDto> produtoDtos = clienteProdutoService.searchProductByTitle(name);
        return ResponseEntity.ok(produtoDtos);
    }
}
