package com.matera.cursoferias.petstore.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;import com.matera.cursoferias.petstore.business.ClienteBusiness;
import com.matera.cursoferias.petstore.business.PetBusiness;
import com.matera.cursoferias.petstore.dto.PetRequestDTO;
import com.matera.cursoferias.petstore.dto.PetResponseDTO;
import com.matera.cursoferias.petstore.entity.Pet;
import com.matera.cursoferias.petstore.service.ClienteService;
import com.matera.cursoferias.petstore.service.EspecieService;
import com.matera.cursoferias.petstore.service.PetService;

@Service
public class PetServiceImpl implements PetService {

	private final ClienteService clienteService;
	private final EspecieService especieService;
	private final PetBusiness petBusiness;

	public PetServiceImpl(ClienteService clienteService, EspecieService especieService, PetBusiness petBusiness) {
		this.clienteService = clienteService;
		this.especieService = especieService;
		this.petBusiness = petBusiness;
	}

	@Override
	public List<PetResponseDTO> findAll() {
		return converteListEntidadeParaListaResponseDTO(petBusiness.findAll());
	}

	@Override
	public PetResponseDTO findById(Long id) {
		return converteEntidadeParaResponseDTO(petBusiness.findById(id));
	}

	@Override
	public PetResponseDTO converteEntidadeParaResponseDTO(Pet entidade) {
		PetResponseDTO pet = new PetResponseDTO();

		pet.setId(entidade.getId());
		pet.setDataNascimento(entidade.getDataNascimento());
		pet.setNome(entidade.getNome());
		pet.setCliente(clienteService.converteEntidadeParaResponseDTO(entidade.getCliente()));
		pet.setEspecie(especieService.converteEntidadeParaResponseDTO(entidade.getEspecie()));

		return pet;

	}

	@Override
	public PetResponseDTO save(Long id, PetRequestDTO requestDto) {
		Pet pet = converteRequestDTOParaEntidade(id, requestDto);
		
		pet = petBusiness.save(pet);
		
		return converteEntidadeParaResponseDTO(pet);
	}

	@Override
	public void deleteById(Long id) {
		petBusiness.deleteById(id);
	}

	@Override
	public Pet converteRequestDTOParaEntidade(Long id, PetRequestDTO requestDTO) {
		Pet pet = id == null ? new Pet() : petBusiness.findById(id); 
		
		pet.setNome(requestDTO.getNome());
		pet.setDataNascimento(requestDTO.getDataNascimento());
		pet.setCliente(clienteService.findEntidadeById(requestDTO.getIdCliente()));
		pet.setEspecie(especieService.findEntidadeById(requestDTO.getIdEspecie()));
		
		return pet;
	}

	@Override
	public List<PetResponseDTO> findByEspecie_Id(Long idEspecie) {
		return converteListEntidadeParaListaResponseDTO(petBusiness.findByEspecie_Id(idEspecie));
	}

	private List<PetResponseDTO> converteListEntidadeParaListaResponseDTO(List<Pet> pets) {
		List<PetResponseDTO> retorno = new ArrayList<>();

		pets.forEach(pet -> retorno.add(converteEntidadeParaResponseDTO(pet)));

		return retorno;
	}

	@Override
	public Pet findEntidadeById(Long id) {
		return petBusiness.findById(id);
	}

	@Override
	public List<PetResponseDTO> findByCliente_Id(Long idCliente) {
		return converteListEntidadeParaListaResponseDTO(petBusiness.findByCliente_Id(idCliente));
	}

}
