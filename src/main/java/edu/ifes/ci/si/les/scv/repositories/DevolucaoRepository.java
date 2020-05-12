package edu.ifes.ci.si.les.scv.repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import edu.ifes.ci.si.les.scv.model.Devolucao;
import edu.ifes.ci.si.les.scv.model.DevolucaoPK;

@Repository
public interface DevolucaoRepository extends JpaRepository<Devolucao, DevolucaoPK> {
    
    @Transactional(readOnly = true)
    @Query(value = "SELECT distinct DEVOLUCAO.EMPRESTIMO_ID, DEVOLUCAO.FITA_ID, DEVOLUCAO.DATA FROM DEVOLUCAO join EMPRESTIMO on DEVOLUCAO.EMPRESTIMO_ID = EMPRESTIMO.ID join CLIENTE on EMPRESTIMO.CLIENTE_ID = ?1 where DEVOLUCAO.DATA > ?2 and DEVOLUCAO.DATA < ?3", nativeQuery = true)
    public Collection<Devolucao> findByClienteAndPeriodo(Integer idCliente, String inicio, String termino);
    
    @Transactional(readOnly = true)
    @Query(value = "select CLIENTE.NOME as nome, count(DEVOLUCAO.DATA) as quantidade from DEVOLUCAO inner join EMPRESTIMO on DEVOLUCAO.EMPRESTIMO_ID = EMPRESTIMO.ID inner join CLIENTE on EMPRESTIMO.CLIENTE_ID = CLIENTE.ID where DEVOLUCAO.DATA > ?1 and DEVOLUCAO.DATA < ?2 group by CLIENTE.NOME", nativeQuery = true)
    public Collection<?> findQuantidadeDevolucaoClienteByPeriodo(String inicio, String termino);

}
