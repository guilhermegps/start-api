package com.project.start.api.repositories.base;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.project.start.api.domain.base.BaseEntity;

@Repository
public interface BaseRepository<E extends BaseEntity> extends JpaRepository<E, UUID>, JpaSpecificationExecutor<E> {

	default Optional<E> findOneByCodigoAndAtivo(Long codigo, Boolean ativo) {
		return this.findOne(
					(root, query, builder) -> builder.and(
									builder.equal(root.get("codigo"), codigo), 
									builder.equal(root.get("ativo"), ativo))
			    );
	}

}
