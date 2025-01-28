package com.facul.cefet_store.repository;

import com.facul.cefet_store.entity.Pedido;
import com.facul.cefet_store.enums.StatusPedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {

    Pedido findByUserIdAndOrderStatus(Long userId, StatusPedido statusPedido);

    List<Pedido> findAllByOrderStatusIn(List<StatusPedido> statusPedidosList);

    List<Pedido> findByUserIdAndOrderStatusIn(Long userId, List<StatusPedido> statusPedidos);
}
