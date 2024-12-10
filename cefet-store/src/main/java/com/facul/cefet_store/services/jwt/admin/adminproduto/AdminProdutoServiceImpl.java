package com.facul.cefet_store.services.jwt.admin.adminproduto;

import com.facul.cefet_store.dto.ProdutoDto;
import com.facul.cefet_store.entity.Categoria;
import com.facul.cefet_store.entity.Produto;
import com.facul.cefet_store.repository.CategoriaRepository;
import com.facul.cefet_store.repository.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdminProdutoServiceImpl implements AdminProdutoService {

    private final ProdutoRepository produtoRepository;

    private final CategoriaRepository categoriaRepository;


    public ProdutoDto addProduct(ProdutoDto produtoDto) throws IOException {
        Produto produto = new Produto();
        produto.setName(produtoDto.getName());
        produto.setDescription(produtoDto.getDescription());
        produto.setPrice(produtoDto.getPrice());
        produto.setImg(produtoDto.getImg().getBytes());

        Categoria categoria = categoriaRepository.findById(produtoDto.getCategoryId()).orElseThrow();

        produto.setCategoria(categoria);
        return produtoRepository.save(produto).getDto();
    }

    public List<ProdutoDto> getAllProducts() {
        List<Produto> produtos = produtoRepository.findAll();
        return produtos.stream().map(Produto::getDto).collect(Collectors.toList());
    }

}
