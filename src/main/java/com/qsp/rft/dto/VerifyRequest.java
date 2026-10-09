package com.qsp.rft.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record VerifyRequest(
		  @NotBlank(message = "Mobile number is required")
		    @Pattern(regexp = "^[6-9]\\d{9}$", message = "Enter a valid 10-digit mobile number")
		    String phone,

		    @NotBlank(message = "OTP is required")
		    @Pattern(regexp = "^\\d{6}$", message = "OTP must be 6 digits")
		    String otp) {}