package com.project.start.api.services;

import java.security.Principal;
import java.util.Optional;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
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
	
	public Optional<UsuarioLogado> authUsuario(String username) {
		var opt = repository.findOneByLoginAndAtivo(username, Boolean.TRUE);
		var logado = opt.map(u -> UsuarioLogado.builder()
							.username(u.getLogin())
							.password(u.getSenha())
							.enabled(true)
							.build()
						).orElse(null);
		
		return Optional.ofNullable(logado);
	}

	public Usuario usuarioSessao() {
		return getUsuarioLogado().orElseThrow();
	}
	
	public Optional<Usuario> getUsuarioLogado() {
		Principal principal = SecurityContextHolder.getContext().getAuthentication();
		if(principal instanceof UsernamePasswordAuthenticationToken userPAToken) {
			var authenticated = userPAToken.isAuthenticated();
			
			if(authenticated) {
				var username = userPAToken.getPrincipal().toString();
				
				return findByLogin(username);
			}
		}
		
    	return Optional.empty();
    }

}
