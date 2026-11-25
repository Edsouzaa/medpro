package br.com.medpro.repository;

import org.springframework.boot.data.autoconfigure.web.DataWebProperties.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.medpro.model.entity.Medico;

public interface MedicoRepository extends JpaRepository<Medico, Long>{
    
    Page<Medico> findAllByAtivoTrue(Pageable paginacao);
}
