package com.example.movie.pay.domain;


import com.example.movie.pay.app.PaymentResultResponse;
import com.example.movie.pay.infra.converter.PaymentActionStatusConverter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(catalog = "paydb", name = "payment_results")
@DynamicInsert
public class PaymentResult extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "payment_base_id")
    private PaymentBase payment;
    @Column(unique = true)
    private String aid;
    @Convert(converter = PaymentActionStatusConverter.class)
    private PaymentActionStatus status;
    @Embedded
    private PaymentAmount paymentAmount;
    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "card_info_id",referencedColumnName = "id")
    private PaymentCardInfo paymentCardInfo;
    private LocalDateTime createdAt;
    private LocalDateTime approvedAt;
    private LocalDateTime canceledAt;

    public static PaymentResult success(PaymentBase paymentBase, PaymentResultResponse response) {
        PaymentAmount amount = PaymentAmount.of(response.amount());
        PaymentCardInfo cardInfo = PaymentCardInfo.of(response.card_info());
        return PaymentResult.builder()
                .payment(paymentBase)
                .aid(response.aid())
                .status(PaymentActionStatus.PAYMENT)
                .paymentAmount(amount)
                .paymentCardInfo(cardInfo)
                .createdAt(response.created_at())
                .approvedAt(response.approved_at())
                .build();
    }
}
