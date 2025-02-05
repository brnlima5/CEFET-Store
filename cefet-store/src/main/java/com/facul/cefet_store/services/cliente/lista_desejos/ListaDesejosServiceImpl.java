package com.facul.cefet_store.services.cliente.lista_desejos;

import com.facul.cefet_store.dto.ListaDesejosDto;
import com.facul.cefet_store.entity.ListaDesejos;
import com.facul.cefet_store.entity.Produto;
import com.facul.cefet_store.entity.Usuario;
import com.facul.cefet_store.repository.ListaDesejosRepository;
import com.facul.cefet_store.repository.ProdutoRepository;
import com.facul.cefet_store.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ListaDesejosServiceImpl implements ListaDesejosService {

    private final UsuarioRepository usuarioRepository;

    private final ProdutoRepository produtoRepository;

    private final ListaDesejosRepository listaDesejosRepository;


    public ListaDesejosDto addProdutoListaDesejos(ListaDesejosDto listaDesejosDto) {
        Optional<Produto> optionalProduto = produtoRepository.findById(listaDesejosDto.getProductId());
        Optional<Usuario> optionalUsuario = usuarioRepository.findById(listaDesejosDto.getUserId());

        if(optionalProduto.isPresent() && optionalUsuario.isPresent()) {
            ListaDesejos listaDesejos = new ListaDesejos();
            listaDesejos.setProduct(optionalProduto.get());
            listaDesejos.setUser(optionalUsuario.get());

            return listaDesejosRepository.save(listaDesejos).getListaDesejosDto();
        }
        return null;
    }

}
