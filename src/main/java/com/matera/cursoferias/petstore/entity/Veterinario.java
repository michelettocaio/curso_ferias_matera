package com.matera.cursoferias.petstore.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
public class Veterinario extends EntidadeBase{
	
	@Column(length = 100, nullable = false)
	private String nome;

	@OneToMany(mappedBy = "veterinario")
	private List<Servico> servicos;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Servico> getServicos() {
		return servicos;
	}

	public void setServicos(List<Servico> servicos) {
		this.servicos = servicos;
	}
}
