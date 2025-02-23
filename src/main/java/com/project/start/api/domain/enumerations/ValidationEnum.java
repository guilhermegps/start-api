package com.project.start.api.domain.enumerations;

import java.util.Collection;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ValidationEnum {
	
	NOT_NULL("NotNull", "vdt.err.valor_nulo", Object.class),
	NOT_EMPTY("NotEmpty", "vdt.err.valor_vazio", Object.class),
	NOT_BLANK("NotBlank", "vdt.err.valor_vazio", Object.class),
	SIZE_STRING("Size", "vdt.err.limite_caracteres", String.class),
	SIZE_COLLECTION("Size", "vdt.err.tamanho_collection", Collection.class),
	POSITIVE("Positive", "vdt.err.numero_positivo", Object.class),
	POSITIVE_ZERO("PositiveOrZero", "vdt.err.numero_positivo_zero", Object.class),
	DEFAULT("default", "vdt.err.campo_invalido", Object.class);

	private String keyValidation;
	private String keyMessage;
	private Class<?> clazz;
	
	public static ValidationEnum getBy(String keyValidation, Class<?> clazz) {
		return List.of(values()).stream()
				.filter(k -> k.getKeyValidation().equals(keyValidation))
				.filter(c -> c.clazz.isAssignableFrom(clazz))
				.findAny()
				.orElse(DEFAULT);
	}

}
