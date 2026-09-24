package com.qsp.rft.controller;

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

import com.qsp.rft.entity.Payment;
import com.qsp.rft.service.PaymentService;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

	@Autowired
	private PaymentService paymentService;
	
	 @PostMapping
	    public Payment savePayment(@RequestBody Payment payment) {
	        return paymentService.savePayment(payment);
	    }
	 
	 @GetMapping
	 public List<Payment> getAllPayments(){
		 return paymentService.getAllPayments();
	 }
	 
	 @GetMapping("/{id}")
	 public Payment getPaymentById(@PathVariable Long id) {
		
		 return paymentService.getPaymentById(id);
	 }
	 
	 @PutMapping("/{id}")
	 public Payment updatePayment(@PathVariable Long id, @RequestBody Payment payment) {
	     return paymentService.updatePayment(id, payment);
	 }
	 @DeleteMapping("/{id}")
	    public void deletePayment(@PathVariable Long id) {
	        paymentService.deletePayment(id);
	    }
}
