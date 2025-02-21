package com.project.start.api.domain.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.project.start.api.domain.Usuario;
import com.project.start.api.domain.base.BaseMapper;
import com.project.start.api.domain.dtos.UsuarioDto;

@Mapper(componentModel = "spring")
public interface UsuarioMapper extends BaseMapper<Usuario, UsuarioDto> {
	
	@Mapping(target = "senha", ignore = true)
	UsuarioDto convert(Usuario entity);

}
