package edu.ifes.ci.si.les.scv.services;

import java.util.Collection;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import edu.ifes.ci.si.les.scv.model.Artista;
import edu.ifes.ci.si.les.scv.repositories.ArtistaRepository;
import edu.ifes.ci.si.les.scv.services.exceptions.DataIntegrityException;
import edu.ifes.ci.si.les.scv.services.exceptions.ObjectNotFoundException;

@Service
public class ArtistaService {

    @Autowired
    private ArtistaRepository repository;

    public Artista findById(final Integer id) {
        try {
        	Artista obj = repository.findById(id).get();
        	return obj;
        } catch (NoSuchElementException e) {
        	throw new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Artista.class.getName());
        }
    }    

    public Collection<Artista> findAll() {
        return repository.findAll();
    }

    public Artista insert(final Artista obj) {
    	obj.setId(null);
        try {
        	return repository.save(obj);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Campo(s) obrigatório(s) do Artista não foi(foram) preenchido(s)");
        }
    }

    public Artista update(final Artista obj) {
    	findById(obj.getId());
        try {
        	return repository.save(obj);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Campo(s) obrigatório(s) do Artista não foi(foram) preenchido(s)");
        }
    }

    public void delete(final Integer id) {
        findById(id);
        try {
            repository.deleteById(id);
        } catch (final DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não é possível excluir uma Artista com participações em Filmes!");
        }
    }

    public Collection<Artista> findAllByOrderByNome() {
        return repository.findAllByOrderByNome();
    }

    public Collection<Artista> findByNome(final String nome) {
        return repository.findByNome(nome);
    }
}
