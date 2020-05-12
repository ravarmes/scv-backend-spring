package edu.ifes.ci.si.les.scv.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.ifes.ci.si.les.scv.model.Multa;
import edu.ifes.ci.si.les.scv.model.MultaPK;

@Repository
public interface MultaRepository extends JpaRepository<Multa, MultaPK> {
	
}
