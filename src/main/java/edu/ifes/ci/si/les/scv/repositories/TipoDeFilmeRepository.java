package edu.ifes.ci.si.les.scv.repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import edu.ifes.ci.si.les.scv.model.TipoDeFilme;

@Repository
public interface TipoDeFilmeRepository extends JpaRepository<TipoDeFilme, Integer> {

    @Transactional(readOnly = true)
    @Query(value = "SELECT * FROM TIPO_DE_FILME WHERE TIPO_DE_FILME.PRECO > ?1", nativeQuery = true)
    public Collection<TipoDeFilme> findMaioresPrecos(Double preco);

}
