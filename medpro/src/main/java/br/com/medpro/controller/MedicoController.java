package br.com.medpro.controller;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.autoconfigure.web.DataWebProperties.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponents;

import br.com.medpro.model.dto.DadosAtualizacaoMedico;
import br.com.medpro.model.dto.DadosCadastroMedico;
import br.com.medpro.model.dto.DadosDetalhamentoMedico;
import br.com.medpro.model.dto.DadosListagemMedico;
import br.com.medpro.model.entity.Medico;
import br.com.medpro.repository.MedicoRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.SuperBuilder;

import org.springframework.web.bind.annotation.RequestParam;



@RestController
@RequestMapping("/medicos")
public class MedicoController {

    @Autowired
    private MedicoRepository medicoRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<DadosDetalhamentoMedico> cadastrar(@RequestMapping @Valid DadosCadastroMedico dados, UriComponents uriBuilder){
        var medico = new Medico(dados);
        medicoRepository.save(medico);
    }
    
    @GetMapping
    public ResponseEntity<Page<DadosListagemMedico>> listar(@PageableDefault(size = 10, sort = "nome") Pageable paginacao){
        var page = medicoRepository.findAllByAtivoTrue(paginacao).map(DadosListagemMedico::new);
        return ResponseEntity.ok(page);
    }
    
    @SuppressWarnings("null")
    @PutMapping
    @Transactional 
    public ResponseEntity<DadosDetalhamentoMedico> atualizar(@RequestBody @Valid DadosAtualizacaoMedico dados){
        var medico = medicoRepository.getReferenceById(dados.id());
        medico.atualizarInformacoes(dados);
        return ResponseEntity.ok(new DadosDetalhamentoMedico(medico));
    }

    @SuppressWarnings("nulls")
    @DeleteMapping("/{id}")
    @Transactional  
    public ResponseEntity<Void> excluir(@PathVariable Long id){
        var medico = medicoRepository.getReferenceById(id);
        medico.excluir();
        return ResponseEntity.noContent().build();
    }
}