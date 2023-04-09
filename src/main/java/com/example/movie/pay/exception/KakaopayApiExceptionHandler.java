package com.example.movie.pay.exception;


import com.example.movie.pay.common.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class KakaopayApiExceptionHandler {

    @ExceptionHandler(KakaopayApiCallException.class)
    public ApiResponse<?> kakaopayApiCallExceptionHandler(KakaopayApiCallException e) {
        e.printStackTrace();
        //sendKakaoMessageSentry(e);
        return ApiResponse.of(KakaopayApiCode.KAKAOPAY_API_EXCEPTION.getCode(), e.getErrorMsg(), e.getErrorResponse());
    }

    @ExceptionHandler(Throwable.class)
    public ApiResponse<?> exception(Throwable e) {
        e.printStackTrace();
        //Sentry.captureException(e);
        return ApiResponse.of("500", e.getMessage());
    }

//    private void sendKakaoMessageSentry(KakaopayApiCallException e) {
//        SentryEvent sentryEvent = new SentryEvent(e);
//        Message message = new Message();
//        message.setMessage(e.getErrorMsg());
//        sentryEvent.setMessage(message);
//        Sentry.captureEvent(sentryEvent);
//    }
}
