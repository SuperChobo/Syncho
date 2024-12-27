package com.hjac.syncho.payment.service;

import java.util.List;

import com.hjac.syncho.payment.model.Payment;

public interface PaymentService {
	
	boolean processPayment(Payment payment, String planType);
	List<Payment> getPaymentsByUserId(int userId);

}
