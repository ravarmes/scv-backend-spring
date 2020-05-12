package edu.ifes.ci.si.les.scv.services;

import java.util.Collection;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import edu.ifes.ci.si.les.scv.model.Filme;
import edu.ifes.ci.si.les.scv.model.Fita;
import edu.ifes.ci.si.les.scv.repositories.FitaRepository;
import edu.ifes.ci.si.les.scv.services.exceptions.DataIntegrityException;
import edu.ifes.ci.si.les.scv.services.exceptions.ObjectNotFoundException;

@Service
public class FitaService {

    @Autowired
    private FitaRepository repository;

    public Fita findById(Integer id) {
    	try {
        	Fita obj = repository.findById(id).get();
        	return obj;
        } catch (NoSuchElementException e) {
        	throw new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Fita.class.getName());
        }
    }

    public Collection<Fita> findAll() {
        return repository.findAll();
    }
    
    public Collection<Fita> findByDanificadaAndDisponivel(Boolean danificada, Boolean disponivel) {
        return repository.findByDanificadaAndDisponivel(danificada, disponivel);
    }

    public Fita insert(Fita obj) {
    	obj.setId(null);
        try {
        	return repository.save(obj);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Campo(s) obrigatório(s) da Fita não foi(foram) preenchido(s)");
        }
    }

    public Fita update(Fita obj) {
    	findById(obj.getId());
        try {
        	return repository.save(obj);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Campo(s) obrigatório(s) da Fita não foi(foram) preenchido(s)");
        }
    }

    public void delete(Integer id) {
        findById(id);
        try {
            repository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não é possível excluir uma Fita vinculada a Itens de Empréstimos!");
        }
    }

    public Collection<Fita> findByFilme(Filme obj) {
        return repository.findByFilme(obj);
    }

}
