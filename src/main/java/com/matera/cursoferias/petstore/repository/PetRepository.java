package com.matera.cursoferias.petstore.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.matera.cursoferias.petstore.entity.Pet;

public interface PetRepository extends CrudRepository<Pet, Long>{
	
	List<Pet> findByEspecie_Id(Long id);
	
	List<Pet> findByEspecie_IdAndDataNascimento(Long id, LocalDate dataNascimento);

	List<Pet> findByCliente_Id(Long id);
	
	@Query("SELECT p FROM Pet p WHERE p.especie.id = ?1")
	List<Pet> findByEspecieIdComJPQL(Long id);
	
	@Query(value = "SELECT p.* FROM Pet p INNER JOIN especie e on p.especie_id = e.id where e.id = :id",
			nativeQuery = true)
	List<Pet> findByEspecieIdComNativeQuery(Long id);
}
