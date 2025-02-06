package com.facul.cefet_store.services.jwt.admin.adminPedido;

import com.facul.cefet_store.dto.AnalisePedido;
import com.facul.cefet_store.dto.PedidoDto;

import java.util.List;

public interface AdminPedidoService {

    List<PedidoDto> getAllPedidos();

    PedidoDto mudarStatusPedido(Long pedidoId, String status);

    AnalisePedido calcularAnalises();

}
