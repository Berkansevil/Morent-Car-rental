package com.archisacademy.morent.controllers;

import com.archisacademy.morent.dtos.requests.ConfirmPaymentRequest;
import com.archisacademy.morent.dtos.requests.PaymentRefundRequest;
import com.archisacademy.morent.dtos.requests.PaymentRequest;
import com.archisacademy.morent.dtos.responses.PaymentRefundResponse;
import com.archisacademy.morent.dtos.responses.PaymentResponse;
import com.archisacademy.morent.services.abstracts.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/payments")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping("/initiate")
    public ResponseEntity<PaymentResponse> initiatePayment(@RequestBody PaymentRequest paymentRequest) {
        PaymentResponse paymentResponse = paymentService.initiatePayment(paymentRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(paymentResponse);
    }

    @PostMapping("/refund")
    public ResponseEntity<PaymentRefundResponse> refundPayment(@RequestBody PaymentRefundRequest paymentRefundRequest) {
        PaymentRefundResponse paymentRefundResponse = paymentService.refundPayment(paymentRefundRequest);
        return ResponseEntity.status(HttpStatus.OK).body(paymentRefundResponse);
    }


    @PostMapping("/confirm")
    public ResponseEntity<PaymentResponse> confirmPayment(@RequestBody ConfirmPaymentRequest confirmPaymentRequest) {
        PaymentResponse paymentResponse = paymentService.confirmPayment(confirmPaymentRequest);
        return ResponseEntity.ok(paymentResponse);
    }
}
