package com.global.globalSoluction.controller;

import com.global.globalSoluction.model.Dados;
import com.global.globalSoluction.model.HistoricoPesquisa;
import com.global.globalSoluction.model.User;
import com.global.globalSoluction.repository.DadosRepository;
import com.global.globalSoluction.repository.HistoricoPesquisaRepository;
import com.global.globalSoluction.repository.UserRepository;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/dados")
public class DadosController {

    @Autowired
    private DadosRepository dadosRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private HistoricoPesquisaRepository historicoRepository;

   @GetMapping
public Page<Dados> listarComFiltros(
        @RequestParam(required = false) String cidade,
        @RequestParam(required = false) Long idUsuario,
        Pageable pageable) {

    Dados filtro = new Dados();

    if (cidade != null && !cidade.isBlank()) {
        filtro.setCidade(cidade.trim());

        if (idUsuario != null) {
            User usuario = userRepository.findById(idUsuario)
                    .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

            boolean jaPesquisada = historicoRepository.existsByUsuarioAndCidadeIgnoreCase(usuario, cidade);

            if (!jaPesquisada && cidade.matches("^[A-Za-zÀ-ÿ\\s]+$")) {
                HistoricoPesquisa historico = HistoricoPesquisa.builder()
                        .usuario(usuario)
                        .cidade(cidade.trim())
                        .dataPesquisa(LocalDateTime.now())
                        .build();
                historicoRepository.save(historico);
            }
        }
    }

    ExampleMatcher matcher = ExampleMatcher.matchingAll()
            .withIgnoreNullValues()
            .withIgnoreCase()
            .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);

    Example<Dados> example = Example.of(filtro, matcher);

    return dadosRepository.findAll(example, pageable);
}

}
