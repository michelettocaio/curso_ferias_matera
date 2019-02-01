package com.matera.cursoferias.petstore.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Servico extends EntidadeBase {

	@Column(length = 500, nullable=false)
	private String observacao;
	
	@Column(name = "data_hora", nullable=false)
	private LocalDateTime dataHora;
	
	@Column(name = "tipo_servico", nullable=false)
	private int tipoServico;
	
	@Column(nullable=false)
	private BigDecimal valor;
	
	@JoinColumn(name = "id_veterinario")
	@ManyToOne
	private Veterinario veterinario;
	
	@JoinColumn(name = "id_pet", nullable=false)
	@ManyToOne
	private Pet pet;

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public LocalDateTime getDataHora() {
		return dataHora;
	}

	public void setDataHora(LocalDateTime dataHora) {
		this.dataHora = dataHora;
	}

	public TipoServico getTipoServico() {
		return TipoServico.valueOf(tipoServico);
	}

	public void setTipoServico(TipoServico tipoServico) {
		this.tipoServico = tipoServico.getId();
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public Pet getPet() {
		return pet;
	}

	public void setPet(Pet pet) {
		this.pet = pet;
	}

	public Veterinario getVeterinario() {
		return veterinario;
	}

	public void setVeterinario(Veterinario veterinario) {
		this.veterinario = veterinario;
	}

	public void setTipoServico(int tipoServico) {
		this.tipoServico = tipoServico;
	}
}
