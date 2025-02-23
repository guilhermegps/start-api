package com.project.start.api.services;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.project.start.api.domain.dtos.LoginDto;

import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;

@Validated
@Service
@RequiredArgsConstructor
public class AuthService {

	private final UsuarioService usuarioService;
	private final AuthenticationManager authenticationManager;

	public User authenticate(@NotNull LoginDto input) {
		authenticationManager
			.authenticate(new UsernamePasswordAuthenticationToken(input.getLogin(), input.getPasswd()));

		return usuarioService.authUsuario(input.getLogin()).orElseThrow();
	}

}
