package com.qsp.rft.service;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qsp.rft.repository.ExpenseRepository;
import com.qsp.rft.repository.PaymentRepository;

@Service
public class ReportService {

	@Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private ExpenseRepository expenseRepository;


    public BigDecimal getProfit() {

        BigDecimal totalPayment = paymentRepository.getTotalPayment();
        BigDecimal totalExpense = expenseRepository.getTotalExpense();

        return totalPayment.subtract(totalExpense);
    }
}
