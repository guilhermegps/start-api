package com.project.start.api.controllers.base;

import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.project.start.api.commons.messages.MessageManager;
import com.project.start.api.domain.base.BaseDto;
import com.project.start.api.domain.base.BaseEntity;
import com.project.start.api.services.base.BaseService;

@RestControllerAdvice
public abstract class BaseController<E extends BaseEntity, D extends BaseDto> {
	
	protected abstract BaseService<E, D> getService();
	
	protected MessageManager getMessages() {
		return getService().getMessages();
	}
	
	public ResponseEntity<D> obter(Long codigo){
		var entity = getService().obter(codigo);
		
		return encapsularResposta(entity);
	}
	
	protected ResponseEntity<String> getSucesso(){		  
		var msg = getMessages().get("sys.info.sucesso");
		  
	    return ResponseEntity.ok(msg);
	}
	
	protected ResponseEntity<D> encapsularResposta(E entity){
		return encapsularResposta(getService().convert(entity));
	}
	
	protected ResponseEntity<D> encapsularResposta(D dto){
		return Optional.ofNullable(dto).map(ResponseEntity::ok).orElseThrow(NoSuchElementException::new);
	}

}
