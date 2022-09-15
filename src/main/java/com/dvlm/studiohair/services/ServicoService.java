package com.dvlm.studiohair.services;

import com.dvlm.studiohair.domain.Servico;
import com.dvlm.studiohair.repositories.ServicoRepository;
import com.dvlm.studiohair.services.excecoes.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServicoService {

    @Autowired
    private ServicoRepository repository;

    public Servico findById (Integer id) {
        Optional<Servico> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! ID: " + id));
    }

    public List<Servico> findAll() {
        return repository.findAll();
    }

    public Servico create(Servico obj) {
        return repository.save(obj);
    }


    public Servico update(Integer id, Servico obj) {
        Servico newObj = findById(id);
        newObj.setNomeServico(obj.getNomeServico());
        newObj.setDescricaoServico(obj.getDescricaoServico());
        newObj.setPrecoServico(obj.getPrecoServico());
        return repository.save(newObj);
    }

}
