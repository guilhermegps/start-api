package com.project.start.api.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.start.api.domain.dtos.UsuarioDto;
import com.project.start.api.domain.mappers.UsuarioMapper;
import com.project.start.api.services.UsuarioService;

import lombok.RequiredArgsConstructor;

@Validated
@RestController
@RequestMapping("/usuario")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService service;
    private final UsuarioMapper mapper;

    @GetMapping("/me")
    public ResponseEntity<UsuarioDto> usuarioSessao() {
    	var user = service.usuarioSessao();
        return ResponseEntity.ok(mapper.convert(user));
    }

}
