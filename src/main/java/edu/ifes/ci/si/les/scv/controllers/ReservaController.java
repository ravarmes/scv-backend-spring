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

import edu.ifes.ci.si.les.scv.model.Reserva;
import edu.ifes.ci.si.les.scv.services.ReservaService;
import edu.ifes.ci.si.les.scv.services.exceptions.ConstraintException;

@RestController
@RequestMapping(value = "/reservas")
public class ReservaController {

    @Autowired
    private ReservaService service;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Collection<Reserva>> findAll() {
        Collection<Reserva> collection = service.findAll();
        return ResponseEntity.ok().body(collection);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Reserva> find(@PathVariable Integer id) {
        Reserva obj = service.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Reserva> insert(@Valid @RequestBody Reserva obj, BindingResult br) {
        if (br.hasErrors())
        	throw new ConstraintException(br.getAllErrors().get(0).getDefaultMessage());
        obj = service.insert(obj);
        return ResponseEntity.ok().body(obj);
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Reserva> update(@Valid @RequestBody Reserva obj, BindingResult br) {
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
    
    @RequestMapping(value = "/findByFitaAndStatus/{idFita}/{status}", method = RequestMethod.GET)
    public ResponseEntity<Reserva> findByFitaAndStatus(@PathVariable Integer idFita, @PathVariable Integer status) {
        Reserva obj = service.findByFitaAndStatus(idFita, status);
        return ResponseEntity.ok().body(obj);
    }

    @RequestMapping(value = "/findByClienteAndPeriodo/{idCliente}/{inicio}/{termino}", method = RequestMethod.GET)
    public ResponseEntity<Collection<Reserva>> findByClienteAndPeriodo(@PathVariable Integer idCliente, @PathVariable String inicio, @PathVariable String termino) {
        Collection<Reserva> collection = service.findByClienteAndPeriodo(idCliente, inicio, termino);
        return ResponseEntity.ok().body(collection);
    }
    
    @RequestMapping(value = "/findQuantidadesReservasOfClientesByPeriodo/{inicio}/{termino}", method = RequestMethod.GET)
    public ResponseEntity<Collection<?>> findQuantidadesReservasOfClientesByPeriodo(@PathVariable String inicio, @PathVariable String termino) {
        Collection<?> collection = service.findQuantidadesReservasOfClientesByPeriodo(inicio, termino);
        return ResponseEntity.ok().body(collection);
    }

}
