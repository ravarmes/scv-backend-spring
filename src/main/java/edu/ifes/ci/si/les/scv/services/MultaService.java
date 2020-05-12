package edu.ifes.ci.si.les.scv.services;

import java.util.Collection;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import edu.ifes.ci.si.les.scv.model.Emprestimo;
import edu.ifes.ci.si.les.scv.model.Fita;
import edu.ifes.ci.si.les.scv.model.ItemDeEmprestimo;
import edu.ifes.ci.si.les.scv.model.Multa;
import edu.ifes.ci.si.les.scv.model.MultaPK;
import edu.ifes.ci.si.les.scv.repositories.EmprestimoRepository;
import edu.ifes.ci.si.les.scv.repositories.FitaRepository;
import edu.ifes.ci.si.les.scv.repositories.ItemDeEmprestimoRepository;
import edu.ifes.ci.si.les.scv.repositories.MultaRepository;
import edu.ifes.ci.si.les.scv.services.exceptions.DataIntegrityException;
import edu.ifes.ci.si.les.scv.services.exceptions.ObjectNotFoundException;

@Service
public class MultaService {

    @Autowired
    private MultaRepository multaRepository;
    @Autowired
    private EmprestimoRepository emprestimoRepository;
    @Autowired
    private FitaRepository fitaRepository;
    @Autowired
    private ItemDeEmprestimoRepository itemDeEmprestimoRepository;

    public Multa findById(Integer idEmprestimo, Integer idFita) {
        Emprestimo emprestimo = new Emprestimo();
        Fita fita = new Fita();
        ItemDeEmprestimo itemDeEmprestimo = new ItemDeEmprestimo();
        MultaPK id = new MultaPK();

        emprestimo = emprestimoRepository.findById(idEmprestimo).get();
        fita = fitaRepository.findById(idFita).get();
        itemDeEmprestimo.getId().setEmprestimo(emprestimo);
        itemDeEmprestimo.getId().setFita(fita);
        itemDeEmprestimo = itemDeEmprestimoRepository.findById(itemDeEmprestimo.getId()).get();
        id.setItemDeEmprestimo(itemDeEmprestimo);

        try {
        	Multa obj = multaRepository.findById(id).get();
        	return obj;
        } catch (NoSuchElementException e) {
        	throw new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Multa.class.getName());
        }
    }

    public Multa findById(MultaPK id) {
        Multa obj = multaRepository.findById(id).get();
        if (obj == null) {
            throw new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Multa.class.getName());
        }
        return obj;
    }

    public Collection<Multa> findAll() {
        return multaRepository.findAll();
    }

    public Multa insert(Multa obj) {
        try {
        	return multaRepository.save(obj);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Campo(s) obrigatório(s) da Multa não foi(foram) preenchido(s): Item de Empréstimo (Empréstimo e/ou Fita)");
        }
    }

    public Multa update(Multa obj) {
        findById(obj.getId());
        try {
        	return multaRepository.save(obj);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Campo(s) obrigatório(s) da Multa não foi(foram) preenchido(s): Item de Empréstimo (Empréstimo e/ou Fita)");
        }
    }

    public void delete(Integer idEmprestimo, Integer idFita) {
        Multa multa = findById(idEmprestimo, idFita);
        try {
            multaRepository.deleteById(multa.getId());
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não é possível excluir esta Multa");
        }
    }

}
