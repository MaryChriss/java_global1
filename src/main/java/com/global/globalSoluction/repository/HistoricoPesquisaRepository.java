package com.global.globalSoluction.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.global.globalSoluction.model.HistoricoPesquisa;
import com.global.globalSoluction.model.User;

public interface HistoricoPesquisaRepository extends JpaRepository<HistoricoPesquisa, Long> {
    List<HistoricoPesquisa> findByUsuarioOrderByDataPesquisaDesc(User usuario);
    
    boolean existsByUsuarioAndCidadeIgnoreCase(User usuario, String cidade);
}
