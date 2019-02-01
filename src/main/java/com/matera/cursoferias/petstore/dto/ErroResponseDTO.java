	package com.matera.cursoferias.petstore.dto;

import java.util.ArrayList;
import java.util.List;

import org.springframework.validation.FieldError;

public class ErroResponseDTO {

	private List<String> erros;
	
	public static ErroResponseDTO buildError(List<String> listaErros) {
		ErroResponseDTO erroDto = new ErroResponseDTO();
		erroDto.setErros(listaErros);
		
		return erroDto;
	}
	
	public static ErroResponseDTO withError(String mensagem) {
		List<String> stringErros = new ArrayList<>();
		stringErros.add(mensagem);
		
		return buildError(stringErros);
	}
	
	public static ErroResponseDTO withError(List<FieldError> erros) {
		ErroResponseDTO dto = new ErroResponseDTO();
		List<String> stringErros = new ArrayList<>();
		erros.forEach(e -> stringErros.add(String.format("%s: %s", e.getField(), e.getDefaultMessage())));
			
		return buildError(stringErros);
	}

	public List<String> getErros() {
		return erros;
	}

	public void setErros(List<String> erros) {
		this.erros = erros;
	}
}
