package com.tarefasColaborativas.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.tarefasColaborativas.dto.UsuarioCadastroDTO;
import com.tarefasColaborativas.dto.UsuarioDTO;
import com.tarefasColaborativas.model.Usuario;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {
	
	    UsuarioDTO toDTO(Usuario usuario);
	    
	    @Mapping(target = "id", ignore = true)
	    Usuario toEntity(UsuarioCadastroDTO dto);
}
