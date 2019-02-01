package com.matera.cursoferias.petstore.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.matera.cursoferias.petstore.dto.PetRequestDTO;
import com.matera.cursoferias.petstore.dto.PetResponseDTO;
import com.matera.cursoferias.petstore.dto.ServicoResponseDTO;
import com.matera.cursoferias.petstore.service.PetService;
import com.matera.cursoferias.petstore.service.ServicoService;

@RestController
@RequestMapping(value = "/api/v1/pets")
public class PetController extends ControllerBase{
	
	private PetService petService;
	private ServicoService servicoService;
	
	public PetController(PetService petService, ServicoService servicoService) {
		this.petService = petService;
		this.servicoService = servicoService;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	private ResponseEntity<PetResponseDTO> findById(@PathVariable("id") Long id){
		PetResponseDTO pet = petService.findById(id);
		
		return ResponseEntity.status(HttpStatus.OK)
							 .body(pet);
	}
	
	@RequestMapping(method = RequestMethod.GET)
	private ResponseEntity<List<PetResponseDTO>> findAll(){
		List<PetResponseDTO> pets = petService.findAll();
		
		return ResponseEntity.status(HttpStatus.OK)
							 .body(pets);
	}
	
	@RequestMapping(value = "", method = RequestMethod.POST)
	public ResponseEntity<Void> save(@Valid @RequestBody PetRequestDTO request){
		PetResponseDTO petResponseDTO = petService.save(null, request);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
											 .path("/{id}")
											 .buildAndExpand(petResponseDTO.getId())
											 .toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@PathVariable("id") Long id, @Valid @RequestBody PetRequestDTO request){
		petService.save(id, request);
		
		return ResponseEntity.noContent().build();
	}
		
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable("id") Long id){
		petService.deleteById(id);
		
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value = "/{id}/servicos", method = RequestMethod.GET)
	public ResponseEntity<List<ServicoResponseDTO>> findServicos(@PathVariable("id") Long id){
		List<ServicoResponseDTO> servicos = servicoService.findByPet_Id(id);
		
		return ResponseEntity.status(HttpStatus.OK)
							 .body(servicos);
	}

}
