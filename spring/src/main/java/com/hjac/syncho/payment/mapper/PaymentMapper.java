package com.hjac.syncho.payment.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.hjac.syncho.payment.model.Payment;

@Mapper
public interface PaymentMapper {

	//결제 추가
   int insertPayment(Payment payment);
   //조회
   List<Payment> selectSubscriptionByUserId(int userId);
	

}
