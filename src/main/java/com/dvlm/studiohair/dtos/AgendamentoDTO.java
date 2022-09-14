package com.dvlm.studiohair.dtos;

import com.dvlm.studiohair.domain.Agendamento;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;

public class AgendamentoDTO implements Serializable {

    private Integer id;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime dataAgendamento = LocalDateTime.now();
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime dataExServico;
    @NotNull(message = "O campo OBSERVAÇÕES é requerido")
    private String observacoes;
    @NotNull(message = "O campo STATUS é requerido")
    private Integer status;
    @NotNull(message = "O campo FUNCIONARIO é requerido")
    private Integer funcionario;
    @NotNull(message = "O campo CLIENTE é requerido")
    private Integer cliente;
    private Integer servico;
    private String nomeFuncionario;
    private String nomeCliente;

    public AgendamentoDTO() {
        super();
    }

    public AgendamentoDTO(Agendamento obj) {
        this.id = obj.getId();
        this.dataAgendamento = obj.getDataAgendamento();
        this.dataExServico = obj.getDataExServico();
        this.observacoes = obj.getObservacoes();
        this.status = obj.getStatus().getCod();
        this.funcionario = obj.getFuncionario().getId();
        this.cliente = obj.getCliente().getId();
        this.servico = obj.getServico().getId();
        this.nomeFuncionario = obj.getFuncionario().getNome();
        this.nomeCliente = obj.getCliente().getNome();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDateTime getDataAgendamento() {
        return dataAgendamento;
    }

    public void setDataAgendamento(LocalDateTime dataAgendamento) {
        this.dataAgendamento = dataAgendamento;
    }

    public LocalDateTime getDataExServico() {
        return dataExServico;
    }

    public void setDataExServico(LocalDateTime dataExServico) {
        this.dataExServico = dataExServico;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Integer funcionario) {
        this.funcionario = funcionario;
    }

    public Integer getCliente() {
        return cliente;
    }

    public void setCliente(Integer cliente) {
        this.cliente = cliente;
    }

    public Integer getServico() {
        return servico;
    }

    public void setServico(Integer servico) {
        this.servico = servico;
    }

    public String getNomeFuncionario() {
        return nomeFuncionario;
    }

    public void setNomeFuncionario(String nomeFuncionario) {
        this.nomeFuncionario = nomeFuncionario;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }
}
