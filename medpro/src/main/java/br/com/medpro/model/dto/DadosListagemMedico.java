package br.com.medpro.model.dto;

import org.springframework.web.bind.annotation.GetMapping;

import br.com.medpro.enums.Especialidade;
import br.com.medpro.model.entity.Medico;

public record DadosListagemMedico(
    String nome,
    String email,
    String crm,
    Especialidade especialidade
) {

    public DadosListagemMedico(Medico medico){
        this(medico.getNome(), medico.getEmail(), medico.getCrm(), medico.getEspecialidade());
    }
    
}
