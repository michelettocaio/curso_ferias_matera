package com.matera.cursoferias.petstore.dto;

import java.util.List;

public class VeterinarioResponseDto {
	
	private Long id;
	private String nome;
	private List<ServicoResponseDTO> servicos;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<ServicoResponseDTO> getServicos() {
		return servicos;
	}

	public void setServicos(List<ServicoResponseDTO> servicos) {
		this.servicos = servicos;
	}
	

}
