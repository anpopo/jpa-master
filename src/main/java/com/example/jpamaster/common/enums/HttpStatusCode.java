package com.example.jpamaster.common.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum HttpStatusCode {

    // 2xx
    OK(200, "SU000", "OK"),


    // 4xx
    BAD_REQUEST(400, "CL000", "Bad Request"),
    NOT_FOUND(404, "CL004", "Not Found"),

    // 5xx
    INTERNAL_SERVER_ERROR(500, "SE000", "Internal Server Error"),

    ;
    private final int status;
    private final String code;
    private final String defaultMessage;
}