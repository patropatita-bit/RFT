package com.qsp.rft.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity
public class Expense {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
        
	@NotNull(message = "Amount is required")
	@Positive(message = "Amount must be greater than 0")
	    private BigDecimal amount;
	@NotBlank(message = "Expense type is required")
	    private String expenseType;
	    private String description; 
	    @NotNull(message = "Expense date is required")
	    private LocalDate expenseDate;
}
