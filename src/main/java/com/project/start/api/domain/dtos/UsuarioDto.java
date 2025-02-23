package com.project.start.api.domain.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.project.start.api.domain.base.BaseDto;
import com.project.start.api.domain.enumerations.PerfilEnum;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioDto extends BaseDto {

	@NotBlank
	@Size(max =  50, min = 3)
	private String login;

	@JsonProperty(access = Access.WRITE_ONLY)
	@NotBlank
	@Size(max = 30, min = 6)
	private String senha;

	@NotBlank
	@Size(max =  100, min = 4)
	private String nome;

	@NotBlank
	@Size(max =  11, min = 11)
	private String cpf;

	@NotBlank
	@Size(max =  50, min = 5)
	private String email;

	@NotNull
	private PerfilEnum perfil;

}
