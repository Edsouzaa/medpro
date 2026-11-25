package br.com.medpro.model.entity;

import org.springframework.data.annotation.Id;

import br.com.medpro.enums.Especialidade;
import br.com.medpro.model.dto.DadosAtualizacaoMedico;
import br.com.medpro.model.dto.DadosCadastroMedico;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity 
@Table(name = "medicos")
@Getter
@NoArgsConstructor 
@AllArgsConstructor 
@EqualsAndHashCode(of = "id") 
public class Medico {

    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String email;
    private String crm;
    private String telefone;

    @Enumerated(EnumType.STRING)
    private Especialidade especialidade;

    @Embedded
    private Endereco endereco;

    private Boolean ativo;

    public Medico(DadosCadastroMedico dados){
        this.nome = dados.nome();
        this.email = dados.email();
        this.crm = dados.crm();
        this.especialidade = dados.especialidade();
        this.telefone = dados.telefone();
        this.endereco = new Endereco(dados.endereco());
    }

    public void atualizarInformacoes(DadosAtualizacaoMedico dados){
        if (dados.nome() != null){
            if (dados.nome().isBlank()){
                throw new IllegalArgumentException("Nome não pode estar em branco");
            }
            this.nome = dados.nome();
        }
        if (dados.telefone() != null){
            if (dados.telefone().isBlank()){
                throw new IllegalArgumentException("Telefone não pode estar em branco.");
            }
            this.telefone = dados.telefone();
        }
        if (dados.endereco() != null){
            this.endereco.atualizarInformacoes(dados.endereco());
        }
    }

    
    public void excluir(){
        this.ativo = false;
    }
}
