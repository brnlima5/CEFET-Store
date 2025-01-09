package com.facul.cefet_store.services.cliente.carrinho;

import com.facul.cefet_store.dto.AddProdutoCarrinhoDto;
import com.facul.cefet_store.entity.ItensCarrinho;
import com.facul.cefet_store.entity.Pedido;
import com.facul.cefet_store.entity.Produto;
import com.facul.cefet_store.entity.Usuario;
import com.facul.cefet_store.enums.StatusPedido;
import com.facul.cefet_store.repository.ItensCarrinhoRepository;
import com.facul.cefet_store.repository.PedidoRepository;
import com.facul.cefet_store.repository.ProdutoRepository;
import com.facul.cefet_store.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

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
                pedidoAtual.getItens().add(carrinho);

                pedidoRepository.save(pedidoAtual);

                return ResponseEntity.status(HttpStatus.CREATED).body(carrinho);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario ou produto não encontrado.");
            }
        }
    }
}
