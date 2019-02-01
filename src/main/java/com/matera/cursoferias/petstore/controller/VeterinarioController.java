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

import com.matera.cursoferias.petstore.dto.ServicoResponseDTO;
import com.matera.cursoferias.petstore.dto.VeterinarioRequestDto;
import com.matera.cursoferias.petstore.dto.VeterinarioResponseDto;
import com.matera.cursoferias.petstore.service.ServicoService;
import com.matera.cursoferias.petstore.service.VeterinarioService;

@RestController
@RequestMapping(value = "/api/v1/veterinarios")
public class VeterinarioController extends ControllerBase {

	private final VeterinarioService veterinarioService;
	private final ServicoService servicoService;

	public VeterinarioController(VeterinarioService veterinarioService, ServicoService servicoService) {
		this.veterinarioService = veterinarioService;
		this.servicoService = servicoService;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	private ResponseEntity<VeterinarioResponseDto> findById(@PathVariable("id") Long id) {
		VeterinarioResponseDto vet = veterinarioService.findById(id);

		return ResponseEntity.status(HttpStatus.OK).body(vet);
	}

	@RequestMapping(value = "", method = RequestMethod.GET)
	private ResponseEntity<List<VeterinarioResponseDto>> findAll() {
		List<VeterinarioResponseDto> vets = veterinarioService.findAll();

		return ResponseEntity.status(HttpStatus.OK).body(vets);
	}

	@RequestMapping(method = RequestMethod.POST)
	private ResponseEntity<Void> save(@Valid @RequestBody VeterinarioRequestDto requestDto) {
		VeterinarioResponseDto responseDto = veterinarioService.save(null, requestDto);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
											 .path("/{id}")
											 .buildAndExpand(responseDto.getId())
											 .toUri();

		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@PathVariable("id") Long id, @Valid @RequestBody VeterinarioRequestDto request){
		veterinarioService.save(id, request);
		
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable("id") Long id){
		veterinarioService.deleteById(id);
		
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value = "/{id}/servicos")
	public ResponseEntity<List<ServicoResponseDTO>> findServicosByVet(@PathVariable("id") Long id){
		List<ServicoResponseDTO> servicos = servicoService.findByVeterinario_Id(id);
		return ResponseEntity.status(HttpStatus.OK)
							 .body(servicos);
	}

}
