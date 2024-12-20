package com.facul.cefet_store.controller.admin;

import com.facul.cefet_store.dto.ProdutoDto;
import com.facul.cefet_store.services.jwt.admin.adminproduto.AdminProdutoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminProdutoController {

    private final AdminProdutoService adminProdutoService;

    @PostMapping("/produto")
    public ResponseEntity<ProdutoDto> addProduct(@ModelAttribute ProdutoDto produtoDto) throws IOException {
        ProdutoDto produtoDto1 = adminProdutoService.addProduct(produtoDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(produtoDto1);
    }

    @GetMapping("/produtos")
    public ResponseEntity<List<ProdutoDto>> getAllProducts() {
        List<ProdutoDto> produtoDtos = adminProdutoService.getAllProducts();
        return ResponseEntity.ok(produtoDtos);
    }

    @GetMapping("/search/{name}")
    public ResponseEntity<List<ProdutoDto>> getAllProductsByName(@PathVariable String name) {
        List<ProdutoDto> produtoDtos = adminProdutoService.getAllProductsByName(name);
        return ResponseEntity.ok(produtoDtos);
    }

    @DeleteMapping("/produto/{productId}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long productId) {
        boolean deleted = adminProdutoService.deleteProduct(productId);
        if(deleted) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
