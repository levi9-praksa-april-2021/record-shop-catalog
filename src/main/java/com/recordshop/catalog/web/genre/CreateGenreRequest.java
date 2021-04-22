package com.recordshop.catalog.web.genre;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class CreateGenreRequest {
	
	@NotBlank(message = "Name cannot be blank")
	private final String name;
}
