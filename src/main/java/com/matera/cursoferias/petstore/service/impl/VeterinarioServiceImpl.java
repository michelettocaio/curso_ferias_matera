package com.matera.cursoferias.petstore.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.matera.cursoferias.petstore.business.VeterinarioBusiness;
import com.matera.cursoferias.petstore.dto.ServicoResponseDTO;
import com.matera.cursoferias.petstore.dto.VeterinarioRequestDto;
import com.matera.cursoferias.petstore.dto.VeterinarioResponseDto;
import com.matera.cursoferias.petstore.entity.Veterinario;
import com.matera.cursoferias.petstore.service.ServicoService;
import com.matera.cursoferias.petstore.service.VeterinarioService;

@Service
public class VeterinarioServiceImpl implements VeterinarioService{
	
	@Autowired
	private VeterinarioBusiness veterinarioBusiness;
	@Autowired
	private ServicoService servicoService;
	
	@Override
	public VeterinarioResponseDto save(Long id, VeterinarioRequestDto requestDto) {
		Veterinario vet = converteRequestDTOParaEntidade(null, requestDto);
		
		return converteEntidadeParaResponseDTO(veterinarioBusiness.save(vet));
	}

	@Override
	public List<VeterinarioResponseDto> findAll() {
		return converteListaEntidadeParaResponseDto(veterinarioBusiness.findAll());
	}
	
	private List<VeterinarioResponseDto> converteListaEntidadeParaResponseDto(List<Veterinario> veterinarios){
		List<VeterinarioResponseDto> dto = new ArrayList<>();
		veterinarios.forEach(vet -> dto.add(converteEntidadeParaResponseDTO(vet)));

		return dto;
	}

	@Override
	public VeterinarioResponseDto findById(Long id) {
		return converteEntidadeParaResponseDTO(veterinarioBusiness.findById(id));
	}

	@Override
	public Veterinario findEntidadeById(Long id) {
		return veterinarioBusiness.findById(id);
	}

	@Override
	public void deleteById(Long id) {
		veterinarioBusiness.deleteById(id);
	}

	@Override
	public Veterinario converteRequestDTOParaEntidade(Long id, VeterinarioRequestDto requestDTO) {
		Veterinario veterinario = id == null ? new Veterinario() : findEntidadeById(id);
		
		veterinario.setNome(requestDTO.getNome());
		
		return veterinario;
	}

	@Override
	public VeterinarioResponseDto converteEntidadeParaResponseDTO(Veterinario entidade) {
		VeterinarioResponseDto dto = new VeterinarioResponseDto();
		dto.setNome(entidade.getNome());
		dto.setId(entidade.getId());
//		dto.setServicos(servicoService.findByVeterinario_Id(entidade.getId()));

		return dto;
	}

}
