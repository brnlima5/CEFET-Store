package com.facul.cefet_store.services.cliente.lista_desejos;

import com.facul.cefet_store.dto.ListaDesejosDto;

import java.util.List;

public interface ListaDesejosService {

    ListaDesejosDto addProdutoListaDesejos(ListaDesejosDto listaDesejosDto);

    List<ListaDesejosDto> getListaDesejosByUserId(Long userId);
}
