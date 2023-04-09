package com.example.movie.docs;

import com.epages.restdocs.apispec.ResourceSnippetDetails;
import com.epages.restdocs.apispec.ResourceSnippetParametersBuilder;
import com.epages.restdocs.apispec.RestAssuredRestDocumentationWrapper;
import com.epages.restdocs.apispec.Schema;
import com.example.movie.config.WireMockInitializer;
import com.example.movie.pay.app.PaymentRequest;
import com.example.movie.pay.app.PaymentResponse;
import com.example.movie.pay.domain.PaymentMethodType;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.restdocs.payload.PayloadDocumentation;
import org.springframework.restdocs.snippet.Snippet;
import org.springframework.test.context.ContextConfiguration;

import java.util.Arrays;
import java.util.List;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;


@ContextConfiguration(initializers = WireMockInitializer.class)
public class PaymentRequestApiDocs extends BaseControllerTest {

    @Autowired
    private WireMockServer mockServer;

    private Snippet REQUEST_FIELDS = requestFields(
            fieldWithPath("orderId").type(JsonFieldType.NUMBER).description("주문번호"),
            fieldWithPath("userId").type(JsonFieldType.NUMBER).description("회원ID"),
            fieldWithPath("productName").type(JsonFieldType.STRING).description("상품이름"),
            fieldWithPath("quantity").type(JsonFieldType.NUMBER).description("수량"),
            fieldWithPath("amount").type(JsonFieldType.NUMBER).description("결제금액"),
            fieldWithPath("paymentMethodType").type(JsonFieldType.STRING).description("결제수단(MONEY,CARD)"),
            fieldWithPath("installMonth").type(JsonFieldType.NUMBER).description("할부개월"),
            fieldWithPath("taxFreeAmount").type(JsonFieldType.NUMBER).optional().description("비과세금액")
            );

    private Snippet RESPONSE_FIELDS = responseFields(apiResponseFields())
            .andWithPrefix("resultInfo.",
                    fieldWithPath("payId").type(JsonFieldType.NUMBER).description("결제ID"),
                    fieldWithPath("tid").type(JsonFieldType.STRING).description("결제고유번호"),
                    fieldWithPath("orderId").type(JsonFieldType.NUMBER).description("주문번호"),
                    fieldWithPath("payAmount").type(JsonFieldType.NUMBER).description("금액"),
                    fieldWithPath("pcUrl").type(JsonFieldType.STRING).description("PC URL")
            );

    @DisplayName("단건결제 API")
    @Test
    void single_success() {
        readyPayment("payment-request.json");

        ExtractableResponse<Response> response = requestPayment();

        String rescd = response.jsonPath().getObject("rescd", String.class);
        assertThat(rescd).isEqualTo("00");
        PaymentResponse resultInfo = response.jsonPath().getObject("resultInfo", PaymentResponse.class);
        assertThat(resultInfo.pcUrl()).isEqualTo("https://online-pay.kakao.com/mockup/v1/69137840d93f62a9738b03b0fd000ab914302e894b0d9c59ae3a2a6702c2d33f/info");
    }

    public ExtractableResponse<Response> requestPayment() {
        PaymentRequest req = PaymentRequest.builder()
                .orderId(1L)
                .userId(1)
                .productName("productName1")
                .quantity(1)
                .amount(35000)
                .paymentMethodType(PaymentMethodType.CARD.name())
                .installMonth(0)
                .taxFreeAmount(0)
                .build();

        ResourceSnippetDetails resourceSnippetParametersBuilder = new ResourceSnippetParametersBuilder();
        ExtractableResponse<Response> response =
                given(this.spec)
                        .filter(
                                RestAssuredRestDocumentationWrapper.document(
                                        "결제요청 Identifier"
                                        , resourceSnippetParametersBuilder
                                                .tag("결제요청")
                                                .summary("결제요청 summary")
                                                .description("카카오페이 결제 요청을 위한 API")
                                                .requestSchema(Schema.schema("PaymentRequest"))
                                                .responseSchema(Schema.schema("ApiResponse<PaymentResponse>"))
                                        , REQUEST_FIELDS
                                        , RESPONSE_FIELDS)
                        )
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .body(req)
                        .log().all()
                        .when()
                        .post("/intsvc/homepage/payment_kakaopay/v1/payment")
                        .then()
                        .log().all()
                        .extract();
        return response;
    }

    private void readyPayment(String responseJson) {
        mockServer.stubFor(
                WireMock.post("/v1/payment/ready")
                        .willReturn(aResponse()
                                .withHeader("content-type", "application/json")
                                .withBodyFile(responseJson)
                        )
        );
    }

    protected List<FieldDescriptor> apiResponseFields() {
        return Arrays.asList(
                PayloadDocumentation.fieldWithPath("rescd").type(JsonFieldType.STRING).description("응답 코드"),
                PayloadDocumentation.fieldWithPath("resmsg").type(JsonFieldType.STRING).description("메시지"),
                PayloadDocumentation.fieldWithPath("resultInfo").optional().description("결과 데이터")
        );
    }
}
