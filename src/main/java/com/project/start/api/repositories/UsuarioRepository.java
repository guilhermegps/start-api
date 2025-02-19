package com.project.start.api.repositories;

import org.springframework.stereotype.Repository;

import com.project.start.api.domain.Usuario;
import com.project.start.api.repositories.base.BaseRepository;

@Repository
public interface UsuarioRepository extends BaseRepository<Usuario> {

}
