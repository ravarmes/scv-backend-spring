package edu.ifes.ci.si.les.scv.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.ifes.ci.si.les.scv.model.Gerente;

@Repository
public interface GerenteRepository extends JpaRepository<Gerente, Integer> {

}
