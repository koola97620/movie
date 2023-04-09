package com.example.movie.pay.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponse<T> {
    private String rescd;
    private String resmsg;

    private T resultInfo;
    public static <T> ApiResponse<T> of(String code, String msg) {
        return ApiResponse.<T>builder()
                .rescd(code)
                .resmsg(msg)
                .build();
    }

    public static <T> ApiResponse<T> of(String code, String msg, T info) {
        return ApiResponse.<T>builder()
                .rescd(code)
                .resmsg(msg)
                .resultInfo(info)
                .build();
    }
}
