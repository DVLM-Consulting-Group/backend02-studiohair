package com.dvlm.studiohair.services;

import com.dvlm.studiohair.domain.Funcionario;
import com.dvlm.studiohair.domain.Pessoa;
import com.dvlm.studiohair.dtos.FuncionarioDTO;
import com.dvlm.studiohair.repositories.FuncionarioRepository;
import com.dvlm.studiohair.repositories.PessoaRepository;
import com.dvlm.studiohair.services.excecoes.DataIntegrityViolationException;
import com.dvlm.studiohair.services.excecoes.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FuncionarioService {

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private BCryptPasswordEncoder encoder;

    public  Funcionario buscarPeloId(Integer id){
        Optional<Funcionario> obj = funcionarioRepository.findById(id); //pode encontrar ou não!
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + id + ", Tipo: " + Funcionario.class.getName()));
    }

    public List<Funcionario> buscarTodos() {
        return funcionarioRepository.findAll();
    }

    public Funcionario criarNovoFuncionario(FuncionarioDTO objDto){
        objDto.setId(null);
        objDto.setSenha(encoder.encode(objDto.getSenha()));
        validaPorCpfEEmail(objDto);
        Funcionario newObj = new Funcionario(objDto);
        return funcionarioRepository.save(newObj);
    }

    private void validaPorCpfEEmail(FuncionarioDTO objDTO) {
        Optional<Pessoa> obj = pessoaRepository.findByCpf(objDTO.getCpf());
        if (obj.isPresent() && obj.get().getId() != objDTO.getId()) {
            throw new DataIntegrityViolationException("CPF já cadastrado no sistema!");
        }

        obj = pessoaRepository.findByEmail(objDTO.getEmail());
        if (obj.isPresent() && obj.get().getId() != objDTO.getId()) {
            throw new DataIntegrityViolationException("E-mail já cadastrado no sistema!");
        }
    }

    public void delete(Integer id) {
        Funcionario obj = buscarPeloId(id);

        if (obj.getAgendamentos().size() > 0) {
            throw new DataIntegrityViolationException("Funcionario possui ordens de serviço e não pode ser deletado!");
        }

        funcionarioRepository.deleteById(id);
    }

    public Funcionario update(Integer id, FuncionarioDTO objDTO) {
        objDTO.setId(id);
        Funcionario oldObj = buscarPeloId(id);

        if(!objDTO.getSenha().equals(oldObj.getSenha()))
            objDTO.setSenha(encoder.encode(objDTO.getSenha()));

        validaPorCpfEEmail(objDTO);
        oldObj = new Funcionario(objDTO);
        return funcionarioRepository.save(oldObj);
    }


}

