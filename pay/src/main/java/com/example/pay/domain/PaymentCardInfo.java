package com.example.pay.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.Objects;


@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(catalog = "paydb", name = "payment_card_info")
public class PaymentCardInfo extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "purchase_corp")
    private String purchaseCorp;
    @Column(name = "purchase_corp_code")
    private String purchaseCorpCode;
    @Column(name = "issuer_corp")
    private String issuerCorp;
    @Column(name = "issuer_corp_code")
    private String issuerCorpCode;
    @Column(name = "kakaopay_puchase_corp")
    private String kakaopayPuchaseCorp;
    @Column(name = "kakaopay_puchase_corp_code")
    private String kakaopayPuchaseCorpCode;
    @Column(name = "kakaopay_issuer_corp")
    private String kakaopayIssuerCorp;
    @Column(name = "kakaopay_issuer_corp_code")
    private String kakaopayIssuerCorpCode;
    @Column(name = "bin")
    private String bin;
    @Column(name = "card_type")
    private String cardType;
    @Column(name = "install_month", nullable = false)
    @ColumnDefault("0")
    private Integer installMonth;
    @Column(name = "approve_id")
    private String approveId;
    @Column(name = "card_mid")
    private String cardMid;
    @Column(name = "interest_free_install")
    @ColumnDefault("'N'")
    private String interestFreeInstall;
    @Column(name = "card_item_code")
    private String cardItemCode;


    public static PaymentCardInfo of(CardInfo cardInfo) {
        if (Objects.isNull(cardInfo)) {
            return null;
        }
        return PaymentCardInfo.builder()
                .purchaseCorp(cardInfo.purchase_corp())
                .purchaseCorpCode(cardInfo.purchase_corp_code())
                .issuerCorp(cardInfo.issuer_corp())
                .issuerCorpCode(cardInfo.issuer_corp_code())
                .kakaopayPuchaseCorp(cardInfo.kakaopay_puchase_corp())
                .kakaopayPuchaseCorpCode(cardInfo.kakaopay_puchase_corp_code())
                .kakaopayIssuerCorp(cardInfo.kakaopay_issuer_corp())
                .kakaopayIssuerCorpCode(cardInfo.kakaopay_issuer_corp_code())
                .bin(cardInfo.bin())
                .cardType(cardInfo.card_type())
                .installMonth(cardInfo.getNumberInstallMonths())
                .approveId(cardInfo.approve_id())
                .cardMid(cardInfo.card_mid())
                .interestFreeInstall(cardInfo.interest_free_install())
                .cardItemCode(cardInfo.card_item_code())
                .build();
    }
}
