package com.matera.cursoferias.petstore.business.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.matera.cursoferias.petstore.business.ServicoBusiness;
import com.matera.cursoferias.petstore.business.VeterinarioBusiness;
import com.matera.cursoferias.petstore.entity.Servico;
import com.matera.cursoferias.petstore.entity.Veterinario;
import com.matera.cursoferias.petstore.exception.BusinessException;
import com.matera.cursoferias.petstore.exception.RegistroNaoEncontradoException;
import com.matera.cursoferias.petstore.repository.VeterinarioRepository;

@Component
public class VeterinarioBusinessImpl implements VeterinarioBusiness {
	
	private final VeterinarioRepository veterinarioRepository;
	private final ServicoBusiness servicoBusiness;

	public VeterinarioBusinessImpl(VeterinarioRepository veterinarioRepository, ServicoBusiness servicoBusiness) {
		this.veterinarioRepository = veterinarioRepository;
		this.servicoBusiness = servicoBusiness;
	}

	@Override
	public Veterinario save(Veterinario entidade) {
		return veterinarioRepository.save(entidade);
	}

	@Override
	public List<Veterinario> findAll() {
		List<Veterinario> veterinarios = new ArrayList<>();
		
		veterinarioRepository.findAll().forEach(vet -> veterinarios.add(vet));
		
		return veterinarios;
	}

	@Override
	public Veterinario findById(Long id) {
		Veterinario vet = veterinarioRepository.findById(id).orElse(null);
		
		if(vet == null) {
			throw new RegistroNaoEncontradoException(String.format("Veterinário %d não encontrado", id));
		}
		return vet;
	}

	@Override
	public void deleteById(Long id) {
		findById(id);
		List<Servico> servicos = servicoBusiness.findByVeterinario_Id(id);
		
		if(!servicos.isEmpty()) {
			throw new BusinessException("Não é possível deletar este veterinário pois tem Servicos vinculados");
		}
		
		veterinarioRepository.deleteById(id);
	}

}
