package com.dvlm.studiohair.services;

import com.dvlm.studiohair.domain.Agendamento;
import com.dvlm.studiohair.domain.Cliente;
import com.dvlm.studiohair.domain.Funcionario;
import com.dvlm.studiohair.domain.Servico;
import com.dvlm.studiohair.domain.enuns.Perfil;
import com.dvlm.studiohair.domain.enuns.Status;
import com.dvlm.studiohair.repositories.AgendamentoRepository;
import com.dvlm.studiohair.repositories.ClienteRepository;
import com.dvlm.studiohair.repositories.FuncionarioRepository;
import com.dvlm.studiohair.repositories.ServicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class DBService {

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private AgendamentoRepository agendamentoRepository;

    @Autowired
    private ServicoRepository servicoRepository;

    public void instanciaDB(){
        Funcionario f1 = new Funcionario(null,"Luan Braz","629.988.730-30",
                "luan@dvlm.com","1234", "(19) 98745-6658");
        f1.addPerfil(Perfil.ADMIN);

        Cliente c1 = new Cliente(null,"Mateus Cavalcante","548.169.780-70",
                "mateus@dvlm.com","123", "(12) 98716-5247");
        c1.addPerfil(Perfil.CLIENTE);

        Servico s1 = new Servico(null,"Corte",
                "Cortar cabelo e lavar",20.00);

        Funcionario f2 = new Funcionario(null,"Diego Dutra","456.123.023-50",
                "diego@gmail.com","123456", "(12) 92569-4136");
        f2.addPerfil(Perfil.ADMIN);

        Agendamento a1 = new Agendamento(null, "Observação", Status.ABERTO, f1, s1, c1);

        funcionarioRepository.saveAll(Arrays.asList(f1));
        clienteRepository.saveAll(Arrays.asList(c1));
        servicoRepository.saveAll(Arrays.asList(s1));
        agendamentoRepository.saveAll(Arrays.asList(a1));
        funcionarioRepository.saveAll(Arrays.asList(f2));
    }
}
