package com.qsp.rft.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DashboardResponse {

	private LocalDate date;
	private BigDecimal totalIncome;
	private BigDecimal totalExpense;
	private BigDecimal profit;

	
}
