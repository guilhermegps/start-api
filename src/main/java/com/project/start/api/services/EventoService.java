package com.project.start.api.services;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.project.start.api.domain.Evento;
import com.project.start.api.domain.Usuario;
import com.project.start.api.domain.enumerations.TipoEventoEnum;
import com.project.start.api.repositories.EventoRepository;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Validated
@Service
@RequiredArgsConstructor
public class EventoService {

	@Getter
	private final EventoRepository repository;
	
	private final UsuarioService usuarioService;
	
	public void registrar(@NotNull TipoEventoEnum tipoEnum, String descricao) {
		registrar(tipoEnum, descricao, usuarioService.usuarioSessao());
	}
	
	public void registrar(@NotNull TipoEventoEnum tipoEnum, String descricao, @NotNull Usuario usuario) {
		var evento = Evento.builder()
				.tipoEvento(tipoEnum.toEntity())
				.descricao(descricao)
				.usuario(usuario)
				.dtRegistro(LocalDateTime.now())
				.ativo(Boolean.TRUE)
				.build();
		
		repository.save(evento);
	}

}
