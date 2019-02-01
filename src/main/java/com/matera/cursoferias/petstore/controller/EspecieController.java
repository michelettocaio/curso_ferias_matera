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

import com.matera.cursoferias.petstore.dto.EspecieRequestDTO;
import com.matera.cursoferias.petstore.dto.EspecieResponseDTO;
import com.matera.cursoferias.petstore.dto.PetResponseDTO;
import com.matera.cursoferias.petstore.service.EspecieService;
import com.matera.cursoferias.petstore.service.PetService;	

@RestController
@RequestMapping(value= "/api/v1/especies")
public class EspecieController extends ControllerBase {
	
	private final EspecieService especieService;
	private final PetService petService;

	public EspecieController(EspecieService especieService, PetService petService) {
		this.especieService = especieService;
		this.petService = petService;
	}

	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	public ResponseEntity<EspecieResponseDTO> findById(@PathVariable("id") Long id) {
		System.out.println("CHAMOU");
		EspecieResponseDTO especieResponseDto = especieService.findById(id);
		
		return ResponseEntity.status(HttpStatus.OK)
							 .body(especieResponseDto);		
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<EspecieResponseDTO>> findAll(){		
		return ResponseEntity.status(HttpStatus.OK)
							 .body(especieService.findAll());
	}
	
	@RequestMapping(value = "", method = RequestMethod.POST)
	public ResponseEntity<Void> save(@Valid @RequestBody EspecieRequestDTO request){
		EspecieResponseDTO especieResponseDTO = especieService.save(null, request);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
											 .path("/{id}")
											 .buildAndExpand(especieResponseDTO.getId())
											 .toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@PathVariable("id") Long id, @Valid @RequestBody EspecieRequestDTO request){
		especieService.save(id, request);
		
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value = "/{id}/pets")
	private ResponseEntity<List<PetResponseDTO>> findByEspecieId(@PathVariable("id") Long id){
		
		List<PetResponseDTO> pets = petService.findByEspecie_Id(id);
		
		return ResponseEntity.status(HttpStatus.OK)
							 .body(pets);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable("id") Long id){
		especieService.deleteById(id);
		
		return ResponseEntity.noContent().build();
	}
	
}
