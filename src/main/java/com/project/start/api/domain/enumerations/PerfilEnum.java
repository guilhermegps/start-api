package com.project.start.api.domain.enumerations;

import java.util.List;
import java.util.UUID;

import com.project.start.api.domain.Perfil;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PerfilEnum {
	
	ADM(UUID.fromString("f0c8362a-2915-48d4-8511-a1ddb909dfb1"), Long.valueOf(1)),
	USER(UUID.fromString("2b46c940-701d-486b-963e-5662bce2a4ab"), Long.valueOf(2));
	
	private UUID id;
	private Long codigo;
	
	public Perfil getEntity() {
		return new Perfil(this.id);
	}
	
	public static PerfilEnum valueOf(Long codigo) {
		return codigo != null ? List.of(values()).stream().filter(e -> e.getCodigo().equals(codigo)).findFirst().orElse(null) : null;
	}
	
	public static PerfilEnum valueOf(UUID id) {
		return id != null ? List.of(values()).stream().filter(e -> e.getId().equals(id)).findFirst().orElse(null) : null;
	}

}
