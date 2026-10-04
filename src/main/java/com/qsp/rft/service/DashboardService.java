package com.qsp.rft.service;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qsp.rft.dto.DashboardResponse;
import com.qsp.rft.repository.ExpenseRepository;
import com.qsp.rft.repository.PaymentRepository;

@Service
public class DashboardService {

	 @Autowired
	    private PaymentRepository paymentRepository;

	    @Autowired
	    private ExpenseRepository expenseRepository;
	    
	    public DashboardResponse getDashboard(LocalDate date) {
	    	   BigDecimal totalIncome = paymentRepository.getTotalPaymentByDate(date);
	           BigDecimal totalExpense = expenseRepository.getTotalExpenseByDate(date);

	           if (totalIncome == null) {
	               totalIncome = BigDecimal.ZERO;
	           }
	           if (totalExpense == null) {
	               totalExpense = BigDecimal.ZERO;
	           }

	           BigDecimal profit = totalIncome.subtract(totalExpense);

	           return new DashboardResponse(date, totalIncome, totalExpense, profit);
	    }
}
