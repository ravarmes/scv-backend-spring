package edu.ifes.ci.si.les.scv.repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import edu.ifes.ci.si.les.scv.model.Filme;
import edu.ifes.ci.si.les.scv.model.Fita;

@Repository
public interface FitaRepository extends JpaRepository<Fita, Integer> {

    @Transactional(readOnly = true)
    public Collection<Fita> findByFilme(Filme filme);
    
    @Transactional(readOnly = true)
    public Collection<Fita> findByDanificadaAndDisponivel(Boolean danificada, Boolean disponivel);
    
    @Transactional(readOnly = true)
    public Fita findByIdAndDisponivel(Integer id, Boolean disponivel);
    
    @Transactional(readOnly = false) // Indica que tratasse de uma transação que altera os dados do banco
    @Modifying // Indique que será executado um executeUpdate em vez de executeQuery
    @Query(value = "update FITA set DISPONIVEL = TRUE where exists (select * from ITEM_DE_EMPRESTIMO where ITEM_DE_EMPRESTIMO.FITA_ID = FITA.ID and ITEM_DE_EMPRESTIMO.EMPRESTIMO_ID = ?1)", nativeQuery = true)
    public void updateDisponivelByEmprestimo(Integer idEmprestimo);

}
