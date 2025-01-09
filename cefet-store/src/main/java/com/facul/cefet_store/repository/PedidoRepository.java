package com.facul.cefet_store.repository;

import com.facul.cefet_store.entity.Pedido;
import com.facul.cefet_store.enums.StatusPedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {

    Pedido findByUserIdAndOrderStatus(Long userId, StatusPedido statusPedido);
}
