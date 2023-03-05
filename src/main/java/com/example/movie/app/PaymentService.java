package com.example.movie.app;

import com.example.movie.infra.KakaopayApiService;
import com.example.movie.infra.KakaopayReadyApiResponse;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    private final KakaopayApiService apiService;

    public PaymentService(KakaopayApiService apiService) {
        this.apiService = apiService;
    }

    public PaymentResponse payment(PaymentRequest req) {
        KakaopayReadyApiResponse ready = apiService.ready(req);

        return new PaymentResponse(null,ready.next_redirect_pc_url());
    }
}
