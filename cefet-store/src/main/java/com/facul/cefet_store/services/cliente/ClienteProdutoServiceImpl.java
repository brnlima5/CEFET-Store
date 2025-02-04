package com.facul.cefet_store.services.cliente;

import com.facul.cefet_store.dto.DetalheProdutoDto;
import com.facul.cefet_store.dto.ProdutoDto;
import com.facul.cefet_store.entity.Avaliacao;
import com.facul.cefet_store.entity.FAQ;
import com.facul.cefet_store.entity.Produto;
import com.facul.cefet_store.repository.AvaliacaoRepository;
import com.facul.cefet_store.repository.FAQRepository;
import com.facul.cefet_store.repository.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClienteProdutoServiceImpl implements ClienteProdutoService {

    private final ProdutoRepository produtoRepository;

    private final FAQRepository faqRepository;

    private final AvaliacaoRepository avaliacaoRepository;


    public List<ProdutoDto> getAllProducts() {
        List<Produto> produtos = produtoRepository.findAll();
        return produtos.stream().map(Produto::getDto).collect(Collectors.toList());
    }

    public List<ProdutoDto> searchProductByTitle(String name) {
        List<Produto> produtos = produtoRepository.findAllByName(name);
        return produtos.stream().map(Produto::getDto).collect(Collectors.toList());
    }

    public DetalheProdutoDto getProductDetailById(Long productId) {
        Optional<Produto> optionalProduto = produtoRepository.findById(productId);

        if(optionalProduto.isPresent()) {
            List<FAQ> faqList = faqRepository.findAllByProductId(productId);
            List<Avaliacao> avaliacaoList = avaliacaoRepository.findAllByProductId(productId);

            DetalheProdutoDto detalheProdutoDto = new DetalheProdutoDto();
            detalheProdutoDto.setProdutoDto(optionalProduto.get().getDto());
            detalheProdutoDto.setFaqDtoList(faqList.stream().map(FAQ::getFAQDto).collect(Collectors.toList()));
            detalheProdutoDto.setAvaliacaoDtoList(avaliacaoList.stream().map(Avaliacao::getDto).collect(Collectors.toList()));

            return detalheProdutoDto;
        }
        return null;
    }

}
