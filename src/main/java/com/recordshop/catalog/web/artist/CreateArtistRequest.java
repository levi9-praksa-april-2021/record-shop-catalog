package com.recordshop.catalog.web.artist;

import javax.validation.constraints.NotBlank;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CreateArtistRequest {
	
	@NotBlank(message = "First name cannot be blank")
	private String firstName;
	@NotBlank(message = "Last name cannot be blank")
	private String lastName;
    
}
