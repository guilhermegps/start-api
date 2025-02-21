package com.project.start.api.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.start.api.domain.dtos.AccessTokenDto;
import com.project.start.api.domain.dtos.LoginDto;
import com.project.start.api.services.AuthService;
import com.project.start.api.services.JwtService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {
	
	private final AuthService service;
	private final JwtService jwtService;
	
	@PostMapping("/login")
    public ResponseEntity<AccessTokenDto> authenticate(@RequestBody LoginDto loginUserDto) {
        var authenticatedUser = service.authenticate(loginUserDto);
        var tokenDto = jwtService.generateToken(authenticatedUser);

        return ResponseEntity.ok(tokenDto);
    }

}
