package com.matera.cursoferias.petstore;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.matera.cursoferias.petstore.business.ClienteBusiness;
import com.matera.cursoferias.petstore.business.EspecieBusiness;
import com.matera.cursoferias.petstore.business.PetBusiness;
import com.matera.cursoferias.petstore.business.ServicoBusiness;
import com.matera.cursoferias.petstore.business.VeterinarioBusiness;
import com.matera.cursoferias.petstore.entity.Cliente;
import com.matera.cursoferias.petstore.entity.Especie;
import com.matera.cursoferias.petstore.entity.Pet;
import com.matera.cursoferias.petstore.entity.Servico;
import com.matera.cursoferias.petstore.entity.TipoServico;
import com.matera.cursoferias.petstore.entity.Veterinario;

@Component
public class DataInitializer implements CommandLineRunner{

	private final EspecieBusiness especieBusiness;
	private final ClienteBusiness clienteBusiness;
	private final PetBusiness petBusiness;
	private final VeterinarioBusiness veterinarioBusiness;
	private final ServicoBusiness servicoBusiness;


	public DataInitializer(EspecieBusiness especieBusiness, ClienteBusiness clienteBusiness, PetBusiness petBusiness,
			VeterinarioBusiness veterinarioBusiness, ServicoBusiness servicoBusiness) {
		this.especieBusiness = especieBusiness;
		this.clienteBusiness = clienteBusiness;
		this.petBusiness = petBusiness;
		this.veterinarioBusiness = veterinarioBusiness;
		this.servicoBusiness = servicoBusiness;
	}


	@Override
	public void run(String... args) throws Exception {
		Especie esp1 = new Especie();
		Especie esp2 = new Especie();
		
		esp1.setDescricao("Cachorro");
		esp2.setDescricao("Gato");
		
		especieBusiness.save(esp1);
		especieBusiness.save(esp2);
		
		Cliente cli1 = new Cliente();
		Cliente cli2 = new Cliente();
		
		cli1.setNome("Ronaldo");
		cli2.setNome("Caio");
		
		clienteBusiness.save(cli1);
		clienteBusiness.save(cli2);
		
		Pet pet1 = new Pet();
		Pet pet2 = new Pet();
		Pet pet3 = new Pet();
		Pet pet4 = new Pet();
		
		pet1.setCliente(cli1);
		pet1.setDataNascimento(LocalDate.parse("2018-01-01"));
		pet1.setEspecie(esp1);
		pet1.setNome("Rex");
		
		pet2.setCliente(cli1);
		pet2.setDataNascimento(LocalDate.parse("2017-01-01"));
		pet2.setEspecie(esp2);
		pet2.setNome("Bidu");
		
		pet3.setCliente(cli2);
		pet3.setDataNascimento(LocalDate.parse("2016-01-01"));
		pet3.setEspecie(esp1);
		pet3.setNome("Totó");
		
		pet4.setCliente(cli2);
		pet4.setDataNascimento(LocalDate.parse("2015-01-01"));
		pet4.setEspecie(esp2);
		pet4.setNome("Carlinhos");
		
		petBusiness.save(pet1);
		petBusiness.save(pet2);
		petBusiness.save(pet3);
		petBusiness.save(pet4);
		
		Veterinario vet1 = new Veterinario();
		Veterinario vet2 = new Veterinario();
		
		vet1.setNome("Emerson");
		vet2.setNome("Michele");
		
		veterinarioBusiness.save(vet1);
		veterinarioBusiness.save(vet2);
		
		Servico ser1 = new Servico();
		Servico ser2 = new Servico();
		Servico ser3 = new Servico();
		Servico ser4 = new Servico();
		
		ser1.setObservacao("Banho tosa  rex");
		ser1.setTipoServico(TipoServico.BANHO_TOSA);
		ser1.setPet(pet1);
		ser1.setValor(new BigDecimal(40));
		ser1.setDataHora(LocalDateTime.parse("2019-01-01T08:00:00"));
		ser1.setVeterinario(vet1);
		
		ser2.setObservacao("Cirurgia  bidu");
		ser2.setTipoServico(TipoServico.CIRURGIA);
		ser2.setPet(pet2);
		ser2.setValor(new BigDecimal(500));
		ser2.setDataHora(LocalDateTime.parse("2019-01-01T09:00:00"));
		ser2.setVeterinario(vet1);
		
		ser3.setObservacao("Consulta  Totó");
		ser3.setTipoServico(TipoServico.CONSULTA);
		ser3.setPet(pet3);
		ser3.setValor(new BigDecimal(250));
		ser3.setDataHora(LocalDateTime.parse("2019-01-01T10:00:00"));
		ser3.setVeterinario(vet2);
		
		ser4.setObservacao("Vacinacao  Carlinhos");
		ser4.setTipoServico(TipoServico.VACINACAO);
		ser4.setPet(pet4);
		ser4.setValor(new BigDecimal(80));
		ser4.setDataHora(LocalDateTime.parse("2019-01-01T11:00:00"));
		ser4.setVeterinario(vet2);
		
		servicoBusiness.save(ser1);
		servicoBusiness.save(ser2);
		servicoBusiness.save(ser3);
		servicoBusiness.save(ser4);
		
	}

}
