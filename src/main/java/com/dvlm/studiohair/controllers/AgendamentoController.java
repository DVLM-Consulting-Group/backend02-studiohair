package com.dvlm.studiohair.controllers;

import com.dvlm.studiohair.domain.Agendamento;
import com.dvlm.studiohair.dtos.AgendamentoDTO;
import com.dvlm.studiohair.services.AgendamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/agendamentos")
public class AgendamentoController {

    @Autowired
    private AgendamentoService service;

    @GetMapping(value = "/{id}")
    public ResponseEntity<AgendamentoDTO> findById(@PathVariable Integer id) {
        Agendamento obj = service.findById(id);
        return ResponseEntity.ok().body(new AgendamentoDTO(obj));
    }

    @GetMapping
    public ResponseEntity<List<AgendamentoDTO>> findAll() {
        List<Agendamento> list = service.findAll();
        List<AgendamentoDTO> listDTO = list.stream().map(obj -> new AgendamentoDTO(obj)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDTO);
    }
}