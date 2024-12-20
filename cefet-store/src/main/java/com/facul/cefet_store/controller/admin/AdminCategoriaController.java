package com.facul.cefet_store.controller.admin;

import com.facul.cefet_store.dto.CategoriaDto;
import com.facul.cefet_store.entity.Categoria;
import com.facul.cefet_store.services.jwt.admin.categoria.CategoriaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminCategoriaController {

    private final CategoriaService categoriaService;

    @PostMapping("categoria")
    public ResponseEntity<Categoria> createCategory(@RequestBody CategoriaDto categoriaDto) {
        Categoria categoria = categoriaService.createCategory(categoriaDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(categoria);
    }

    @GetMapping("")
    public ResponseEntity<List<Categoria>> getAllCategories() {
        return ResponseEntity.ok(categoriaService.getAllCategories());
    }

}
