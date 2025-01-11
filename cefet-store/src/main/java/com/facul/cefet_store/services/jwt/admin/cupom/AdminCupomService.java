package com.facul.cefet_store.services.jwt.admin.cupom;

import com.facul.cefet_store.entity.Cupom;

import java.util.List;

public interface AdminCupomService {

    Cupom createCupom(Cupom cupom);

    List<Cupom> getAllCupons();
}
