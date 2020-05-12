package edu.ifes.ci.si.les.scv.services;

import java.util.Collection;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import edu.ifes.ci.si.les.scv.model.Bairro;
import edu.ifes.ci.si.les.scv.repositories.BairroRepository;
import edu.ifes.ci.si.les.scv.services.exceptions.DataIntegrityException;
import edu.ifes.ci.si.les.scv.services.exceptions.ObjectNotFoundException;

@Service
public class BairroService {

    @Autowired
    private BairroRepository repository;

    public Bairro findById(Integer id) {
    	try {
        	Bairro obj = repository.findById(id).get();
        	return obj;
        } catch (NoSuchElementException e) {
        	throw new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Bairro.class.getName());
        }
    }

    public Collection<Bairro> findAll() {
        return repository.findAll();
    }

    public Bairro insert(Bairro obj) {
    	obj.setId(null);
        try {
        	return repository.save(obj);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Campo(s) obrigatório(s) do Bairro não foi(foram) preenchido(s): Cidade");
        }
    }

    public Bairro update(Bairro obj) {
    	findById(obj.getId());
        try {
        	return repository.save(obj);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Campo(s) obrigatório(s) do Bairro não foi(foram) preenchido(s): Cidade");
        }
    }

    public void delete(Integer id) {
        findById(id);
        try {
            repository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não é possível excluir um Bairro associado a uma Pessoa (gerente, funcionário ou cliente)!");
        }
    }

}
