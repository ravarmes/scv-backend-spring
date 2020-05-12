package edu.ifes.ci.si.les.scv.services;

import java.util.Collection;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import edu.ifes.ci.si.les.scv.model.Cliente;
import edu.ifes.ci.si.les.scv.model.Reserva;
import edu.ifes.ci.si.les.scv.repositories.ClienteRepository;
import edu.ifes.ci.si.les.scv.repositories.ReservaRepository;
import edu.ifes.ci.si.les.scv.services.exceptions.BusinessRuleException;
import edu.ifes.ci.si.les.scv.services.exceptions.DataIntegrityException;
import edu.ifes.ci.si.les.scv.services.exceptions.ObjectNotFoundException;

@Service
public class ReservaService {

    @Autowired
    private ReservaRepository repository;
    @Autowired
	private ClienteRepository clienteRepository;

    public Reserva findById(Integer id) {
    	try {
        	Reserva obj = repository.findById(id).get();
        	return obj;
        } catch (NoSuchElementException e) {
        	throw new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Reserva.class.getName());
        }
    }

    public Collection<Reserva> findAll() {
        return repository.findAll();
    }
    
    public Reserva findByFitaAndStatus(Integer idFita, Integer status) {
        return repository.findByFitaAndStatus(idFita, status);
    }

    public Reserva insert(Reserva obj) {
        try {
        	if(verificarRegrasDeNegocio(obj)) {
	        	obj.setId(null);
	        	return repository.save(obj);
        	}
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Campo(s) obrigatório(s) da Reserva não foi(foram) preenchido(s): Cliente ou Fita");
        }
        return null;
    }

    public Reserva update(Reserva obj) {
        findById(obj.getId());
        try {
        	return repository.save(obj);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Campo(s) obrigatório(s) da Reserva não foi(foram) preenchido(s): Cliente ou Fita");
        }
    }

    public void delete(Integer id) {
        findById(id);
        try {
            repository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não é possível excluir esta Reserva!");
        }
    }

    public Collection<Reserva> findByClienteAndPeriodo(Integer idCliente, String inicio, String termino) {
        return repository.findByClienteAndPeriodo(idCliente, inicio, termino);
    }

    public Collection<?> findQuantidadesReservasOfClientesByPeriodo(String inicio, String termino){
        return repository.findQuantidadesReservasOfClientesByPeriodo(inicio, termino);
    }
    
    // Implementando as regras de negócio relacionadas ao processo de negócio Reserva
 	// Regra de Negócio 1: Cliente não pode ter multas não pagas
 	public boolean verificarRegrasDeNegocio(Reserva obj) {
 		
 		// Regra de Negócio 1: Cliente não pode ter multas não pagas
 		Collection<Cliente> devedores = clienteRepository.findDevedores();
 		boolean clienteDevedor = false;
 		for (Cliente devedor : devedores) {
 			if (devedor.getId() == obj.getCliente().getId()) {
 				clienteDevedor = true;
 			}
 		}
 		if (clienteDevedor) {
 			throw new BusinessRuleException("Este cliente deve multas anteriores!");
 		}
 		return true;
 	}

}
