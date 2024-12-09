package com.facul.cefet_store.services.jwt.admin.categoria;

import com.facul.cefet_store.dto.CategoriaDto;
import com.facul.cefet_store.entity.Categoria;
import com.facul.cefet_store.repository.CategoriaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoriaServiceImpl implements CategoriaService {

    private final CategoriaRepository categoriaRepository;


    public Categoria createCategory(CategoriaDto categoriaDto) {
        Categoria categoria = new Categoria();
        categoria.setName(categoriaDto.getName());
        categoria.setDescription(categoriaDto.getDescription());

        return categoriaRepository.save(categoria);
    }

    public List<Categoria> getAllCategories() {
        return categoriaRepository.findAll();
    }
}
