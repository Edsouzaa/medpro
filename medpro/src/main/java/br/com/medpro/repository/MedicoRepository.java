package br.com.medpro.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.medpro.model.entity.Medico;

public interface MedicoRepository extends JpaRepository<Medico, Long>{
    
}
