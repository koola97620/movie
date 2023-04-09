package com.example.movie.pay.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaymentAmount {
    @Column(name = "total_amount", nullable = false)
    @ColumnDefault("0")
    private Integer total;
    @Column(name = "tax_free", nullable = false)
    @ColumnDefault("0")
    private Integer tax_free;
    @Column(name = "vat", nullable = false)
    @ColumnDefault("0")
    private Integer vat;
    @Column(name = "point", nullable = false)
    @ColumnDefault("0")
    private Integer point;
    @Column(name = "discount", nullable = false)
    @ColumnDefault("0")
    private Integer discount;
    @Column(name = "green_deposit", nullable = false)
    @ColumnDefault("0")
    private Integer greenDeposit;

    public static PaymentAmount of(Amount amount) {
        if (Objects.isNull(amount)) {
            return PaymentAmount.builder().total(0).tax_free(0).vat(0).point(0).discount(0).greenDeposit(0).build();
        }
        return PaymentAmount.builder()
                .total(amount.total())
                .tax_free(amount.tax_free())
                .vat(amount.vat())
                .point(amount.point())
                .discount(amount.discount())
                .greenDeposit(amount.green_deposit())
                .build();
    }
}
