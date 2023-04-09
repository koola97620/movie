package com.example.movie.pay.app;

import com.example.movie.pay.domain.KakaopayPaymentStatus;
import com.example.movie.pay.domain.PaymentBase;
import com.example.movie.pay.domain.PaymentBaseRepository;
import com.example.movie.pay.domain.PgType;
import com.example.movie.pay.exception.KakaopayPaymentExceptionMessage;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;

@Service
public class PaymentBaseService {

    private final PaymentBaseRepository paymentBaseRepository;

    public PaymentBaseService(PaymentBaseRepository paymentBaseRepository) {
        this.paymentBaseRepository = paymentBaseRepository;
    }

    public PaymentBase create(PaymentRequest req) {
        return paymentBaseRepository.save(
                PaymentBase.builder()
                        .pgType(PgType.KAKAOPAY)
                        .status(KakaopayPaymentStatus.CREATED)
                        .orderId(req.orderId())
                        .userId(req.userId())
                        .payAmount(req.amount())
                        .build()
        );
    }

    public PaymentBase findByPayId(Long payId) {
        return paymentBaseRepository.findByPayId(payId)
                .orElseThrow(() -> new EntityNotFoundException(KakaopayPaymentExceptionMessage.NOT_FOUND_PAYMENT_PAY_ID.formatted(payId)));
    }

    @Transactional
    public void updateStatus(Long payId, KakaopayPaymentStatus status) {
        PaymentBase payment = paymentBaseRepository.findByPayId(payId)
                .orElseThrow(() -> new EntityNotFoundException(KakaopayPaymentExceptionMessage.NOT_FOUND_PAYMENT_PAY_ID.formatted(payId)));
        payment.updateStatus(status, LocalDateTime.now());
    }
}
