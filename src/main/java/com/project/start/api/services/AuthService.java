package com.project.start.api.services;

import java.util.List;
import java.util.Optional;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.project.start.api.domain.dtos.LoginDto;
import com.project.start.api.domain.enumerations.PerfilEnum;
import com.project.start.api.domain.enumerations.TipoEventoEnum;

import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;

@Validated
@Service
@RequiredArgsConstructor
public class AuthService {

	private final UsuarioService usuarioService;
	private final AuthenticationManager authenticationManager;
	protected final EventoService eventoService;

	public User authenticate(@NotNull LoginDto input) {
		var username = input.getLogin();
		try {
			authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(username, input.getPasswd()));
		} catch(BadCredentialsException e) {
        	regEvento(username, TipoEventoEnum.FALHA_AUTENTICACAO, "log.alert.falha_autenticacao", username);
			throw e;
		}

		regEvento(username, TipoEventoEnum.AUTENTICACAO, "log.alert.autenticado", username);
		
		return authUsuario(username).orElseThrow();
	}
	
	public Optional<User> authUsuario(String username) {
		var opt = usuarioService.findByLogin(username);
		
		return opt.map(u -> {
				var perfil = PerfilEnum.valueOf(u.getPerfil().getId());
			
				return (User) User
				        .withUsername(u.getLogin())
				        .password(u.getSenha())
				        .authorities(List.of(perfil.name()).toArray(new String[0]))
				        .build();
			});
	}
	
    public void regEvento(String username, TipoEventoEnum tipo, String keyMsg, Object... params) {
		usuarioService.findByLogin(username)
				.ifPresent(usuario -> eventoService.registrar(tipo, usuarioService.getMessages().get(keyMsg, params), usuario));
	}

}
