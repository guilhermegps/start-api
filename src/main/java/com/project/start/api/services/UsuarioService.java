package com.project.start.api.services;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.project.start.api.domain.Usuario;
import com.project.start.api.repositories.UsuarioRepository;
import com.project.start.api.services.base.BaseService;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Validated
@Service
@RequiredArgsConstructor
public class UsuarioService extends BaseService<Usuario> {

	@Getter
	private final UsuarioRepository repository;

}
