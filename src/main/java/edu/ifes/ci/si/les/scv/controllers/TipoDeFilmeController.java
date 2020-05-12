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

import edu.ifes.ci.si.les.scv.model.TipoDeFilme;
import edu.ifes.ci.si.les.scv.services.TipoDeFilmeService;
import edu.ifes.ci.si.les.scv.services.exceptions.ConstraintException;

@RestController
@RequestMapping(value = "/tiposdefilme")
public class TipoDeFilmeController {

    @Autowired
    private TipoDeFilmeService service;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Collection<TipoDeFilme>> findAll() {
        Collection<TipoDeFilme> collection = service.findAll();
        return ResponseEntity.ok().body(collection);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<TipoDeFilme> find(@PathVariable Integer id) {
        TipoDeFilme obj = service.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<TipoDeFilme> insert(@Valid @RequestBody TipoDeFilme obj, BindingResult br) {
        if (br.hasErrors())
        	throw new ConstraintException(br.getAllErrors().get(0).getDefaultMessage());
        obj = service.insert(obj);
        return ResponseEntity.ok().body(obj);
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<TipoDeFilme> update(@Valid @RequestBody TipoDeFilme obj, BindingResult br) {
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

    @RequestMapping(value = "/maioresprecos/{preco}", method = RequestMethod.GET)
    public ResponseEntity<Collection<TipoDeFilme>> findMaioresPrecos(@PathVariable Double preco) {
        Collection<TipoDeFilme> collection = service.findMaioresPrecos(preco);
        return ResponseEntity.ok().body(collection);
    }

}
