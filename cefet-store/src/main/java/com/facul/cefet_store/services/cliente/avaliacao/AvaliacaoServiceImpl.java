package com.facul.cefet_store.services.cliente.avaliacao;

import com.facul.cefet_store.dto.AvaliacaoDto;
import com.facul.cefet_store.dto.ProdutoDto;
import com.facul.cefet_store.dto.ProdutosPedidosDto;
import com.facul.cefet_store.entity.*;
import com.facul.cefet_store.repository.AvaliacaoRepository;
import com.facul.cefet_store.repository.PedidoRepository;
import com.facul.cefet_store.repository.ProdutoRepository;
import com.facul.cefet_store.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AvaliacaoServiceImpl implements AvaliacaoService {

    private final PedidoRepository pedidoRepository;

    private final ProdutoRepository produtoRepository;

    private final UsuarioRepository usuarioRepository;

    private final AvaliacaoRepository avaliacaoRepository;

    public ProdutosPedidosDto getProdutosPedidosById(Long pedidoId) {
        Optional<Pedido> optionalPedido = pedidoRepository.findById(pedidoId);
        ProdutosPedidosDto produtosPedidosDto = new ProdutosPedidosDto();

        if(optionalPedido.isPresent()) {
            produtosPedidosDto.setValorPedido(optionalPedido.get().getAmount());

            List<ProdutoDto> produtoDtoList = new ArrayList<>();
            for(ItensCarrinho itensCarrinho : optionalPedido.get().getCartItems()) {
                ProdutoDto produtoDto = new ProdutoDto();

                produtoDto.setId(itensCarrinho.getProduct().getId());
                produtoDto.setName(itensCarrinho.getProduct().getName());
                produtoDto.setPrice(itensCarrinho.getPrice());
                produtoDto.setQuantity(itensCarrinho.getQuantity());
                produtoDto.setByteImg(itensCarrinho.getProduct().getImg());

                produtoDtoList.add(produtoDto);
            }
            produtosPedidosDto.setProdutoDtoList(produtoDtoList);
        }
        return produtosPedidosDto;
    }

    public AvaliacaoDto getAvaliacao(AvaliacaoDto avaliacaoDto) throws IOException {
        Optional<Produto> optionalProduto = produtoRepository.findById(avaliacaoDto.getProductId());
        Optional<Usuario> optionalUsuario = usuarioRepository.findById(avaliacaoDto.getUserId());

        if(optionalProduto.isPresent() && optionalUsuario.isPresent()) {
            Avaliacao avaliacao = new Avaliacao();

            avaliacao.setRating(avaliacaoDto.getRating());
            avaliacao.setDescription(avaliacaoDto.getDescription());
            avaliacao.setUser(optionalUsuario.get());
            avaliacao.setProduct(optionalProduto.get());
            avaliacao.setImg(avaliacaoDto.getImg().getBytes());

            return avaliacaoRepository.save(avaliacao).getDto();
        }
        return null;
    }
}
