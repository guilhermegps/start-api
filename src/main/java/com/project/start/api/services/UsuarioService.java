package com.project.start.api.services;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.project.start.api.domain.Usuario;
import com.project.start.api.domain.dtos.UsuarioDto;
import com.project.start.api.domain.enumerations.PerfilEnum;
import com.project.start.api.domain.mappers.UsuarioMapper;
import com.project.start.api.repositories.UsuarioRepository;
import com.project.start.api.services.base.BaseCRUDService;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Validated
@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class UsuarioService extends BaseCRUDService<Usuario, UsuarioDto> {

	@Getter
	private final UsuarioRepository repository;
	@Getter
	private final UsuarioMapper mapper;
	@Getter
	private String entityName = "Usuário";
	
	@Lazy
	private final PasswordEncoder pdEncoder;
	
	public Optional<Usuario> findByLogin(String login) {
		return repository.findOneByLoginAndAtivo(login, Boolean.TRUE);
	}

	public Usuario usuarioSessao() {
		return getUsuarioLogado().orElseThrow();
	}
	
	public Optional<Usuario> getUsuarioLogado() {
		Principal principal = SecurityContextHolder.getContext().getAuthentication();
		if(principal instanceof UsernamePasswordAuthenticationToken userPAToken) {
			var authenticated = userPAToken.isAuthenticated();
			
			if(authenticated) {
				var logado = (User) userPAToken.getPrincipal();
				
				return findByLogin(logado.getUsername());
			}
		}
		
    	return Optional.empty();
    }
	
	@Override
	public Usuario create(@NotNull UsuarioDto input) {
		input.setSenha(pdEncoder.encode(input.getSenha()));
		
		return super.create(input);
	}
	
	@Override
	public Usuario update(@NotNull Long code, @NotNull UsuarioDto input) {
		var senha = input.getSenha();
		if(StringUtils.isNotBlank(senha))
			input.setSenha(pdEncoder.encode(senha));
		
		return super.update(code, input);
	}

}
