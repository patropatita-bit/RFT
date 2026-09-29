package com.qsp.rft.controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qsp.rft.entity.Expense;
import com.qsp.rft.service.ExpenseService;

@RestController
@RequestMapping("/api/expenses")
public class ExpenseController {

	@Autowired
 private 	ExpenseService expenseService;
	
	
	@PostMapping
	public Expense saveExpense(@RequestBody Expense expense) {
		 return expenseService.saveExpense(expense);
	}
	
	  // READ ALL
    @GetMapping
    public List<Expense> getAllExpenses() {
        return expenseService.getAllExpenses();
    }
    @GetMapping("/{id}")
    public Expense getExpenseById(@PathVariable Long id) {
        return expenseService.getExpenseById(id);
    }
    
    @PutMapping("/{id}")
    public Expense updateExpense(@PathVariable Long id,@RequestBody Expense expense) {


        return expenseService.updateExpense(id, expense);
    }
    
    @DeleteMapping("/{id}")
    public void deleteExpense(@PathVariable Long id) {
        expenseService.deleteExpense(id);
    }
    
    @GetMapping("/total")
    public BigDecimal getTotalExpense() {
        return expenseService.getTotalExpense();
    }
}
