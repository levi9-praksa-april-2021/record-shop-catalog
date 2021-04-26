package com.recordshop.catalog.web.genre;

import javax.validation.constraints.NotBlank;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CreateGenreRequest {
	
	@NotBlank(message = "Name cannot be blank")
	private String name;
}
