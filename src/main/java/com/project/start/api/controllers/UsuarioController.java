package com.project.start.api.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.start.api.controllers.base.BaseCRUDController;
import com.project.start.api.domain.Usuario;
import com.project.start.api.domain.dtos.UsuarioDto;
import com.project.start.api.services.UsuarioService;

import jakarta.validation.Valid;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Validated
@RestController
@RequestMapping("usuario")
@RequiredArgsConstructor
public class UsuarioController extends BaseCRUDController<Usuario, UsuarioDto> {

	@Getter
    private final UsuarioService service;

    @GetMapping
    public ResponseEntity<UsuarioDto> usuarioSessao() {
    	var user = service.usuarioSessao();
        return toResponse(user);
    }

    @GetMapping("listar")
    public ResponseEntity<List<UsuarioDto>> listarTodos() {
    	var lista = service.findAll();
        return toResponse(lista);
    }

    @GetMapping("{cod}")
    public ResponseEntity<UsuarioDto> detalhar(@PathVariable Long cod) {
        return detail(cod);
    }

    @PostMapping
    public ResponseEntity<UsuarioDto> criar(@RequestBody @Valid final UsuarioDto input) {
        return create(input);
    }

    @PutMapping("{cod}")
    public ResponseEntity<String> alterar(@PathVariable Long cod, 
    		@RequestBody @Valid final UsuarioDto input) {
        return update(cod, input);
    }

    @DeleteMapping("{cod}")
    public ResponseEntity<String> inatiar(@PathVariable Long cod) {
        return disable(cod);
    }
}
