package com.facul.cefet_store.services.jwt.admin.adminPedido;

import com.facul.cefet_store.dto.PedidoDto;
import com.facul.cefet_store.entity.Pedido;
import com.facul.cefet_store.enums.StatusPedido;
import com.facul.cefet_store.repository.PedidoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdminPedidoServiceImpl implements AdminPedidoService {

    private final PedidoRepository pedidoRepository;

    public List<PedidoDto> getAllPedidos() {
        List<Pedido> pedidoList = pedidoRepository.findAllByOrderStatusIn(List.of(StatusPedido.Adicionado, StatusPedido.Enviado, StatusPedido.Entregue));
        return pedidoList.stream().map(Pedido::getPedidoDto).collect(Collectors.toList());
    }


    public PedidoDto mudarStatusPedido(Long pedidoId, String status) {
        Optional<Pedido> optionalPedido = pedidoRepository.findById(pedidoId);

        if(optionalPedido.isPresent()) {
            Pedido pedido = optionalPedido.get();

            if(Objects.equals(status, "Enviado")) {
                pedido.setOrderStatus(StatusPedido.Enviado);
            } else if(Objects.equals(status, "Entregue")) {
                pedido.setOrderStatus(StatusPedido.Entregue);
            }
            return pedidoRepository.save(pedido).getPedidoDto();
        }
        return null;
    }

}
