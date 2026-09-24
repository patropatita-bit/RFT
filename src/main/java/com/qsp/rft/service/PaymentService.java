package com.qsp.rft.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.qsp.rft.entity.Payment;
import com.qsp.rft.repository.PaymentRepository;

@Service
public class PaymentService {

	@Autowired
	private PaymentRepository paymentRepository;
	
	public Payment savePayment(Payment payment) {
		
		return paymentRepository.save(payment);
		
	}
	 
	public List<Payment> getAllPayments(){
		return paymentRepository.findAll();
	}
	
	 public Payment getPaymentById(Long id) {
      return paymentRepository.findById(id).orElse(null);
	 }
	 
	 public Payment updatePayment(Long id,Payment payment) {
		Payment existingPayment= paymentRepository.findById(id).orElse(null);
		 
		if(existingPayment == null) {
			return null;
		}
		existingPayment.setAmount(payment.getAmount());
		existingPayment.setPaymentMode(payment.getPaymentMode());
		existingPayment.setDescription(payment.getDescription());

		    return paymentRepository.save(existingPayment);
		}
	  public void deletePayment(Long id) {
	        paymentRepository.deleteById(id);
	    }
		
	 }


