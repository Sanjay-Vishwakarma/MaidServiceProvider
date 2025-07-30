package com.maid.service.provider.dto;

public record ErrorResponse(
    int status,
    String error,
    String message
) {}