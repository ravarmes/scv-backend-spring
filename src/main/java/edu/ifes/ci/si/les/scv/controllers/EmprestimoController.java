package edu.ifes.ci.si.les.scv.controllers;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import edu.ifes.ci.si.les.scv.model.Cliente;
import edu.ifes.ci.si.les.scv.model.Emprestimo;
import edu.ifes.ci.si.les.scv.services.EmprestimoService;
import edu.ifes.ci.si.les.scv.services.exceptions.ConstraintException;

@RestController
@RequestMapping(value = "/emprestimos")
public class EmprestimoController {

    @Autowired
    private EmprestimoService service;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Collection<Emprestimo>> findAll() {
        Collection<Emprestimo> collection = service.findAll();
        return ResponseEntity.ok().body(collection);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Emprestimo> find(@PathVariable Integer id) {
        Emprestimo obj = service.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Emprestimo> insert(@Valid @RequestBody Emprestimo obj, BindingResult br) {
        if (br.hasErrors())
        	throw new ConstraintException(br.getAllErrors().get(0).getDefaultMessage());
        obj = service.insert(obj);
        return ResponseEntity.ok().body(obj);
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Emprestimo> update(@Valid @RequestBody Emprestimo obj, BindingResult br) {
        if (br.hasErrors())
        	throw new ConstraintException(br.getAllErrors().get(0).getDefaultMessage());
    	obj = service.update(obj);
        return ResponseEntity.ok().body(obj);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "/findTotaisAnoMes", method = RequestMethod.GET)
    public ResponseEntity<Collection<?>> findTotaisAnoMes() {
        Collection<?> collection = service.findTotaisAnoMes();
        return ResponseEntity.ok().body(collection);
    }

    @RequestMapping(value = "/findByCliente/{idCliente}", method = RequestMethod.GET)
    public ResponseEntity<Collection<Emprestimo>> findByCliente(@PathVariable Integer idCliente) {
        Cliente cliente = new Cliente();
        cliente.setId(idCliente);
        Collection<Emprestimo> collection = service.findByCliente(cliente);
        return ResponseEntity.ok().body(collection);
    }

    @RequestMapping(value = "/findByClienteAndPeriodo/{idCliente}/{inicio}/{termino}", method = RequestMethod.GET)
    public ResponseEntity<Collection<Emprestimo>> findByClienteAndPeriodo(@PathVariable Integer idCliente, @PathVariable String inicio, @PathVariable String termino) {
        Collection<Emprestimo> collection = service.findByClienteAndPeriodo(idCliente, inicio, termino);
        return ResponseEntity.ok().body(collection);
    }

    @RequestMapping(value = "/findTotaisAndQuantidadesEmprestimosOfClientesByPeriodo/{inicio}/{termino}", method = RequestMethod.GET)
    public ResponseEntity<Collection<?>> findTotaisAndQuantidadesEmprestimosOfClientesByPeriodo(@PathVariable String inicio, @PathVariable String termino) {
        Collection<?> collection = service.findTotaisAndQuantidadesEmprestimosOfClientesByPeriodo(inicio, termino);
        return ResponseEntity.ok().body(collection);
    }

    @RequestMapping(value = "/findQuantidadesEmprestimosOfBairrosByPeriodo/{inicio}/{termino}", method = RequestMethod.GET)
    public ResponseEntity<Collection<?>> findQuantidadesEmprestimosOfBairrosByPeriodo(@PathVariable String inicio, @PathVariable String termino) {
        Collection<?> collection = service.findQuantidadesEmprestimosOfBairrosByPeriodo(inicio, termino);
        return ResponseEntity.ok().body(collection);
    }

    @RequestMapping(value = "/findQuantidadesEmprestimosOfFilmesByPeriodo/{inicio}/{termino}", method = RequestMethod.GET)
    public ResponseEntity<Collection<?>> findQuantidadesEmprestimosOfFilmesByPeriodo(@PathVariable String inicio, @PathVariable String termino) {
        Collection<?> collection = service.findQuantidadesEmprestimosOfFilmesByPeriodo(inicio, termino);
        return ResponseEntity.ok().body(collection);
    }

}
