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
import java.util.Optional;
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

    public List<ProdutoDto> getAllProductsByName(String name) {
        List<Produto> produtos = produtoRepository.findAllByName(name);
        return produtos.stream().map(Produto::getDto).collect(Collectors.toList());
    }

    public boolean deleteProduct(Long id) {
        Optional<Produto> optionalProduto = produtoRepository.findById(id);
        if(optionalProduto.isPresent()) {
            produtoRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public ProdutoDto getProductById(Long productId) {
        Optional<Produto> optionalProduto = produtoRepository.findById(productId);

        if(optionalProduto.isPresent()) {
            return optionalProduto.get().getDto();
        } else {
            return null;
        }
    }

    public ProdutoDto updateProduct(Long produtoId, ProdutoDto produtoDto) throws IOException {
        Optional<Produto> optionalProduto = produtoRepository.findById(produtoId);
        Optional<Categoria> optionalCategoria = categoriaRepository.findById(produtoDto.getCategoryId());

        //---------teste---------
        System.out.println("ID Produto: " + produtoId);
        System.out.println("Produto DTO: " +
                produtoDto.getId() + " / " +
                produtoDto.getName() + "/ " +
                produtoDto.getPrice());
        //----------------------
        if(optionalProduto.isPresent() && optionalCategoria.isPresent()) {
            System.out.println("->update");
            Produto produto = optionalProduto.get();

            produto.setName(produtoDto.getName());
            produto.setPrice(produtoDto.getPrice());
            produto.setDescription(produtoDto.getDescription());
            produto.setCategoria(optionalCategoria.get());
            if(produtoDto.getImg() != null) {
                produto.setImg(produtoDto.getImg().getBytes());
            }
            return produtoRepository.save(produto).getDto();
        } else {
            System.out.println("->nulo");
            return null;
        }
    }


}
