package com.archisacademy.morent.services.concretes;

import com.archisacademy.morent.dtos.requests.ConfirmPaymentRequest;
import com.archisacademy.morent.dtos.requests.NotificationRequest;
import com.archisacademy.morent.dtos.requests.PaymentRefundRequest;
import com.archisacademy.morent.dtos.requests.PaymentRequest;
import com.archisacademy.morent.dtos.responses.PaymentHistoryRespone;
import com.archisacademy.morent.dtos.responses.PaymentRefundResponse;
import com.archisacademy.morent.dtos.responses.PaymentResponse;
import com.archisacademy.morent.entities.Booking;
import com.archisacademy.morent.entities.Payment;
import com.archisacademy.morent.entities.User;
import com.archisacademy.morent.exceptions.PaymentNotFoundException;
import com.archisacademy.morent.exceptions.book.BookingNotFoundException;
import com.archisacademy.morent.exceptions.UserNotFoundException;
import com.archisacademy.morent.repositories.BookingRepository;
import com.archisacademy.morent.repositories.PaymentRepository;
import com.archisacademy.morent.repositories.UserRepository;
import com.archisacademy.morent.services.abstracts.PaymentService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;

    private final BookingRepository bookingRepository;
    private final ModelMapper modelMapper;
    private final NotificationServiceImpl notificationService;
    private final UserRepository userRepository;

    @Override
    @Transactional(propagation= Propagation.REQUIRED)
    public PaymentResponse initiatePayment(PaymentRequest paymentRequest) {

        Optional<User> isUserHere = Optional.ofNullable(userRepository.findByUserId(paymentRequest.getUserId()).orElseThrow(() -> new UserNotFoundException("User not found!!!")));
        Optional<Booking> byBookingIdEquals = bookingRepository.findByBookingIdEquals(paymentRequest.getBookingId());
        if (byBookingIdEquals.isPresent()) {
            Payment payment = new Payment();
            payment.setPaymentId(paymentRequest.getBookingId());
            payment.setUser(isUserHere.get());
            payment.setAmount(paymentRequest.getAmount());
            payment.setBooking(byBookingIdEquals.get());
            payment.setPaymentMethod(paymentRequest.getPaymentMethod());
            payment.setStatus("PENDING");
            Payment save = paymentRepository.save(payment);
            PaymentResponse paymentResponse = modelMapper.map(save, PaymentResponse.class);
            paymentResponse.setMessage("Payment initiated");
            return paymentResponse;
        }else {
            throw new BookingNotFoundException("Booking not found with this id: " + paymentRequest.getBookingId());
        }
    }


    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public PaymentResponse confirmPayment(ConfirmPaymentRequest confirmPaymentRequest) {
        Optional<Payment> optionalPayment = paymentRepository.findByPaymentId(confirmPaymentRequest.getPaymentId());

        if (optionalPayment.isEmpty()) {
            throw new PaymentNotFoundException("Payment not found with ID: " + confirmPaymentRequest.getPaymentId());
        }

        Payment payment = optionalPayment.get();
        payment.setStatus(confirmPaymentRequest.getStatus());
        paymentRepository.save(payment);

        return new PaymentResponse("Payment confirmed");
    }

    @Override
    public PaymentRefundResponse refundPayment(PaymentRefundRequest paymentRefundRequest) {

        try {

            Booking booking = bookingRepository.findByBookingId(paymentRefundRequest.getBookingId())
                    .orElseThrow(() -> new BookingNotFoundException("Booking not found with this id: " + paymentRefundRequest.getBookingId()));

            Payment payment = paymentRepository.findByBookingId(booking.getId())
                    .orElseThrow(() -> new PaymentNotFoundException("Payment not found with this id: " + booking.getId()));

            if (payment.getAmount().compareTo(paymentRefundRequest.getAmount()) >= 0) {
                payment.setAmount(paymentRefundRequest.getAmount());
                paymentRepository.save(payment);
                String message = "Your refund processed successfully";
                NotificationRequest notificationRequest = new NotificationRequest(booking.getUser().getUserId(),message);
                notificationService.createNotification(notificationRequest);
                return new PaymentRefundResponse("Refund processed successfully");
            } else {
                return new PaymentRefundResponse("Refund amount exceeds payment amount");
            }

        }catch (Exception e) {
            throw new BookingNotFoundException("Booking not found with this id: " + paymentRefundRequest.getBookingId());
        }

    }

    @Override
    public List<PaymentHistoryRespone> userPaymentHistory(UUID userId){
       User user = userRepository.findByUserId(userId).orElseThrow(()-> new UserNotFoundException("User not found"));

       List<Payment> payments = user.getPayments();
       if (payments.isEmpty()){
           throw new RuntimeException("No payments found!!!");
       }
       List<PaymentHistoryRespone> paymentHistoryRespones = payments.stream().map(payment -> modelMapper.map(payment, PaymentHistoryRespone.class)).collect(Collectors.toList());
       return paymentHistoryRespones;
    }

}