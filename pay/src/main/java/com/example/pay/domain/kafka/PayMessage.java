package com.example.pay.domain.kafka;

public record PayMessage(
        String title,
        String message
) {
    public boolean isErrorMessage() {
        return title.contains("error");
    }
}
