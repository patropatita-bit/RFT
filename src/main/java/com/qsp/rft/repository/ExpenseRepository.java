package com.qsp.rft.repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.qsp.rft.entity.Expense;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {

    // ALL TIME
    @Query("SELECT SUM(e.amount) FROM Expense e")
    BigDecimal getTotalExpense();

    // ONE DATE (dashboard)
    @Query("SELECT SUM(e.amount) FROM Expense e WHERE e.expenseDate = :date")
    BigDecimal getTotalExpenseByDate(@Param("date") LocalDate date);

    @Query("SELECT e.expenseType, SUM(e.amount) FROM Expense e WHERE e.expenseDate = :date GROUP BY e.expenseType")
    List<Object[]> getExpenseByType(@Param("date") LocalDate date);

    // DATE RANGE (report)
    @Query("SELECT SUM(e.amount) FROM Expense e WHERE e.expenseDate BETWEEN :start AND :end")
    BigDecimal getTotalExpenseBetween(@Param("start") LocalDate start, @Param("end") LocalDate end);

    @Query("SELECT e.expenseType, SUM(e.amount) FROM Expense e WHERE e.expenseDate BETWEEN :start AND :end GROUP BY e.expenseType")
    List<Object[]> getExpenseByTypeBetween(@Param("start") LocalDate start, @Param("end") LocalDate end);
}