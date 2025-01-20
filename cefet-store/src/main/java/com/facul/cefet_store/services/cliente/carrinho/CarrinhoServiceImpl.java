package com.facul.cefet_store.services.cliente.carrinho;

import com.facul.cefet_store.dto.AddProdutoCarrinhoDto;
import com.facul.cefet_store.dto.ItensCarrinhoDto;
import com.facul.cefet_store.dto.PedidoDto;
import com.facul.cefet_store.entity.*;
import com.facul.cefet_store.enums.StatusPedido;
import com.facul.cefet_store.exceptions.ValidationException;
import com.facul.cefet_store.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CarrinhoServiceImpl implements CarrinhoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ItensCarrinhoRepository itensCarrinhoRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private CupomRepository cupomRepository;


    public ResponseEntity<?> addProductToCart(AddProdutoCarrinhoDto addProdutoCarrinhoDto) {
        Pedido pedidoAtual = pedidoRepository.findByUserIdAndOrderStatus(addProdutoCarrinhoDto.getUserId(), StatusPedido.Pendente);
        Optional<ItensCarrinho> optionalItensCarrinho = itensCarrinhoRepository.findByProductIdAndOrderIdAndUserId
                (addProdutoCarrinhoDto.getProductId(), pedidoAtual.getId(), addProdutoCarrinhoDto.getUserId());

        if(optionalItensCarrinho.isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        } else {
            Optional<Produto> optionalProduto = produtoRepository.findById(addProdutoCarrinhoDto.getProductId());
            Optional<Usuario> optionalUsuario = usuarioRepository.findById(addProdutoCarrinhoDto.getUserId());

            if (optionalProduto.isPresent() && optionalUsuario.isPresent()) {
                ItensCarrinho carrinho = new ItensCarrinho();
                carrinho.setProduct(optionalProduto.get());
                carrinho.setPrice(optionalProduto.get().getPrice());
                carrinho.setQuantity(1L);
                carrinho.setUser(optionalUsuario.get());
                carrinho.setOrder(pedidoAtual);

                ItensCarrinho updateCarrinho = itensCarrinhoRepository.save(carrinho);

                pedidoAtual.setTotalAmount(pedidoAtual.getTotalAmount() + carrinho.getPrice());
                pedidoAtual.setAmount(pedidoAtual.getAmount() + carrinho.getPrice());
                pedidoAtual.getCartItems().add(carrinho);

                pedidoRepository.save(pedidoAtual);

                return ResponseEntity.status(HttpStatus.CREATED).body(carrinho);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario ou produto não encontrado.");
            }
        }
    }

    public PedidoDto getCartByUserId(Long userId) {
        Pedido pedidoAtual = pedidoRepository.findByUserIdAndOrderStatus(userId, StatusPedido.Pendente);
        List<ItensCarrinhoDto> itensCarrinhoDto = pedidoAtual.getCartItems().stream().map(ItensCarrinho::getCarrinhoDto).collect(Collectors.toList());
        PedidoDto pedidoDto = new PedidoDto();
        pedidoDto.setAmount(pedidoAtual.getAmount());
        pedidoDto.setId(pedidoAtual.getId());
        pedidoDto.setOrderStatus(pedidoAtual.getOrderStatus());
        pedidoDto.setDiscount(pedidoAtual.getDiscount());
        pedidoDto.setTotalAmount(pedidoAtual.getTotalAmount());
        pedidoDto.setCartItems(itensCarrinhoDto);

        if(pedidoAtual.getCupom() != null) {
            pedidoDto.setCupomName(pedidoAtual.getCupom().getName());
        }

        return pedidoDto;
    }

    public PedidoDto applyCupom(Long userId, String code) {
        Pedido pedidoAtual = pedidoRepository.findByUserIdAndOrderStatus(userId, StatusPedido.Pendente);
        Cupom cupom = cupomRepository.findByCode(code).orElseThrow(()-> new ValidationException("Cupom não encontrado."));

        if(cupomExpirado(cupom)) {
            throw new ValidationException("Cupom expirado.");
        }

        double desconto = ((cupom.getDiscount() / 100.0) * pedidoAtual.getTotalAmount());
        double liquido = pedidoAtual.getTotalAmount() - desconto;

        pedidoAtual.setAmount((long)liquido);
        pedidoAtual.setDiscount((long)desconto);
        pedidoAtual.setCupom(cupom);

        pedidoRepository.save(pedidoAtual);

        return pedidoAtual.getPedidoDto();
    }

    private boolean cupomExpirado(Cupom cupom) {
        Date dataAtual = new Date();
        Date dataExpiracao = cupom.getExpirationDate();

        return dataExpiracao != null && dataAtual.after(dataExpiracao);
    }


}
