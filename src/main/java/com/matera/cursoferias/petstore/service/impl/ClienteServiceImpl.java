package com.matera.cursoferias.petstore.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.matera.cursoferias.petstore.business.ClienteBusiness;
import com.matera.cursoferias.petstore.dto.ClienteRequestDTO;
import com.matera.cursoferias.petstore.dto.ClienteResponseDTO;
import com.matera.cursoferias.petstore.entity.Cliente;
import com.matera.cursoferias.petstore.service.ClienteService;

@Service
public class ClienteServiceImpl implements ClienteService{

	private final ClienteBusiness clienteBusiness;
	
	public ClienteServiceImpl(ClienteBusiness clienteBusiness) {
		this.clienteBusiness = clienteBusiness;
	}

	@Override
	public List<ClienteResponseDTO> findAll() {
		List<ClienteResponseDTO> clientes = new ArrayList<>();
	
		clienteBusiness.findAll().forEach(cliente -> clientes.add(converteEntidadeParaResponseDTO(cliente)));
		
		return clientes;
	}

	@Override
	public ClienteResponseDTO findById(Long id) {
		return converteEntidadeParaResponseDTO(clienteBusiness.findById(id));
	}

	@Override
	public ClienteResponseDTO converteEntidadeParaResponseDTO(Cliente entidade) {
		ClienteResponseDTO clienteDto = new ClienteResponseDTO();

		clienteDto.setId(entidade.getId());
		clienteDto.setNome(entidade.getNome());
		
		return clienteDto;
	}

	@Override
	public ClienteResponseDTO save(Long id, ClienteRequestDTO requestDto) {
		Cliente cliente = converteRequestDTOParaEntidade(id, requestDto);
		
		cliente = clienteBusiness.save(cliente);
		
		return converteEntidadeParaResponseDTO(cliente);
	}

	@Override
	public void deleteById(Long id) {
		clienteBusiness.deleteById(id);
	}

	@Override
	public Cliente converteRequestDTOParaEntidade(Long id, ClienteRequestDTO requestDTO) {
		Cliente cliente = id == null ? new Cliente() : clienteBusiness.findById(id);

		cliente.setNome(requestDTO.getNome());
		
		return cliente;
	}

	@Override
	public Cliente findEntidadeById(Long id) {
		return clienteBusiness.findById(id);
	}

}
