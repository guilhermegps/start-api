package com.project.start.api.domain.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
public class AccessTokenDto {
	
	private String token;
	private Long expiresIn;
	private String tokenType;

}
