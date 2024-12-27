package com.hjac.syncho.payment.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hjac.syncho.payment.model.Payment;
import com.hjac.syncho.payment.service.PaymentService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
@RequestMapping("/payments")
public class PaymentController {

	
	@Autowired
	private PaymentService paymentService;
	
	//월간 결제 처리
	@PostMapping("/monthly")
	public ResponseEntity<String> processMonthlyPayment(@RequestBody Payment payment) {
		
		boolean isProcessed=paymentService.processPayment(payment, "MONTHLY");
		
		return isProcessed ? 
	               ResponseEntity.ok("Monthly payment processed successfully.") : 
	               ResponseEntity.badRequest().body("Payment processing failed.");
	}
	//연간결제
	@PostMapping("/yearly")
	public ResponseEntity<String> processYearlyPaymen(@RequestBody Payment payment) {
		boolean isProcessed=paymentService.processPayment(payment, "MONTHLY");
		
		return isProcessed ? 
	               ResponseEntity.ok("Yearly payment processed successfully.") : 
	               ResponseEntity.badRequest().body("Payment processing failed.");
	}
	//특정 사용자 결제 기록조회
	@GetMapping("/{userId}")
	public ResponseEntity<?> getUserPayments(@PathVariable int userId) {
		
		List<Payment> payments=paymentService.getPaymentsByUserId(userId);
		return payments !=null && !payments.isEmpty() ?
				ResponseEntity.ok(payments):
				ResponseEntity.notFound().build();
		}
	
	
	
}
