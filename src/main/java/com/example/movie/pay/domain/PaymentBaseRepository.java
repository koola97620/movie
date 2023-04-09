package com.example.movie.pay.domain;


import org.springframework.data.repository.Repository;

import java.util.Optional;

public interface PaymentBaseRepository extends Repository<PaymentBase, Long> {
    PaymentBase save(PaymentBase paymentBase);
    Optional<PaymentBase> findByPayId(Long payId);
}
