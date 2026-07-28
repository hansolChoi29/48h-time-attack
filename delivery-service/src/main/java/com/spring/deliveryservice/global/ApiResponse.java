package com.spring.deliveryservice.global;

public record ApiResponse<T>(
        boolean success,
        int code,
        String message,
        T data
) {
    public static <T> ApiResponse<T> success(
            String message,
            T data
    ) {
        return new ApiResponse<>(
                true,
                200,
                message,
                data
        );
    }

    public static ApiResponse<Void> fail(ErrorCode errorCode) {
        return new ApiResponse<>(
                false,
                errorCode.getStatus().value(),
                errorCode.getMessage(),
                null
        );
    }
}