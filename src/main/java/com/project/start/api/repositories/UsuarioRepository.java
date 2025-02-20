package com.project.start.api.repositories;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.project.start.api.domain.Usuario;
import com.project.start.api.repositories.base.BaseRepository;

@Repository
public interface UsuarioRepository extends BaseRepository<Usuario> {

	Optional<Usuario> findOneByLoginAndAtivo(String login, Boolean true1);

}
