package com.facul.cefet_store.repository;

import com.facul.cefet_store.entity.Cupom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CupomRepository extends JpaRepository<Cupom, Long> {

    boolean existsByCode(String code);
}
