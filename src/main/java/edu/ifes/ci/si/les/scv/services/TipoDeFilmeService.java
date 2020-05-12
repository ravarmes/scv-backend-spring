package edu.ifes.ci.si.les.scv.services;

import java.util.Collection;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import edu.ifes.ci.si.les.scv.model.TipoDeFilme;
import edu.ifes.ci.si.les.scv.repositories.TipoDeFilmeRepository;
import edu.ifes.ci.si.les.scv.services.exceptions.DataIntegrityException;
import edu.ifes.ci.si.les.scv.services.exceptions.ObjectNotFoundException;

@Service
public class TipoDeFilmeService {

    @Autowired
    private TipoDeFilmeRepository repository;

    public TipoDeFilme findById(Integer id) {
    	try {
        	TipoDeFilme obj = repository.findById(id).get();
        	return obj;
        } catch (NoSuchElementException e) {
        	throw new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + TipoDeFilme.class.getName());
        }
    }

    public Collection<TipoDeFilme> findAll() {
        return repository.findAll();
    }

    public TipoDeFilme insert(TipoDeFilme obj) {
        obj.setId(null);
        try {
        	return repository.save(obj);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Campo(s) obrigatório(s) do Tipo de Filme não foi(foram) preenchido(s)");
        }
    }

    public TipoDeFilme update(TipoDeFilme obj) {
        findById(obj.getId());
        try {
        	return repository.save(obj);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Campo(s) obrigatório(s) do Tipo de Filme não foi(foram) preenchido(s)");
        }
    }

    public void delete(Integer id) {
        findById(id);
        try {
            repository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não é possível excluir um Tipo de Filme com Filmes vinculados a Empréstimos!");
        }
    }

    public Collection<TipoDeFilme> findMaioresPrecos(Double preco) {
        return repository.findMaioresPrecos(preco);
    }

}
