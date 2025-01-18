package com.facul.cefet_store.services.jwt.admin.cupom;

import com.facul.cefet_store.entity.Cupom;
import com.facul.cefet_store.exceptions.ValidationException;
import com.facul.cefet_store.repository.CupomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminCupomServiceImpl implements AdminCupomService {

    private final CupomRepository cupomRepository;

    public Cupom createCupom(Cupom cupom) {
        if(cupomRepository.existsByCode(cupom.getCode())) {
            throw new ValidationException("O código do cupom já existe.");

        }
        return cupomRepository.save(cupom);
    }

    public List<Cupom> getAllCupons() {
        return cupomRepository.findAll();
    }
}
