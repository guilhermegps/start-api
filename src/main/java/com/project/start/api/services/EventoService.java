package com.project.start.api.services;

import java.time.LocalDateTime;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

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
				.ipUsuario(getRequestIp())
				.ativo(Boolean.TRUE)
				.build();
		
		repository.save(evento);
	}

	
	/**
	 * Irá retornar o IP da requisição.
	 * Para que ele retorne sempre um Ipv4 é necessário configurar na JVM -Djava.net.preferIPv4Stack=true
	 * 
	 * @return String ip
	 */
	public static String getRequestIp() {
		var sra = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes());
		
		if(sra!=null) {
			var request = sra.getRequest();
			var remoteAddr = request.getHeader("X-FORWARDED-FOR");
			if (StringUtils.isBlank(remoteAddr))
				remoteAddr = request.getRemoteAddr();
			
			return remoteAddr;
		}
		
		return null;
    }

}
