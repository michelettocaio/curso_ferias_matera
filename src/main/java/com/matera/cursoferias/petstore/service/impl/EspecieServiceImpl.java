package com.matera.cursoferias.petstore.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.matera.cursoferias.petstore.business.EspecieBusiness;
import com.matera.cursoferias.petstore.dto.EspecieRequestDTO;
import com.matera.cursoferias.petstore.dto.EspecieResponseDTO;
import com.matera.cursoferias.petstore.entity.Especie;
import com.matera.cursoferias.petstore.service.EspecieService;

@Service
public class EspecieServiceImpl implements EspecieService{

	private final EspecieBusiness especieBusiness;
	
	public EspecieServiceImpl(EspecieBusiness especieBusiness) {
		this.especieBusiness = especieBusiness;
	}

	@Override
	public List<EspecieResponseDTO> findAll() {
		List<Especie> especies = especieBusiness.findAll();
		List<EspecieResponseDTO> especiesDto = new ArrayList<>();
		
		especies.forEach(especie -> especiesDto.add(converteEntidadeParaResponseDTO(especie)));
		
		return especiesDto;
	}

	@Override
	public EspecieResponseDTO findById(Long id) {
		return converteEntidadeParaResponseDTO(findEntidadeById(id));
	}

	@Override 
	public EspecieResponseDTO converteEntidadeParaResponseDTO(Especie entidade) {
		EspecieResponseDTO especieDto = new EspecieResponseDTO();

		especieDto.setDescricao(entidade.getDescricao());
		especieDto.setId(entidade.getId());
		
		return especieDto;
	}

	@Override
	public EspecieResponseDTO save(Long id, EspecieRequestDTO requestDto) {
		Especie especie = converteRequestDTOParaEntidade(id, requestDto);
			
		especie = especieBusiness.save(especie);
		
		return converteEntidadeParaResponseDTO(especie);
	}

	@Override
	public void deleteById(Long id) {
		especieBusiness.deleteById(id);
	}

	@Override
	public Especie converteRequestDTOParaEntidade(Long id, EspecieRequestDTO requestDTO) {
		
		Especie especie = id == null ? new Especie() : findEntidadeById(id);
		
		especie.setDescricao(requestDTO.getDescricao());
		
		return especie;
		
	}

	@Override
	public Especie findEntidadeById(Long id) {
		return especieBusiness.findById(id); 
	}

}
