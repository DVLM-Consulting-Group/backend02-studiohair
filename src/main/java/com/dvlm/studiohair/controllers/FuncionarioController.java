package com.dvlm.studiohair.controllers;

import com.dvlm.studiohair.domain.Funcionario;
import com.dvlm.studiohair.dtos.FuncionarioDTO;
import com.dvlm.studiohair.services.FuncionarioService;
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
@RequestMapping(value = "/funcionarios")
public class FuncionarioController {

    @Autowired
    private FuncionarioService funcionarioService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<FuncionarioDTO> buscarPeloId(@PathVariable Integer id){
        FuncionarioDTO funcionarioDTO = new FuncionarioDTO(funcionarioService.buscarPeloId(id));
        return ResponseEntity.ok().body(funcionarioDTO);
    }

    @GetMapping
    public ResponseEntity<List<FuncionarioDTO>> buscarTodos(){

        List<FuncionarioDTO> listaDTO =funcionarioService.buscarTodos().stream()
                .map(func -> new FuncionarioDTO(func)).collect(Collectors.toList());

        return ResponseEntity.ok().body(listaDTO);
    }

    @PostMapping
    public ResponseEntity<FuncionarioDTO> criarNovoFuncionario(@Valid @RequestBody FuncionarioDTO objDTO){
        Funcionario newObj = funcionarioService.criarNovoFuncionario(objDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newObj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<FuncionarioDTO> update(@PathVariable Integer id, @Valid @RequestBody FuncionarioDTO objDTO) {
        Funcionario obj = funcionarioService.update(id, objDTO);
        return ResponseEntity.ok().body(new FuncionarioDTO(obj));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<FuncionarioDTO> delete(@PathVariable Integer id) {
        funcionarioService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

