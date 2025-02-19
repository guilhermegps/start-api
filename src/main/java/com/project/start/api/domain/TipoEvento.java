package com.project.start.api.domain;

import com.project.start.api.domain.base.BaseEntityComplete;

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
public class TipoEvento extends BaseEntityComplete {

	@Column(length = 50, nullable = false)
	private String descricao;

}
