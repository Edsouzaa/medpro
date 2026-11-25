package br.com.medpro.model.dto;

import br.com.medpro.enums.Especialidade;

public record DadosDetalhamentoMedico(Long id, String nome, String email, String telefone, String crm, Especialidade especialidade, Endereco endereco) {
    
}
