package com.project.start.api.services.base;

import java.util.Objects;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import com.project.start.api.domain.base.BaseDto;
import com.project.start.api.domain.base.BaseEntity;

import jakarta.validation.constraints.NotNull;

@Validated
@Component
public abstract class BaseCRUDService<E extends BaseEntity, D extends BaseDto> extends BaseService<E, D> {

    public ValidationInterface<D> validation() { return null; }

	@Transactional
	public E cadastrar(@NotNull D input) {
		if (Objects.nonNull(validation()))
			validation().create(input);

		var entity = getMapper().convert(input);
		entity.setId(null);
		entity.setAtivo(Boolean.TRUE);

		return getRepository().save(entity);
	}

	@Transactional
	public E alterar(@NotNull Long code, @NotNull D dto){
		if (Objects.nonNull(validation()))
			validation().update(code, dto);

		var entity = obter(code);
		
		var upEntity = getMapper().convert(dto);
		BeanUtils.copyProperties(upEntity, entity, "id", "codigo", "ativo");
		
		return getRepository().save(entity);
	}

	@Transactional
	public E inativar(@NotNull Long code){
		var entity = obter(code);
		entity.setAtivo(Boolean.FALSE);
		
		return getRepository().save(entity);
	}

	@Transactional
	public Long excluir(@NotNull Long code) {
		var entity = obter(code);
		getRepository().delete(entity);

		return code;
	}

}
