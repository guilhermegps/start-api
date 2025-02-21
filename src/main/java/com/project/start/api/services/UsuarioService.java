package com.project.start.api.services;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.project.start.api.domain.Usuario;
import com.project.start.api.domain.dtos.UsuarioLogado;
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
//	private final PasswordEncoder passwordEncoder;
	
	public Optional<Usuario> findByLogin(String login) {
		return repository.findOneByLoginAndAtivo(login, Boolean.TRUE);
	}
	
	public Optional<UsuarioLogado> usuarioLogado(String username) {
		var opt = repository.findOneByLoginAndAtivo(username, Boolean.TRUE);
		var logado = opt.map(u -> UsuarioLogado.builder()
							.username(u.getLogin())
							.password(u.getSenha())
							.enabled(true)
							.build()
						).orElse(null);
		
		return Optional.ofNullable(logado);
	}

}
