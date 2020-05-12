package edu.ifes.ci.si.les.scv.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.ifes.ci.si.les.scv.model.Bairro;

@Repository
public interface BairroRepository extends JpaRepository<Bairro, Integer> {

}
