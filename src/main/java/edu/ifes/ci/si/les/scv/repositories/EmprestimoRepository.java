package edu.ifes.ci.si.les.scv.repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import edu.ifes.ci.si.les.scv.model.Cliente;
import edu.ifes.ci.si.les.scv.model.Emprestimo;

@Repository
public interface EmprestimoRepository extends JpaRepository<Emprestimo, Integer> {
	
    @Transactional(readOnly = true)
    public Collection<Emprestimo> findByCliente(Cliente cliente);

    @Transactional(readOnly = true)
    @Query(value = "select * from EMPRESTIMO where EMPRESTIMO.CLIENTE_ID = ?1 and EMPRESTIMO.DATA > ?2 and EMPRESTIMO.DATA < ?3", nativeQuery = true)
    public Collection<Emprestimo> findByClienteAndPeriodo(Integer idCliente, String inicio, String termino);

    @Transactional(readOnly = true)
    @Query(value = "select CLIENTE.NOME as nome, sum(VALOR) as total, count(VALOR) as quantidade from EMPRESTIMO inner join CLIENTE on EMPRESTIMO.CLIENTE_ID = CLIENTE.ID where DATA >= ?1 and DATA <= ?2 group by EMPRESTIMO.CLIENTE_ID", nativeQuery = true)
    public Collection<?> findTotaisAndQuantidadesEmprestimosOfClientesByPeriodo(String inicio, String termino);

    @Transactional(readOnly = true)
    @Query(value = "select BAIRRO.NOME, count(EMPRESTIMO.ID) as QUANTIDADE from EMPRESTIMO INNER JOIN CLIENTE ON EMPRESTIMO.CLIENTE_ID = CLIENTE.ID INNER JOIN BAIRRO ON CLIENTE.BAIRRO_ID = BAIRRO.ID where DATA >= ?1 and DATA <= ?2 group by BAIRRO.NOME", nativeQuery = true)
    public Collection<?> findQuantidadesEmprestimosOfBairrosByPeriodo(String inicio, String termino);

    @Transactional(readOnly = true)
    @Query(value = "select FILME.TITULO as FILME, count(ITEM_DE_EMPRESTIMO.ENTREGA) as QUANTIDADE from ITEM_DE_EMPRESTIMO inner join FITA on ITEM_DE_EMPRESTIMO.FITA_ID = FITA.ID inner join FILME on FITA.FILME_ID = FILME.ID where ITEM_DE_EMPRESTIMO.ENTREGA > ?1 and ITEM_DE_EMPRESTIMO.ENTREGA < ?2 group by FILME.TITULO", nativeQuery = true)
    public Collection<?> findQuantidadesEmprestimosOfFilmesByPeriodo(String inicio, String termino);

    @Transactional(readOnly = true)
    @Query(value = "select count(EMPRESTIMO.ID), extract(year from data) as ano, extract(month from data) as mes from EMPRESTIMO group by ano, mes order by ano, mes", nativeQuery = true)
    public Collection<?> findTotaisAnoMes();

    @Transactional(readOnly = true)
    @Query(value = "select count(id), extract(year from data) as ano, extract(month from data) as mes from emprestimo group by ano, mes order by ano, mes", nativeQuery = true)
    public Collection<?> findTotaisAnoMesSQLNativo();

}
