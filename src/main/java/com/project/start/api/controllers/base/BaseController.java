package com.project.start.api.controllers.base;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.project.start.api.commons.messages.MessageManager;
import com.project.start.api.domain.base.BaseDto;
import com.project.start.api.domain.base.BaseEntity;
import com.project.start.api.domain.enumerations.TipoEventoEnum;
import com.project.start.api.services.base.BaseService;

@RestControllerAdvice
public abstract class BaseController<E extends BaseEntity, D extends BaseDto> {
	
	protected abstract BaseService<E, D> getService();
	
	protected MessageManager getMessages() {
		return getService().getMessages();
	}
	
	public ResponseEntity<D> detail(Long codigo){
		var entity = getService().obter(codigo);
		
		return toResponse(entity);
	}
	
	protected ResponseEntity<String> getSucess(){		  
		var msg = getMessages().get("sys.info.sucesso");
		  
	    return ResponseEntity.ok(msg);
	}
	
	protected ResponseEntity<D> toResponse(E entity){
		return toResponse(getService().convert(entity));
	}
	
	protected ResponseEntity<D> toResponse(D dto){
		var response = Optional.ofNullable(dto).map(ResponseEntity::ok).orElseThrow(NoSuchElementException::new);
		
		regEvento(TipoEventoEnum.VISUALIZACAO, "log.alert.registro", getService().getEntityName(), dto.getCodigo());
		return response;
	}
	
	protected ResponseEntity<List<D>> toResponse(List<E> lista){
		var response = ResponseEntity.ok(getService().convert(lista));
		
		regEvento(TipoEventoEnum.VISUALIZACAO, "log.alert.listagem", getService().getEntityName());
		return response;
	}
	
	protected ResponseEntity<Page<D>> toResponse(Page<E> page){
		var response = ResponseEntity.ok(getService().convert(page));
		
		regEvento(TipoEventoEnum.VISUALIZACAO, "log.alert.listagem", getService().getEntityName());
		return response;
	}
	
	protected void regEvento(TipoEventoEnum tipo, String keyMsg, Object... params) {
		getService().regEvento(tipo, getMessages().get(keyMsg, params));
	}
}
