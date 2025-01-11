package com.facul.cefet_store.controller.admin;

import com.facul.cefet_store.entity.Cupom;
import com.facul.cefet_store.exceptions.ValidationException;
import com.facul.cefet_store.services.jwt.admin.cupom.AdminCupomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/cupons")
@RequiredArgsConstructor
public class AdminCupomController {

    private final AdminCupomService adminCupomService;

    @PostMapping
    public ResponseEntity<?> createCupom(@RequestBody Cupom cupom) {
        try {
            Cupom createCupom = adminCupomService.createCupom(cupom);
            return ResponseEntity.ok(createCupom);
        } catch (ValidationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<Cupom>> getAllCupons() {
        return ResponseEntity.ok(adminCupomService.getAllCupons());
    }
}
