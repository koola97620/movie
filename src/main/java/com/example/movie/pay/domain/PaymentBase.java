package com.example.movie.pay.domain;

import com.example.movie.pay.app.PaymentResultResponse;
import com.example.movie.pay.infra.converter.KakaopayPaymentStatusConverter;
import com.example.movie.pay.infra.converter.PgTypeConverter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(catalog = "paydb", name = "payment_base")
public class PaymentBase extends BaseEntity {
    @Id
    @Column(name = "id")
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "KAKAOPAY_PAYID_GENERATOR")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long payId;
    private String tid;

    @Column(name = "pg_type")
    @Convert(converter = PgTypeConverter.class)
    private PgType pgType;

    @Convert(converter = KakaopayPaymentStatusConverter.class)
    private KakaopayPaymentStatus status;
    @Column(name = "order_id")
    private Long orderId;
    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "pay_amount")
    private Integer payAmount;
    @OneToMany(mappedBy = "payment", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Set<PaymentResult> paymentResults = new HashSet<>();

    @Column(name = "upd_dt")
    private LocalDateTime updDt;

    public void updateTid(String tid) {
        this.tid = tid;
    }

    public void updatePayResult(KakaopayPaymentStatus status, PaymentResultResponse response) {
        this.status = status;
        if (Objects.isNull(this.paymentResults)) {
            this.paymentResults = new HashSet<>();
        }
        this.paymentResults.add(PaymentResult.success(this, response));
    }

    public void updateStatus(KakaopayPaymentStatus status, LocalDateTime currentTime) {
        this.status = status;
        this.updDt = currentTime;
    }
}
