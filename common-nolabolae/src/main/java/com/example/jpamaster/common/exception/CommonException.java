package com.example.jpamaster.common.exception;

import com.example.jpamaster.common.enums.HttpStatusCode;
import java.time.LocalDateTime;
import lombok.Getter;

@Getter
public class CommonException extends RuntimeException {
    private final HttpStatusCode httpStatusCode;
    private final String message;
    private final LocalDateTime createdAt = LocalDateTime.now();

    public CommonException(HttpStatusCode httpStatusCode) {
        this(httpStatusCode, httpStatusCode.getDefaultMessage());
    }

    public CommonException(HttpStatusCode httpStatusCode, String message) {
        this.httpStatusCode = httpStatusCode;
        this.message = message;
    }
}