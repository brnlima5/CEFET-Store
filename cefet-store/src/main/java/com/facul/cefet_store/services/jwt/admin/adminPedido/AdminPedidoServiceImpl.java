package com.facul.cefet_store.services.jwt.admin.adminPedido;

import com.facul.cefet_store.dto.AnalisePedido;
import com.facul.cefet_store.dto.PedidoDto;
import com.facul.cefet_store.entity.Pedido;
import com.facul.cefet_store.enums.StatusPedido;
import com.facul.cefet_store.repository.PedidoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
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

    public AnalisePedido calcularAnalises() {
        LocalDate dataAtual = LocalDate.now();
        LocalDate dataMesAnterior = dataAtual.minusMonths(1);

        Long pedidosMesAtual = getTotalPedidosPorMes(dataAtual.getMonthValue(),  dataAtual.getYear());
        Long pedidosMesAnterior = getTotalPedidosPorMes(dataMesAnterior.getMonthValue(), dataMesAnterior.getYear());

        Long ganhoMesAtual = getGanhoTotalPorMes(dataAtual.getMonthValue(),  dataAtual.getYear());
        Long ganhoMesAnterior = getGanhoTotalPorMes(dataMesAnterior.getMonthValue(), dataMesAnterior.getYear());

        Long adicionado = pedidoRepository.countByOrderStatus(StatusPedido.Adicionado);
        Long enviado = pedidoRepository.countByOrderStatus(StatusPedido.Enviado);
        Long entregue = pedidoRepository.countByOrderStatus(StatusPedido.Entregue);

        return new AnalisePedido(
                adicionado,
                enviado,
                entregue,
                pedidosMesAtual,
                pedidosMesAnterior,
                ganhoMesAtual,
                ganhoMesAnterior);
    }

    public Long getTotalPedidosPorMes(int mes, int ano) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, ano);
        calendar.set(Calendar.MONTH, mes - 1);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);

        Date inicioDoMes = calendar.getTime();

        //Move calendário p/ o final do mês
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);

        Date finalDoMes = calendar.getTime();

        List<Pedido> pedidos = pedidoRepository.findByDateBetweenAndOrderStatus(inicioDoMes, finalDoMes, StatusPedido.Entregue);

        return (long) pedidos.size();
     }

    public Long getGanhoTotalPorMes(int mes, int ano) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, ano);
        calendar.set(Calendar.MONTH, mes - 1);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);

        Date inicioDoMes = calendar.getTime();

        //Move calendário p/ o final do mês
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);

        Date finalDoMes = calendar.getTime();

        List<Pedido> pedidos = pedidoRepository.findByDateBetweenAndOrderStatus(inicioDoMes, finalDoMes, StatusPedido.Entregue);

        Long total = 0L;

        for(Pedido pedido: pedidos) {
            total += pedido.getAmount();
        }
        return total;
    }

}
