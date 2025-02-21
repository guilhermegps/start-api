package com.project.start.api.services;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.project.start.api.domain.dtos.LoginDto;
import com.project.start.api.domain.dtos.UsuarioLogado;

import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;

@Validated
@Service
@RequiredArgsConstructor
public class AuthService {
	
	private final UsuarioService usuarioService;
	private final PasswordEncoder passwordEncoder;
	private final AuthenticationManager authenticationManager;

	public UsuarioLogado authenticate(@NotNull LoginDto input) {
		try {
			authenticationManager
					.authenticate(new UsernamePasswordAuthenticationToken(input.getLogin(), input.getPasswd()));
		} catch (Exception e) {
			e.printStackTrace();
		}

		return usuarioService.usuarioLogado(input.getLogin()).orElseThrow();
	}
	
//	public Usuario getUsuarioLogado() {
//		Principal principal = SecurityContextHolder.getContext().getAuthentication();
//		if(principal instanceof UsernamePasswordAuthenticationToken userPAToken) {
//			var authenticated = userPAToken.isAuthenticated();
//			
//			if(authenticated)
//				return (Usuario) userPAToken.getPrincipal();
//		}
//		
//    	return null;
//    }

}
