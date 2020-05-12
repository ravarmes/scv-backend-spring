package edu.ifes.ci.si.les.scv.repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import edu.ifes.ci.si.les.scv.model.Filme;
import edu.ifes.ci.si.les.scv.model.TipoDeFilme;

@Repository
public interface FilmeRepository extends JpaRepository<Filme, Integer> {

    @Transactional(readOnly = true)
    public Collection<Filme> findByTipoDeFilme(TipoDeFilme tipoDeFilme);
    	
}
	