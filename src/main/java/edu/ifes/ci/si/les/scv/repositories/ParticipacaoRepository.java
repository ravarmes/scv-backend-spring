package edu.ifes.ci.si.les.scv.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.ifes.ci.si.les.scv.model.Participacao;

@Repository
public interface ParticipacaoRepository extends JpaRepository<Participacao, Integer> {
	
}
