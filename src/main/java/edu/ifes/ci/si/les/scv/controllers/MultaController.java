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

import edu.ifes.ci.si.les.scv.model.Multa;
import edu.ifes.ci.si.les.scv.services.MultaService;
import edu.ifes.ci.si.les.scv.services.exceptions.ConstraintException;

@RestController
@RequestMapping(value = "/multas")
public class MultaController {

    @Autowired
    private MultaService service;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Collection<Multa>> findAll() {
        Collection<Multa> collection = service.findAll();
        return ResponseEntity.ok().body(collection);
    }

    @RequestMapping(value = "/{emprestimoId}/{fitaId}", method = RequestMethod.GET)
    public ResponseEntity<Multa> find(@PathVariable Integer emprestimoId, @PathVariable Integer fitaId) {
        Multa obj = service.findById(emprestimoId, fitaId);
        return ResponseEntity.ok().body(obj);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Multa> insert(@Valid @RequestBody Multa obj, BindingResult br) {
        if (br.hasErrors())
        	throw new ConstraintException(br.getAllErrors().get(0).getDefaultMessage());
        obj = service.insert(obj);
        return ResponseEntity.ok().body(obj);
    }
    
    @RequestMapping(value = "/{emprestimoId}/{fitaId}", method = RequestMethod.PUT)
    public ResponseEntity<Multa> update(@Valid @RequestBody Multa obj, BindingResult br) {
        if (br.hasErrors())
        	throw new ConstraintException(br.getAllErrors().get(0).getDefaultMessage());
        obj = service.update(obj);
        return ResponseEntity.ok().body(obj);
    }

    @RequestMapping(value = "/{emprestimoId}/{fitaId}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable Integer emprestimoId, @PathVariable Integer fitaId) {
        service.delete(emprestimoId, fitaId);
        return ResponseEntity.noContent().build();
    }

}
