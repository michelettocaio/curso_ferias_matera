package com.matera.cursoferias.petstore.dto;

import java.math.BigDecimal;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class ServicoRequestDTO {

	@NotNull(message = "Observação é de preenchimento obrigatório")
	@Size(max = 500, message = "Observação não pode ultrapassar 500 caracteres")
	private String observacao;
	@NotNull(message = "Tipo do Serviço é de preenchimento obrigatório")
	private int tipoServico;
	@NotNull(message = "Valor é de preenchimento obrigatório")
	private BigDecimal valor;
	@NotNull(message = "Id do Pet é de preenchimento obrigatório")
	private Long idPet;
	@NotNull(message = "Id do Veterinário é de preenchimento obrigatório")
	private Long idVeterinario;
	
	public Long getIdVeterinario() {
		return idVeterinario;
	}
	public void setIdVeterinario(Long idVeterinario) {
		this.idVeterinario = idVeterinario;
	}
	public String getObservacao() {
		return observacao;
	}
	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}
	public int getTipoServico() {
		return tipoServico;
	}
	public void setTipoServico(int tipoServico) {
		this.tipoServico = tipoServico;
	}
	public BigDecimal getValor() {
		return valor;
	}
	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
	public Long getIdPet() {
		return idPet;
	}
	public void setIdPet(Long idPet) {
		this.idPet = idPet;
	}
}
