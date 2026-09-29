package com.qsp.rft.repository;

import java.math.BigDecimal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.qsp.rft.entity.Expense;

import jakarta.persistence.Id;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {

	@Query("SELECT SUM(e.amount) FROM Expense e")
	BigDecimal getTotalExpense();
}
