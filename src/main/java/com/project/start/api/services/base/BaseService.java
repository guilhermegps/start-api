package com.project.start.api.services.base;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import com.project.start.api.commons.messages.MessageManager;
import com.project.start.api.commons.support.exceptions.NotFoundException;
import com.project.start.api.domain.base.BaseDto;
import com.project.start.api.domain.base.BaseEntity;
import com.project.start.api.domain.base.BaseMapper;
import com.project.start.api.domain.enumerations.TipoEventoEnum;
import com.project.start.api.repositories.base.BaseRepository;
import com.project.start.api.services.EventoService;

import lombok.Getter;

@Validated
@Component
public abstract class BaseService<E extends BaseEntity, D extends BaseDto> {

	protected abstract BaseRepository<E> getRepository();
    public abstract BaseMapper<E, D> getMapper();
    public abstract String getEntityName();

	@Getter
	@Autowired
	protected MessageManager messages;
	@Lazy
	@Autowired
	protected EventoService eventoService;
	
	public E obter(Long codigo){
		var entity = (codigo!=null) ? getRepository().findOneByCodigoAndAtivo(codigo, Boolean.TRUE)
				.orElseThrow(() -> new NotFoundException(getEntityName(), codigo)) : null;
		
		return entity;
	}

	public Page<E> findAll(Pageable pageable) {
		return getRepository().findAll(pageable);
	}

	public List<E> findAll() {
		return getRepository().findAll();
	}

	public List<E> findAll(Sort sort) {
		return getRepository().findAll(sort);
	}

	public Optional<E> findById(UUID id) {
		return getRepository().findById(id);
	}

	public List<E> findAllById(Iterable<UUID> ids) {
		return getRepository().findAllById(ids);
	}

	public List<E> saveAll(Iterable<E> entities) {
		return getRepository().saveAll(entities);
	}

	public boolean existsById(UUID id) {
		return getRepository().existsById(id);
	}

	public void flush() {
		getRepository().flush();
	}

	public Page<E> findAll(Example<E> example, Pageable pageable) {
		return getRepository().findAll(example, pageable);
	}

	public List<E> findAll(Example<E> example) {
		return getRepository().findAll(example);
	}

	public boolean exists(Example<E> example) {
		return getRepository().exists(example);
	}

	public List<E> findAll(Example<E> example, Sort sort) {
		return getRepository().findAll(example, sort);
	}

    @Transactional(readOnly = true)
	public D convert(E entity) {
    	if(entity!=null)
    		entity = getRepository().getReferenceById(entity.getId());

		return getMapper().convert(entity);
	}

    @Transactional(readOnly = true)
    public List<D> convert(List<E> lista) {
    	if(lista!=null)
    		lista = lista.stream()
    		.map(entity -> getRepository().getReferenceById(entity.getId()))
    		.toList();

		return getMapper().convert(lista);
	}

    @Transactional(readOnly = true)
    public Page<D> convert(Page<E> page) {
    	if(page!=null)
    		page = page.map(entity -> getRepository().getReferenceById(entity.getId()));

		return getMapper().convert(page);
	}
	
    public void regEvento(TipoEventoEnum tipo, String keyMsg, Object... params) {
		eventoService.registrar(tipo, getMessages().get(keyMsg, params));
	}

}
