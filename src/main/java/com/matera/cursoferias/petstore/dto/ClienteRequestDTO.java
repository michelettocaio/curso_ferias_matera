package com.matera.cursoferias.petstore.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class ClienteRequestDTO {
	
	@NotBlank(message = "Nome é de preenchimento obrigatório")
	@Size(max = 100, message = "Nome não pode ultrapassar 100 caracteres.")
	private String nome;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
}
