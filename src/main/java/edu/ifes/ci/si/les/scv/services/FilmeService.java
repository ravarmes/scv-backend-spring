package edu.ifes.ci.si.les.scv.services;

import java.util.Collection;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.ifes.ci.si.les.scv.model.Filme;
import edu.ifes.ci.si.les.scv.model.Participacao;
import edu.ifes.ci.si.les.scv.model.TipoDeFilme;
import edu.ifes.ci.si.les.scv.repositories.FilmeRepository;
import edu.ifes.ci.si.les.scv.services.exceptions.BusinessRuleException;
import edu.ifes.ci.si.les.scv.services.exceptions.DataIntegrityException;
import edu.ifes.ci.si.les.scv.services.exceptions.ObjectNotFoundException;

@Service
public class FilmeService {

    @Autowired
    private FilmeRepository repository;

    public Filme findById(Integer id) {
    	try {
        	Filme obj = repository.findById(id).get();
        	return obj;
        } catch (NoSuchElementException e) {
        	throw new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Filme.class.getName());
        }
    }

    @Transactional
    public Collection<Filme> findAll() {
        return repository.findAll();
    }

    public Filme insert(Filme obj) {
        try {
        	if(verificarRegrasDeNegocio(obj)) {
		    	obj.setId(null);
		        for (Participacao item : obj.getParticipacoes()) {
		            item.setFilme(obj);
		        }
		        return repository.save(obj);
	        }
        } catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Campo(s) obrigatório(s) do Filme não foi(foram) preenchido(s): Tipo de Filme, Diretor ou Participação");
		}
        return null;
    }

    public Filme update(Filme obj) {
        try {
        	findById(obj.getId());
	        for (Participacao item : obj.getParticipacoes()) {
	            item.setFilme(obj);
	        }
	        return repository.save(obj);
        } catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Campo(s) obrigatório(s) do Filme não foi(foram) preenchido(s): Tipo de Filme, Diretor ou Participação");
		}
    }

    public void delete(Integer id) {
        findById(id);
        try {
            repository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não é possível excluir um Filme que possui Fitas associadas a Empréstimos!");
        }
    }

    public Collection<Filme> findByTipoDeFilme(TipoDeFilme tipoDeFilme) {
        return repository.findByTipoDeFilme(tipoDeFilme);
    }
    
    // Implementando as regras de negócio relacionadas a manutenção de cadastro de filme
 	// Regra de Negócio 1: Filme deve possuir, no mínimo, um diretor
    // Regra de Negócio 2: Filme deve possuir, no mínimo, uma participação de artista
 	public boolean verificarRegrasDeNegocio(Filme obj) {
 		boolean minimoDiretores = false;
 		boolean minimoArtistas = false;
 		
 		// Regra de Negócio 1: Filme deve possuir, no mínimo, um diretor
 		if(obj.getDiretores() != null)
 			if(obj.getDiretores().size() >= 1)
 				minimoDiretores = true;
 			else 
 				throw new BusinessRuleException("O filme deve possuir, no mínimo, um diretor!");
 				
 		// Regra de Negócio 2: Filme deve possuir, no mínimo, uma participação de artista
 	 	if(obj.getParticipacoes() != null)
 	 		if(obj.getParticipacoes().size() >= 1)
 	 			minimoArtistas = true;
 	 		else
 	 			throw new BusinessRuleException("O filme deve possuir, no mínimo, uma participação de artista!");
 	 	
 		if(minimoDiretores && minimoArtistas)
 			return true;
 		else
 			return false;
 	}

}
