package com.qsp.rft.repository;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.qsp.rft.entity.Expense;

import jakarta.persistence.Id;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {

	@Query("SELECT SUM(e.amount) FROM Expense e")
	BigDecimal getTotalExpense();
	
	@Query("SELECT SUM(e.amount) FROM Expense e WHERE e.expenseDate = :date")
	BigDecimal getTotalExpenseByDate(@Param("date") LocalDate date);
	
	
}