package com.dvlm.studiohair.services;

import com.dvlm.studiohair.domain.Funcionario;
import com.dvlm.studiohair.repositories.FuncionarioRepository;
import com.dvlm.studiohair.repositories.PessoaRepository;
import com.dvlm.studiohair.services.excecoes.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FuncionarioService {

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Autowired
    private PessoaRepository pessoaRepository;

    public Funcionario buscarPeloId(Integer id){
        Optional<Funcionario> obj = funcionarioRepository.findById(id); //pode encontrar ou não!
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + id + ", Tipo: " + Funcionario.class.getName()));
    }
}
