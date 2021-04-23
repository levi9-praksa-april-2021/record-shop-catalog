package com.recordshop.catalog.web.artist;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class CreateArtistRequest {
	
	@NotBlank(message = "First name cannot be blank")
	private final String firstName;
	@NotBlank(message = "Last name cannot be blank")
	private final String lastName;
    
}
