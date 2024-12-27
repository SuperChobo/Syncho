package com.hjac.syncho.payment.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hjac.syncho.payment.mapper.PaymentMapper;
import com.hjac.syncho.payment.model.Payment;

@Service
public class PaymentServiceImpl implements PaymentService {

	
	@Autowired
    private PaymentMapper paymentMapper;
	
	@Override
	public boolean processPayment(Payment payment, String planType) {
		
		payment.setPaymentStatus("SUCCESS");
		payment.setPaymentDate(String.valueOf(java.time.LocalDateTime.now()));
		payment.setTransactionId(java.util.UUID.randomUUID().toString());
		
		int result=paymentMapper.insertPayment(payment);
		return result>0;
	}

	@Override
	public List<Payment> getPaymentsByUserId(int userId) {
		
		return paymentMapper.selectSubscriptionByUserId(userId);
	}

}
