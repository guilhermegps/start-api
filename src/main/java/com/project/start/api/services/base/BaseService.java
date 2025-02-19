package com.project.start.api.services.base;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.project.start.api.domain.base.BaseEntity;
import com.project.start.api.repositories.base.BaseRepository;

public abstract class BaseService<E extends BaseEntity> {

	protected abstract BaseRepository<E> getRepository();

	public E save(E entity) {
		return getRepository().save(entity);
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

}
