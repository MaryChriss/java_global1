package com.global.globalSoluction.controller;

import com.global.globalSoluction.model.HistoricoPesquisa;
import com.global.globalSoluction.model.User;
import com.global.globalSoluction.repository.HistoricoPesquisaRepository;
import com.global.globalSoluction.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/historico")
public class HistoricoPesquisaController {

    @Autowired
    private HistoricoPesquisaRepository historicoRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/{idUsuario}")
    public List<HistoricoPesquisa> listarHistorico(@PathVariable Long idUsuario) {
        User usuario = userRepository.findById(idUsuario)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        return historicoRepository.findByUsuarioOrderByDataPesquisaDesc(usuario);
    }
}
