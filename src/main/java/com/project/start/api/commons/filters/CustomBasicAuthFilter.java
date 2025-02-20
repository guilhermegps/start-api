package com.project.start.api.commons.filters;

import java.io.IOException;
import java.util.Base64;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.project.start.api.services.UsuarioService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CustomBasicAuthFilter extends OncePerRequestFilter {
	
	private final UsuarioService usuarioService;

    private static final int BASIC_LENGTH = 6;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        var headerAuthorization = request.getHeader("Authorization");

        if (headerAuthorization == null || !headerAuthorization.startsWith("Basic ")) {
            filterChain.doFilter(request, response);
            return;
        }
        var basicToken = headerAuthorization.substring(BASIC_LENGTH);
        var basicTokenBytes = Base64.getDecoder().decode(basicToken);
        var basicTokenValue = new String(basicTokenBytes);
        var basicAuthsSplit = basicTokenValue.split(":");
        
        var userOpt = usuarioService.findByLogin(basicAuthsSplit[0]);
        userOpt.ifPresent(user -> {
        	if (user.getSenha().equals(basicAuthsSplit[1])) {
        		var authToken = new UsernamePasswordAuthenticationToken(user, null, null);
        		SecurityContextHolder.getContext().setAuthentication(authToken);
        	}        	
        });
        
        filterChain.doFilter(request, response);
    }
}