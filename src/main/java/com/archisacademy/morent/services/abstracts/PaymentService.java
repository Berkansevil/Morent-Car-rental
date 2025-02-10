package com.archisacademy.morent.services.abstracts;

import com.archisacademy.morent.dtos.requests.ConfirmPaymentRequest;
import com.archisacademy.morent.dtos.requests.PaymentRefundRequest;
import com.archisacademy.morent.dtos.requests.PaymentRequest;
import com.archisacademy.morent.dtos.responses.PaymentHistoryRespone;
import com.archisacademy.morent.dtos.responses.PaymentRefundResponse;
import com.archisacademy.morent.dtos.responses.PaymentResponse;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

public interface PaymentService {
    PaymentResponse initiatePayment(PaymentRequest paymentRequest);

    @Transactional(propagation = Propagation.REQUIRED)

    PaymentResponse confirmPayment(ConfirmPaymentRequest confirmPaymentRequest);

    PaymentRefundResponse refundPayment(PaymentRefundRequest paymentRefundRequest);


    List<PaymentHistoryRespone> userPaymentHistory(UUID userId);
}
