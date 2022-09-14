package com.dvlm.studiohair.services;

import com.dvlm.studiohair.domain.Agendamento;
import com.dvlm.studiohair.domain.Cliente;
import com.dvlm.studiohair.domain.Funcionario;
import com.dvlm.studiohair.domain.Servico;
import com.dvlm.studiohair.domain.enuns.Status;
import com.dvlm.studiohair.dtos.AgendamentoDTO;
import com.dvlm.studiohair.repositories.AgendamentoRepository;
import com.dvlm.studiohair.services.excecoes.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class AgendamentoService {

    @Autowired
    private AgendamentoRepository repository;

    @Autowired
    private FuncionarioService funcionarioService;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private ServicoService servicoService;

    public Agendamento findById(Integer id) {
        Optional<Agendamento> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! ID: " + id));
    }

    public List<Agendamento> findAll() {
        return repository.findAll();
    }

    public Agendamento create(AgendamentoDTO obj) {
        return repository.save(newAgendamento(obj));
    }

    public Agendamento update(Integer id, AgendamentoDTO objDTO) {
        objDTO.setId(id);
        Agendamento oldObj = findById(id);
        oldObj = newAgendamento(objDTO);
        return repository.save(oldObj);
    }

    private Agendamento newAgendamento(AgendamentoDTO obj) {
        Funcionario funcionario = funcionarioService.buscarPeloId(obj.getFuncionario());
        Cliente cliente = clienteService.findById(obj.getCliente());
        Servico servico = servicoService.findById(obj.getServico());

        Agendamento agendamento = new Agendamento();
        if(obj.getId() != null) {
            agendamento.setId(obj.getId());
        }

        if(obj.getStatus().equals(1)) {
            agendamento.setDataExServico(LocalDateTime.now());
        }


        agendamento.setFuncionario(funcionario);
        agendamento.setCliente(cliente);
        agendamento.setServico(servico);
        agendamento.setStatus(Status.toEnum(obj.getStatus()));
        agendamento.setObservacoes(obj.getObservacoes());
        return agendamento;
    }


}
