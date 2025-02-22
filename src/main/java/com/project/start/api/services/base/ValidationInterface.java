package com.project.start.api.services.base;


public interface ValidationInterface<D> {
	
    public void create(D dto);

    public void update(Long id, D dto);

}
