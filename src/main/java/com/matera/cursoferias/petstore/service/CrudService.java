package com.matera.cursoferias.petstore.service;

import java.util.List;

import com.matera.cursoferias.petstore.entity.EntidadeBase;

public interface CrudService<RequestDTO, ResponseDTO, Entidade extends EntidadeBase, ID> {

	ResponseDTO save(ID id, RequestDTO requestDto);
	
	List<ResponseDTO> findAll();

	ResponseDTO findById(ID id);
	
	Entidade findEntidadeById(ID id);
	
	void deleteById(ID id);

	Entidade converteRequestDTOParaEntidade(ID id, RequestDTO requestDTO);
	
	ResponseDTO converteEntidadeParaResponseDTO(Entidade entidade);
	
}
