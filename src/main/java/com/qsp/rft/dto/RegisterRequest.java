package com.qsp.rft.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record RegisterRequest(
		 @NotBlank(message = "Name is required")
		    @Size(max = 60, message = "Name is too long")
		    String name,

		    @NotBlank(message = "Restaurant name is required")
		    @Size(max = 80, message = "Restaurant name is too long")
		    String restaurantName,

		    @NotBlank(message = "Mobile number is required")
		    @Pattern(regexp = "^[6-9]\\d{9}$", message = "Enter a valid 10-digit mobile number")
		    String phone) {
	  
}
