package com.facul.cefet_store.controller.cliente;

import com.facul.cefet_store.dto.ListaDesejosDto;
import com.facul.cefet_store.services.cliente.lista_desejos.ListaDesejosService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/cliente")
public class ListaDesejosController {

    private final ListaDesejosService listaDesejosService;

    @PostMapping("/lista-desejos")
    public ResponseEntity<?> addProdutoListaDesejos(@RequestBody ListaDesejosDto listaDesejosDto) {
        ListaDesejosDto addedProduto = listaDesejosService.addProdutoListaDesejos(listaDesejosDto);

        if(addedProduto == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Deu ruim.");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(addedProduto);
    }

    @GetMapping("/lista-desejos/{userId}")
    public ResponseEntity<List<ListaDesejosDto>> getListaDesejosByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(listaDesejosService.getListaDesejosByUserId(userId));
    }
}
