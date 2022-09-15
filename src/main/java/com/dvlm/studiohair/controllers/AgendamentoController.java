package com.dvlm.studiohair.controllers;

import com.dvlm.studiohair.domain.Agendamento;
import com.dvlm.studiohair.dtos.AgendamentoDTO;
import com.dvlm.studiohair.services.AgendamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin("*")   // faz com q api esteja disponivel para requisições de mult. fontes
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

    @PostMapping
    public ResponseEntity<AgendamentoDTO> create(@Valid @RequestBody AgendamentoDTO objdDto) {
        Agendamento newObj = service.create(objdDto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newObj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<AgendamentoDTO> update(@PathVariable Integer id, @Valid @RequestBody AgendamentoDTO objDTO) {
        Agendamento newObj = service.update(id, objDTO);
        return ResponseEntity.ok().body(new AgendamentoDTO(newObj));
    }
}
