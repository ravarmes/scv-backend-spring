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

import edu.ifes.ci.si.les.scv.model.Artista;
import edu.ifes.ci.si.les.scv.services.ArtistaService;
import edu.ifes.ci.si.les.scv.services.exceptions.ConstraintException;

@RestController
@RequestMapping(value = "/artistas")
public class ArtistaController {

    @Autowired
    private ArtistaService service;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Collection<Artista>> findAll() {
        Collection<Artista> collection = service.findAll();
        return ResponseEntity.ok().body(collection);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Artista> find(@PathVariable Integer id) {
        Artista obj = service.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    
     /* Este método insere um Artista esperando receber dois parâmetros no Request:
     * artista: objeto Artista no formato de String JSON
     * artistaImagem: File
     */
    @RequestMapping(value = "/insertfile", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<Artista> insertfile(@RequestParam("artista") String artista, @RequestParam("artistaImagem") MultipartFile artistaImagem) {
		Artista obj;
		try {
			obj = new ObjectMapper().readValue(artista, Artista.class);
			obj.setImagem(artistaImagem.getBytes());
			obj = service.insert(obj);
			return ResponseEntity.ok().body(obj);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
    
    /* Este método insere um Artista esperando receber dois parâmetros no Request:
     * artista: objeto Artista no formato de String JSON
     * artistaImagem: File
     */
    @RequestMapping(value = "/updatefile/{id}", method = RequestMethod.PUT, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<Artista> updatefile(String artista, @RequestParam("artistaImagem") MultipartFile artistaImagem) {
		Artista obj;
		try {
			obj = new ObjectMapper().readValue(artista, Artista.class);
			obj.setImagem(artistaImagem.getBytes());
			obj = service.update(obj);
			return ResponseEntity.ok().body(obj);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
    /* Este método altera um Artista esperando receber dois parâmetros no Request:
     * artista: objeto Artista no formato de String JSON
     * artistaImagem: File
     */
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Artista> insert(@Valid @RequestBody Artista obj, BindingResult br) {
        if (br.hasErrors())
        	throw new ConstraintException(br.getAllErrors().get(0).getDefaultMessage());
        obj = service.insert(obj);
        return ResponseEntity.ok().body(obj);
    }
    
    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public ResponseEntity<Artista> update(@Valid @RequestBody Artista obj, BindingResult br) {
        if (br.hasErrors())
        	throw new ConstraintException(br.getAllErrors().get(0).getDefaultMessage());
        obj = service.update(obj);
        return ResponseEntity.ok().body(obj);
    }
    

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<Artista> update(String artista, @RequestParam("artistaImagem") MultipartFile artistaImagem) {
		Artista obj;
		try {
			obj = new ObjectMapper().readValue(artista, Artista.class);
			obj.setImagem(artistaImagem.getBytes());
			obj = service.update(obj);
			return ResponseEntity.ok().body(obj);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "/orderByNome", method = RequestMethod.GET)
    public ResponseEntity<Collection<Artista>> findAllByOrderByNome() {
        Collection<Artista> collection = service.findAllByOrderByNome();
        return ResponseEntity.ok().body(collection);
    }

    @RequestMapping(value = "/byNome/{nome}", method = RequestMethod.GET)
    public ResponseEntity<Collection<Artista>> findByNome(@PathVariable String nome) {
        Collection<Artista> collection = service.findByNome(nome);
        return ResponseEntity.ok().body(collection);
    }

}
