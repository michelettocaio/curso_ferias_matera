package com.matera.cursoferias.petstore.service;

import com.matera.cursoferias.petstore.dto.VeterinarioRequestDto;
import com.matera.cursoferias.petstore.dto.VeterinarioResponseDto;
import com.matera.cursoferias.petstore.entity.Veterinario;

public interface VeterinarioService extends CrudService<VeterinarioRequestDto, VeterinarioResponseDto, Veterinario, Long>{

}
