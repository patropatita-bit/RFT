package com.qsp.rft.repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.qsp.rft.entity.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

    // ALL TIME
    @Query("SELECT SUM(p.amount) FROM Payment p")
    BigDecimal getTotalPayment();

    // ONE DATE (dashboard)
    @Query("SELECT SUM(p.amount) FROM Payment p WHERE p.paymentDate = :date")
    BigDecimal getTotalPaymentByDate(@Param("date") LocalDate date);

    @Query("SELECT p.paymentMode, SUM(p.amount) FROM Payment p WHERE p.paymentDate = :date GROUP BY p.paymentMode")
    List<Object[]> getIncomeByMode(@Param("date") LocalDate date);

    // DATE RANGE (report)
    @Query("SELECT SUM(p.amount) FROM Payment p WHERE p.paymentDate BETWEEN :start AND :end")
    BigDecimal getTotalPaymentBetween(@Param("start") LocalDate start, @Param("end") LocalDate end);

    @Query("SELECT p.paymentMode, SUM(p.amount) FROM Payment p WHERE p.paymentDate BETWEEN :start AND :end GROUP BY p.paymentMode")
    List<Object[]> getIncomeByModeBetween(@Param("start") LocalDate start, @Param("end") LocalDate end);
}