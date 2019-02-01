package com.matera.cursoferias.petstore.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.matera.cursoferias.petstore.dto.ClienteRequestDTO;
import com.matera.cursoferias.petstore.dto.ClienteResponseDTO;
import com.matera.cursoferias.petstore.service.ClienteService;
import com.matera.cursoferias.petstore.service.PetService;

@RunWith(SpringRunner.class)
@WebMvcTest(ClienteController.class)
public class ClienteControllerTest {
	
	@Autowired
	private MockMvc mvc;
	
	@MockBean
	private ClienteService clienteService;
	
	@MockBean
	private PetService petService;
	
	@Test
	public void validaSave() throws Exception {
		ClienteResponseDTO responseDto = new ClienteResponseDTO();
		responseDto.setId(3L);
		responseDto.setNome("Ronaldo");
		
		Mockito.when(clienteService.save(Mockito.eq(null), Mockito.any(	))).thenReturn(responseDto);
		
		mvc.perform(post("/api/v1/clientes").contentType(APPLICATION_JSON).content("{ \"nome\": \"Ronaldo\" }"))
					.andExpect(status().isCreated())
					.andExpect(jsonPath("$").doesNotExist());
//		clienteService.save(null, requestDto);
	}
	
	@Test
	public void validaFindAll() throws Exception {
		List<ClienteResponseDTO> listDto = new ArrayList<>();
		ClienteResponseDTO responseDto = new ClienteResponseDTO();
		responseDto.setId(3L);
		responseDto.setNome("Ronaldo");
		
		ClienteResponseDTO responseDto2 = new ClienteResponseDTO();
		responseDto2.setId(4L);
		responseDto2.setNome("Caio");
		
		listDto = Arrays.asList(responseDto, responseDto2);
		
		Mockito.when(clienteService.findAll()).thenReturn(listDto);
		
		mvc.perform(get("/api/v1/clientes").contentType(APPLICATION_JSON))
					.andExpect(status().isOk())
					.andExpect(jsonPath("$", hasSize(listDto.size())))
					.andExpect(jsonPath("$[0].id", equalTo(responseDto.getId().intValue())))
					.andExpect(jsonPath("$[0].nome", equalTo(responseDto.getNome())))
					.andExpect(jsonPath("$[1].id", equalTo(responseDto2.getId().intValue())))
					.andExpect(jsonPath("$[1].nome", equalTo(responseDto2.getNome())));
	}

}
