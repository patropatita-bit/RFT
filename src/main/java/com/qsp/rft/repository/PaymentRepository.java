package com.qsp.rft.repository;

import java.math.BigDecimal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.qsp.rft.entity.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

	@Query("SELECT SUM(p.amount) FROM  Payment p")
  BigDecimal getTotalPayment();	
}