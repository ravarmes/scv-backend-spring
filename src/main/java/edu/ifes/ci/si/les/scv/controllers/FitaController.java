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

import edu.ifes.ci.si.les.scv.model.Filme;
import edu.ifes.ci.si.les.scv.model.Fita;
import edu.ifes.ci.si.les.scv.services.FitaService;
import edu.ifes.ci.si.les.scv.services.exceptions.ConstraintException;

@RestController
@RequestMapping(value="/fitas")
public class FitaController {
	
	@Autowired
	private FitaService service;
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<Collection<Fita>> findAll() {
		Collection<Fita> collection = service.findAll();
		return ResponseEntity.ok().body(collection);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<Fita> find(@PathVariable Integer id) {
		Fita obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Fita> insert(@Valid @RequestBody Fita obj, BindingResult br) {
        if (br.hasErrors())
        	throw new ConstraintException(br.getAllErrors().get(0).getDefaultMessage());
        obj = service.insert(obj);
        return ResponseEntity.ok().body(obj);
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Fita> update(@Valid @RequestBody Fita obj, BindingResult br) {
        if (br.hasErrors())
        	throw new ConstraintException(br.getAllErrors().get(0).getDefaultMessage());
        obj = service.update(obj);
        return ResponseEntity.ok().body(obj);
    }
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value="/findByDanificadaAndDisponivel/{danificada}/{disponivel}", method=RequestMethod.GET)
	public ResponseEntity<Collection<Fita>> findByDanificadaAndDisponivel(@PathVariable Boolean danificada, @PathVariable Boolean disponivel) {
		Collection<Fita> collection = service.findByDanificadaAndDisponivel(danificada, disponivel);
		return ResponseEntity.ok().body(collection);
	}

	@RequestMapping(value="/findByFilme/{idFilme}", method=RequestMethod.GET)
	public ResponseEntity<Collection<Fita>> findByFilme(@PathVariable Integer idFilme) {
		Filme obj = new Filme();
		obj.setId(idFilme);
		Collection<Fita> collection = service.findByFilme(obj);
		return ResponseEntity.ok().body(collection);
	}
	
}
