package com.facul.cefet_store.services.jwt.admin.categoria;

import com.facul.cefet_store.dto.CategoriaDto;
import com.facul.cefet_store.entity.Categoria;

import java.util.List;

public interface CategoriaService {

    Categoria createCategory(CategoriaDto categoriaDto);

    List<Categoria> getAllCategories();
}
