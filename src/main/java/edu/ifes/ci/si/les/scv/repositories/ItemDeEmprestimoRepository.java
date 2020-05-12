package edu.ifes.ci.si.les.scv.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.ifes.ci.si.les.scv.model.ItemDeEmprestimo;
import edu.ifes.ci.si.les.scv.model.ItemDeEmprestimoPK;

@Repository
public interface ItemDeEmprestimoRepository extends JpaRepository<ItemDeEmprestimo, ItemDeEmprestimoPK> {
	
}
