package com.matera.cursoferias.petstore.service.impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.matera.cursoferias.petstore.business.ServicoBusiness;
import com.matera.cursoferias.petstore.dto.ServicoRequestDTO;
import com.matera.cursoferias.petstore.dto.ServicoResponseDTO;
import com.matera.cursoferias.petstore.entity.Servico;
import com.matera.cursoferias.petstore.entity.TipoServico;
import com.matera.cursoferias.petstore.service.PetService;
import com.matera.cursoferias.petstore.service.ServicoService;
import com.matera.cursoferias.petstore.service.VeterinarioService;

@Service
public class ServicoServiceImpl implements ServicoService {

	@Autowired
	private ServicoBusiness servicoBusiness;
	@Autowired
	private PetService petService;
	@Autowired
	private VeterinarioService veterinarioService;


	@Override
	public List<ServicoResponseDTO> findAll() {
		List<Servico> servicos = servicoBusiness.findAll();

		return converteListaEntidadeParaListaResponseDto(servicos);
	}

	@Override
	public List<ServicoResponseDTO> converteListaEntidadeParaListaResponseDto(List<Servico> servicos) {
		List<ServicoResponseDTO> dto = new ArrayList<>();
		servicos.forEach(servico -> dto.add(converteEntidadeParaResponseDTO(servico)));

		return dto;
	}

	@Override
	public ServicoResponseDTO findById(Long id) {
		return converteEntidadeParaResponseDTO(findEntidadeById(id));
	}

	@Override
	public ServicoResponseDTO converteEntidadeParaResponseDTO(Servico entidade) {
		ServicoResponseDTO servico = new ServicoResponseDTO();
		servico.setId(entidade.getId());
		servico.setDataHora(entidade.getDataHora());
		servico.setTipoServico(entidade.getTipoServico().getDescricao());
		servico.setObservacao(entidade.getObservacao());
		servico.setValor(entidade.getValor());
		servico.setPet(petService.converteEntidadeParaResponseDTO(entidade.getPet()));
		servico.setVeterinario(veterinarioService.converteEntidadeParaResponseDTO(entidade.getVeterinario()));

		return servico;
	}

	@Override
	public ServicoResponseDTO save(Long id, ServicoRequestDTO requestDto) {		
		Servico servico = servicoBusiness.save(converteRequestDTOParaEntidade(id, requestDto));
				
		return converteEntidadeParaResponseDTO(servico);
	}

	@Override
	public void deleteById(Long id) {
		servicoBusiness.deleteById(id);
	}

	@Override
	public Servico converteRequestDTOParaEntidade(Long id, ServicoRequestDTO requestDTO) {
		Servico servico = id == null ? new Servico() : findEntidadeById(id);
		
		servico.setDataHora(LocalDateTime.now());
		servico.setObservacao(requestDTO.getObservacao());
		servico.setPet(petService.findEntidadeById(requestDTO.getIdPet()));
		servico.setTipoServico(requestDTO.getTipoServico());
		servico.setValor(requestDTO.getValor());
		servico.setVeterinario(veterinarioService.findEntidadeById(requestDTO.getIdVeterinario()));
		
		return servico;
	}

	@Override
	public Servico findEntidadeById(Long id) {
		return servicoBusiness.findById(id);
	}

	@Override
	public List<ServicoResponseDTO> findByPet_Id(Long id) {
		List<Servico> servicos = servicoBusiness.findByPet_Id(id);
				
		return converteListaEntidadeParaListaResponseDto(servicos); 
	}

	@Override
	public List<ServicoResponseDTO> findByDataHoraBetween(LocalDate dataInicial, LocalDate dataFinal) {
		List<Servico> servicos = servicoBusiness.findByDataHoraBetween(dataInicial.atTime(0, 0, 0), dataFinal.atTime(23, 59, 59));
	
		return converteListaEntidadeParaListaResponseDto(servicos);
	}

	@Override
	public List<ServicoResponseDTO> findByVeterinario_Id(Long id) {
		List<Servico> servicos = servicoBusiness.findByVeterinario_Id(id);
		
		return converteListaEntidadeParaListaResponseDto(servicos);
	}

}
