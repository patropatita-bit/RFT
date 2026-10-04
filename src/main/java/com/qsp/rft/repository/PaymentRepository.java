package com.qsp.rft.repository;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.qsp.rft.entity.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

	@Query("SELECT SUM(p.amount) FROM  Payment p")
  BigDecimal getTotalPayment();	
	
	@Query("SELECT SUM(p.amount) FROM Payment p WHERE p.paymentDate = :date")
	BigDecimal getTotalPaymentByDate(@Param("date") LocalDate date);
}