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
public class Evento extends BaseEntity {
	
	@Column(nullable = false)
	private String descricao;

	@Column(name = "ip_usuario")
	private String ipUsuario;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_usuario")
	private Usuario usuario;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_tipo_evento")
	private TipoEvento tipoEvento;

}
