package com.matera.cursoferias.petstore.business;

import java.util.List;

public interface CrudBusiness<Entidade, ID> {

	Entidade save(Entidade entidade);
	
	List<Entidade> findAll();
	
	Entidade findById(ID id);
	
	void deleteById(ID id);
}
