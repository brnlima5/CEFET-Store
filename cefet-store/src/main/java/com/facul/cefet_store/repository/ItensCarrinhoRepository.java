package com.facul.cefet_store.repository;

import com.facul.cefet_store.entity.ItensCarrinho;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ItensCarrinhoRepository extends JpaRepository<ItensCarrinho, Long> {

    Optional<ItensCarrinho> findByProductIdAndOrderIdAndUserId(Long productId, Long orderId, Long userId);
}
