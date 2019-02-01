package com.matera.cursoferias.petstore.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class VeterinarioRequestDto {
	
	@NotNull(message = "nome: Nome é de preenchimento obrigatório.")
	@Size(max = 100, message = "nome: Nome não pode ter mais que 100 caracteres.")
	private String nome;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
}
