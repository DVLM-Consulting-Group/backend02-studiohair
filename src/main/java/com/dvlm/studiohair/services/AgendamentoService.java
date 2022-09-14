package com.dvlm.studiohair.services;

import com.dvlm.studiohair.domain.Agendamento;
import com.dvlm.studiohair.repositories.AgendamentoRepository;
import com.dvlm.studiohair.services.excecoes.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AgendamentoService {

    @Autowired
    private AgendamentoRepository repository;

    public Agendamento findById(Integer id) {
        Optional<Agendamento> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! ID: " + id));
    }

    public List<Agendamento> findAll() {
        return repository.findAll();
    }
}
