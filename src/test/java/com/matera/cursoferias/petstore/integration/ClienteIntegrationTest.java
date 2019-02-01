package com.matera.cursoferias.petstore.integration;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;

import com.matera.cursoferias.petstore.dto.ClienteRequestDTO;
import com.matera.cursoferias.petstore.dto.ClienteResponseDTO;
import com.matera.cursoferias.petstore.repository.ClienteRepository;
import com.matera.cursoferias.petstore.repository.EspecieRepository;
import com.matera.cursoferias.petstore.repository.PetRepository;
import com.matera.cursoferias.petstore.repository.ServicoRepository;

import io.restassured.response.Response;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class ClienteIntegrationTest {
	
	@LocalServerPort
	private int port;
	
	@Autowired
	private ServicoRepository servicoRepository;
	
	@Autowired
	private PetRepository petRepository;
	
	@Autowired
	private EspecieRepository especieRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Before
	public void limpaBase() {
//		servicoRepository.deleteAll();
//		petRepository.deleteAll();
//		especieRepository.deleteAll();
//		clienteRepository.deleteAll();
	}
	
	@Test
	public void criarClienteComSucesso() {
		ClienteRequestDTO clienteDto = new ClienteRequestDTO();
		clienteDto.setNome("Ronaldo");
		
		Response response = given().port(port)
			   .body(clienteDto)
			   .header("Content-Type", "application/json")
			   .post("/petstore/api/v1/clientes")
			   .andReturn();
		
		assertEquals(HttpStatus.CREATED.value(), response.getStatusCode());
		assertTrue(response.getHeader("location").matches(".*/api/v1/clientes/[0-9]+"));
		
	}
	
	@Test
	public void criarClienteSemNomeErro() {
		ClienteRequestDTO clienteDto = new ClienteRequestDTO();
		
		 given().port(port)
			   .body(clienteDto)
			   .header("Content-Type", "application/json")
			   .post("/petstore/api/v1/clientes")
			   .then()
			   		.statusCode(HttpStatus.BAD_REQUEST.value())
			   		.body("erros.size()", Matchers.is(1))
			   		.body("erros[0]", Matchers.equalTo("nome: Nome é de preenchimento obrigatório"));
		
	}
	
	@Test
	public void deletarClienteComSucesso() { 
		ClienteRequestDTO clienteDto = new ClienteRequestDTO();
		clienteDto.setNome("Ronaldo");
		
		Response response = given().port(port)
			   .body(clienteDto)
			   .header("Content-Type", "application/json")
			   .post("/petstore/api/v1/clientes")
			   .andReturn();
		
		assertEquals(HttpStatus.CREATED.value(), response.getStatusCode());
		assertTrue(response.getHeader("location").matches(".*/api/v1/clientes/[0-9]+"));
		
		Response retornoDelete = given()
			.port(port)
			.header("Content-Type", "application/json")
			.delete(response.getHeader("location"))
			.andReturn();
		
		assertEquals(HttpStatus.NO_CONTENT.value(), retornoDelete.getStatusCode());
		
		given()
			.port(port)
			.header("Content-Type", "application/json")
			.get(response.getHeader("location"))
			.then()
				.statusCode(HttpStatus.NOT_FOUND.value())
				.body("erros.size()", Matchers.is(1));
	}
	
	@Test
	public void atualizaCliente() {
		ClienteRequestDTO clienteDto = new ClienteRequestDTO();
		clienteDto.setNome("Ronaldo");
		
		Response response = given().port(port)
			   .body(clienteDto)
			   .header("Content-Type", "application/json")
			   .post("/petstore/api/v1/clientes")
			   .andReturn();
		assertEquals(HttpStatus.CREATED.value(), response.getStatusCode());
		assertTrue(response.getHeader("location").matches(".*/api/v1/clientes/[0-9]+"));
		
		clienteDto.setNome("Caio");
		
		Response responseUpdate = given().port(port)
				   .body(clienteDto)
				   .header("Content-Type", "application/json")
				   .put(response.getHeader("location"))
				   .andReturn();
		
		assertEquals(HttpStatus.NO_CONTENT.value(), responseUpdate.getStatusCode());
		
		
		ClienteResponseDTO responseDto = given()
										.port(port)
									    .get(response.getHeader("location"))
									    .then()
									    	.statusCode(HttpStatus.OK.value())
									    	.extract()
									    	.body()
									    	.as(ClienteResponseDTO.class);
		
		assertEquals(responseDto.getNome(), "Caio");
		
	}
	
}
