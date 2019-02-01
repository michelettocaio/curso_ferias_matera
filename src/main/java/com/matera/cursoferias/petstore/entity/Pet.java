package com.matera.cursoferias.petstore.entity;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Pet extends EntidadeBase {
	
	@Column(length = 30, nullable=false)
	private String nome;
	
	@Column(name = "data_nascimento", nullable=false)
	private LocalDate dataNascimento;
	
	@ManyToOne
	@JoinColumn(name = "id_cliente", nullable=false)
	private Cliente cliente;
	
	@ManyToOne
	@JoinColumn(name = "id_especie", nullable=false)
	private Especie especie;
	
	@OneToMany(mappedBy = "pet")
	private List<Servico> servicos;
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public Especie getEspecie() {
		return especie;
	}

	public void setEspecie(Especie especie) {
		this.especie = especie;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<Servico> getServicos() {
		return servicos;
	}

	public void setServicos(List<Servico> servicos) {
		this.servicos = servicos;
	}
}
