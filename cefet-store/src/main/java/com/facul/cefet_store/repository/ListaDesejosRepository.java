package com.facul.cefet_store.repository;

import com.facul.cefet_store.entity.ListaDesejos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ListaDesejosRepository extends JpaRepository<ListaDesejos, Long> {

    List<ListaDesejos> findAllByUserId(Long userId);
}
