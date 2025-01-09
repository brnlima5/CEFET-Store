package com.facul.cefet_store.services.cliente;

import com.facul.cefet_store.dto.ProdutoDto;
import com.facul.cefet_store.entity.Produto;
import com.facul.cefet_store.repository.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClienteProdutoServiceImpl implements ClienteProdutoService {

    private final ProdutoRepository produtoRepository;



    public List<ProdutoDto> getAllProducts() {
        List<Produto> produtos = produtoRepository.findAll();
        return produtos.stream().map(Produto::getDto).collect(Collectors.toList());
    }

    public List<ProdutoDto> searchProductByTitle(String name) {
        List<Produto> produtos = produtoRepository.findAllByName(name);
        return produtos.stream().map(Produto::getDto).collect(Collectors.toList());
    }
}
