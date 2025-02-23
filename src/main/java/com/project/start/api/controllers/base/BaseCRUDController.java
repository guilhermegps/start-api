package com.project.start.api.controllers.base;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.project.start.api.domain.base.BaseDto;
import com.project.start.api.domain.base.BaseEntity;
import com.project.start.api.services.base.BaseCRUDService;

@RestControllerAdvice
public abstract class BaseCRUDController<E extends BaseEntity, D extends BaseDto> extends BaseController<E, D> {

	@Override
	protected abstract BaseCRUDService<E, D> getService();
	
	protected ResponseEntity<D> create(D input){
		  var entity = getService().create(input);
		  
	    return ResponseEntity.ok(getService().getMapper().convert(entity));
	}

	protected ResponseEntity<String> update(Long codigo, D input) {
		getService().update(codigo, input);

	    return getSucess();
	}

	protected ResponseEntity<String> disable(Long codigo) {
		getService().disable(codigo);

	    return getSucess();
	}

	protected ResponseEntity<String> remove(Long codigo) {
		getService().remove(codigo);

	    return getSucess();
	}
	
}
