package com.project.start.api.domain.enumerations;

import java.util.List;
import java.util.UUID;

import com.project.start.api.domain.TipoEvento;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TipoEventoEnum {
	INCLUSAO(UUID.fromString("359478a8-f1f6-486e-bdc2-504bbead5fbc"), 1L, "Inclusão de registro"),
	ALTERACAO(UUID.fromString("e2f4f40c-0340-4404-ad32-1a14692de709"), 2L, "Alteração de registro"),
	DESATIVACAO(UUID.fromString("d3320a32-b803-40f9-ab35-13c29833f214"), 3L, "Desativação de registro"),
	REMOCAO(UUID.fromString("a6c4efd6-0407-4bfb-9400-dd6618b0b069"), 4L, "Remoção de Registro"),
	VISUALIZACAO(UUID.fromString("ca359094-7080-4d4e-b3b6-fd64f5ca7a71"), 5L, "Visualização"),
	AUTENTICACAO(UUID.fromString("7fc34510-0501-4768-beca-bd9345683a93"), 6L, "Autenticação no sistema"),
	FALHA_AUTENTICACAO(UUID.fromString("3e2f3d03-42c5-4ae2-a240-114d5b332b4c"), 7L, "Falha na autenticação no sistema"),
	ACESSO_NEGADO(UUID.fromString("eb37a61c-ce15-4b88-87f0-68b75911239f"), 8L, "Tentativa de acesso não autorizada"),
	GEROU_ARQUIVO(UUID.fromString("8d8d76b8-b78b-407b-92c9-d5c452713c65"), 9L, "Geração de arquivo")
	;
	
	private UUID id;
	private Long codigo;
	private String descricao;
	
	public TipoEvento toEntity() {
		return new TipoEvento(id);
	}
	
	public static TipoEventoEnum valueOf(Long codigo) {
		return codigo != null ? List.of(values()).stream().filter(e -> e.getCodigo().equals(codigo)).findFirst().orElse(null) : null;
	}
	
	public static TipoEventoEnum valueOf(UUID id) {
		return id != null ? List.of(values()).stream().filter(e -> e.getId().equals(id)).findFirst().orElse(null) : null;
	}
}
