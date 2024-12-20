package com.facul.cefet_store.services.jwt.admin.adminproduto;

import com.facul.cefet_store.dto.ProdutoDto;

import java.io.IOException;
import java.util.List;

public interface AdminProdutoService {

    ProdutoDto addProduct(ProdutoDto produtoDto) throws IOException;

    List<ProdutoDto> getAllProducts();

    List<ProdutoDto> getAllProductsByName(String name);

    boolean deleteProduct(Long id);
}
