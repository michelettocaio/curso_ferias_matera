package com.matera.cursoferias.petstore.entity;

public enum TipoServico {
	BANHO_TOSA(1, "Banho e Tosa"),
	CIRURGIA(2, "Cirurgia"),
	CONSULTA(3, "Consulta"),
	VACINACAO(4, "Vacinação");
	
	private int id;
	private String descricao;
	
	private TipoServico(int id, String descricao) {
		this.id = id;
		this.descricao = descricao;
	}

	public static TipoServico valueOf(int id) {
		for(TipoServico tipo : values()) {
			if(tipo.getId() == id) {
				return tipo;
			}
		}
		return null;
	}
	
	public int getId() {
		return id;
	}

	public String getDescricao() {
		return descricao;
	}
}
