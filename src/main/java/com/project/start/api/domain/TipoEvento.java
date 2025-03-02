package com.project.start.api.domain;

import java.util.UUID;

import com.project.start.api.domain.base.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
public class TipoEvento extends BaseEntity {

	@Column(length = 50, nullable = false)
	private String descricao;
	
	public TipoEvento(UUID id) {
		this.id = id;
	}

}
