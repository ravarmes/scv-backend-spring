package edu.ifes.ci.si.les.scv.controllers;

import java.io.IOException;
import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;

import edu.ifes.ci.si.les.scv.model.Filme;
import edu.ifes.ci.si.les.scv.model.TipoDeFilme;
import edu.ifes.ci.si.les.scv.services.FilmeService;
import edu.ifes.ci.si.les.scv.services.exceptions.ConstraintException;

@RestController
@RequestMapping(value = "/filmes")
public class FilmeController {

	@Autowired
	private FilmeService service;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<Collection<Filme>> findAll() {
		Collection<Filme> collection = service.findAll();
		return ResponseEntity.ok().body(collection);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Filme> find(@PathVariable Integer id) {
		Filme obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}

	/* Este método insere um Filme esperando receber dois parâmetros no Request:
     * artista: objeto Filme no formato de String JSON
     * artistaImagem: File
     */
	@RequestMapping(value = "/insertfile", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<Filme> insert(@RequestParam("filme") String filme, @RequestParam("filmeImagem") MultipartFile filmeImagem) {
		Filme obj;
		try {
			obj = new ObjectMapper().readValue(filme, Filme.class);
			obj.setImagem(filmeImagem.getBytes());
			obj = service.insert(obj);
			return ResponseEntity.ok().body(obj);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	

	/* Este método altera um Filme esperando receber dois parâmetros no Request:
     * artista: objeto Filme no formato de String JSON
     * artistaImagem: File
     */
	@RequestMapping(value = "/updatefile/{id}", method = RequestMethod.PUT, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<Filme> update(String filme, @RequestParam("filmeImagem") MultipartFile filmeImagem) {
		Filme obj;
		try {
			obj = new ObjectMapper().readValue(filme, Filme.class);
			obj.setImagem(filmeImagem.getBytes());
			obj = service.update(obj);
			return ResponseEntity.ok().body(obj);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Filme> insert(@Valid @RequestBody Filme obj, BindingResult br) {
		if (br.hasErrors())
        	throw new ConstraintException(br.getAllErrors().get(0).getDefaultMessage());
        obj = service.insert(obj);
        return ResponseEntity.ok().body(obj);
    }
    
    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public ResponseEntity<Filme> update(@Valid @RequestBody Filme obj, BindingResult br) {
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

	@RequestMapping(value = "/findByTipoDeFilme/{idTipoDeFilme}", method = RequestMethod.GET)
	public ResponseEntity<Collection<Filme>> findByTipoDeFilme(@PathVariable Integer idTipoDeFilme) {
		TipoDeFilme tipoDeFilme = new TipoDeFilme();
		tipoDeFilme.setId(idTipoDeFilme);
		Collection<Filme> collection = service.findByTipoDeFilme(tipoDeFilme);
		return ResponseEntity.ok().body(collection);
	}

	@RequestMapping(value = "/imagem/{id}", method = RequestMethod.GET)
	public ResponseEntity<byte[]> findImagem(@PathVariable Integer id) {
		Filme obj = service.findById(id);
		return ResponseEntity.ok().body(obj.getImagem());
	}

}
