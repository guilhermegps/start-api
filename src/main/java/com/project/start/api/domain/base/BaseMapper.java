package com.project.start.api.domain.base;

import java.util.List;

import org.springframework.data.domain.Page;

public interface BaseMapper <E extends BaseEntity, D extends BaseDto> {
	
	D convert(E entity);
	
	E convert(D dto);

	List<D> convert(List<E> lista);

	List<E> convertDTO(List<D> lista);
	
	default Page<D> convert(Page<E> pageEntity) { 
		return pageEntity.map(this::convert);
	}

}
