package com.project.start.api.domain;

import com.project.start.api.domain.base.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;


@SuperBuilder
@Getter
@Setter
@Entity
@Table(schema = "START")
@AllArgsConstructor
@NoArgsConstructor
public class Usuario extends BaseEntity {
	
	@Column(length = 50, nullable = false)
	private String login;

	@Column(length = 100, nullable = false)
	private String nome;

	@Column(length = 11, nullable = false)
	private String cpf;
	
	@Column(length = 60, nullable = false)
	private String senha;
	
	@Column(length = 50, nullable = false)
	private String email;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_perfil")
	private Perfil perfil;

}
