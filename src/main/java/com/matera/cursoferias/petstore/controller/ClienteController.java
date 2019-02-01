package com.matera.cursoferias.petstore.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.matera.cursoferias.petstore.dto.ClienteRequestDTO;
import com.matera.cursoferias.petstore.dto.ClienteResponseDTO;
import com.matera.cursoferias.petstore.dto.ErroResponseDTO;
import com.matera.cursoferias.petstore.dto.EspecieRequestDTO;
import com.matera.cursoferias.petstore.dto.EspecieResponseDTO;
import com.matera.cursoferias.petstore.dto.PetResponseDTO;
import com.matera.cursoferias.petstore.exception.RegistroNaoEncontradoException;
import com.matera.cursoferias.petstore.service.ClienteService;
import com.matera.cursoferias.petstore.service.PetService;
import com.matera.cursoferias.petstore.service.impl.ClienteServiceImpl;

@RestController
@RequestMapping(value = "/api/v1/clientes")
public class ClienteController extends ControllerBase {

	private final ClienteService clienteService;
	private final PetService petService;
	
	public ClienteController(ClienteService clienteService, PetService petService) {
		this.clienteService = clienteService;
		this.petService = petService;
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	private ResponseEntity<ClienteResponseDTO> findById(@PathVariable("id") Long id){
		ClienteResponseDTO cliente = clienteService.findById(id);
		
		return ResponseEntity.status(HttpStatus.OK)
							 .body(cliente);	
	}
	
	@RequestMapping(method = RequestMethod.GET)
	private ResponseEntity<List<ClienteResponseDTO>> findAll(){
		List<ClienteResponseDTO> clientes = clienteService.findAll();
		
		return ResponseEntity.status(HttpStatus.OK)
							 .body(clientes);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> save(@Valid @RequestBody ClienteRequestDTO request){
		ClienteResponseDTO clienteResponseDTO = clienteService.save(null, request);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
											 .path("/{id}")
											 .buildAndExpand(clienteResponseDTO.getId())
											 .toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@PathVariable("id") Long id, @Valid @RequestBody ClienteRequestDTO request){
		clienteService.save(id, request);
		
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value = "/{id}/pets")
	public ResponseEntity<List<PetResponseDTO>> findPets(@PathVariable("id") Long id){ 
		List<PetResponseDTO> petsDto = petService.findByCliente_Id(id);
		
		return ResponseEntity.status(HttpStatus.OK)
							 .body(petsDto);
	}
		
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable("id") Long id){
		clienteService.deleteById(id);
		
		return ResponseEntity.noContent().build();
	}
	
}
