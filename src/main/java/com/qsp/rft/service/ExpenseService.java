package com.qsp.rft.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qsp.rft.entity.Expense;
import com.qsp.rft.repository.ExpenseRepository;

@Service
public class ExpenseService {

	
	@Autowired
	private ExpenseRepository expenseRepository;
	
	  public Expense saveExpense(Expense expense) {
	        return expenseRepository.save(expense);
	    }
	 
	    public List<Expense> getAllExpenses() {
	        return expenseRepository.findAll();
	    }

	    // READ BY ID
	    public Expense getExpenseById(Long id) {
	        return expenseRepository.findById(id).orElse(null);
	    }

	    // UPDATE
	    public Expense updateExpense(Long id, Expense expense) {

	        Expense existingExpense =
	                expenseRepository.findById(id).orElse(null);

	        if (existingExpense == null) {
	            return null;
	        }

	        existingExpense.setAmount(expense.getAmount());
	        existingExpense.setExpenseType(expense.getExpenseType());
	        existingExpense.setExpenseDate(expense.getExpenseDate());
	        existingExpense.setDescription(expense.getDescription());

	        return expenseRepository.save(existingExpense);
	    }

	    // DELETE
	    public void deleteExpense(Long id) {
	        expenseRepository.deleteById(id);
	    }
	    
	    public BigDecimal getTotalExpense() {
	        return expenseRepository.getTotalExpense();
	    }
}
