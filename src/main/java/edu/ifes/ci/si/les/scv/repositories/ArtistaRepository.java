package edu.ifes.ci.si.les.scv.repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import edu.ifes.ci.si.les.scv.model.Artista;

@Repository
public interface ArtistaRepository extends JpaRepository<Artista, Integer> {

    @Transactional(readOnly = true)
    public Collection<Artista> findAllByOrderByNome();

    @Transactional(readOnly = true)
    public Collection<Artista> findByNome(String nome);

}
